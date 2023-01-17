package org.demo.shoelace.components.input.events;

import java.util.Map;

import org.dwcj.webcomponent.annotations.EventName;
import org.dwcj.webcomponent.events.Event;
import org.demo.shoelace.components.input.SlInput;

/**
 * The input blur event.
 * 
 * Emitted when the input loses focus.
 * 
 * @author Hyyan Abo Fakher
 */
@EventName("sl-blur")
public class SlInputBlurEvent extends Event<SlInput> {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public SlInputBlurEvent(SlInput control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}