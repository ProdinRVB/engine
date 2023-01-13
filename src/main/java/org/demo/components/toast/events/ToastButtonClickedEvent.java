package org.demo.components.toast.events;

import java.util.Map;

import org.demo.components.toast.Toast;
import org.dwcj.webcomponent.events.Event;

/**
 * The event fired when a button is clicked
 * 
 * @author Hyyan Abo Fakher
 */
public class ToastButtonClickedEvent extends Event<Toast> {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public ToastButtonClickedEvent(Toast control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }

  /**
   * Get the id of the button that was clicked
   * 
   * @return the button id
   */
  public String getButtonId() {
    return (String) getEventMap().get("buttonToastId");
  }
}