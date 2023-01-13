package org.demo.components.drawer.events;

import java.util.Map;

import org.demo.components.applayout.AppLayout;
import org.dwcj.webcomponent.events.Event;

public class DrawerClosedEvent extends Event<AppLayout> {

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
