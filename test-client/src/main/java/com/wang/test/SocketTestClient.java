package com.wang.test;

/**
 *测试消费者(客户端)
 *
 * @author C.Wang
 * @CreateTime 2022/4/24 22:56
 */

import com.wang.rpc.api.ByeService;
import com.wang.rpc.api.HelloObject;
import com.wang.rpc.api.HelloService;
import com.wang.rpc.serializer.CommonSerializer;
import com.wang.rpc.transport.RpcClientProxy;
import com.wang.rpc.serializer.KryoSerializer;
import com.wang.rpc.transport.socket.client.SocketClient;

/**
 * 客户端方面，我们需要通过动态代理，生成代理对象，并调用，动态代理会自动帮我们向服务端发送请求的
 */
public class SocketTestClient {
    public static void main(String[] args) {
        SocketClient client = new SocketClient(CommonSerializer.KRYO_SERIALIZER);
        RpcClientProxy proxy = new RpcClientProxy(client);
        HelloService helloService = proxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(12, "This is a message");
        String res = helloService.hello(object);
        System.out.println(res);
        ByeService byeService = proxy.getProxy(ByeService.class);
        System.out.println(byeService.bye("Netty"));

    }
}
