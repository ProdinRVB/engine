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
@EventName("sl-close")
@EventExpressions(detail = "event.detail.name = event.target.panel;", stopPropagation = "true", preventDefault = "true")
public class SlTabCloseEvent extends SlTabAbstractEvent {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public SlTabCloseEvent(SlTabs control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}
