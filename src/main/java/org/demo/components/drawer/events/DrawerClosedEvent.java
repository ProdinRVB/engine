package org.demo.components.drawer.events;

import java.util.Map;

import org.demo.components.applayout.AppLayout;
import org.dwcj.webcomponent.annotations.EventExpressions;
import org.dwcj.webcomponent.annotations.EventName;
import org.dwcj.webcomponent.events.Event;

/**
 * Event fired when the drawer is closed.
 * 
 * @author Hyyan Abo Fakher
 */
@EventName(value = "bbj-drawer-closed")
@EventExpressions(filter = "event.target.isSameNode(component)")
public final class DrawerClosedEvent extends Event<AppLayout> {

  /**
   * Creates a new event.
   * 
   * @param target the target of the event
   * @param detail the detail of the event
   */
  public DrawerClosedEvent(AppLayout target, Map<String, Object> detail) {
    super(target, detail);
  }
}
