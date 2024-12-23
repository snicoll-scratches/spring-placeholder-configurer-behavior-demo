package org.example.consumer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class ConsumerApplicationTests {

  private static final String EXPECTED_CONTENT_FROM_VALUE_BAR_ANNOTATION = "parsed by MySourceType: foo://bar";

  private static final String EXPECTED_CONTENT_FROM_VALUE_BAZ_ANNOTATION = "parsed by MySourceType: foo://baz";

  @Autowired
  private Consumer consumer;

  @Test
  void testLoadProperties() {
    assertThat(consumer.getBarValueField()).isEqualTo(EXPECTED_CONTENT_FROM_VALUE_BAR_ANNOTATION);
    assertThat(consumer.getBazValueField()).isEqualTo(EXPECTED_CONTENT_FROM_VALUE_BAZ_ANNOTATION);
  }

}
