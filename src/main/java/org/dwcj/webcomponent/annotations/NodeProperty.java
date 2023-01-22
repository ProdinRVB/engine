package org.dwcj.webcomponent.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The NodeProperty annotation is used to set properties on the web component
 * upon creation.
 * 
 * @author Hyyan Abo Fakher
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(NodeProperty.Container.class)
@Inherited
@Documented
public @interface NodeProperty {
  /** The name of the attribute */
  String name();

  /** The value of the attribute */
  String value() default "";

  @Target(ElementType.TYPE)
  @Retention(RetentionPolicy.RUNTIME)
  @Inherited
  @Documented
  public @interface Container {
    NodeProperty[] value();
  }
}
