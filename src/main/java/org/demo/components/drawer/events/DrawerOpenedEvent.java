package org.demo.components.drawer.events;

import java.util.Map;

import org.demo.components.applayout.AppLayout;
import org.dwcj.webcomponent.annotations.EventExpressions;
import org.dwcj.webcomponent.annotations.EventName;
import org.dwcj.webcomponent.events.Event;

/**
 * Fired when the drawer is opened.
 * 
 * @author Hyyan Abo Fakher
 */
@EventName(value = "bbj-drawer-opened")
@EventExpressions(filter = "event.target.isSameNode(component)")
public final class DrawerOpenedEvent extends Event<AppLayout> {

  /**
   * Creates a new event.
   * 
   * @param target the target of the event
   * @param detail the detail of the event
   */
  public DrawerOpenedEvent(AppLayout target, Map<String, Object> detail) {
    super(target, detail);
  }
}
