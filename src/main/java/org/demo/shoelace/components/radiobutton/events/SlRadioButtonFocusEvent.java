package org.demo.shoelace.components.radiobutton.events;

import java.util.Map;

import org.dwcj.webcomponent.annotations.EventName;
import org.dwcj.webcomponent.events.Event;
import org.demo.shoelace.components.radiobutton.SlRadioButton;

/**
 * The checkbox focus event.
 * 
 * Emitted when the checkbox gains focus.
 * 
 * @author Hyyan Abo Fakher
 */
@EventName("sl-focus")
public class SlRadioButtonFocusEvent extends Event<SlRadioButton> {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public SlRadioButtonFocusEvent(SlRadioButton control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}
