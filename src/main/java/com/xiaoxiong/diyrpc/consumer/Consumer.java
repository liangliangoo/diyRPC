package com.xiaoxiong.diyrpc.consumer;

import com.xiaoxiong.diyrpc.framework.proxy.ProxyFactory;
import com.xiaoxiong.diyrpc.provider.api.HelloService;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/19 9:47
 */
public class Consumer {

  public static void main(String[] args) {
//    HttpClient httpClient = new HttpClient();
//    Invocation invocation = new Invocation(HelloService.class.getName(), "sayHello",
//        new Class[]{String.class}, new Object[]{"xiaoxiong"});
//    String result = httpClient.send("localhost", 8080, invocation);

    //通过动态代理实现
    HelloService helloService = ProxyFactory.getProxy(HelloService.class);

    System.out.println(helloService.sayHello("xiaoxiong"));

  }

}
