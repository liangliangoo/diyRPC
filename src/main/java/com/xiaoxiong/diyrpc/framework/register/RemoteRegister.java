package com.xiaoxiong.diyrpc.framework.register;

import com.xiaoxiong.diyrpc.framework.URL;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/19 10:41
 */
public class RemoteRegister {

  private static final Map<String, List<URL>> REGISTER = new ConcurrentHashMap<>();

  public static void regist(String interfaceName, URL url) {
    List<URL> urlList = REGISTER.getOrDefault(interfaceName, null);
    if (urlList == null) {
      REGISTER.put(interfaceName, Collections.singletonList(url));
    } else {
      urlList.add(url);
    }
  }

  public static List<URL> get(String interfaceName) {
    return REGISTER.get(interfaceName);
  }

}
