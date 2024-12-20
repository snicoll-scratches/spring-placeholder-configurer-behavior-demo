package org.example.consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {
  private static final String INDEX_PAGE = "index.html";

  @Value("${foo://from-value:from-value}")
  private String valueField;

  private ConsumerConfiguration consumerConfiguration;

  public WebController(ConsumerConfiguration configuration) {
    this.consumerConfiguration = configuration;
  }

  @GetMapping("/")
  public ModelAndView renderIndex(ModelMap map) {
    String valueFromConfigurationFile = consumerConfiguration.getValue();
    map.put("resolvedFromValue", valueField);
    map.put("resolvedFromConfigFile", valueFromConfigurationFile);
    return new ModelAndView(INDEX_PAGE, map);
  }
}
