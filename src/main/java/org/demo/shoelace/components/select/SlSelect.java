package org.demo.shoelace.components.select;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.demo.shoelace.components.SlComponent;
import org.demo.shoelace.components.select.events.SlSelectBlurEvent;
import org.demo.shoelace.components.select.events.SlSelectChangeEvent;
import org.demo.shoelace.components.select.events.SlSelectClearEvent;
import org.demo.shoelace.components.select.events.SlSelectHideEvent;
import org.demo.shoelace.components.select.events.SlSelectInputEvent;
import org.demo.shoelace.components.select.events.SlSelectFocusEvent;
import org.demo.shoelace.enums.SlSize;
import org.dwcj.exceptions.DwcRuntimeException;
import org.dwcj.webcomponent.PropertyDescriptor;
import org.dwcj.webcomponent.annotations.NodeName;
import org.dwcj.webcomponent.events.EventListener;

/**
 * A Shoelace select.
 * 
 * @see <a href="https://shoelace.style/components/select">Shoelace Select</a>
 * @since 1.0.0
 */
@NodeName("sl-select")
public final class SlSelect extends SlComponent {

  /**
   * The preferred placement of the select's menu. Note that the actual placement
   * may vary as needed to keep the listbox inside of the viewport.
   */
  public enum Placement {
    TOP("top"),
    BOTTOM("bottom");

    private final String value;

    private Placement(String value) {
      this.value = value;
    }

    /**
     * @return the value
     */
    public String getValue() {
      return value;
    }

    /**
     * @param value the value as string
     */
    @Override
    public String toString() {
      return value;
    }
  }

  private List<SlOption> options = new ArrayList<>();
  private List<SlOption> selected = new ArrayList<>();

  // Properties
  private final PropertyDescriptor<String> NAME = PropertyDescriptor.property("name", "");
  private final PropertyDescriptor<String> VALUE = PropertyDescriptor.attribute("value", "");
  private final PropertyDescriptor<String> DEFAULT_VALUE = PropertyDescriptor.property("defaultValue", "");
  private final PropertyDescriptor<String> SIZE = PropertyDescriptor.property("size", SlSize.MEDIUM.getValue());
  private final PropertyDescriptor<String> PLACEHOLDER = PropertyDescriptor.property("placeholder", "");
  private final PropertyDescriptor<Boolean> MULTIPLE = PropertyDescriptor.property("multiple", false);
  private final PropertyDescriptor<Integer> MAX_OPTIONS_VISIBLE = PropertyDescriptor.property("maxOptionsVisible", 3);
  private final PropertyDescriptor<Boolean> DISABLED = PropertyDescriptor.property("disabled", false);
  private final PropertyDescriptor<Boolean> CLEARABLE = PropertyDescriptor.property("clearable", false);
  private final PropertyDescriptor<Boolean> OPEN = PropertyDescriptor.property("open", false);
  private final PropertyDescriptor<Boolean> HOIST = PropertyDescriptor.property("hoist", false);
  private final PropertyDescriptor<Boolean> FILLED = PropertyDescriptor.property("filled", false);
  private final PropertyDescriptor<Boolean> PILL = PropertyDescriptor.property("pill", false);
  private final PropertyDescriptor<String> LABEL = PropertyDescriptor.property("label", "");
  private final PropertyDescriptor<String> PLACEMENT = PropertyDescriptor.property("placement",
      Placement.BOTTOM.getValue());
  private final PropertyDescriptor<String> HELP_TEXT = PropertyDescriptor.property("helpText", "");
  private final PropertyDescriptor<Boolean> REQUIRED = PropertyDescriptor.property("required", false);

  /**
   * Create a new select.
   */
  public SlSelect() {
    super();

    addChangeListener((SlSelectChangeEvent event) -> {
      selected.clear();
      List<SlOption> selectedOptions = event.getSelected();
      for (SlOption option : selectedOptions) {
        selected.add(option);
      }
    });
  }

