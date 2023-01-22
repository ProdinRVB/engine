package org.demo.shoelace.components.drawer.events;

import java.util.Map;

import org.dwcj.webcomponent.annotations.EventName;
import org.demo.shoelace.components.drawer.SlDrawer;

/**
 * Emitted when the details closes.
 * 
 * @author Hyyan Abo Fakher
 */
@EventName("sl-hide")
public class SlDrawerCloseEvent extends SlDrawerAbstractEvent {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public SlDrawerCloseEvent(SlDrawer control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}
