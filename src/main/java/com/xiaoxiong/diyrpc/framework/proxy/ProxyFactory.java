package com.xiaoxiong.diyrpc.framework.proxy;

import com.xiaoxiong.diyrpc.framework.Invocation;
import com.xiaoxiong.diyrpc.framework.URL;
import com.xiaoxiong.diyrpc.framework.protocol.http.HttpClient;
import com.xiaoxiong.diyrpc.framework.register.RemoteRegister;
import java.lang.reflect.Proxy;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/19 15:56
 */
public class ProxyFactory {

  public static <T> T getProxy(Class interfaceClass) {

    return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass},
        (proxy, method, args) -> {
          HttpClient httpClient = new HttpClient();
          Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(), method.getParameterTypes(), args);

          /**
           * 用Map存放的时候会出现问题，应为consumer和provider是想个不同的进程
           * 导致RemoteRegister 中的map对不上
           * 如果服务是在一台主机上可以通过持久化的形式解决这个问题
           */
          URL url = RemoteRegister.random(interfaceClass.getName());
          String result = httpClient.send(url.getHostName(), url.getPort(), invocation);
          return result;
        });

  }

}
