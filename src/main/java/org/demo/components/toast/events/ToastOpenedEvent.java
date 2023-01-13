package org.demo.components.toast.events;

import java.util.Map;

import org.demo.components.toast.Toast;
import org.dwcj.webcomponent.events.Event;

/**
 * ToastOpenedEvent. This event is fired when the toast is opened.
 * 
 * @author Hyyan Abo Fakher
 */
public class ToastOpenedEvent extends Event<Toast> {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public ToastOpenedEvent(Toast control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}
