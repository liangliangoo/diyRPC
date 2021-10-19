package com.xiaoxiong.diyrpc.framework.protocol.http;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/19 12:16
 */
public class DispatcherServlet extends HttpServlet {

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    new HttpServerHandler().handler(req, resp);
  }
}
