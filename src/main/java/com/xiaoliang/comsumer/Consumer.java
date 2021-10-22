package com.xiaoliang.comsumer;

import com.xiaoliang.framework.proxy.ProxyFactory;
import com.xiaoliang.provider.api.HelloService;

public class Consumer {

    public static void main(String[] args) {

        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        for (int i = 0; i < 10; i++) {
            String result = helloService.sayHello("小熊" + i);
            System.out.println(result);
        }

    }
}
