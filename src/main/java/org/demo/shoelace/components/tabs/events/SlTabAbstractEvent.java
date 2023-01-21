package org.demo.shoelace.components.tabs.events;

import java.util.Map;

import org.dwcj.webcomponent.events.Event;
import org.demo.shoelace.components.tabs.SlTabs;

/**
 * @author Hyyan Abo Fakher
 */
public abstract class SlTabAbstractEvent extends Event<SlTabs> {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public SlTabAbstractEvent(SlTabs control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }

  /**
   * Get the tab id.
   * 
   * @return the tab id
   */
  public String getId() {
    @SuppressWarnings("unchecked")
    Map<String, String> detail = (Map<String, String>) getEventMap().get("detail");

    return detail.get("name");
  }
}
