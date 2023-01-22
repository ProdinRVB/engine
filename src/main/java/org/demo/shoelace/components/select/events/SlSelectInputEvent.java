package org.demo.shoelace.components.select.events;

import java.util.Map;

import org.demo.shoelace.components.select.SlSelect;
import org.dwcj.webcomponent.annotations.EventName;

/**
 * Emitted when the control receives input.
 * 
 * @author Hyyan Abo Fakher
 */
@EventName("sl-input")
public class SlSelectInputEvent extends SlSelectAbstractEvent {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public SlSelectInputEvent(SlSelect control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}
