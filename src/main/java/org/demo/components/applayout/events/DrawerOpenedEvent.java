package org.demo.components.applayout.events;

import java.util.Map;

import org.demo.components.applayout.AppLayout;
import org.dwcj.webcomponent.events.Event;

public class DrawerOpenedEvent extends Event<AppLayout> {

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
