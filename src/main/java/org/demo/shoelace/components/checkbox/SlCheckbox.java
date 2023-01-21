package org.demo.shoelace.components.checkbox;

import org.demo.shoelace.components.SlComponent;
import org.demo.shoelace.components.checkbox.events.SlCheckboxBlurEvent;
import org.demo.shoelace.components.checkbox.events.SlCheckboxChangeEvent;
import org.demo.shoelace.components.checkbox.events.SlCheckboxClickEvent;
import org.demo.shoelace.components.checkbox.events.SlCheckboxFocusEvent;
import org.demo.shoelace.components.checkbox.events.SlCheckboxInputEvent;
import org.demo.shoelace.enums.SlSize;
import org.dwcj.interfaces.HasControlText;
import org.dwcj.webcomponent.PropertyDescriptor;
import org.dwcj.webcomponent.annotations.NodeName;
import org.dwcj.webcomponent.events.EventListener;

/**
 * A checkbox component.
 * 
 * @see <a href="https://shoelace.style/components/checkbox">Shoelace - Checkbox</a>
 * @author Hyyan Abo Fakher
 * @since 1.0.0
 */
@NodeName("sl-checkbox")
public final class SlCheckbox extends SlComponent implements HasControlText {

  // Properties
  private static PropertyDescriptor<String> NAME = PropertyDescriptor.property("name", "");
  private static PropertyDescriptor<String> VALUE = PropertyDescriptor.property("value", "");
  private static PropertyDescriptor<String> SIZE = PropertyDescriptor.property("size", SlSize.MEDIUM.getValue());
  private static PropertyDescriptor<Boolean> DISABLED = PropertyDescriptor.property("disabled", false);
  private static PropertyDescriptor<Boolean> REQUIRED = PropertyDescriptor.property("required", false);
  private static PropertyDescriptor<Boolean> CHECKED = PropertyDescriptor.property("checked", false);
  private static PropertyDescriptor<Boolean> INDETERMINATE = PropertyDescriptor.property("indeterminate", false);
  private static PropertyDescriptor<Boolean> DEFAULT_CHECKED = PropertyDescriptor.property("defaultChecked", false);

  /**
   * Create a new checkbox.
   */
  public SlCheckbox() {
    super();
  }

  /**
   * Create a new checkbox.
   * 
   * @param text The checkbox's text.
   */
  public SlCheckbox(String text) {
    super();
    setText(text);
  }

  /**
   * Set the checkbox's name.
   * 
   * @param name The name of the checkbox, submitted as a name/value pair with
   *             form data.
   * @return the checkbox
   */
  public SlCheckbox setName(String name) {
    set(NAME, name);
    return this;
  }

  /**
   * Get the checkbox's name.
   * 
   * @return The name of the checkbox, submitted as a name/value pair with form
   *         data.
   */
  public String getName() {
    return get(NAME);
  }

  /**
   * Set the checkbox's value.
   * 
   * @param value The current value of the checkbox, submitted as a name/value
   *              pair with form data.
   * @return the checkbox
   */
  public SlCheckbox setValue(String value) {
    set(VALUE, value);
    return this;
  }

  /**
   * Get the checkbox's value.
   * 
   * @return The current value of the checkbox, submitted as a name/value pair
   *         with form data.
   */
  public String getValue() {
    return get(VALUE);
  }

  /**
   * Set the checkbox's size.
   * 
   * @param size The checkbox's size.
   * @return the checkbox
   */
  public SlCheckbox setSize(SlSize size) {
    set(SIZE, size.getValue());
    return this;
  }

  /**
   * Get the checkbox's size.
   * 
   * @return The checkbox's size.
   */
  public SlSize getSize() {
    return SlSize.valueOf(get(SIZE));
  }

  /**
   * Set the checkbox's disabled state.
   * 
   * @param disabled Disables the checkbox.
   * @return the checkbox
   */
  public SlCheckbox setDisabled(boolean disabled) {
    set(DISABLED, disabled);
    return this;
  }

  /**
   * Get the checkbox's disabled state.
   * 
   * @return Disables the checkbox.
   */
  public boolean isDisabled() {
    return get(DISABLED, true, Boolean.class);
  }

  /**
   * Set the checkbox's required state.
   * 
   * @param required Makes the checkbox a required field.
   * @return the checkbox
   */
  public SlCheckbox setRequired(boolean required) {
    set(REQUIRED, required);
    return this;
  }

  /**
   * Get the checkbox's required state.
   * 
   * @return Makes the checkbox a required field.
   */
  public boolean isRequired() {
    return get(REQUIRED);
  }

  /**
   * Set the checkbox's checked state.
   * 
   * @param checked Draws the checkbox in a checked state.
   * @return the checkbox
   */
  public SlCheckbox setChecked(boolean checked) {
    set(CHECKED, checked);
    return this;
  }

  /**
   * Get the checkbox's checked state.
   * 
   * @return Draws the checkbox in a checked state.
   */
  public boolean isChecked() {
    return get(CHECKED, true, Boolean.class);
  }

  /**
   * Set the checkbox's indeterminate state.
   * 
   * @param indeterminate Draws the checkbox in an indeterminate state. This is
   *                      usually applied to
   *                      checkboxes that represents a "select all/none" behavior
   *                      when associated checkboxes have a
   *                      mix of checked and unchecked states.
   * @return the checkbox
   */
  public SlCheckbox setIndeterminate(boolean indeterminate) {
    set(INDETERMINATE, indeterminate);
    return this;
  }

