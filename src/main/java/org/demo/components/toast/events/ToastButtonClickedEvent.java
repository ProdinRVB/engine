package org.demo.components.toast.events;

import java.util.Map;

import org.demo.components.toast.Toast;
import org.dwcj.webcomponent.annotations.EventExpressions;
import org.dwcj.webcomponent.annotations.EventName;
import org.dwcj.webcomponent.events.Event;

/**
 * Event fired when a toast button is clicked.
 * 
 * @author Hyyan Abo Fakher
 */
@EventName(value = "click")
@EventExpressions(
    // Make sure to report events only if the target has the data-action attribute
    filter = "event.target.closest(\"[data-action]\") != null;",
    // Build the event data and set the button action id to the event detail
    detail = "event.toastBtnId = event.target.closest(\"[data-action]\").getAttribute(\"data-action\");")
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
    return String.valueOf(getEventMap().get("toastBtnId"));
  }
}