package org.demo.shoelace.components.input;

import org.demo.shoelace.components.SlComponent;
import org.demo.shoelace.components.input.events.SlInputBlurEvent;
import org.demo.shoelace.components.input.events.SlInputChangeEvent;
import org.demo.shoelace.components.input.events.SlInputClearEvent;
import org.demo.shoelace.components.input.events.SlInputFocusEvent;
import org.demo.shoelace.components.input.events.SlInputModifiedEvent;
import org.demo.shoelace.enums.SlSize;
import org.dwcj.webcomponent.PropertyDescriptor;
import org.dwcj.webcomponent.annotations.NodeName;
import org.dwcj.webcomponent.events.EventListener;

/**
 * The input component.
 * 
 * @see <a href="https://shoelace.style/components/input">Shoelace - Input</a>
 * @author Hyyan Abo Fakher
 * @since 1.0.0
 */
@NodeName("sl-input")
public final class SlInput extends SlComponent {

  /**
   * The type of input
   */
  public enum Type {
    DATE("date"),
    DATETIME_LOCAL("datetime-local"),
    EMAIL("email"),
    NUMBER("number"),
    PASSWORD("password"),
    SEARCH("search"),
    TEL("tel"),
    TEXT("text"),
    TIME("time"),
    URL("url");

    private final String value;

    Type(String value) {
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

    /**
     * @param value the value as string
     * @return the type
     */
    public static Type fromString(String value) {
      for (Type type : Type.values()) {
        if (type.value.equals(value)) {
          return type;
        }
      }
      return null;
    }
  }

  /**
   * Controls whether and how text input is automatically capitalized as it is
   * entered by the user.
   */
  public enum AutoCapitalize {
    OFF("off"),
    NONE("none"),
    ON("on"),
    SENTENCES("sentences"),
    WORDS("words"),
    CHARACTERS("characters");

    private final String value;

    AutoCapitalize(String value) {
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

    /**
     * @param value the value as string
     * @return the auto capitalize
     */
    public static AutoCapitalize fromString(String value) {
      for (AutoCapitalize autoCapitalize : AutoCapitalize.values()) {
        if (autoCapitalize.value.equals(value)) {
          return autoCapitalize;
        }
      }
      return null;
    }
  }

  /**
   * Used to customize the label or icon of the Enter key on virtual keyboards.
   */
  public enum EnterKeyHint {
    DONE("done"),
    GO("go"),
    NEXT("next"),
    PREVIOUS("previous"),
    SEARCH("search"),
    SEND("send");

    private final String value;

    EnterKeyHint(String value) {
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

    /**
     * @param value the value as string
     * @return the enter key hint
     */
    public static EnterKeyHint fromString(String value) {
      for (EnterKeyHint enterKeyHint : EnterKeyHint.values()) {
        if (enterKeyHint.value.equals(value)) {
          return enterKeyHint;
        }
      }
      return null;
    }
  }

  /**
   * Tells the browser what type of data will be entered by the user, allowing it
   * to display the appropriate virtual keyboard on supportive devices..
   */
  public enum InputMode {
    NONE("none"),
    TEXT("text"),
    TEL("tel"),
    URL("url"),
    EMAIL("email"),
    NUMERIC("numeric"),
    DECIMAL("decimal"),
    SEARCH("search");

    private final String value;

    InputMode(String value) {
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

    /**
     * @param value the value as string
     * @return the input mode
     */
    public static InputMode fromString(String value) {
      for (InputMode inputMode : InputMode.values()) {
        if (inputMode.value.equals(value)) {
          return inputMode;
        }
      }
      return null;
    }
  }

  /** The selection direction */
  public enum SelectionDirection {
    // 'forward' | 'backward' | 'none'

    FORWARD("forward"),
    BACKWARD("backward"),
    NONE("none");

    private final String value;

    SelectionDirection(String value) {
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

    /**
     * @param value the value as string
     * @return the selection direction
     */
    public static SelectionDirection fromString(String value) {
      for (SelectionDirection selectionDirection : SelectionDirection.values()) {
        if (selectionDirection.value.equals(value)) {
          return selectionDirection;
        }
      }
      return null;
    }
  }

