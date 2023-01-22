package org.demo.shoelace.components.button.events;

import java.util.Map;

import org.dwcj.webcomponent.annotations.EventExpressions;
import org.dwcj.webcomponent.annotations.EventName;
import org.dwcj.webcomponent.events.Event;
import org.demo.shoelace.components.button.SlIconButton;

/**
 * The button blur event.
 * 
 * @author Hyyan Abo Fakher
 */
@EventName("sl-blur")
@EventExpressions(filter = "event.target.isSameNode(component)")
public class SlIconButtonBlurEvent extends Event<SlIconButton> {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public SlIconButtonBlurEvent(SlIconButton control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}