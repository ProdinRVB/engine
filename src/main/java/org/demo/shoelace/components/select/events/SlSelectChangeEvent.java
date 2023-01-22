package org.demo.shoelace.components.select.events;

import java.util.Map;

import org.demo.shoelace.components.select.SlSelect;
import org.dwcj.webcomponent.annotations.EventName;

/**
 * Emitted when the control's value changes.
 * 
 * @author Hyyan Abo Fakher
 */
@EventName("sl-change")
public class SlSelectChangeEvent extends SlSelectAbstractEvent {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public SlSelectChangeEvent(SlSelect control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}