  // Properties
  private final PropertyDescriptor<String> TYPE = PropertyDescriptor.property("type", Type.TEXT.getValue());
  private final PropertyDescriptor<String> NAME = PropertyDescriptor.property("name", "");
  private final PropertyDescriptor<String> VALUE = PropertyDescriptor.property("value", "");
  private final PropertyDescriptor<String> DEFAULT_VALUE = PropertyDescriptor.property("defaultValue", "");
  private final PropertyDescriptor<String> SIZE = PropertyDescriptor.property("size", SlSize.MEDIUM.getValue());
  private final PropertyDescriptor<Boolean> FILLED = PropertyDescriptor.property("filled", false);
  private final PropertyDescriptor<Boolean> PILL = PropertyDescriptor.property("pill", false);
  private final PropertyDescriptor<String> LABEL = PropertyDescriptor.property("label", "");
  private final PropertyDescriptor<String> HELP_TEXT = PropertyDescriptor.property("helpText", "");
  private final PropertyDescriptor<Boolean> CLEARABLE = PropertyDescriptor.property("clearable", false);
  private final PropertyDescriptor<Boolean> DISABLED = PropertyDescriptor.property("disabled", false);
  private final PropertyDescriptor<String> PLACEHOLDER = PropertyDescriptor.property("placeholder", "");
  private final PropertyDescriptor<Boolean> READONLY = PropertyDescriptor.property("readonly", false);
  private final PropertyDescriptor<Boolean> PASSWORD_TOGGLE = PropertyDescriptor.property("passwordToggle", false);
  private final PropertyDescriptor<Boolean> PASSWORD_VISIBLE = PropertyDescriptor.property("passwordVisible", false);
  private final PropertyDescriptor<Boolean> NO_SPIN_BUTTONS = PropertyDescriptor.property("noSpinButtons", false);
  private final PropertyDescriptor<Boolean> REQUIRED = PropertyDescriptor.property("required", false);
  private final PropertyDescriptor<String> PATTERN = PropertyDescriptor.property("pattern", "");
  private final PropertyDescriptor<Integer> MIN_LENGTH = PropertyDescriptor.property("minlength", 0);
  private final PropertyDescriptor<Integer> MAX_LENGTH = PropertyDescriptor.property("maxlength", 0);
  private final PropertyDescriptor<Integer> MIN = PropertyDescriptor.property("min", 0);
  private final PropertyDescriptor<Integer> MAX = PropertyDescriptor.property("max", 0);
  private final PropertyDescriptor<Integer> STEP = PropertyDescriptor.property("step", 0);
  private final PropertyDescriptor<String> AUTO_CAPITALIZE = PropertyDescriptor.property("autocapitalize", "");
  private final PropertyDescriptor<String> AUTO_CORRECT = PropertyDescriptor.property("autocorrect", "");
  private final PropertyDescriptor<String> AUTO_COMPLETE = PropertyDescriptor.property("autocomplete", "");
  private final PropertyDescriptor<Boolean> AUTO_FOCUS = PropertyDescriptor.property("autofocus", false);
  private final PropertyDescriptor<String> ENTER_KEY_HINT = PropertyDescriptor.property("enterkeyhint", "");
  private final PropertyDescriptor<Boolean> SPELLCHECK = PropertyDescriptor.property("spellcheck", true);
  private final PropertyDescriptor<String> INPUT_MODE = PropertyDescriptor.property("inputmode", "");

  /**
   * Create a new input.
   */
  public SlInput() {
    super();
  }

  /**
   * Create a new input.
   * 
   * @param type the type
   */
  public SlInput(Type type) {
    this();
    setType(type);
  }

  /**
   * Create a new input.
   * 
   * @param value the value
   */
  public SlInput(String value) {
    this();
    setValue(value);
  }

  /**
   * Create a new input.
   * 
   * @param type  the type
   * @param label the label
   */
  public SlInput(Type type, String label) {
    this(type);
    setLabel(label);
  }

