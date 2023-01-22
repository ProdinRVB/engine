package org.demo.shoelace.components.colorpicker.events;

import java.awt.Color;
import java.util.Map;

import org.dwcj.webcomponent.annotations.EventExpressions;
import org.dwcj.webcomponent.events.Event;
import org.demo.shoelace.components.colorpicker.SlColorPicker;
import org.demo.shoelace.utils.CssColor;

/**
 * @author Hyyan Abo Fakher
 */
@EventExpressions(detail = "event.detail.value = component.getFormattedValue('rgba');", filter = "event.target.isSameNode(component)")
public class SlColorPickerAbstractEvent extends Event<SlColorPicker> {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public SlColorPickerAbstractEvent(SlColorPicker control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }

  /**
   * The value of the color picker.
   * 
   * @return the value
   */
  public Color getValue() {
    @SuppressWarnings("unchecked")
    Map<String, Object> detail = (Map<String, Object>) getEventMap().get("detail");
    String value = (String) detail.get("value");

    if (value == null) {
      return null;
    }

    return CssColor.fromCssString(value);
  }

  /**
   * Get the value as a formatted css string.
   * 
   * @param format the format to use
   * @return the value
   */
  public String getFormattedValue(CssColor.Format format) {
    return CssColor.toCssString(getValue(), format);
  }

  /**
   * Get the value as a formatted css string.
   * 
   * @param format the format to use (hex, rgb, rgba, hsl, hsla, hsv, hsva)
   * @return the value
   */
  public String getFormattedValue(String format) {
    return getFormattedValue(CssColor.Format.fromString(format));
  }

  /**
   * Get the value as a formatted css hex string.
   * 
   * @return the value as hex
   */
  public String getFormattedValue() {
    return getFormattedValue(CssColor.Format.HEX);
  }
}
