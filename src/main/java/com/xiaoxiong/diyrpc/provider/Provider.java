package com.xiaoxiong.diyrpc.provider;

import com.xiaoxiong.diyrpc.framework.URL;
import com.xiaoxiong.diyrpc.framework.protocol.http.HttpServer;
import com.xiaoxiong.diyrpc.framework.register.LocalRegister;
import com.xiaoxiong.diyrpc.framework.register.RemoteRegister;
import com.xiaoxiong.diyrpc.provider.api.HelloService;
import com.xiaoxiong.diyrpc.provider.api.impl.HelloServiceImpl;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/19 10:22
 */
public class Provider {

  public static void main(String[] args) {

    // 本地注册
    LocalRegister.regist(HelloService.class.getName(), HelloServiceImpl.class);

    // 远程注册
    URL url = new URL("localhost", 8080);
    RemoteRegister.regist(HelloService.class.getName(), url);

    // 启动Tomcat
    HttpServer httpServer = new HttpServer();
    httpServer.start("localhost",8080);
  }

}
