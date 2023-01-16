package org.demo.shoelace.checkbox.events;

import java.util.Map;

import org.dwcj.webcomponent.events.Event;
import org.demo.shoelace.checkbox.SlCheckbox;

/**
 * The checkbox blur event.
 * 
 * Emitted when the checkbox loses focus.
 * 
 * @author Hyyan Abo Fakher
 */
public class SlCheckboxBlurEvent extends Event<SlCheckbox> {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public SlCheckboxBlurEvent(SlCheckbox control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}