  /**
   * Create a new input.
   * 
   * @param type  the type
   * @param label the label
   * @param value the value
   */
  public SlInput(Type type, String label, String value) {
    this(type, label);
    setValue(value);
  }

  /**
   * Set the type of the input.
   * 
   * The type of input. Works the same as a native <input> element, but only a
   * subset of types are supported. Defaults to text.
   * 
   * @param type
   * @return the input
   * @see Type
   */
  public SlInput setType(Type type) {
    set(TYPE, type.getValue());
    return this;
  }

  /**
   * Get the type of the input.
   * 
   * @return the type
   */
  public Type getType() {
    return Type.fromString(get(TYPE));
  }

  /**
   * Set the name of the input.
   * 
   * The name of the control, which is submitted with the form data.
   * 
   * @param name
   * @return the input
   */
  public SlInput setName(String name) {
    set(NAME, name);
    return this;
  }

  /**
   * Get the name of the input.
   * 
   * @return the name
   */
  public String getName() {
    return get(NAME);
  }

  /**
   * Set the value of the input.
   * 
   * The current value of the input, submitted as a name/value pair with form
   * data.
   * 
   * @param value
   * @return the input
   */
  public SlInput setValue(String value) {
    set(VALUE, value);
    return this;
  }

  /**
   * Get the value of the input.
   * 
   * @return the value
   */
  public String getValue() {
    return get(VALUE, true, String.class);
  }

  /**
   * Set the default value of the input.
   * 
   * The default value of the form control. Primarily used for resetting the form
   * control.
   * 
   * @param defaultValue
   * @return the input
   */
  public SlInput setDefaultValue(String defaultValue) {
    set(DEFAULT_VALUE, defaultValue);
    return this;
  }

  /**
   * Get the default value of the input.
   * 
   * @return the default value
   */
  public String getDefaultValue() {
    return get(DEFAULT_VALUE);
  }

  /**
   * Set the size of the input.
   * 
   * The size of the input. Defaults to medium.
   * 
   * @param size
   * @return the input
   * @see SlSize
   */
  public SlInput setSize(SlSize size) {
    set(SIZE, size.getValue());
    return this;
  }

  /**
   * Get the size of the input.
   * 
   * @return the size
   */
  public SlSize getSize() {
    return SlSize.fromString(get(SIZE));
  }

  /**
   * Set the filled state of the input.
   * 
   * Whether the input is filled. Defaults to false.
   * 
   * @param filled
   * @return the input
   */
  public SlInput setFilled(boolean filled) {
    set(FILLED, filled);
    return this;
  }

  /**
   * Get the filled state of the input.
   * 
   * @return the filled state
   */
  public boolean isFilled() {
    return get(FILLED);
  }

  /**
   * Set if the input is drawn with a pill style.
   * 
   * Draws a pill-style input with rounded edges.
   * 
   * @param disabled
   * @return the input
   */
  public SlInput setPill(boolean pill) {
    set(PILL, pill);
    return this;
  }

  /**
   * Get if the input is drawn with a pill style.
   * 
   * @return the pill state
   */
  public boolean isPill() {
    return get(PILL);
  }

  /**
   * Set the help text of the input.
   * 
   * Help text that describes how to use the input.
   * 
   * @param helpText
   * @return the input
   */
  public SlInput setHelpText(String helpText) {
    set(HELP_TEXT, helpText);
    return this;
  }

  /**
   * Get the help text of the input.
   * 
   * @return the help text
   */
  public String getHelpText() {
    return get(HELP_TEXT);
  }

  /**
   * Adds a clear button when the input is not empty.
   * 
   * @param clearable true to add a clear button
   * @return the input
   */
  public SlInput setClearable(boolean clearable) {
    set(CLEARABLE, clearable);
    return this;
  }

  /**
   * Get if the input has a clear button.
   * 
   * @return true if the input has a clear button
   */
  public boolean isClearable() {
    return get(CLEARABLE);
  }

