package org.demo.shoelace.components.select.events;

import java.util.Map;

import org.demo.shoelace.components.select.SlSelect;
import org.dwcj.webcomponent.annotations.EventExpressions;
import org.dwcj.webcomponent.annotations.EventName;

/**
 * Emitted when the control gains focus.
 * 
 * @author Hyyan Abo Fakher
 */
@EventName("sl-focus")
@EventExpressions(stopPropagation = "true")
public class SlSelectFocusEvent extends SlSelectAbstractEvent {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public SlSelectFocusEvent(SlSelect control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}
