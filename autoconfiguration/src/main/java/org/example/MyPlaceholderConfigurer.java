package org.example;

import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurablePropertyResolver;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySourcesPropertyResolver;

public class MyPlaceholderConfigurer extends
    PropertySourcesPlaceholderConfigurer {

  @Override
  protected ConfigurablePropertyResolver createPropertyResolver(
      MutablePropertySources propertySources) {
    return new PropertySourcesPropertyResolver(propertySources) {

      private String getEscapedKey(String key) {
        String escapedKey = key.replaceAll("foo:\\/\\/", "foo\\\\://");
        return escapedKey;
      }

      @Override
      public String resolvePlaceholders(String key) {
        return super.resolvePlaceholders(getEscapedKey(key));
      }

      @Override
      public String resolveRequiredPlaceholders(String key) {
        return super.resolvePlaceholders(getEscapedKey(key));
      }
    };
  }
}
