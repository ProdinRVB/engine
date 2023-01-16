package org.demo.shoelace.input.events;

import java.util.Map;
import org.demo.shoelace.input.SlInput;

/**
 * The checkbox focus event.
 * 
 * Emitted when an alteration to the control's value is committed by the user.
 * 
 * @author Hyyan Abo Fakher
 */
public class SlInputChangeEvent extends SlInputModifiedEvent {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public SlInputChangeEvent(SlInput control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}
