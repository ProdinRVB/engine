package org.demo.shoelace.components.alert.events;

import java.util.Map;

import org.dwcj.webcomponent.annotations.EventExpressions;
import org.dwcj.webcomponent.annotations.EventName;
import org.dwcj.webcomponent.events.Event;
import org.demo.shoelace.components.alert.SlAlert;

/**
 * The checkbox focus event.
 * 
 * Emitted when the checkbox receives input.
 * 
 * @author Hyyan Abo Fakher
 */
@EventName("sl-show")
@EventExpressions(filter = "event.target.isSameNode(component)")
public class SlAlertOpenEvent extends Event<SlAlert> {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public SlAlertOpenEvent(SlAlert control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}
