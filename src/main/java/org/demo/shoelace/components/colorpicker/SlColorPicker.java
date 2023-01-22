package org.demo.shoelace.components.colorpicker;

import java.awt.Color;
import java.text.Format;
import java.util.Arrays;
import java.util.List;

import org.demo.shoelace.components.SlComponent;
import org.demo.shoelace.components.colorpicker.events.SlColorPickerChangeEvent;
import org.demo.shoelace.components.colorpicker.events.SlColorPickerInputEvent;
import org.demo.shoelace.enums.SlSize;
import org.demo.shoelace.utils.CssColor;
import org.dwcj.webcomponent.PropertyDescriptor;
import org.dwcj.webcomponent.annotations.NodeName;
import org.dwcj.webcomponent.annotations.NodeProperty;
import org.dwcj.webcomponent.events.EventListener;

/**
 * Shoelace color picker component.
 * 
 * @see <a href="https://shoelace.style/components/color-picker">Shoelace Color
 *      Picker</a>
 * @author Hyyan Abo Fakher
 * @since 1.0.0
 */
@NodeName("sl-color-picker")
@NodeProperty(name = "format", value = "rgb")
public final class SlColorPicker extends SlComponent {

  private final PropertyDescriptor<String> VALUE = PropertyDescriptor.property("value", "");
  private final PropertyDescriptor<String> DEFAULT_VALUE = PropertyDescriptor.property("defaultValue", "");
  private final PropertyDescriptor<String> LABEL = PropertyDescriptor.property("label", "");
  private final PropertyDescriptor<String> FORMAT = PropertyDescriptor.property("format", "hex");
  private final PropertyDescriptor<Boolean> INLINE = PropertyDescriptor.property("inline", false);
  private final PropertyDescriptor<String> SIZE = PropertyDescriptor.property("size", "medium");
  private final PropertyDescriptor<Boolean> NO_FORMAT_TOGGLE = PropertyDescriptor.property("noFormatToggle", false);
  private final PropertyDescriptor<String> NAME = PropertyDescriptor.property("name", "");
  private final PropertyDescriptor<Boolean> DISABLED = PropertyDescriptor.property("disabled", false);
  private final PropertyDescriptor<Boolean> HOIST = PropertyDescriptor.property("hoist", false);
  private final PropertyDescriptor<Boolean> OPACITY = PropertyDescriptor.property("opacity", false);
  private final PropertyDescriptor<Boolean> UPPERCASE = PropertyDescriptor.property("uppercase", false);
  private final PropertyDescriptor<List<String>> SWATCHES = PropertyDescriptor.property("swatches", List.of());

  /**
   * Create new instance of color picker.
   */
  public SlColorPicker() {
    super();
  }

  /**
   * Create new instance of color picker.
   * 
   * @param value The current value of the color picker.
   */
  public SlColorPicker(Color value) {
    super();
    setDefaultValue(value);
    setValue(value);
  }

  /**
   * Set the current value of the color picker.
   * 
   * @param value The current value of the color picker.
   * @return color picker
   */
  public SlColorPicker setValue(Color value) {
    CssColor.Format format = CssColor.Format.fromString(getFormat());
    set(VALUE, CssColor.toCssString(value, format));

    return this;
  }

  /**
   * Get the current value of the color picker.
   * 
   * @return The current value of the color picker.
   */
  public Color getValue() {
    return CssColor.fromCssString(get(VALUE, true, String.class));
  }

  /**
   * Set the default value of the form control.
   * 
   * @param value The default value of the form control.
   * @return color picker
   */
  public SlColorPicker setDefaultValue(Color value) {
    CssColor.Format format = CssColor.Format.fromString(getFormat());
    set(DEFAULT_VALUE, CssColor.toCssString(value, format));
    
    return this;
  }

  /**
   * Get the default value of the form control.
   * 
   * @return The default value of the form control.
   */
  public Color getDefaultValue() {
    return CssColor.fromCssString(get(DEFAULT_VALUE));
  }

  /**
   * Set the color picker's label.
   * 
   * The color picker's label. This will not be displayed, but it will be
   * announced by assistive devices.
   * 
   * @param value The color picker's label.
   * @return color picker
   */
  public SlColorPicker setLabel(String value) {
    set(LABEL, value);
    return this;
  }

  /**
   * Get the color picker's label.
   * 
   * @return The color picker's label.
   */
  public String getLabel() {
    return get(LABEL);
  }

  /**
   * Set whether the color picker is inline.
   * 
   * @param value Whether the color picker is inline.
   * @return color picker
   */
  public SlColorPicker setInline(boolean value) {
    set(INLINE, value);
    return this;
  }

  /**
   * Get whether the color picker is inline.
   * 
   * @return Whether the color picker is inline.
   */
  public boolean isInline() {
    return get(INLINE);
  }

  /**
   * Set the size of the color picker's trigger.
   * 
   * @param value The size of the color picker's trigger.
   * @return color picker
   */
  public SlColorPicker setSize(SlSize value) {
    set(SIZE, value.getValue());
    return this;
  }

  /**
   * Get the size of the color picker's trigger.
   * 
   * @return The size of the color picker's trigger.
   */
  public SlSize getSize() {
    return SlSize.valueOf(get(SIZE));
  }

