package org.demo.components.toast.events;

import java.util.Map;

import org.demo.components.toast.Toast;
import org.dwcj.webcomponent.events.Event;

/**
 * ToastClosedEvent. This event is fired when the toast is closed.
 * 
 * @author Hyyan Abo Fakher
 */
public class ToastClosedEvent extends Event<Toast> {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public ToastClosedEvent(Toast control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}