package org.demo.shoelace.button.events;

import java.util.Map;

import org.dwcj.webcomponent.events.Event;
import org.demo.shoelace.button.SlButton;

/**
 * The button blur event.
 * 
 * @author Hyyan Abo Fakher
 */
public class SlButtonBlurEvent extends Event<SlButton> {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public SlButtonBlurEvent(SlButton control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}