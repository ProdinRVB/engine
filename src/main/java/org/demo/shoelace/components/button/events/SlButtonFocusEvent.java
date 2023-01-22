package org.demo.shoelace.components.button.events;

import java.util.Map;

import org.dwcj.webcomponent.annotations.EventExpressions;
import org.dwcj.webcomponent.annotations.EventName;
import org.dwcj.webcomponent.events.Event;
import org.demo.shoelace.components.button.SlButton;

/**
 * The button focus event.
 * 
 * @author Hyyan Abo Fakher
 */
@EventName("sl-focus")
@EventExpressions( filter = "event.target.isSameNode(component)")
public class SlButtonFocusEvent extends Event<SlButton> {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public SlButtonFocusEvent(SlButton control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}