  /**
   * Set The name of the select, submitted as a name/value pair with form data.
   * 
   * @param name The name of the select.
   * @return select
   */
  public SlSelect setName(String name) {
    set(NAME, name);
    return this;
  }

  /**
   * Get The name of the select.
   * 
   * @return The name of the select.
   */
  public String getName() {
    return get(NAME);
  }

  /**
   * Set The current value of the select, submitted as a name/value pair with form
   * data. When multiple is enabled, the value will be a space-delimited list of
   * values based on the options selected.
   * 
   * @param value The current value of the select.
   * @return select
   */
  public SlSelect setSelected(SlOption... value) {
    String values = "";

    selected.clear();

    if (isMultiple()) {
      StringBuilder sb = new StringBuilder();
      for (SlOption option : value) {
        if (contains(option)) {
          sb.append(option.getValue()).append(" ");
          selected.add(option);
        }
      }

      values = sb.toString();
    } else {
      if (contains(value[0])) {
        values = value[0].getValue();
        selected.add(value[0]);
      }
    }

    set(VALUE, values);

    return this;
  }

  /**
   * Set The current value of the select, submitted as a name/value pair with form
   * data. When multiple is enabled, the value will be a space-delimited list of
   * values based on the options selected.
   * 
   * @param value The current value of the select.
   * @return select
   */
  public SlSelect setSelected(String... options) {
    // fin the options
    List<SlOption> selectedOptions = new ArrayList<>();
    for (String option : options) {
      for (SlOption slOption : this.options) {
        if (slOption.getValue().equals(option)) {
          selectedOptions.add(slOption);
        }
      }
    }

    return setSelected(selectedOptions.toArray(new SlOption[selectedOptions.size()]));
  }

  /**
   * Get The current value of the select.
   * 
   * @return The current value of the select.
   */
  public List<SlOption> getSelected() {
    return selected;
  }

  /**
   * Set The default value of the form control. Primarily used for resetting the
   * form control.
   * 
   * @param defaultValue The default value of the form control.
   * @return select
   */
  public SlSelect setDefaultValue(String defaultValue) {
    set(DEFAULT_VALUE, defaultValue);
    return this;
  }

  /**
   * Get The default value of the form control.
   * 
   * @return The default value of the form control.
   */
  public String getDefaultValue() {
    return get(DEFAULT_VALUE);
  }

  /**
   * Set The select's size.
   * 
   * @param size The select's size.
   * @return select
   */
  public SlSelect setSize(SlSize size) {
    set(SIZE, size.getValue());
    return this;
  }

  /**
   * Get The select's size.
   * 
   * @return The select's size.
   */
  public SlSize getSize() {
    return SlSize.valueOf(get(SIZE));
  }

  /**
   * Set Placeholder text to show as a hint when the select is empty.
   * 
   * @param placeholder Placeholder text to show as a hint when the select is
   *                    empty.
   * @return select
   */
  public SlSelect setPlaceholder(String placeholder) {
    set(PLACEHOLDER, placeholder);
    return this;
  }

  /**
   * Get Placeholder text to show as a hint when the select is empty.
   * 
   * @return Placeholder text to show as a hint when the select is empty.
   */
  public String getPlaceholder() {
    return get(PLACEHOLDER);
  }

  /**
   * Set Enables multiple selection. When multiple is enabled, the value will be a
   * space-delimited list of values based on the options selected.
   * 
   * @param multiple Enables multiple selection.
   * @return select
   */
  public SlSelect setMultiple(boolean multiple) {
    set(MULTIPLE, multiple);
    return this;
  }

  /**
   * Get Enables multiple selection.
   * 
   * @return Enables multiple selection.
   */
  public boolean isMultiple() {
    return get(MULTIPLE);
  }

  /**
   * Set The maximum number of options visible in the listbox before scrolling is
   * enabled. Note that this number includes the selected option, so the listbox
   * will never be empty.
   * 
   * @param maxOptionsVisible The maximum number of options visible in the listbox
   *                          before scrolling is enabled.
   * @return select
   */
  public SlSelect setMaxOptionsVisible(int maxOptionsVisible) {
    set(MAX_OPTIONS_VISIBLE, maxOptionsVisible);
    return this;
  }

