package org.example.consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class Consumer {

  @Value("${foo://bar}")
  private String barValueField;

  @Value("${foo://baz}")
  private String bazValueField;

  public String getBarValueField() {
    return this.barValueField;
  }

  public String getBazValueField() {
    return this.bazValueField;
  }
}
