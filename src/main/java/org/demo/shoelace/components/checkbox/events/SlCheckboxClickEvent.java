package org.demo.shoelace.components.checkbox.events;

import java.util.Map;

import org.demo.shoelace.components.checkbox.SlCheckbox;
import org.dwcj.webcomponent.annotations.EventName;

/**
 * The checkbox click event.
 * 
 * @author Hyyan Abo Fakher
 */
@EventName("click")
public class SlCheckboxClickEvent extends SlCheckboxInputEvent {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public SlCheckboxClickEvent(SlCheckbox control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}