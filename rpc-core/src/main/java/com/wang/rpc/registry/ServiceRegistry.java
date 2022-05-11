package com.wang.rpc.registry;

import java.net.InetSocketAddress;

/**
 * 服务注册中心通用接口
 *
 * @author C.Wang
 * @CreateTime 2022/4/25 22:51
 */


public interface ServiceRegistry {

    /**
     *  将一个服务注册进注册表
     *
     * @param serviceName 服务名称
     * @param inetSocketAddress  提供服务的地址
     */
    void register(String serviceName, InetSocketAddress inetSocketAddress);


}