  /**
   * Set the label of the input.
   * 
   * The label of the input.
   * 
   * @param label
   * @return the input
   */
  public SlInput setLabel(String label) {
    set(LABEL, label);
    return this;
  }

  /**
   * Get the label of the input.
   * 
   * @return the label
   */
  public String getLabel() {
    return get(LABEL);
  }

  /**
   * Set the placeholder of the input.
   * 
   * The placeholder text of the input.
   * 
   * @param placeholder
   * @return the input
   */
  public SlInput setPlaceholder(String placeholder) {
    set(PLACEHOLDER, placeholder);
    return this;
  }

  /**
   * Get the placeholder of the input.
   * 
   * @return the placeholder
   */
  public String getPlaceholder() {
    return get(PLACEHOLDER);
  }

  /**
   * Set the disabled state of the input.
   * 
   * Whether the input is disabled. Defaults to false.
   * 
   * @param disabled
   * @return the input
   */
  public SlInput setDisabled(boolean disabled) {
    set(DISABLED, disabled);
    return this;
  }

  /**
   * Get the disabled state of the input.
   * 
   * @return the disabled state
   */
  public boolean isDisabled() {
    return get(DISABLED, true, Boolean.class);
  }

  /**
   * Set the readonly state of the input.
   * 
   * Whether the input is readonly. Defaults to false.
   * 
   * @param readonly
   * @return the input
   */
  public SlInput setReadonly(boolean readonly) {
    set(READONLY, readonly);
    return this;
  }

  /**
   * Get the readonly state of the input.
   * 
   * @return the readonly state
   */
  public boolean isReadonly() {
    return get(READONLY);
  }

  /**
   * Adds a button to toggle the password's visibility. Only applies to password
   * types.
   * 
   * @param passwordToggle true to add a password toggle button
   * @return the input
   */
  public SlInput setPasswordToggle(boolean passwordToggle) {
    set(PASSWORD_TOGGLE, passwordToggle);
    return this;
  }

  /**
   * Get if the input has a password toggle button.
   * 
   * @return true if the input has a password toggle button
   */
  public boolean isPasswordToggle() {
    return get(PASSWORD_TOGGLE);
  }

  /**
   * Determines whether or not the password is currently visible. Only applies to
   * password input types.
   * 
   * @param passwordVisible true if the password is visible
   */
  public SlInput setPasswordVisible(boolean passwordVisible) {
    set(PASSWORD_VISIBLE, passwordVisible);
    return this;
  }

  /**
   * Get if the password is currently visible.
   * 
   * @return true if the password is visible
   */
  public boolean isPasswordVisible() {
    return get(PASSWORD_VISIBLE);
  }

  /**
   * Hides the browser's built-in increment/decrement spin buttons for number
   * inputs.
   * 
   * @param noSpinButtons true to hide the spin buttons
   * @return the input
   */
  public SlInput noSpinButtons(boolean noSpinButtons) {
    set(NO_SPIN_BUTTONS, noSpinButtons);
    return this;
  }

  /**
   * Get if the input has spin buttons.
   * 
   * @return true if the input has spin buttons
   */
  public boolean hasSpinButtons() {
    return !get(NO_SPIN_BUTTONS);
  }

  /**
   * Set the required state of the input.
   * 
   * Whether the input is required. Defaults to false.
   * 
   * @param required
   * @return the input
   */
  public SlInput setRequired(boolean required) {
    set(REQUIRED, required);
    return this;
  }

  /**
   * Get the required state of the input.
   * 
   * @return the required state
   */
  public boolean isRequired() {
    return get(REQUIRED);
  }

  /**
   * Set the pattern of the input.
   * 
   * The pattern to validate the input against.
   * 
   * @param pattern
   * @return the input
   */
  public SlInput setPattern(String pattern) {
    set(PATTERN, pattern);
    return this;
  }

  /**
   * Get the pattern of the input.
   * 
   * @return the pattern
   */
  public String getPattern() {
    return get(PATTERN);
  }

  /**
   * Set the minimum length of input that will be considered valid.
   * 
   * @param minLength the minimum length
   * @return the input
   */
  public SlInput setMinLength(int minLength) {
    set(MIN_LENGTH, minLength);
    return this;
  }

