package org.demo.shoelace.components.radiogroup.events;

import java.util.Map;

import org.dwcj.webcomponent.annotations.EventExpressions;
import org.dwcj.webcomponent.annotations.EventName;
import org.dwcj.webcomponent.events.Event;
import org.demo.shoelace.components.radiogroup.SlRadioGroup;

/**
 * The radio group input event.
 * 
 * Emitted when the radio group receives user input.
 * 
 * @author Hyyan Abo Fakher
 */
@EventName("sl-input")
@EventExpressions(detail = "event.detail.value = component.value;")
public class SlRadioGroupInputEvent extends Event<SlRadioGroup> {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public SlRadioGroupInputEvent(SlRadioGroup control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }

  /**
   * Get the current value of the radio group,
   * 
   * @return the value
   */
  public String getValue() {
    @SuppressWarnings("unchecked")
    Map<String, Object> detail = (Map<String, Object>) getEventMap().get("detail");

    return (String) detail.get("value");
  }
}
