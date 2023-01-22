package org.demo.shoelace.components.drawer.events;

import java.util.Map;

import org.dwcj.webcomponent.annotations.EventExpressions;
import org.dwcj.webcomponent.events.Event;
import org.demo.shoelace.components.drawer.SlDrawer;

/**
 * @author Hyyan Abo Fakher
 */
@EventExpressions(detail = "event.detail.opened = event.target.opened", filter = "event.target.isSameNode(component)")
public class SlDrawerAbstractEvent extends Event<SlDrawer> {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public SlDrawerAbstractEvent(SlDrawer control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }

  /**
   * Indicates whether or not the drawer is open.
   * 
   * @return true if the drawer is open
   */
  public boolean isOpened() {
    @SuppressWarnings("unchecked")
    Map<String, Object> detail = (Map<String, Object>) getEventMap().get("detail");
    return (boolean) detail.get("open");
  }
}
