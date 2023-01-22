package org.demo.shoelace.components.select.events;

import java.util.Map;

import org.demo.shoelace.components.select.SlSelect;
import org.dwcj.webcomponent.annotations.EventName;

/**
 * Emitted when the control's value is cleared.
 * 
 * @author Hyyan Abo Fakher
 */
@EventName("sl-clear")
public class SlSelectClearEvent extends SlSelectAbstractEvent {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public SlSelectClearEvent(SlSelect control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}
