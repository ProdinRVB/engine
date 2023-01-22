package org.demo.shoelace.components.drawer.events;

import java.util.Map;

import org.dwcj.webcomponent.annotations.EventName;
import org.demo.shoelace.components.drawer.SlDrawer;

/**
 * Emitted when the drawer opens.
 * 
 * @author Hyyan Abo Fakher
 */
@EventName("sl-show")
public class SlDrawerOpenEvent extends SlDrawerAbstractEvent {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public SlDrawerOpenEvent(SlDrawer control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}
