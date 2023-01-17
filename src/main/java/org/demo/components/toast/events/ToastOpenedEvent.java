package org.demo.components.toast.events;

import java.util.Map;

import org.demo.components.toast.Toast;
import org.dwcj.webcomponent.annotations.EventName;
import org.dwcj.webcomponent.events.Event;

/**
 * Event fired when the toast is opened.
 * 
 * @author Hyyan Abo Fakher
 */
@EventName("bbj-opened")
public class ToastOpenedEvent extends Event<Toast> {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public ToastOpenedEvent(Toast control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}
