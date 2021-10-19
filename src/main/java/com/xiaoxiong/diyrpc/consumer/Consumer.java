package com.xiaoxiong.diyrpc.consumer;

import com.xiaoxiong.diyrpc.framework.Invocation;
import com.xiaoxiong.diyrpc.framework.protocol.http.HttpClient;
import com.xiaoxiong.diyrpc.provider.api.HelloService;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/19 9:47
 */
public class Consumer {

  public static void main(String[] args) {
    HttpClient httpClient = new HttpClient();
    Invocation invocation = new Invocation(HelloService.class.getName(), "sayHello",
        new Class[]{String.class}, new Object[]{"xiaoxiong"});
    String result = httpClient.send("localhost", 8080, invocation);
    System.out.println(result);

  }

}
