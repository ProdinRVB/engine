package org.demo.shoelace.components.button.events;

import java.util.Map;

import org.dwcj.webcomponent.annotations.EventExpressions;
import org.dwcj.webcomponent.annotations.EventName;
import org.dwcj.webcomponent.events.Event;
import org.demo.shoelace.components.button.SlIconButton;

/**
 * The button focus event.
 * 
 * @author Hyyan Abo Fakher
 */
@EventName("sl-focus")
@EventExpressions(filter = "event.target.isSameNode(component)")
public class SlIconButtonFocusEvent extends Event<SlIconButton> {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public SlIconButtonFocusEvent(SlIconButton control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}
