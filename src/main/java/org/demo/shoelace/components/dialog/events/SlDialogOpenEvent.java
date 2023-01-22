package org.demo.shoelace.components.dialog.events;

import java.util.Map;

import org.dwcj.webcomponent.annotations.EventExpressions;
import org.dwcj.webcomponent.annotations.EventName;
import org.dwcj.webcomponent.events.Event;
import org.demo.shoelace.components.dialog.SlDialog;

/**
 * 	Emitted when the details opens.
 * 
 * @author Hyyan Abo Fakher
 */
@EventName("sl-show")
@EventExpressions(filter = "event.target.isSameNode(component)")
public class SlDialogOpenEvent extends Event<SlDialog> {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public SlDialogOpenEvent(SlDialog control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}
