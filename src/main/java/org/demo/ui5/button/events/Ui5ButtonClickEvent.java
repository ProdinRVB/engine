package org.demo.ui5.button.events;

import java.util.Map;

import org.demo.ui5.button.Ui5Button;
import org.dwcj.webcomponent.annotations.EventName;
import org.dwcj.webcomponent.events.Event;

/**
 * The button click event.
 * 
 * @author Hyyan Abo Fakher
 */
@EventName("click")
public class Ui5ButtonClickEvent extends Event<Ui5Button> {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public Ui5ButtonClickEvent(Ui5Button control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}