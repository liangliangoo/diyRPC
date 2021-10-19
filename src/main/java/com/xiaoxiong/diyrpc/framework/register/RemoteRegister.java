package com.xiaoxiong.diyrpc.framework.register;

import com.xiaoxiong.diyrpc.framework.URL;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/19 10:41
 */
public class RemoteRegister {

  private static Map<String, List<URL>> REGISTER = new ConcurrentHashMap<>();

  public static void regist(String interfaceName, URL url) {
    List<URL> urlList = REGISTER.getOrDefault(interfaceName, null);
    if (urlList == null) {
      REGISTER.put(interfaceName, Collections.singletonList(url));
    } else {
      urlList.add(url);
    }

    saveFile();
  }

  public static List<URL> get(String interfaceName) {
    return REGISTER.get(interfaceName);
  }

  /**
   * 实现一个简单的随机负载均衡算法
   * @param interfaceName String
   * @return URL
   */
  public static URL random(String interfaceName) {
    REGISTER = getFile();
    List<URL> urls = REGISTER.get(interfaceName);
    if (urls.isEmpty()) {
      return null;
    }
    Random random = new Random();
    int idx = random.nextInt(urls.size());
    return urls.get(idx);
  }

  /**
   * 解决文件共享问题
   */
  private static void saveFile(){
    try {
      FileOutputStream fos = new FileOutputStream("/text.txt");
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(REGISTER);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  private static Map<String,List<URL>> getFile() {
    try {
      FileInputStream fis = new FileInputStream("/text.txt");
      ObjectInputStream objectInputStream = new ObjectInputStream(fis);
      return (Map<String, List<URL>>) objectInputStream.readObject();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }

}