  /**
   * Get the checkbox's indeterminate state.
   * 
   * @return Draws the checkbox in an indeterminate state. This is usually applied
   *         to checkboxes that
   *         represents a "select all/none" behavior when associated checkboxes
   *         have a mix of checked
   *         and unchecked states.
   */
  public boolean isIndeterminate() {
    return get(INDETERMINATE);
  }

  /**
   * Set the checkbox's default checked state.
   * 
   * The default value of the form control. Primarily used for resetting the form
   * control.
   * 
   * @param defaultChecked Draws the checkbox in a checked state by default.
   * @return the checkbox
   */
  public SlCheckbox setDefaultChecked(boolean defaultChecked) {
    set(DEFAULT_CHECKED, defaultChecked);
    return this;
  }

  /**
   * Get the checkbox's default checked state.
   * 
   * @return Draws the checkbox in a checked state by default.
   */
  public boolean isDefaultChecked() {
    return get(DEFAULT_CHECKED);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public SlCheckbox setText(String text) {
    addRawSlot(text);
    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getText() {
    return getRawSlot();
  }

  /**
   * Click the checkbox.
   * 
   * @return the checkbox
   */
  public SlCheckbox click() {
    invokeAsync("click");
    return this;
  }

  /**
   * Focus the checkbox.
   * 
   * @return the checkbox
   */
  public SlCheckbox focus() {
    invokeAsync("focus");
    return this;
  }

  /**
   * Blur the checkbox.
   * 
   * @return the checkbox
   */
  public SlCheckbox blur() {
    invokeAsync("blur");
    return this;
  }

  /**
   * Check the validity of the component.
   * 
   * @return the checkbox
   */
  public SlCheckbox checkValidity() {
    invokeAsync("checkValidity");
    return this;
  }

  /**
   * Report the validity of the component.
   * 
   * @return the checkbox
   */
  public boolean reportValidity() {
    String result = (String) invoke("reportValidity");
    return Boolean.parseBoolean(result);
  }

  /**
   * Set the custom validity of the button.
   * 
   * @param message
   * @return the checkbox
   */
  public SlCheckbox setCustomValidity(String message) {
    invokeAsync("setCustomValidity", message);
    return this;
  }

  /**
   * Add a click listener to the checkbox.
   * 
   * @param listener The listener to add.
   * @return the checkbox
   */
  public SlCheckbox addClickListener(EventListener<SlCheckboxClickEvent> listener) {
    addEventListener(SlCheckboxClickEvent.class, listener);
    return this;
  }

  /**
   * Remove a click listener from the checkbox.
   * 
   * @param listener The listener to remove.
   * @return the checkbox
   */
  public SlCheckbox removeClickListener(EventListener<SlCheckboxClickEvent> listener) {
    removeEventListener(SlCheckboxClickEvent.class, listener);
    return this;
  }

  /**
   * Add a focus listener to the checkbox.
   * 
   * @param listener The listener to add.
   * @return the checkbox
   */
  public SlCheckbox addFocusListener(EventListener<SlCheckboxFocusEvent> listener) {
    addEventListener(SlCheckboxFocusEvent.class, listener);
    return this;
  }

  /**
   * Remove a focus listener from the checkbox.
   * 
   * @param listener The listener to remove.
   * @return the checkbox
   */
  public SlCheckbox removeFocusListener(EventListener<SlCheckboxFocusEvent> listener) {
    removeEventListener(SlCheckboxFocusEvent.class, listener);
    return this;
  }

  /**
   * Add a blur listener to the checkbox.
   * 
   * @param listener The listener to add.
   * @return the checkbox
   */
  public SlCheckbox addBlurListener(EventListener<SlCheckboxBlurEvent> listener) {
    addEventListener(SlCheckboxBlurEvent.class, listener);
    return this;
  }

  /**
   * Remove a blur listener from the checkbox.
   * 
   * @param listener The listener to remove.
   * @return the checkbox
   */
  public SlCheckbox removeBlurListener(EventListener<SlCheckboxBlurEvent> listener) {
    removeEventListener(SlCheckboxBlurEvent.class, listener);
    return this;
  }

  /**
   * Add a change listener to the checkbox.
   * 
   * @param listener The listener to add.
   * @return the checkbox
   */
  public SlCheckbox addChangeListener(EventListener<SlCheckboxChangeEvent> listener) {
    addEventListener(SlCheckboxChangeEvent.class, listener);
    return this;
  }

  /**
   * Remove a change listener from the checkbox.
   * 
   * @param listener The listener to remove.
   * @return the checkbox
   */
  public SlCheckbox removeChangeListener(EventListener<SlCheckboxChangeEvent> listener) {
    removeEventListener(SlCheckboxChangeEvent.class, listener);
    return this;
  }

  /**
   * Add an input listener to the checkbox.
   * 
   * @param listener The listener to add.
   * @return the checkbox
   */
  public SlCheckbox addInputListener(EventListener<SlCheckboxInputEvent> listener) {
    addEventListener(SlCheckboxInputEvent.class, listener);
    return this;
  }

  /**
   * Remove an input listener from the checkbox.
   * 
   * @param listener The listener to remove.
   * @return the checkbox
   */
  public SlCheckbox removeInputListener(EventListener<SlCheckboxInputEvent> listener) {
    removeEventListener(SlCheckboxInputEvent.class, listener);
    return this;
  }
}