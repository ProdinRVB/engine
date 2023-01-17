package org.demo.shoelace.components.checkbox.events;

import java.util.Map;

import org.dwcj.webcomponent.annotations.EventName;
import org.dwcj.webcomponent.events.Event;
import org.demo.shoelace.components.checkbox.SlCheckbox;

/**
 * The checkbox blur event.
 * s
 * Emitted when the checkbox loses focus.
 * 
 * @author Hyyan Abo Fakher
 */
@EventName("sl-blur")
public class SlCheckboxBlurEvent extends Event<SlCheckbox> {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public SlCheckboxBlurEvent(SlCheckbox control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}