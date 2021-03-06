package com.wang.test;

import com.wang.rpc.annotation.ServiceScan;
import com.wang.rpc.api.HelloService;
import com.wang.rpc.serializer.CommonSerializer;
import com.wang.rpc.transport.RpcServer;
import com.wang.rpc.transport.netty.server.NettyServer;
import com.wang.rpc.provider.ServiceProviderImpl;
import com.wang.rpc.registry.ServiceRegistry;
import com.wang.rpc.serializer.ProtobufSerializer;

/**
 *  测试用Netty服务提供者（服务端）
 *
 * @author C.Wang
 * @CreateTime 2022/4/27 23:30
 */

@ServiceScan
public class NettyTestServer {
    public static void main(String[] args) {
        RpcServer server = new NettyServer("127.0.0.1", 9999, CommonSerializer.PROTOBUF_SERIALIZER);
        server.start();
    }
}
