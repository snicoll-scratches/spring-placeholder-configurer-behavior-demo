package org.example;

import org.springframework.core.env.EnumerablePropertySource;

/**
 * In a real setup, this should not be in an autoconfig class
 */
  public class MyPropertySource extends EnumerablePropertySource<MySourceType> {

    public MyPropertySource(
        String propertySourceName,
        MySourceType sourceType) {
      super(propertySourceName, sourceType);
    }

    @Override
    public Object getProperty(String name) {
      return getSource().getFooProperty(name);
    }

    @Override
    public String[] getPropertyNames() {
      return new String[0];
    }

}