package org.demo.shoelace.components.select.events;

import java.util.Map;

import org.demo.shoelace.components.select.SlSelect;
import org.dwcj.webcomponent.annotations.EventExpressions;
import org.dwcj.webcomponent.annotations.EventName;

/**
 * Emitted when the select's menu opens.
 * 
 * @author Hyyan Abo Fakher
 */
@EventName("sl-hide")
@EventExpressions(stopPropagation = "true")
public class SlSelectHideEvent extends SlSelectAbstractEvent {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public SlSelectHideEvent(SlSelect control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}