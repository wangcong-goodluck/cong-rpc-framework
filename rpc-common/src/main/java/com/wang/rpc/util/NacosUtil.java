package com.wang.rpc.util;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.wang.rpc.enumeration.RpcError;
import com.wang.rpc.exception.RpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.List;

/**
 *  管理 Nacos 连接等工具类
 *
 * @author C.Wang
 * @CreateTime 2022/5/10 21:21
 */


public class NacosUtil {

    private static final Logger logger = LoggerFactory.getLogger(NacosUtil.class);

    private static final String SERVER_ADDR = "127.0.0.1:8848";

    public static NamingService getNacosNamingService() {
        try {
            return NamingFactory.createNamingService(SERVER_ADDR);
        } catch (NacosException e) {
            logger.error("连接到 Nacos时有错误发生：", e);
            throw new RpcException(RpcError.FAILED_TO_CONNECT_TO_SERVICE_REGISTRY);
        }
    }

    public static void registerService(NamingService namingService, String serviceName, InetSocketAddress address) throws NacosException {
        namingService.registerInstance(serviceName, address.getHostName(), address.getPort());
    }

    public static List<Instance> getAllInstance(NamingService namingService, String serviceName) throws NacosException {
        return namingService.getAllInstances(serviceName);
    }
}
