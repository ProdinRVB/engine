package org.demo.shoelace.components.select.events;

import java.util.Map;

import org.demo.shoelace.components.select.SlSelect;
import org.dwcj.webcomponent.annotations.EventName;

/**
 * Emitted when the control loses focus.
 * 
 * @author Hyyan Abo Fakher
 */
@EventName("sl-blur")
public class SlSelectBlurEvent extends SlSelectAbstractEvent {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public SlSelectBlurEvent(SlSelect control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}
