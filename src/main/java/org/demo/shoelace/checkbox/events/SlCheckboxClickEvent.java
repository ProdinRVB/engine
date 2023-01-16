package org.demo.shoelace.checkbox.events;

import java.util.Map;

import org.demo.shoelace.checkbox.SlCheckbox;

/**
 * The checkbox click event.
 * 
 * @author Hyyan Abo Fakher
 */
public class SlCheckboxClickEvent extends SlCheckboxInputEvent {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public SlCheckboxClickEvent(SlCheckbox control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}