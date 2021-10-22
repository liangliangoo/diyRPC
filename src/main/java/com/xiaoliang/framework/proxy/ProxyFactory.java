package com.xiaoliang.framework.proxy;

import com.xiaoliang.framework.LoadBalance;
import com.xiaoliang.framework.URL;
import com.xiaoliang.framework.protocol.Invocation;
import com.xiaoliang.framework.protocol.NettyClient;
import com.xiaoliang.framework.register.ZookeeperRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class ProxyFactory<T> {

    @SuppressWarnings("unchecked")
    public static <T> T getProxy(final Class interfaceClass) {

        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(), method.getParameterTypes(), args);

                try {
                    NettyClient nettyClient = new NettyClient();

                    List<URL> urls = ZookeeperRegister.get(interfaceClass.getName());
                    URL url = LoadBalance.random(urls);

                    System.out.println("消费者选择的服务提供者地址是："+ url.toString());
                    String result = nettyClient.send(url.getHostname(), url.getPort(), invocation);
                    return result;
                } catch (Exception e) {
                    return doMock(invocation);
                }
            }
        });
    }

    private static Object doMock(Invocation invocation) {
        return "容错逻辑";
    }

}
