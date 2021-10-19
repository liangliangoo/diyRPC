package com.xiaoxiong.diyrpc.framework.protocol.http;

import org.apache.catalina.Context;
import org.apache.catalina.Engine;
import org.apache.catalina.Host;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Server;
import org.apache.catalina.Service;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.startup.Tomcat.FixContextListener;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/19 12:03
 */
public class HttpServer {

  /**
   * 内嵌Tomcat
   * @param hostName String
   * @param port     Integer
   */
  public void start(String hostName, Integer port) {
    Tomcat tomcat = new Tomcat();

    Server server = tomcat.getServer();
    Service service = server.findService("Tomcat");

    Connector connector = new Connector();
    connector.setPort(port);

    Engine engine = new StandardEngine();
    engine.setDefaultHost(hostName);

    Host host = new StandardHost();
    host.setName(hostName);

    String contextPath = "";
    Context context = new StandardContext();
    context.setPath(contextPath);
    context.addLifecycleListener(new FixContextListener());

    host.addChild(context);
    engine.addChild(host);

    service.setContainer(engine);
    service.addConnector(connector);

    tomcat.addServlet(contextPath,"dispatcher",new DispatcherServlet());
    context.addServletMappingDecoded("/*","dispatcher");

    try {
      tomcat.start();
      tomcat.getServer().await();
    } catch (LifecycleException e) {
      e.printStackTrace();
    }
  }

}
