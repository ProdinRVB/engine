package org.demo.shoelace.components.input.events;

import java.util.Map;

import org.demo.shoelace.components.input.SlInput;
import org.dwcj.webcomponent.annotations.EventName;
import org.dwcj.webcomponent.events.Event;

/**
 * The checkbox click event.
 * 
 * Emitted when the clear button is activated.
 * 
 * @author Hyyan Abo Fakher
 */
@EventName("sl-clear")
public class SlInputClearEvent extends Event<SlInput> {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public SlInputClearEvent(SlInput control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}