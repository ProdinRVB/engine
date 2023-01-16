package org.demo.shoelace.components.checkbox.events;

import java.util.Map;

import org.dwcj.webcomponent.events.Event;
import org.demo.shoelace.components.checkbox.SlCheckbox;

/**
 * The checkbox focus event.
 * 
 * Emitted when the checkbox receives input.
 * 
 * @author Hyyan Abo Fakher
 */
public class SlCheckboxInputEvent extends Event<SlCheckbox> {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public SlCheckboxInputEvent(SlCheckbox control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }

  /**
   * Get the checked state.
   * 
   * @return the checked state
   */
  public boolean isChecked() {
    @SuppressWarnings("unchecked")
    Map<String, Object> detail = (Map<String, Object>) getEventMap().get("detail");

    return (boolean) detail.get("checked");
  }
}
