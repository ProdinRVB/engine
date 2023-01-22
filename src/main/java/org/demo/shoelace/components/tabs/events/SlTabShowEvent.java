package org.demo.shoelace.components.tabs.events;

import java.util.Map;

import org.dwcj.webcomponent.annotations.EventExpressions;
import org.dwcj.webcomponent.annotations.EventName;
import org.demo.shoelace.components.tabs.SlTabs;

/**
 * The SlTabsShowEvent event is dispatched when a tab is shown.
 * 
 * @author Hyyan Abo Fakher
 */
@EventName("sl-tab-show")
@EventExpressions(stopPropagation = "true", preventDefault = "true", filter = "event.target.isSameNode(component)")
public class SlTabShowEvent extends SlTabAbstractEvent {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public SlTabShowEvent(SlTabs control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}
