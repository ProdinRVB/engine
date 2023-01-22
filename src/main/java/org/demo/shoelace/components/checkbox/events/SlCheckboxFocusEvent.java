package org.demo.shoelace.components.checkbox.events;

import java.util.Map;

import org.dwcj.webcomponent.annotations.EventExpressions;
import org.dwcj.webcomponent.annotations.EventName;
import org.dwcj.webcomponent.events.Event;
import org.demo.shoelace.components.checkbox.SlCheckbox;

/**
 * The checkbox focus event.
 * 
 * Emitted when the checkbox gains focus.
 * 
 * @author Hyyan Abo Fakher
 */
@EventName("sl-focus")
@EventExpressions( filter = "event.target.isSameNode(component)")
public class SlCheckboxFocusEvent extends Event<SlCheckbox> {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public SlCheckboxFocusEvent(SlCheckbox control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}