  /**
   * Get The maximum number of options visible in the listbox before scrolling is
   * enabled.
   * 
   * @return The maximum number of options visible in the listbox before scrolling
   *         is enabled.
   */
  public int getMaxOptionsVisible() {
    return get(MAX_OPTIONS_VISIBLE);
  }

  /**
   * Set Disables the select.
   * 
   * @param disabled Disables the select.
   * @return select
   */
  public SlSelect setDisabled(boolean disabled) {
    set(DISABLED, disabled);
    return this;
  }

  /**
   * Get Disables the select.
   * 
   * @return Disables the select.
   */
  public boolean isDisabled() {
    return get(DISABLED);
  }

  /**
   * Set Enables the clear button.
   * 
   * @param clearable Enables the clear button.
   * @return select
   */
  public SlSelect setClearable(boolean clearable) {
    set(CLEARABLE, clearable);
    return this;
  }

  /**
   * Get Enables the clear button.
   * 
   * @return Enables the clear button.
   */
  public boolean isClearable() {
    return get(CLEARABLE);
  }

  /**
   * Open the select.
   * 
   * @return select
   */
  public SlSelect open() {
    set(OPEN, true);
    return this;
  }

  /**
   * Get the select's open state.
   * 
   * @return the select's open state
   */
  public boolean isOpen() {
    return get(OPEN, true, Boolean.class);
  }

  /**
   * Close the select.
   * 
   * @return select
   */
  public SlSelect close() {
    set(OPEN, false);
    return this;
  }

  /**
   * Enable this option to prevent the listbox from being clipped when the
   * component is placed inside a container with overflow: auto|scroll. Hoisting
   * uses a fixed positioning strategy that works in many, but not all, scenarios.
   * 
   * @param placement The select's placement.
   * @return select
   */
  public SlSelect setHoisted(boolean hoisted) {
    set(HOIST, hoisted);
    return this;
  }

  /**
   * Get the select's hoisted state.
   * 
   * @return the select's hoisted state
   */
  public boolean isHoisted() {
    return get(HOIST);
  }

  /**
   * Draws a filled select.
   * 
   * @param filled Draws a filled select.
   * @return select
   */
  public SlSelect setFilled(boolean filled) {
    set(FILLED, filled);
    return this;
  }

  /**
   * Get the select's filled state.
   * 
   * @return the select's filled state
   */
  public boolean isFilled() {
    return get(FILLED);
  }

  /**
   * Set The select's pill style.
   * 
   * @param pill The select's pill style.
   * @return select
   */
  public SlSelect setPill(boolean pill) {
    set(PILL, pill);
    return this;
  }

  /**
   * Get The select's pill style.
   * 
   * @return The select's pill style.
   */
  public boolean isPill() {
    return get(PILL);
  }

  /**
   * Set The select's label.
   * 
   * @param label The select's label.
   * @return select
   */
  public SlSelect setLabel(String label) {
    set(LABEL, label);
    return this;
  }

  /**
   * Get The select's label.
   * 
   * @return The select's label.
   */
  public String getLabel() {
    return get(LABEL);
  }

  /**
   * Set The select's placement.
   * 
   * @param placement The select's placement.
   * @return select
   */
  public SlSelect setPlacement(Placement placement) {
    set(PLACEMENT, placement.getValue());
    return this;
  }

  /**
   * Get The select's placement.
   * 
   * @return The select's placement.
   */
  public Placement getPlacement() {
    return Placement.valueOf(get(PLACEMENT));
  }

  /**
   * The select's help text. If you need to display HTML, use the help-text slot
   * instead.
   * 
   * @param helpText The select's help text.
   * @return select
   */
  public SlSelect setHelpText(String helpText) {
    set(HELP_TEXT, helpText);
    return this;
  }

