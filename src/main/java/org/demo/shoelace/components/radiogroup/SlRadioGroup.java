package org.demo.shoelace.components.radiogroup;

import java.util.HashMap;
import java.util.Map;

import org.demo.shoelace.components.SlComponent;
import org.demo.shoelace.components.radio.SlRadio;
import org.demo.shoelace.components.radiobutton.SlRadioButton;
import org.demo.shoelace.components.radiogroup.events.SlRadioGroupChangeEvent;
import org.demo.shoelace.components.radiogroup.events.SlRadioGroupInputEvent;
import org.dwcj.controls.AbstractControl;
import org.dwcj.webcomponent.PropertyDescriptor;
import org.dwcj.webcomponent.annotations.NodeName;
import org.dwcj.webcomponent.events.EventListener;

/**
 * A radio group component.
 * 
 * @see <a href="https://shoelace.style/components/radio-group">Shoelace - Radio
 *      Group</a>
 * @author Hyyan Abo Fakher
 * @since 1.0.0
 */
@NodeName("sl-radio-group")
public final class SlRadioGroup extends SlComponent {

  private final Map<AbstractControl, String> controls = new HashMap<>();

  // Properties
  private static PropertyDescriptor<String> LABEL = PropertyDescriptor.property("label", "");
  private static PropertyDescriptor<String> HELP_TEXT = PropertyDescriptor.property("helpText", "");
  private static PropertyDescriptor<String> NAME = PropertyDescriptor.property("name", "option");
  private static PropertyDescriptor<String> VALUE = PropertyDescriptor.property("value", "");
  private static PropertyDescriptor<Boolean> REQUIRED = PropertyDescriptor.property("required", false);

  /**
   * Create a new radio group component.
   */
  public SlRadioGroup() {
    super();
  }

  /**
   * Create a new radio group component with the given label.
   * 
   * @param label the label
   */
  public SlRadioGroup(String label) {
    this();
    setLabel(label);
  }

  /**
   * Create a new radio group component with the given label and help text.
   * 
   * @param label    the label
   * @param helpText the help text
   */
  public SlRadioGroup(String label, String helpText) {
    this(label);
    setHelpText(helpText);
  }

  /**
   * Set the radio group's label. Required for proper accessibility.
   * 
   * @param label the label
   * @return this
   */
  public SlRadioGroup setLabel(String label) {
    set(LABEL, label);
    return this;
  }

  /**
   * Get the radio group's label.
   * 
   * @return the label
   */
  public String getLabel() {
    return get(LABEL);
  }

  /**
   * Set the radio group's help text.
   * 
   * @param helpText the help text
   * @return this
   */
  public SlRadioGroup setHelpText(String helpText) {
    set(HELP_TEXT, helpText);
    return this;
  }

  /**
   * Get the radio group's help text.
   * 
   * @return the help text
   */
  public String getHelpText() {
    return get(HELP_TEXT);
  }

  /**
   * Set the name of the radio group, submitted as a name/value pair with form
   * data.
   * 
   * @param name the name
   * @return this
   */
  public SlRadioGroup setName(String name) {
    set(NAME, name);
    return this;
  }

  /**
   * Get the name of the radio group, submitted as a name/value pair with form
   * data.
   * 
   * @return the name
   */
  public String getName() {
    return get(NAME);
  }

  /**
   * Set the current value of the radio group, submitted as a name/value pair with
   * form data.
   * 
   * @param value the value
   * @return this
   */
  public SlRadioGroup setValue(String value) {
    set(VALUE, value);
    return this;
  }

  /**
   * Get the current value of the radio group, submitted as a name/value pair with
   * form data.
   * 
   * @return the value
   */
  public String getValue() {
    return get(VALUE);
  }

  /**
   * Set whether the radio group is required.
   * 
   * @param required whether the radio group is required
   * @return this
   */
  public SlRadioGroup setRequired(boolean required) {
    set(REQUIRED, required);
    return this;
  }

  /**
   * Get whether the radio group is required.
   * 
   * @return whether the radio group is required
   */
  public boolean isRequired() {
    return get(REQUIRED);
  }

  /**
   * Add a radio to the radio group.
   * 
   * @param radio the radio
   * @return the radio group
   */
  public SlRadioGroup add(SlRadio... radio) {
    for (SlRadio r : radio) {
      String uuid = addControl(r);
      if (uuid != null && !uuid.isEmpty()) {
        controls.put(r, uuid);
      }
    }

    return this;
  }

  /**
   * Remove a radio from the radio group.
   * 
   * @param radio the radio
   * @return the radio group
   */
  public SlRadioGroup remove(SlRadio... radio) {
    for (SlRadio r : radio) {
      String uuid = controls.get(r);
      if (uuid != null && !uuid.isEmpty()) {
        removeControl(uuid);
      }
    }

    return this;
  }

  /**
   * Add a radio button to the radio group.
   * 
   * @param radio the radio button
   * @return the radio group
   */
  public SlRadioGroup add(SlRadioButton... radio) {
    for (SlRadioButton r : radio) {
      String uuid = addControl(r);
      if (uuid != null && !uuid.isEmpty()) {
        controls.put(r, uuid);
      }
    }

    return this;
  }

  /**
   * Remove a radio button from the radio group.
   * 
   * @param radio the radio button
   * @return the radio group
   */
  public SlRadioGroup remove(SlRadioButton... radio) {
    for (SlRadioButton r : radio) {
      String uuid = controls.get(r);
      if (uuid != null && !uuid.isEmpty()) {
        removeControl(uuid);
      }
    }

    return this;
  }

  /**
   * Check the validity of the group.
   * 
   * @return the group
   */
  public SlRadioGroup checkValidity() {
    invokeAsync("checkValidity");
    return this;
  }

  /**
   * Report the validity of the group.
   * 
   * @return the group
   */
  public Boolean reportValidity() {
    String result = (String) invoke("reportValidity");
    return Boolean.parseBoolean(result);
  }

  /**
   * Set the custom validity of the group.
   * 
   * @param message
   * @return the group
   */
  public SlRadioGroup setCustomValidity(String message) {
    invokeAsync("setCustomValidity", message);
    return this;
  }

  /**
   * Add input listener to the group.
   * 
   * @param listener the listener
   * @return the group
   */
  public SlRadioGroup addInputListener(EventListener<SlRadioGroupInputEvent> listener) {
    addEventListener(SlRadioGroupInputEvent.class, listener);
    return this;
  }

  /**
   * Remove input listener from the group.
   * 
   * @param listener the listener
   * @return the group
   */
  public SlRadioGroup removeInputListener(EventListener<SlRadioGroupInputEvent> listener) {
    removeEventListener(SlRadioGroupInputEvent.class, listener);
    return this;
  }

  /**
   * Add change listener to the group.
   * 
   * @param listener the listener
   * @return the group
   */
  public SlRadioGroup addChangeListener(EventListener<SlRadioGroupChangeEvent> listener) {
    addEventListener(SlRadioGroupChangeEvent.class, listener);
    return this;
  }

  /**
   * Remove change listener from the group.
   * 
   * @param listener the listener
   * @return the group
   */
  public SlRadioGroup removeChangeListener(EventListener<SlRadioGroupChangeEvent> listener) {
    removeEventListener(SlRadioGroupChangeEvent.class, listener);
    return this;
  }
}
