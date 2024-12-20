package org.example;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@AutoConfiguration
@ConditionalOnClass(MyPlaceholderConfigurer.class)
@AutoConfigureBefore(PropertyPlaceholderAutoConfiguration.class)
public class MyAutoconfiguration {
  @Bean
  public static PropertySourcesPlaceholderConfigurer myPlaceholdersResolver() {
    return new MyPlaceholderConfigurer();
  }
}