  /**
   * Get The select's help text.
   * 
   * @return The select's help text.
   */
  public String getHelpText() {
    return get(HELP_TEXT);
  }

  /**
   * Set The select's required attribute.
   * 
   * @param required The select's required attribute.
   * @return select
   */
  public SlSelect setRequired(boolean required) {
    set(REQUIRED, required);
    return this;
  }

  /**
   * Get The select's required attribute.
   * 
   * @return The select's required attribute.
   */
  public boolean isRequired() {
    return get(REQUIRED);
  }

  /**
   * Focus the button.
   * 
   * @return the button
   */
  public SlSelect focus() {
    invokeAsync("focus");
    return this;
  }

  /**
   * Blur the button.
   * 
   * @return the button
   */
  public SlSelect blur() {
    callAsyncFunction("blur");
    return this;
  }

  /**
   * Check the validity of the button.
   * 
   * @return the button
   */
  public SlSelect checkValidity() {
    callAsyncFunction("checkValidity");
    return this;
  }

  /**
   * Report the validity of the button.
   * 
   * @return the button
   */
  public Boolean reportValidity() {
    String result = (String) invoke("reportValidity");
    return Boolean.parseBoolean(result);
  }

  /**
   * Set the custom validity of the button.
   * 
   * @param message
   * @return the button
   */
  public SlSelect setCustomValidity(String message) {
    callAsyncFunction("setCustomValidity", message);
    return this;
  }

  /**
   * Add an input listener to the select.
   * 
   * @param listener
   * @return the select
   */
  public SlSelect addInputListener(EventListener<SlSelectInputEvent> listener) {
    addEventListener(SlSelectInputEvent.class, listener);
    return this;
  }

  /**
   * Remove an input listener from the select.
   * 
   * @param listener
   * @return the select
   */
  public SlSelect removeInputListener(EventListener<SlSelectInputEvent> listener) {
    removeEventListener(SlSelectInputEvent.class, listener);
    return this;
  }

  /**
   * Add an change listener to the select.
   * 
   * @param listener
   * @return the select
   */
  public SlSelect addChangeListener(EventListener<SlSelectChangeEvent> listener) {
    addEventListener(SlSelectChangeEvent.class, listener);
    return this;
  }

  /**
   * Remove an change listener from the select.
   * 
   * @param listener
   * @return the select
   */
  public SlSelect removeChangeListener(EventListener<SlSelectChangeEvent> listener) {
    removeEventListener(SlSelectChangeEvent.class, listener);
    return this;
  }

  /**
   * Add a clear listener to the select.
   * 
   * @param listener
   * @return the select
   */
  public SlSelect addClearListener(EventListener<SlSelectClearEvent> listener) {
    addEventListener(SlSelectClearEvent.class, listener);
    return this;
  }

  /**
   * Remove a clear listener from the select.
   * 
   * @param listener
   * @return the select
   */
  public SlSelect removeClearListener(EventListener<SlSelectClearEvent> listener) {
    removeEventListener(SlSelectClearEvent.class, listener);
    return this;
  }

  /**
   * Add a show listener to the select.
   * 
   * @param listener
   * @return the select
   */
  public SlSelect addShowListener(EventListener<SlSelectFocusEvent> listener) {
    addEventListener(SlSelectFocusEvent.class, listener);
    return this;
  }

  /**
   * Remove a show listener from the select.
   * 
   * @param listener
   * @return the select
   */
  public SlSelect removeShowListener(EventListener<SlSelectFocusEvent> listener) {
    removeEventListener(SlSelectFocusEvent.class, listener);
    return this;
  }

  /**
   * Add a hide listener to the select.
   * 
   * @param listener
   * @return the select
   */
  public SlSelect addHideListener(EventListener<SlSelectHideEvent> listener) {
    addEventListener(SlSelectHideEvent.class, listener);
    return this;
  }

