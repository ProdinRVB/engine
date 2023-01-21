package org.demo.shoelace.components.tabs.events;

import java.util.Map;

import org.demo.shoelace.components.tabs.SlTabs;
import org.dwcj.webcomponent.annotations.EventExpressions;
import org.dwcj.webcomponent.annotations.EventName;

/**
 * The SlTabsHideEvent event is dispatched when a tab is hidden.
 * 
 * @author Hyyan Abo Fakher
 */
@EventName("sl-tab-hide")
@EventExpressions(
    // only fire the event if the tab is connected to the DOM
    filter = ""
        + "const target = event.target;"
        + "if (!target || !target.isConnected) return false; "
        + "const tab = target.querySelector('sl-tab[panel=\"' + event.detail.name + '\"]');"
        + "return tab && tab.isConnected;", stopPropagation = "true", preventDefault = "true")
public class SlTabHideEvent extends SlTabAbstractEvent {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public SlTabHideEvent(SlTabs control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}
