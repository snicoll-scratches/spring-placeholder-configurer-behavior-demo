package org.example;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.BootstrapRegistry;
import org.springframework.boot.context.config.ConfigDataLocation;
import org.springframework.boot.context.config.ConfigDataLocationNotFoundException;
import org.springframework.boot.context.config.ConfigDataLocationResolver;
import org.springframework.boot.context.config.ConfigDataLocationResolverContext;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;

public class MyConfigDataLocationResolver implements
    ConfigDataLocationResolver<MyConfigDataResource> {

  public static final String PREFIX = "foo://";

  @Override
  public boolean isResolvable(ConfigDataLocationResolverContext context,
      ConfigDataLocation location) {
    return location.hasPrefix(PREFIX);
  }

  @Override
  public List<MyConfigDataResource> resolve(ConfigDataLocationResolverContext context,
      ConfigDataLocation location)
      throws ConfigDataLocationNotFoundException, ConfigDataResourceNotFoundException {
    registerSecretManagerBeans(context);

    return Collections.singletonList(
        new MyConfigDataResource(location));
  }

  private static void registerSecretManagerBeans(ConfigDataLocationResolverContext context) {
    // Register the placeholder configurer
    registerBean(
        context, MyPlaceholderConfigurer.class, new MyPlaceholderConfigurer());
    // Register and promote the placeholder configurer
//    registerAndPromoteBean(
//        context,
//        MyPlaceholderConfigurer.class,
//        BootstrapRegistry.InstanceSupplier.of(new MyPlaceholderConfigurer()));
  }

  /**
   * Registers a bean in the Bootstrap Registry.
   *
   * <p>The Bootstrap Registry is a temporary context which exists for creating
   * the ConfigData property sources.
   */
  private static <T> void registerBean(
      ConfigDataLocationResolverContext context, Class<T> type, T instance) {
    context.getBootstrapContext()
        .registerIfAbsent(type, BootstrapRegistry.InstanceSupplier.of(instance));
  }

  /**
   * Registers the bean in the Bootstrap Registry *and* promotes it to be in the standard
   * application context.
   */
  private static <T> void registerAndPromoteBean(
      ConfigDataLocationResolverContext context, Class<T> type,
      BootstrapRegistry.InstanceSupplier<T> supplier) {
    context.getBootstrapContext().registerIfAbsent(type, supplier);
    context.getBootstrapContext().addCloseListener(event -> {
      T instance = event.getBootstrapContext().get(type);
      String beanName = "gcp-secretmanager-config-data-" + type.getSimpleName();
      ConfigurableListableBeanFactory factory = event.getApplicationContext().getBeanFactory();
      if (!factory.containsSingleton(beanName)) {
        factory.registerSingleton(beanName, instance);
      }
    });
  }
}
