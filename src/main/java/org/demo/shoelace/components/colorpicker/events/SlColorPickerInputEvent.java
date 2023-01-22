package org.demo.shoelace.components.colorpicker.events;

import java.util.Map;

import org.dwcj.webcomponent.annotations.EventName;
import org.demo.shoelace.components.colorpicker.SlColorPicker;

/**
 * Emitted when the color picker's value changes.
 * 
 * @author Hyyan Abo Fakher
 */
@EventName("sl-input")
public class SlColorPickerInputEvent extends SlColorPickerAbstractEvent {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public SlColorPickerInputEvent(SlColorPicker control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}