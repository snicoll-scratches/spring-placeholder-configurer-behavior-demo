package org.example;

public class MySourceType {

  public String getFooProperty(Object value) {
    return "parsed by MySourceType: " + value.toString();
  }
}
