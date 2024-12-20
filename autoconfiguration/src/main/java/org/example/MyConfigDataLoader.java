package org.example;

import java.io.IOException;
import java.util.Collections;
import org.springframework.boot.context.config.ConfigData;
import org.springframework.boot.context.config.ConfigDataLoader;
import org.springframework.boot.context.config.ConfigDataLoaderContext;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;

public class MyConfigDataLoader implements
    ConfigDataLoader<MyConfigDataResource> {

  @Override
  public ConfigData load(
      ConfigDataLoaderContext context,
      MyConfigDataResource resource)
      throws IOException, ConfigDataResourceNotFoundException {

    return new ConfigData(Collections.singleton(new MyPropertySource(
        "my-property-source", new MySourceType())));
  }
}