  /**
   * Set whether to hide the format toggle.
   * 
   * @param value Whether to hide the format toggle.
   * @return color picker
   */
  public SlColorPicker setNoFormatToggle(boolean value) {
    set(NO_FORMAT_TOGGLE, value);
    return this;
  }

  /**
   * Get whether to hide the format toggle.
   * 
   * @return Whether to hide the format toggle.
   */
  public boolean isNoFormatToggle() {
    return get(NO_FORMAT_TOGGLE);
  }

  /**
   * Set the name of the form control.
   * 
   * @param value The name of the form control.
   * @return color picker
   */
  public SlColorPicker setName(String value) {
    set(NAME, value);
    return this;
  }

  /**
   * Get the name of the form control.
   * 
   * @return The name of the form control.
   */
  public String getName() {
    return get(NAME);
  }

  /**
   * Set whether the color picker is disabled.
   * 
   * @param value Whether the color picker is disabled.
   * @return color picker
   */
  public SlColorPicker setDisabled(boolean value) {
    set(DISABLED, value);
    return this;
  }

  /**
   * Get whether the color picker is disabled.
   * 
   * @return Whether the color picker is disabled.
   */
  public boolean isDisabled() {
    return get(DISABLED, true, Boolean.class);
  }

  /**
   * Set whether the color picker is hoisted.
   * 
   * @param value Whether the color picker is hoisted.
   * @return color picker
   */
  public SlColorPicker setHoist(boolean value) {
    set(HOIST, value);
    return this;
  }

  /**
   * Get whether the color picker is hoisted.
   * 
   * Enable this option to prevent the panel from being clipped when the component
   * is placed inside a container with overflow: auto|scroll. Hoisting uses a
   * fixed positioning strategy that works in many, but not all, scenarios.
   * 
   * @return Whether the color picker is hoisted.
   */
  public boolean isHoist() {
    return get(HOIST);
  }

  /**
   * Shows the opacity slider.
   * 
   * @param value true to show the opacity slider
   * @return color picker
   */
  public SlColorPicker setOpacity(boolean value) {
    set(OPACITY, value);
    return this;
  }

  /**
   * Get whether to show the opacity slider.
   * 
   * @return Whether to show the opacity slider.
   */
  public boolean isOpacity() {
    return get(OPACITY);
  }

  /**
   * By default, values are lowercase. With this attribute, values will be
   * uppercase instead.
   * 
   * @param value true to use uppercase values
   * @return color picker
   */
  public SlColorPicker setUppercase(boolean value) {
    set(UPPERCASE, value);
    return this;
  }

  /**
   * Get whether to use uppercase values.
   * 
   * @return Whether to use uppercase values.
   */
  public boolean isUppercase() {
    return get(UPPERCASE);
  }

  /**
   * Set the color picker's swatches.
   * 
   * @param value The color picker's swatches.
   * @return color picker
   */
  public SlColorPicker setSwatches(List<String> value) {
    set(SWATCHES, value);
    return this;
  }

  /**
   * Set the color picker's swatches.
   * 
   * @param value The color picker's swatches.
   * @return color picker
   */
  public SlColorPicker setSwatches(String... value) {
    set(SWATCHES, Arrays.asList(value));
    return this;
  }

  /**
   * Get the color picker's swatches.
   * 
   * @return The color picker's swatches.
   */
  public List<String> getSwatches() {
    return get(SWATCHES);
  }

  /**
   * Check the validity of the picker
   * 
   * @return the picker
   */
  public SlColorPicker checkValidity() {
    callAsyncFunction("checkValidity");
    return this;
  }

  /**
   * Report the validity of the picker
   * 
   * @return the picker
   */
  public Boolean reportValidity() {
    String result = (String) callFunction("reportValidity");
    return Boolean.parseBoolean(result);
  }

  /**
   * Set the custom validity of the picker
   * 
   * @param message
   * @return the picker
   */
  public SlColorPicker setCustomValidity(String message) {
    callAsyncFunction("setCustomValidity", message);
    return this;
  }

  /**
   * Add an input listener to the color picker.
   * 
   * @param listener
   * @return the color picker
   */
  public SlColorPicker addClickListener(EventListener<SlColorPickerInputEvent> listener) {
    addEventListener(SlColorPickerInputEvent.class, listener);
    return this;
  }

  /**
   * Remove an input listener from the color picker.
   * 
   * @param listener
   * @return the color picker
   */
  public SlColorPicker removeClickListener(EventListener<SlColorPickerInputEvent> listener) {
    removeEventListener(SlColorPickerInputEvent.class, listener);
    return this;
  }

  /**
   * Add a change listener to the color picker.
   * 
   * @param listener
   * @return the color picker
   */
  public SlColorPicker addChangeListener(EventListener<SlColorPickerChangeEvent> listener) {
    addEventListener(SlColorPickerChangeEvent.class, listener);
    return this;
  }

  /**
   * Remove a change listener from the color picker.
   * 
   * @param listener
   * @return the color picker
   */
  public SlColorPicker removeChangeListener(EventListener<SlColorPickerChangeEvent> listener) {
    removeEventListener(SlColorPickerChangeEvent.class, listener);
    return this;
  }

  /**
   * Get the format to use.
   * 
   * @return The format to use.
   */
  private String getFormat() {
    return get(FORMAT, true, String.class);
  }
}
