package org.dwcj.webcomponent.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to define a web component dom event name.
 * 
 * @author Hyyan Abo Fakher
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EventName {

  /** The name of the dom event as defined in the web component. */
  String value();
}
