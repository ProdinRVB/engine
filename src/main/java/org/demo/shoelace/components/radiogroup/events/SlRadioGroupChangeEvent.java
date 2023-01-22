package org.demo.shoelace.components.radiogroup.events;

import java.util.Map;

import org.dwcj.webcomponent.annotations.EventName;
import org.demo.shoelace.components.radiogroup.SlRadioGroup;

/**
 * The radio group input event.
 * 
 * Emitted when the radio group receives user input.
 * 
 * @author Hyyan Abo Fakher
 */
@EventName("sl-change")
public class SlRadioGroupChangeEvent extends SlRadioGroupInputEvent {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public SlRadioGroupChangeEvent(SlRadioGroup control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}
