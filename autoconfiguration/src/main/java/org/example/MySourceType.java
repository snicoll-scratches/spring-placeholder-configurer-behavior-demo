package org.example;

public class MySourceType {

  public String getFooProperty(Object value) {
    if (((String) value).endsWith("//baz")) {
      // we act as if foo://baz can't be resolved to demonstrate the fallback value
      return null;
    }
    return value.toString().split("//")[1].toUpperCase();
  }
}
