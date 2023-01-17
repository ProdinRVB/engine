package org.demo.shoelace.components.checkbox.events;

import java.util.Map;

import org.demo.shoelace.components.checkbox.SlCheckbox;
import org.dwcj.webcomponent.annotations.EventName;

/**
 * The checkbox focus event.
 * 
 * Emitted when the checked state changes.
 * 
 * @author Hyyan Abo Fakher
 */
@EventName("sl-change")
public class SlCheckboxChangeEvent extends SlCheckboxInputEvent {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public SlCheckboxChangeEvent(SlCheckbox control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}
