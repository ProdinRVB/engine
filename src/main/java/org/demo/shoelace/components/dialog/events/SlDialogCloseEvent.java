package org.demo.shoelace.components.dialog.events;

import java.util.Map;

import org.dwcj.webcomponent.annotations.EventName;
import org.dwcj.webcomponent.events.Event;
import org.demo.shoelace.components.dialog.SlDialog;

/**
 * The checkbox focus event.
 * 
 * Emitted when the checkbox receives input.
 * 
 * @author Hyyan Abo Fakher
 */
@EventName("sl-hide")
public class SlDialogCloseEvent extends Event<SlDialog> {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public SlDialogCloseEvent(SlDialog control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}
