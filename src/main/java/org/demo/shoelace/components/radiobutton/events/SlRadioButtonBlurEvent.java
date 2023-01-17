package org.demo.shoelace.components.radiobutton.events;

import java.util.Map;

import org.dwcj.webcomponent.annotations.EventName;
import org.dwcj.webcomponent.events.Event;
import org.demo.shoelace.components.radiobutton.SlRadioButton;

/**
 * The checkbox blur event.
 * s
 * Emitted when the checkbox loses focus.
 * 
 * @author Hyyan Abo Fakher
 */
@EventName("sl-blur")
public class SlRadioButtonBlurEvent extends Event<SlRadioButton> {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public SlRadioButtonBlurEvent(SlRadioButton control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}