  /**
   * Remove a hide listener from the select.
   * 
   * @param listener
   * @return the select
   */
  public SlSelect removeHideListener(EventListener<SlSelectHideEvent> listener) {
    removeEventListener(SlSelectHideEvent.class, listener);
    return this;
  }

  /**
   * Add focus listener to the select.
   * 
   * @param listener
   * @return the select
   */
  public SlSelect addFocusListener(EventListener<SlSelectFocusEvent> listener) {
    addEventListener(SlSelectFocusEvent.class, listener);
    return this;
  }

  /**
   * Remove focus listener from the select.
   * 
   * @param listener
   * @return the select
   */
  public SlSelect removeFocusListener(EventListener<SlSelectFocusEvent> listener) {
    removeEventListener(SlSelectFocusEvent.class, listener);
    return this;
  }

  /**
   * Add blur listener to the select.
   * 
   * @param listener
   * @return the select
   */
  public SlSelect addBlurListener(EventListener<SlSelectBlurEvent> listener) {
    addEventListener(SlSelectBlurEvent.class, listener);
    return this;
  }

  /**
   * Remove blur listener from the select.
   * 
   * @param listener
   * @return the select
   */
  public SlSelect removeBlurListener(EventListener<SlSelectBlurEvent> listener) {
    removeEventListener(SlSelectBlurEvent.class, listener);
    return this;
  }

  /**
   * Add option to the select.
   * 
   * @param option The option to add.
   * @return select
   */
  public SlSelect add(SlOption option) {
    options.add(option);

    // call addPropertyChangeListener with reflection
    // to keep track of changes in the breadcrumb item
    Method method = null;
    try {
      method = option.getClass().getDeclaredMethod("addPropertyChangeListener", PropertyChangeListener.class);
      method.setAccessible(true);
      method.invoke(option, new SlOptionChangeListener());
    } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
        | InvocationTargetException e) {
      throw new DwcRuntimeException("Failed to add property change listener to option item", e);
    }

    updateInClient(option);

