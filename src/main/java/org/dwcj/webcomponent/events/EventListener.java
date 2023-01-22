package org.dwcj.webcomponent.events;

import java.io.Serializable;

/**
 * The listener interface for receiving events. The class that is interested
 * in processing an event implements this interface, and the object created
 * with that class is registered with a control.
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