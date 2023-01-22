package org.demo.shoelace.components.input.events;

import java.util.Map;
import org.demo.shoelace.components.input.SlInput;
import org.dwcj.webcomponent.annotations.EventExpressions;
import org.dwcj.webcomponent.annotations.EventName;

/**
 * The checkbox focus event.
 * 
 * Emitted when an alteration to the control's value is committed by the user.
 * 
 * @author Hyyan Abo Fakher
 */
@EventName("sl-change")
@EventExpressions( filter = "event.target.isSameNode(component)")
public class SlInputChangeEvent extends SlInputModifiedEvent {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public SlInputChangeEvent(SlInput control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}
