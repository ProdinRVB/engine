package org.demo.shoelace.components.input.events;

import java.util.Map;

import org.dwcj.webcomponent.annotations.EventExpressions;
import org.dwcj.webcomponent.annotations.EventName;
import org.dwcj.webcomponent.events.Event;
import org.demo.shoelace.components.input.SlInput;

/**
 * The checkbox focus event.
 * 
 * Emitted when the control receives input.
 * 
 * @author Hyyan Abo Fakher
 */
@EventName("sl-input")
@EventExpressions(detail = "event.detail.value = event.target.value;")
public class SlInputModifiedEvent extends Event<SlInput> {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public SlInputModifiedEvent(SlInput control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }

  /**
   * The value of the input.
   * 
   * @return the value
   */
  public String getValue() {
    @SuppressWarnings("unchecked")
    Map<String, Object> detail = (Map<String, Object>) getEventMap().get("detail");
    
    return (String) detail.get("value");
  }
}
