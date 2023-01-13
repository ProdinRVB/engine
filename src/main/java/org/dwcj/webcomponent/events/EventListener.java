package org.dwcj.webcomponent.events;

import java.io.Serializable;

/**
 * The listener interface for receiving wc events. The class that is interested
 * in processing a wc event implements this interface, and the object created
 * with that class is registered with a component, using the component's
 * <code>addEventListener</code> method. When the wc event occurs, that object's
 * appropriate method is invoked.
 *
 * @param <T>
 *            the generic type
 */
@FunctionalInterface
public interface EventListener<T extends Event<?>>
    extends java.util.EventListener, Serializable {

  /**
   * Invoked when a component event has been fired.
   *
   * @param event component event
   */
  void execute(T event);
}