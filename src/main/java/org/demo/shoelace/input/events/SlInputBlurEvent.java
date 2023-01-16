package org.demo.shoelace.input.events;

import java.util.Map;

import org.dwcj.webcomponent.events.Event;
import org.demo.shoelace.input.SlInput;

/**
 * The input blur event.
 * 
 * Emitted when the input loses focus.
 * 
 * @author Hyyan Abo Fakher
 */
public class SlInputBlurEvent extends Event<SlInput> {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public SlInputBlurEvent(SlInput control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}