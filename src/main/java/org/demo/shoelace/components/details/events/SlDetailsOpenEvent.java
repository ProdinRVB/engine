package org.demo.shoelace.components.details.events;

import java.util.Map;

import org.dwcj.webcomponent.annotations.EventExpressions;
import org.dwcj.webcomponent.annotations.EventName;
import org.dwcj.webcomponent.events.Event;
import org.demo.shoelace.components.details.SlDetails;

/**
 * Emitted when the details opens.
 * 
 * @author Hyyan Abo Fakher
 */
@EventName("sl-show")
@EventExpressions(filter = "event.target.isSameNode(component)")
public class SlDetailsOpenEvent extends Event<SlDetails> {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public SlDetailsOpenEvent(SlDetails control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}
