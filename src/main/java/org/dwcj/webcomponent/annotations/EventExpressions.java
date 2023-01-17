package org.dwcj.webcomponent.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to define a web component dom event expressions.
 * 
 * @author Hyyan Abo Fakher
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface EventExpressions {

  /** The filter expression to check if the event should be fired. */
  String filter() default "";

  /** The data builder expression to build the event detail. */
  String detail() default "";

  /** The prevent default expression. */
  String preventDefault() default "";

  /** The stop propagation expression. */
  String stopPropagation() default "";

  /** The stop immediate propagation expression. */
  String stopImmediatePropagation() default "";
}
