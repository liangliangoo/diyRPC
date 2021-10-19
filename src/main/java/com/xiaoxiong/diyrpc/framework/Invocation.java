package com.xiaoxiong.diyrpc.framework;

import java.io.Serializable;
import lombok.Data;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/19 12:34
 */
@Data
public class Invocation implements Serializable {

  private String interfaceName;
  private String methodName;
  // 参数类型
  private Class[] paramTypes;
  // 参数
  private Object[] params;

  public Invocation(String interfaceName, String methodName, Class[] paramTypes, Object[] params) {
    this.interfaceName = interfaceName;
    this.methodName = methodName;
    this.paramTypes = paramTypes;
    this.params = params;
  }
}
