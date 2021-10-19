package com.xiaoxiong.diyrpc.framework.register;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/19 10:35
 */
public class LocalRegister {

  private static Map<String, Class> map = new ConcurrentHashMap<>();

  public static void regist(String interfaceName, Class implClass) {
    map.put(interfaceName, implClass);
  }

  public static Class get(String interfaceName) {
    return map.getOrDefault(interfaceName, null);
  }

}
