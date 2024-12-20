package org.example;

import java.util.Objects;
import org.springframework.boot.context.config.ConfigDataLocation;
import org.springframework.boot.context.config.ConfigDataResource;

public class MyConfigDataResource extends ConfigDataResource {

  private final ConfigDataLocation location;

  public MyConfigDataResource(ConfigDataLocation location) {
    this.location = location;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof MyConfigDataResource)) {
      return false;
    }
    MyConfigDataResource that = (MyConfigDataResource) o;
    return location.equals(that.location);
  }

  @Override
  public int hashCode() {
    return Objects.hash(location);
  }

  @Override
  public String toString() {
    return "SecretManagerConfigDataResource{"
        + "location=" + location
        + '}';
  }
}