  /**
   * Get the minimum length of input that will be considered valid.
   * 
   * @return the minimum length
   */
  public int getMinLength() {
    return get(MIN_LENGTH);
  }

  /**
   * Set the maximum length of input that will be considered valid.
   * 
   * @param maxLength the maximum length
   * @return the input
   */
  public SlInput setMaxLength(int maxLength) {
    set(MAX_LENGTH, maxLength);
    return this;
  }

  /**
   * Get the maximum length of input that will be considered valid.
   * 
   * @return the maximum length
   */
  public int getMaxLength() {
    return get(MAX_LENGTH);
  }

  /**
   * Set the minimum value of input that will be considered valid.
   * 
   * @param min the minimum value
   * @return the input
   */
  public SlInput setMin(int min) {
    set(MIN, min);
    return this;
  }

  /**
   * Get the minimum value of input that will be considered valid.
   * 
   * @return the minimum value
   */
  public int getMin() {
    return get(MIN);
  }

  /**
   * Set the maximum value of input that will be considered valid.
   * 
   * @param max the maximum value
   * @return the input
   */
  public SlInput setMax(int max) {
    set(MAX, max);
    return this;
  }

  /**
   * Get the maximum value of input that will be considered valid.
   * 
   * @return the maximum value
   */
  public int getMax() {
    return get(MAX);
  }

  /**
   * Set the step value of input that will be considered valid.
   * 
   * @param step the step value
   * @return the input
   */
  public SlInput setStep(int step) {
    set(STEP, step);
    return this;
  }

  /**
   * Get the step value of input that will be considered valid.
   * 
   * @return the step value
   */
  public int getStep() {
    return get(STEP);
  }

  /**
   * Set the auto capitalize state of the input.
   * 
   * Controls whether and how text input is automatically capitalized as it is
   * entered by the user.
   * 
   * @param autoCapitalize
   * @return the input
   */
  public SlInput setAutoCapitalize(AutoCapitalize autoCapitalize) {
    set(AUTO_CAPITALIZE, autoCapitalize.getValue());
    return this;
  }

  /**
   * Get the auto capitalize state of the input.
   * 
   * @return the auto capitalize state
   */
  public AutoCapitalize getAutoCapitalize() {
    return AutoCapitalize.fromString(get(AUTO_CAPITALIZE));
  }

  /**
   * Set the auto complete state of the input.
   * 
   * Specifies what permission the browser has to provide assistance in filling
   * out form field values. Refer to this page on MDN for available values.
   * 
   * @param autoComplete
   * @return the input
   */
  public SlInput setAutoComplete(String autoComplete) {
    set(AUTO_COMPLETE, autoComplete);
    return this;
  }

  /**
   * Get the auto complete state of the input.
   * 
   * @return the auto complete state
   */
  public String getAutoComplete() {
    return get(AUTO_COMPLETE);
  }

  // public SlInput setAutoCo

  /**
   * Set the autofocus state of the input.
   * 
   * Whether the input is autofocus. Defaults to false.
   * 
   * @param autofocus
   * @return the input
   */
  public SlInput setAutoFocus(boolean autofocus) {
    set(AUTO_FOCUS, autofocus);
    return this;
  }

  /**
   * Get the autofocus state of the input.
   * 
   * @return the autofocus state
   */
  public boolean isAutoFocus() {
    return get(AUTO_FOCUS);
  }

  /**
   * Set the auto correct state of the input.
   * 
   * Indicates whether the browser's autocorrect feature is on or off.
   * 
   * @param autoCorrect
   * @return the input
   */
  public SlInput setAutoCorrect(boolean autoCorrect) {
    set(AUTO_CORRECT, autoCorrect ? "on" : "off");
    return this;
  }

  /**
   * Get the auto correct state of the input.
   * 
   * @return the auto correct state
   */
  public boolean isAutoCorrect() {
    return "on".equals(get(AUTO_CORRECT));
  }

