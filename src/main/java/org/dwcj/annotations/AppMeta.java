package org.dwcj.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotates a class to set the meta tags of the app. It can be used
 * multiple times to set multiple meta tags. The attributes of the meta tag can
 * be set using the {@link Attribute} annotation. The attributes can be
 * repeated multiple times to set multiple attributes but the attributes are
 * optional.
 * 
 * The annotation can be used on the class level only and the class must extend
 * `org.dwcj.App` in order for the annotation to be processed.
 * 
 * <pre>
 * {@code
 * &#64;AppMeta(name = "description", content = "My App")
 * &#64;AppMeta(name = "keywords", content = "My, App, Java")
 * &#64;AppMeta(name = "theme-color", content = "#000000", attributes = {
 *  &#64;Attribute(name = "media", value = "(prefers-color-scheme: dark)"),
 *  &#64;Attribute(name = "name", value = "theme-color") })
 * </pre>
 * 
 * @see AppMetas
 * @see Attribute
 * 
 * @author Hyyan Abo Fakher
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(AppMeta.Container.class)
@Inherited
@Documented
public @interface AppMeta {
  /** The name of the meta tag */
  String name();

  /** The content of the meta tag */
  String content();

  /** The attributes of the meta tag */
  Attribute[] attributes() default {};

  @Target(ElementType.TYPE)
  @Retention(RetentionPolicy.RUNTIME)
  @Inherited
  @Documented
  public @interface Container {
    AppMeta[] value();
  }
}
