package com.xiaoxiong.diyrpc.provider.api.impl;

import com.xiaoxiong.diyrpc.provider.api.HelloService;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/19 10:33
 */
public class HelloServiceImpl implements HelloService {

  @Override
  public String sayHello(String userName) {
    return "hello " + userName;
  }
}