  /**
   * Set the auto complete state of the input.
   * 
   * Specifies what permission the browser has to provide assistance in filling
   * out form field values. Refer to this page on MDN for available values.
   * 
   * @param autoComplete
   * @return the input
   */
  public SlInput setAutoComplete(boolean autoComplete) {
    set(AUTO_COMPLETE, autoComplete ? "on" : "off");
    return this;
  }

  /**
   * Get the auto complete state of the input.
   * 
   * @return the auto complete state
   */
  public boolean isAutoComplete() {
    return "on".equals(get(AUTO_COMPLETE));
  }

  /**
   * Used to customize the label or icon of the Enter key on virtual keyboards.
   * 
   * @param autoSave
   * @return the input
   */
  public SlInput setEnterKeyHint(EnterKeyHint enterKeyHint) {
    set(ENTER_KEY_HINT, enterKeyHint.getValue());
    return this;
  }

  /**
   * Get the enter key hint state of the input.
   * 
   * @return the auto save state
   */
  public EnterKeyHint getEnterKeyHint() {
    return EnterKeyHint.fromString(get(ENTER_KEY_HINT));
  }

  /**
   * Enables spell checking on the input.
   * 
   * @param spellCheck true to enable spell checking
   * @return the input
   */
  public SlInput setSpellCheck(boolean spellCheck) {
    set(SPELLCHECK, spellCheck);
    return this;
  }

  /**
   * Get the spell check state of the input.
   * 
   * @return the spell check state
   */
  public boolean isSpellCheck() {
    return get(SPELLCHECK);
  }

  /**
   * Set the input mode.
   * 
   * @param mode the input mode
   * @return the input
   */
  public SlInput setInputMode(InputMode mode) {
    set(INPUT_MODE, mode.getValue());
    return this;
  }

  /**
   * Get the input mode.
   * 
   * @return the input mode
   */
  public InputMode getInputMode() {
    return InputMode.fromString(get(INPUT_MODE));
  }

  /**
   * Set the prefix of the input.
   * 
   * @param prefix the prefix
   * @return the input
   */
  public SlInput setPrefix(String prefix) {
    addRawSlot("prefix", prefix);
    return this;
  }

  /**
   * Get the prefix of the input.
   * 
   * @return the prefix
   */
  public String getPrefix() {
    return getRawSlot("prefix");
  }

  /**
   * Set the suffix of the input.
   * 
   * @param suffix the suffix
   * @return the input
   */
  public SlInput setSuffix(String suffix) {
    addRawSlot("suffix", suffix);
    return this;
  }

  /**
   * Get the suffix of the input.
   * 
   * @return the suffix
   */
  public String getSuffix() {
    return getRawSlot("suffix");
  }

  /**
   * Clear the input.
   * 
   * @return the input
   */
  public SlInput clear() {
    setValue("");
    return this;
  }

  /**
   * Focus the input.
   * 
   * @return the input
   */
  public SlInput focus() {
    invokeAsync("focus");
    return this;
  }

  /**
   * Blur the input.
   * 
   * @return the input
   */
  public SlInput blur() {
    invokeAsync("blur");
    return this;
  }

  /**
   * Select the input.
   * 
   * @return the input
   */
  public SlInput select() {
    invokeAsync("select");
    return this;
  }

  /**
   * Set the selection range of the input.
   * 
   * @param start     the start index
   * @param end       the end index
   * @param direction the selection direction
   * 
   * @return the input
   */
  public SlInput setSelectionRange(int start, int end, SelectionDirection direction) {
    invokeAsync("setSelectionRange", start, end, direction.getValue());
    return this;
  }

  /**
   * Set the selection range of the input.
   * 
   * @param start the start index
   * @param end   the end index
   * 
   * @return the input
   */
  public SlInput setSelectionRange(int start, int end) {
    setSelectionRange(start, end, SelectionDirection.NONE);
    return this;
  }

  /**
   * Set the selection range of the input.
   * 
   * @param start the start index
   * 
   * @return the input
   */
  public SlInput setSelectionRange(int start) {
    setSelectionRange(start, getValue().length());
    return this;
  }