    return this;
  }

  /**
   * Add option to the select.
   * 
   * @param value The option's value.
   * @return select
   */
  public SlSelect add(String value) {
    return add(new SlOption(value));
  }

  /**
   * Add option to the select.
   * 
   * @param text  The option's text.
   * @param value The option's value.
   * 
   * @return select
   */
  public SlSelect add(String text, String value) {
    return add(new SlOption(text, value));
  }

  /**
   * Add options to the select.
   * 
   * @param values The options to add.
   * @return select
   */
  public SlSelect add(String... values) {
    for (String option : values) {
      add(option);
    }
    return this;
  }

  /**
   * Add options to the select.
   * 
   * @param options The options to add.
   * @return select
   */
  public SlSelect add(SlOption... options) {
    for (SlOption option : options) {
      add(option);
    }
    return this;
  }

  /**
   * Remove option from the select.
   * 
   * @param option The option to remove.
   * @return select
   */
  public SlSelect remove(SlOption option) {
    options.remove(option);
    removeInClient(option);
    return this;
  }

  /**
   * Remove option from the select.
   * 
   * @param option The option to remove.
   * @return select
   */
  public SlSelect remove(String option) {
    // find the option with the given text
    SlOption found = null;
    for (SlOption o : options) {
      if (o.getValue().equals(option)) {
        found = o;
        break;
      }
    }

    // remove the option if found
    if (found != null) {
      remove(found);
    }

    return this;
  }

  /**
   * Remove all options from the select.
   * 
   * @return select
   */
  public SlSelect removeAll() {
    options.clear();
    removeAllInClient();
    return this;
  }

  /**
   * Get an option from the select by index.
   * 
   * @return option
   */
  public SlOption get(int index) {
    return options.get(index);
  }

  /**
   * Get an option from the select by value.
   * 
   * @return option
   */
  public SlOption get(String value) {
    for (SlOption option : options) {
      if (option.getValue().equals(value)) {
        return option;
      }
    }

    return null;
  }

  /**
   * Check if the select contains the given option.
   * 
   * @param option the option to check
   * @return true if the select contains the given option, false otherwise
   */
  public boolean contains(SlOption option) {
    return options.contains(option);
  }

  /**
   * Check if the select contains the given option.
   * 
   * @param option the option to check
   * @return true if the select contains the given option, false otherwise
   */
  public boolean contains(String option) {
    for (SlOption o : options) {
      if (o.getValue().equals(option)) {
        return true;
      }
    }

    return false;
  }

  /**
   * Check if the select is empty.
   * 
   * @return true if the select is empty, false otherwise
   */
  public boolean isEmpty() {
    return options.isEmpty();
  }

  /**
   * Get the number of options in the select.
   * 
   * @return the number of options in the select
   */
  public int size() {
    return options.size();
  }

  /**
   * Update the given option in the client side.
   * 
   * @param option the option to update
   */
  private void updateInClient(SlOption option) {
    int index = options.indexOf(option);
    StringBuilder js = new StringBuilder();

    js.append("let option = component.querySelector('sl-option:nth-child(").append(index + 1).append(")');")
        .append("if (!option) {")
        .append(" option = document.createElement('sl-option');")
        .append(" component.appendChild(option);")
        .append("}")
        .append("option.innerHTML = \\`").append(option.getText()).append("\\`;")
        .append("option.value = \\`").append(option.getValue()).append("\\`;")
        .append("option.disabled = ").append(option.isDisabled()).append(";");

    // check if option is created, if not, create it
    String prefix = option.getPrefix();
    if (prefix != null && !prefix.isEmpty()) {
      js.append("let prefix = option.querySelector('span[slot=\"prefix\"]');")
          .append("if (!prefix) {")
          .append(" prefix = document.createElement('span');")
          .append(" prefix.setAttribute('slot', 'prefix');")
          .append(" prefix.style.display = 'flex';")
          .append(" option.appendChild(prefix);")
          .append("}")
          .append("prefix.innerHTML = \\`").append(option.getPrefix()).append("\\`;");
    } else {
      js.append("const prefix = option.querySelector('span[slot=\"prefix\"]');")
          .append("if (prefix) {")
          .append(" prefix.remove();")
          .append("}");
    }

    String suffix = option.getSuffix();
    if (suffix != null && !suffix.isEmpty()) {
      js.append("let suffix = option.querySelector('span[slot=\"suffix\"]');")
          .append("if (!suffix) {")
          .append(" suffix = document.createElement('span');")
          .append(" suffix.setAttribute('slot', 'suffix');")
          .append(" suffix.style.display = 'flex';")
          .append(" option.appendChild(suffix);")
          .append("}")
          .append("suffix.innerHTML = \\`").append(option.getSuffix()).append("\\`;");
    } else {
      js.append("const suffix = option.querySelector('span[slot=\"suffix\"]');")
          .append("if (suffix) {")
          .append(" suffix.remove();")
          .append("}");
    }

    js.append("return;"); // to avoid auto wrapping

    executeAsyncExpression(js.toString());
  }

  /**
   * Remove the given option from the client side.
   * 
   * @param option the option to remove
   */
  private void removeInClient(SlOption option) {
    int index = options.indexOf(option);
    executeAsyncExpression("const option = component.querySelector('sl-option:nth-child(" + (index + 1) + ")');"
        + "if (option) {"
        + " option.remove();"
        + "}"
        + "return;"); // to avoid auto wrapping
  }

  /**
   * Remove all options from the client side.
   */
  private void removeAllInClient() {
    executeAsyncExpression("component.innerHTML = '';"
        + "return;"); // to avoid auto wrapping
  }

  /**
   * An item change listener. When a property of a SlOption changes, this
   * listener will update the corresponding property of the sl-option
   * element.
   * 
   * @author Hyyan Abo Fakher
   */
  private class SlOptionChangeListener implements PropertyChangeListener {

    /**
     * {@inheritDoc}
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
      updateInClient((SlOption) evt.getSource());
    }
  }
}
