package com.xiaoxiong.diyrpc.framework.protocol.http;

import com.xiaoxiong.diyrpc.framework.Invocation;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.io.IOUtils;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/19 12:28
 */
public class HttpClient {

  public String send(String hostName, Integer port, Invocation invocation) {
    ObjectOutputStream oos = null;
    try {
      URL url = new URL("http", hostName, port, "/");
      HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

      httpURLConnection.setRequestMethod("POST");
      httpURLConnection.setDoOutput(true);

      OutputStream outputStream = httpURLConnection.getOutputStream();
      oos = new ObjectOutputStream(outputStream);
      oos.writeObject(invocation);
      oos.flush();

      InputStream inputStream = httpURLConnection.getInputStream();

      String result = IOUtils.toString(inputStream);

      return result;

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        assert oos != null;
        oos.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return null;
  }

}
