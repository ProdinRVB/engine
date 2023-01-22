package org.demo.shoelace.components.radio.events;

import java.util.Map;

import org.demo.shoelace.components.radio.SlRadio;
import org.dwcj.webcomponent.annotations.EventExpressions;
import org.dwcj.webcomponent.annotations.EventName;
import org.dwcj.webcomponent.events.Event;

/**
 * The checkbox focus event.
 * 
 * Emitted when the checkbox gains focus.
 * 
 * @author Hyyan Abo Fakher
 */
@EventName("sl-focus")
@EventExpressions( filter = "event.target.isSameNode(component)")
public class SlRadioFocusEvent extends Event<SlRadio> {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public SlRadioFocusEvent(SlRadio control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}
