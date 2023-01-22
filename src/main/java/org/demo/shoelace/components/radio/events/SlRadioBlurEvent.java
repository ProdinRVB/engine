package org.demo.shoelace.components.radio.events;

import java.util.Map;

import org.dwcj.webcomponent.annotations.EventExpressions;
import org.dwcj.webcomponent.annotations.EventName;
import org.dwcj.webcomponent.events.Event;
import org.demo.shoelace.components.radio.SlRadio;

/**
 * The checkbox blur event.
 * s
 * Emitted when the checkbox loses focus.
 * 
 * @author Hyyan Abo Fakher
 */
@EventName("sl-blur")
@EventExpressions( filter = "event.target.isSameNode(component)")
public class SlRadioBlurEvent extends Event<SlRadio> {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public SlRadioBlurEvent(SlRadio control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}