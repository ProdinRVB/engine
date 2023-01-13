package org.dwcj.webcomponent.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to define a web component. This annotation is used to define a
 * web component class. The web component class must extend the
 * {@link org.dwcj.webcomponent.WebComponent} class.
 * 
 * @author Hyyan Abo Fakher
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface NodeName {

    /** The tag name of the web component */
    String value();
}
