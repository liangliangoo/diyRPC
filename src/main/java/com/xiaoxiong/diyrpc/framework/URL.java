package com.xiaoxiong.diyrpc.framework;

import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/19 10:38
 */
@Data
@Accessors(chain = true)
public class URL implements Serializable {

  private String hostName;
  private Integer port;

  public URL() {
  }

  public URL(String hostName, Integer port) {
    this.hostName = hostName;
    this.port = port;
  }

  @Override
  public String toString() {
    return "URL{" +
        "hostName='" + hostName + '\'' +
        ", port=" + port +
        '}';
  }
}
