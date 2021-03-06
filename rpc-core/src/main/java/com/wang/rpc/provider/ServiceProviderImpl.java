package com.wang.rpc.provider;

import com.wang.rpc.enumeration.RpcError;
import com.wang.rpc.exception.RpcException;
import com.wang.rpc.registry.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 *默认的服务注册表,提供服务注册服务
 *
 * @author C.Wang
 * @CreateTime 2022/4/25 22:54
 */

/**
 * 将服务名与提供服务的对象的对应关系保存在一个ConcurrentHashMap中，并使用一个Set来保存当前有哪些对象已经被注册。
 * 在注册服务时，默认采用这个对象实现的接口的完整类名作为服务名。
 */
public class ServiceProviderImpl implements ServiceProvider {
    private static final Logger logger = LoggerFactory.getLogger(ServiceProviderImpl.class);

    //static 保证全局唯一的注册信息
    private static final Map<String, Object> serviceMap = new ConcurrentHashMap<>();
    private static final Set<String> registeredService = ConcurrentHashMap.newKeySet();

    @Override
    public <T> void addServiceProvider(T service, String serviceName) {

        if (registeredService.contains(serviceName)) return;
        registeredService.add(serviceName);
        serviceMap.put(serviceName, service);
        logger.info("向接口：{} 注册服务：{}", service.getClass().getInterfaces(), serviceName);
    }

    /**
     *  获取服务的对象，只需要去Map中查找就行了。
     *
     * @param serviceName
     * @return
     */
    @Override
    public synchronized Object getServiceProvider(String serviceName) {
        Object service = serviceMap.get(serviceName);
        if (service == null) {
            throw new RpcException(RpcError.SERVICE_NOT_FOUND);
        }
        return service;
    }
}
