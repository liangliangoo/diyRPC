package com.xiaoliang.provider;

import com.xiaoliang.framework.URL;
import com.xiaoliang.framework.protocol.NettyServer;
import com.xiaoliang.framework.register.LocalRegister;
import com.xiaoliang.framework.register.ZookeeperRegister;
import com.xiaoliang.provider.api.HelloService;
import com.xiaoliang.provider.impl.HelloServiceImpl;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Provider {

    public static void main(String[] args) throws UnknownHostException {

        String interfaceName = HelloService.class.getName();
        URL url = new URL(InetAddress.getLocalHost().getHostAddress(), 8081);

        LocalRegister.regist(interfaceName, HelloServiceImpl.class);
        ZookeeperRegister.regist(interfaceName, url);

        NettyServer nettyServer = new NettyServer();
        nettyServer.start(url.getHostname(), url.getPort());

        System.out.println(String.format("success, 成功暴露%s服务，地址为%s", interfaceName, url.toString()));
    }
}