  /**
   * Displays the browser picker for an input element (only works if the browser
   * supports it for the input type).
   * 
   * @return the input
   */
  public SlInput showPicker() {
    invokeAsync("showPicker");
    return this;
  }

  /**
   * Increments the value of a numeric input type by the value of the step
   * attribute.
   * 
   * @return the input
   */
  public SlInput stepUp() {
    invokeAsync("stepUp");
    return this;
  }

  /**
   * Decrements the value of a numeric input type by the value of the step
   * attribute.
   * 
   * @return the input
   */
  public SlInput stepDown() {
    invokeAsync("stepDown");
    return this;
  }

  /**
   * Check the validity of the component.
   * 
   * @return the checkbox
   */
  public SlInput checkValidity() {
    invokeAsync("checkValidity");
    return this;
  }

  /**
   * Report the validity of the component.
   * 
   * @return the input
   */
  public boolean reportValidity() {
    String result = (String) invoke("reportValidity");
    return Boolean.parseBoolean(result);
  }

  /**
   * Set the custom validity of the button.
   * 
   * @param message
   * @return the input
   */
  public SlInput setCustomValidity(String message) {
    invokeAsync("setCustomValidity", message);
    return this;
  }

  /**
   * Add a focus listener to the input.
   * 
   * @param listener The listener to add.
   * @return the input
   */
  public SlInput addFocusListener(EventListener<SlInputFocusEvent> listener) {
    addEventListener(SlInputFocusEvent.class, listener);
    return this;
  }

  /**
   * Remove a focus listener from the input.
   * 
   * @param listener The listener to remove.
   * @return the input
   */
  public SlInput removeFocusListener(EventListener<SlInputFocusEvent> listener) {
    removeEventListener(SlInputFocusEvent.class, listener);
    return this;
  }

  /**
   * Add a blur listener to the input.
   * 
   * @param listener The listener to add.
   * @return the input
   */
  public SlInput addBlurListener(EventListener<SlInputBlurEvent> listener) {
    addEventListener(SlInputBlurEvent.class, listener);
    return this;
  }

  /**
   * Remove a blur listener from the input.
   * 
   * @param listener The listener to remove.
   * @return the input
   */
  public SlInput removeBlurListener(EventListener<SlInputBlurEvent> listener) {
    removeEventListener(SlInputBlurEvent.class, listener);
    return this;
  }

  /**
   * Add a change listener to the input.
   * 
   * @param listener The listener to add.
   * @return the input
   */
  public SlInput addChangeListener(EventListener<SlInputChangeEvent> listener) {
    addEventListener(SlInputChangeEvent.class, listener);
    return this;
  }

  /**
   * Remove a change listener from the input.
   * 
   * @param listener The listener to remove.
   * @return the input
   */
  public SlInput removeChangeListener(EventListener<SlInputChangeEvent> listener) {
    removeEventListener(SlInputChangeEvent.class, listener);
    return this;
  }

  /**
   * Add a modified listener to the input.
   * 
   * @param listener The listener to add.
   * @return the input
   */
  public SlInput addModifiedListener(EventListener<SlInputModifiedEvent> listener) {
    addEventListener(SlInputModifiedEvent.class, listener);
    return this;
  }

  /**
   * Remove a modified listener from the input.
   * 
   * @param listener The listener to remove.
   * @return the input
   */
  public SlInput removeModifiedListener(EventListener<SlInputModifiedEvent> listener) {
    removeEventListener(SlInputModifiedEvent.class, listener);
    return this;
  }

  /**
   * Add a clear listener to the input.
   * 
   * @param listener The listener to add.
   * @return the input
   */
  public SlInput addClearListener(EventListener<SlInputClearEvent> listener) {
    addEventListener(SlInputClearEvent.class, listener);
    return this;
  }

  /**
   * Remove a clear listener from the input.
   * 
   * @param listener The listener to remove.
   * @return the input
   */
  public SlInput removeClearListener(EventListener<SlInputClearEvent> listener) {
    removeEventListener(SlInputClearEvent.class, listener);
    return this;
  }
}
