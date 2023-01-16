package org.demo.shoelace.input.events;

import java.util.Map;

import org.dwcj.webcomponent.events.Event;
import org.demo.shoelace.input.SlInput;

/**
 * The checkbox focus event.
 * 
 * Emitted when the checkbox gains focus.
 * 
 * @author Hyyan Abo Fakher
 */
public class SlInputFocusEvent extends Event<SlInput> {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public SlInputFocusEvent(SlInput control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}
