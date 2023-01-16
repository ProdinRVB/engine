package org.demo.shoelace.checkbox.events;

import java.util.Map;

import org.dwcj.webcomponent.events.Event;
import org.demo.shoelace.checkbox.SlCheckbox;

/**
 * The checkbox focus event.
 * 
 * Emitted when the checkbox gains focus.
 * 
 * @author Hyyan Abo Fakher
 */
public class SlCheckboxFocusEvent extends Event<SlCheckbox> {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public SlCheckboxFocusEvent(SlCheckbox control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}
