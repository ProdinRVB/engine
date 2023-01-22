package org.demo.shoelace.components.details.events;

import java.util.Map;


import org.dwcj.webcomponent.annotations.EventName;
import org.dwcj.webcomponent.events.Event;
import org.demo.shoelace.components.details.SlDetails;

/**
 * Emitted when the details closes.
 * 
 * @author Hyyan Abo Fakher
 */
@EventName("sl-hide")
public class SlDetailsCloseEvent extends Event<SlDetails> {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public SlDetailsCloseEvent(SlDetails control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}
