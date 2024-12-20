package org.example.consumer;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = ConsumerApplication.class)
class ConsumerApplicationTests {
  private static final String EXPECTED_CONTENT_FROM_VALUE_ANNOTATION = "bar";
  private static final String EXPECTED_CONTENT_FROM_CONFIG_FILE = "baz";

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Test
  void testLoadProperties() {
    ResponseEntity<String> response = this.testRestTemplate.getForEntity("/", String.class);
    assertTrue(response.getStatusCode().is2xxSuccessful());
    System.out.println(response.getBody());
    assertTrue(response.getBody().contains(EXPECTED_CONTENT_FROM_VALUE_ANNOTATION));
    assertTrue(response.getBody().contains(EXPECTED_CONTENT_FROM_CONFIG_FILE));
  }

}
