package org.example;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;

@AutoConfiguration
@AutoConfigureBefore(PropertyPlaceholderAutoConfiguration.class)
public class MyAutoconfiguration {
  // we don't need to override any beans
}
