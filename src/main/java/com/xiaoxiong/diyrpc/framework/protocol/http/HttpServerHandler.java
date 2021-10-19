package com.xiaoxiong.diyrpc.framework.protocol.http;

import com.xiaoxiong.diyrpc.framework.Invocation;
import com.xiaoxiong.diyrpc.framework.register.LocalRegister;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/19 12:17
 */
public class HttpServerHandler {

  /**
   * 处理请求返回结果
   * @param req   HttpServletRequest
   * @param resp  HttpServletResponse
   */
  public void handler(HttpServletRequest req, HttpServletResponse resp) {
    try (ObjectInputStream ois = new ObjectInputStream(req.getInputStream())) {

      Invocation invocation = (Invocation) ois.readObject();

      Class implClass = LocalRegister.get(invocation.getInterfaceName());

      Method method = implClass.getMethod(invocation.getMethodName(), invocation.getParamTypes());

      String result = (String) method.invoke(implClass.newInstance(), invocation.getParams());

      IOUtils.write(result,resp.getOutputStream());
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    }
  }

}
