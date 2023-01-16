package org.demo.shoelace.button;

import org.demo.shoelace.SlComponent;
import org.demo.shoelace.badge.SlBadge;
import org.demo.shoelace.button.events.SlButtonBlurEvent;
import org.demo.shoelace.button.events.SlButtonClickEvent;
import org.demo.shoelace.button.events.SlButtonFocusEvent;
import org.dwcj.App;
import org.dwcj.annotations.InlineStyleSheet;
import org.dwcj.interfaces.HasControlText;
import org.dwcj.webcomponent.PropertyDescriptor;
import org.dwcj.webcomponent.annotations.NodeName;
import org.dwcj.webcomponent.events.EventListener;

/**
 * SlButton component.
 * 
 * @see <a href="https://shoelace.style/components/button">Shoelace Button</a>
 * @author Hyyan Abo Fakher
 * @since 1.0.0
 */
@NodeName("sl-button")
@InlineStyleSheet(id = "sl-button-styles", once = true, top = false, value = "" +
    "[sl-button] [slot='prefix']," +
    "[sl-button] [slot='suffix']{" +
    " display: flex" +
    "}" +
    "[sl-button] [sl-badge]{" +
    " position: absolute;" +
    " top: 0;" +
    " right: 0;" +
    " translate: 50% -50%;" +
    " pointer-events: none;" +
    "}")
public final class SlButton extends SlComponent<SlButton> implements HasControlText {

  /**
   * The button's theme variant.
   */
  public enum Variant {
    DEFAULT("default"),
    PRIMARY("primary"),
    SUCCESS("success"),
    NEUTRAL("neutral"),
    WARNING("warning"),
    DANGER("danger"),
    TEXT("text");

    private final String value;

    Variant(String value) {
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

  /**
   * The button's size.
   */
  public enum Size {
    SMALL("small"),
    MEDIUM("medium"),
    LARGE("large");

    private final String value;

    Size(String value) {
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

  /**
   * The type of button.
   */
  public enum Type {
    BUTTON("button"),
    SUBMIT("submit"),
    RESET("reset");

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
  }

  /**
   * Tells the browser where to open the link.
   */
  public enum Target {
    BLANK("_blank"),
    PARENT("_parent"),
    SELF("_self"),
    TOP("_top");

    private final String value;

    Target(String value) {
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

  /**
   * Used to override the form owner's target attribute.
   */
  public enum FormTarget {
    SELF("_self"),
    BLANK("_blank"),
    PARENT("_parent"),
    TOP("_top");

    private final String value;

    FormTarget(String value) {
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

  // The badge component uuid
  String badgeId = null;

  // Properties
  private static PropertyDescriptor<String> VARIANT = PropertyDescriptor.property("variant",
      Variant.DEFAULT.getValue());
  private static PropertyDescriptor<String> SIZE = PropertyDescriptor.property("size", Size.MEDIUM.getValue());
  private static PropertyDescriptor<Boolean> CARET = PropertyDescriptor.property("caret", false);
  private static PropertyDescriptor<Boolean> DISABLED = PropertyDescriptor.property("disabled", false);
  private static PropertyDescriptor<Boolean> LOADING = PropertyDescriptor.property("loading", false);
  private static PropertyDescriptor<Boolean> OUTLINE = PropertyDescriptor.property("outline", false);
  private static PropertyDescriptor<Boolean> PILL = PropertyDescriptor.property("pill", false);
  private static PropertyDescriptor<Boolean> CIRCLE = PropertyDescriptor.property("circle", false);
  private static PropertyDescriptor<String> TYPE = PropertyDescriptor.property("type", Type.BUTTON.getValue());
  private static PropertyDescriptor<String> NAME = PropertyDescriptor.property("name", "");
  private static PropertyDescriptor<String> VALUE = PropertyDescriptor.property("value", "");
  private static PropertyDescriptor<String> HREF = PropertyDescriptor.property("href", "");
  private static PropertyDescriptor<String> TARGET = PropertyDescriptor.property("target", "");
  private static PropertyDescriptor<String> DOWNLOAD = PropertyDescriptor.property("download", "");
  private static PropertyDescriptor<String> FORM = PropertyDescriptor.property("form", "");
  private static PropertyDescriptor<String> FORM_ACTION = PropertyDescriptor.property("formAction", "");
  private static PropertyDescriptor<String> FORM_ENCTYPE = PropertyDescriptor.property("formEnctype", "");
  private static PropertyDescriptor<String> FORM_METHOD = PropertyDescriptor.property("formMethod", "");
  private static PropertyDescriptor<Boolean> FORM_NO_VALIDATE = PropertyDescriptor.property("formNoValidate", false);
  private static PropertyDescriptor<String> FORM_TARGET = PropertyDescriptor.property("formTarget", "");
  private static PropertyDescriptor<Boolean> UPDATE_COMPLETE = PropertyDescriptor.property("updateComplete", false);

  /**
   * Create a button.
   */
  public SlButton() {
    super();
  }

  /**
   * Create a button with the given text.
   * 
   * @param text the text
   */
  public SlButton(String text) {
    super();
    this.setText(text);
  }

  /**
   * Set the variant of the button. The variant changes the appearance of the
   * button. Available variants are: default, primary, success, neutral, warning,
   * danger, and text.
   * 
   * @param variant
   * @return the button
   * @see Variant
   */
  public SlButton setVariant(Variant variant) {
    set(VARIANT, variant.getValue());
    return this;
  }

  /**
   * Get the variant of the button. The variant changes the appearance of the
   * button. Available variants are: default, primary, success, neutral, warning,
   * danger, and text.
   * 
   * @return the variant
   * @see Variant
   */
  public Variant getVariant() {
    return Variant.valueOf(get(VARIANT));
  }

  /**
   * Set the size of the button. Available sizes are: small, medium, and large.
   * 
   * @param size
   * @return the button
   * @see Size
   */
  public SlButton setSize(Size size) {
    set(SIZE, size.getValue());
    return this;
  }

  /**
   * Get the size of the button. Available sizes are: small, medium, and large.
   * 
   * @return the size
   * @see Size
   */
  public Size getSize() {
    return Size.valueOf(get(SIZE));
  }

  /**
   * Set whether the button should display a caret. Carets are used to indicate
   * that the button triggers a dropdown menu.
   * 
   * @param caret
   * @return the button
   */
  public SlButton setCaret(boolean caret) {
    set(CARET, caret);
    return this;
  }

  /**
   * Get whether the button should display a caret. Carets are used to indicate
   * that the button triggers a dropdown menu.
   * 
   * @return true if the button should display a caret
   */
  public boolean isCaret() {
    return get(CARET);
  }

  /**
   * Set whether the button should be disabled.
   * 
   * @param disabled
   * @return the button
   */
  public SlButton setDisabled(boolean disabled) {
    set(DISABLED, disabled);
    return this;
  }

  /**
   * Get whether the button should be disabled.
   * 
   * @return true if the button should be disabled
   */
  public boolean isDisabled() {
    return get(DISABLED, true, Boolean.class);
  }

  /**
   * Set whether the button should display a loading spinner.
   * 
   * @param outline
   * @return the button
   */
  public SlButton setLoading(boolean loading) {
    set(LOADING, loading);
    return this;
  }

  /**
   * Get whether the button should display a loading spinner.
   * 
   * @return true if the button should display a loading spinner
   */
  public boolean isLoading() {
    return get(LOADING, true, Boolean.class);
  }

  /**
   * Set whether the button should be outlined.
   * 
   * @param outline
   * @return the button
   */
  public SlButton setOutline(boolean outline) {
    set(OUTLINE, outline);
    return this;
  }

  /**
   * Get whether the button should be outlined.
   * 
   * @return true if the button should be outlined
   */
  public boolean isOutline() {
    return get(OUTLINE);
  }

  /**
   * Set whether the button should be a pill.
   * 
   * @param pill
   * @return the button
   */
  public SlButton setPill(boolean pill) {
    set(PILL, pill);
    return this;
  }

  /**
   * Get whether the button should be a pill.
   * 
   * @return true if the button should be a pill
   */
  public boolean isPill() {
    return get(PILL);
  }

  /**
   * Set whether the button should be a circle.
   * 
   * @param circle
   * @return the button
   */
  public SlButton setCircle(boolean circle) {
    set(CIRCLE, circle);
    return this;
  }

  /**
   * Get whether the button should be a circle.
   * 
   * @return true if the button should be a circle
   */
  public boolean isCircle() {
    return get(CIRCLE);
  }

  /**
   * Set the type of the button. Available types are: button, reset, and submit.
   * 
   * Note that the default value is button instead of submit, which is opposite of
   * how native <button> elements behave. When the type is submit, the button will
   * submit the surrounding form.
   * 
   * 
   * @param type
   * @return the button
   * @see Type
   */
  public SlButton setType(Type type) {
    set(TYPE, type.getValue());
    return this;
  }

  /**
   * Get the type of the button. Available types are: button, reset, and submit.
   * 
   * @return the type
   * @see Type
   */
  public Type getType() {
    return Type.valueOf(get(TYPE));
  }

  /**
   * Set the name of the button.
   * 
   * The name of the button, submitted as a name/value pair with form data, but
   * only when this button is the submitter. This attribute is ignored when href
   * is present.
   * 
   * @param name
   * @return the button
   */
  public SlButton setName(String name) {
    set(NAME, name);
    return this;
  }

  /**
   * Get the name of the button.
   * 
   * @return the name
   */
  public String getName() {
    return get(NAME);
  }

  /**
   * Set the value of the button.
   * 
   * The value of the button, submitted as a pair with the button's name as part
   * of the form data, but only when this button is the submitter. This attribute
   * is ignored when href is present.
   * 
   * @param value
   * @return the button
   */
  public SlButton setValue(String value) {
    set(VALUE, value);
    return this;
  }

  /**
   * Get the value of the button.
   * 
   * @return the value
   */
  public String getValue() {
    return get(VALUE);
  }

  /**
   * Set the href of the button.
   * 
   * When set, the underlying button will be rendered as an <a> with this href
   * instead of a <button>.
   * 
   * @param href
   * @return the button
   */
  public SlButton setHref(String href) {
    set(HREF, href);
    return this;
  }

  /**
   * Get the href of the button.
   * 
   * @return the href
   */
  public String getHref() {
    return get(HREF);
  }

  /**
   * Set the target of the button.
   * 
   * Tells the browser where to open the link. Only used when href is present.
   * 
   * @param target
   * @return the button
   */
  public SlButton setTarget(String target) {
    set(TARGET, target);
    return this;
  }

  /**
   * Get the target of the button.
   * 
   * @return the target
   */
  public String getTarget() {
    return get(TARGET);
  }

  /**
   * Set the download of the button.
   * 
   * Tells the browser to download the linked file as this filename. Only used
   * when href is present.
   * 
   * @param download
   * @return the button
   */
  public SlButton setDownload(String download) {
    set(DOWNLOAD, download);
    return this;
  }

  /**
   * Get the download of the button.
   * 
   * @return the download
   */
  public String getDownload() {
    return get(DOWNLOAD);
  }

  /**
   * Set the form of the button.
   * 
   * The "form owner" to associate the button with. If omitted, the closest
   * containing form will be used instead. The value of this attribute must be an
   * id of a form in the same document or shadow root as the button.
   * 
   * @param form
   * @return the button
   */
  public SlButton setForm(String form) {
    set(FORM, form);
    return this;
  }

  /**
   * Get the form of the button.
   * 
   * @return the form
   */
  public String getForm() {
    return get(FORM);
  }

  /**
   * Set the form action of the button.
   * 
   * Used to override the form owner's action attribute.
   * 
   * @param formAction
   * @return the button
   */
  public SlButton setFormAction(String formAction) {
    set(FORM_ACTION, formAction);
    return this;
  }

  /**
   * Get the form action of the button.
   * 
   * @return the form action
   */
  public String getFormAction() {
    return get(FORM_ACTION);
  }

  /**
   * Set the form method of the button.
   * 
   * Used to override the form owner's method attribute.
   * 
   * @param formMethod
   * @return the button
   */
  public SlButton setFormMethod(String formMethod) {
    set(FORM_METHOD, formMethod);
    return this;
  }

  /**
   * Get the form method of the button.
   * 
   * @return the form method
   */
  public String getFormMethod() {
    return get(FORM_METHOD);
  }

  /**
   * Set the form target of the button.
   * 
   * Used to override the form owner's target attribute.
   * 
   * @param formTarget
   * @return the button
   */
  public SlButton setFormTarget(String formTarget) {
    set(FORM_TARGET, formTarget);
    return this;
  }

  /**
   * Get the form target of the button.
   * 
   * @return the form target
   */
  public String getFormTarget() {
    return get(FORM_TARGET);
  }

  /**
   * Set the form no validate of the button.
   * 
   * Used to override the form owner's novalidate attribute.
   * 
   * @param formNoValidate
   * @return the button
   */
  public SlButton setFormNoValidate(boolean formNoValidate) {
    set(FORM_NO_VALIDATE, formNoValidate);
    return this;
  }

  /**
   * Get the form no validate of the button.
   * 
   * @return the form no validate
   */
  public boolean isFormNoValidate() {
    return get(FORM_NO_VALIDATE);
  }

  /**
   * Set the form enctype of the button.
   * 
   * Used to override the form owner's enctype attribute.
   * 
   * @param formEnctype
   * @return the button
   */
  public SlButton setFormEnctype(String formEnctype) {
    set(FORM_ENCTYPE, formEnctype);
    return this;
  }

  /**
   * Get the form enctype of the button.
   * 
   * @return the form enctype
   */
  public String getFormEnctype() {
    return get(FORM_ENCTYPE);
  }

  /**
   * Check if the button is a form submit button.
   * 
   * A read-only promise that resolves when the component has finished updating.
   * 
   * @param formAutocomplete
   * @return the button
   */
  public Boolean isFormUpdateComplete() {
    return get(UPDATE_COMPLETE, true, Boolean.class);
  }

  /**
   * Set the text of the button.
   * 
   * @param text the text
   * @return the button
   */
  public SlButton setText(String text) {
    addRawSlot(text);
    return this;
  }

  /**
   * Get the text of the button.
   * 
   * @return the text
   */
  public String getText() {
    return getRawSlot();
  }

  /**
   * Set the prefix of the button.
   * 
   * @param prefix the prefix
   * @return the button
   */
  public SlButton setPrefix(String prefix) {
    addRawSlot("prefix", prefix);
    return this;
  }

  /**
   * Get the prefix of the button.
   * 
   * @return the prefix
   */
  public String getPrefix() {
    return getRawSlot("prefix");
  }

  /**
   * Set the suffix of the button.
   * 
   * @param suffix the suffix
   * @return the button
   */
  public SlButton setSuffix(String suffix) {
    addRawSlot("suffix", suffix);
    return this;
  }

  /**
   * Get the suffix of the button.
   * 
   * @return the suffix
   */
  public String getSuffix() {
    return getRawSlot("suffix");
  }

  /**
   * Set the badge of the button.
   * 
   * @param badge the badge
   * @return the button
   */
  public SlButton setBadge(SlBadge badge) {
    if (badge == null) {
      if (this.badgeId != null) {
        removeControl(this.badgeId);
        this.badgeId = null;
      }
    } else {
      this.badgeId = addControl(badge);
    }

    return this;
  }

  /**
   * Get the badge of the button.
   * 
   * @return the badge
   */
  public SlBadge getBadge() {
    return (SlBadge) getControl(this.badgeId);
  }

  /**
   * Click the button.
   * 
   * @return the button
   */
  public SlButton click() {
    invokeAsync("click");
    return this;
  }

  /**
   * Focus the button.
   * 
   * @return the button
   */
  public SlButton focus() {
    invokeAsync("focus");
    return this;
  }

  /**
   * Blur the button.
   * 
   * @return the button
   */
  public SlButton blur() {
    invokeAsync("blur");
    return this;
  }

  /**
   * Check the validity of the button.
   * 
   * @return the button
   */
  public SlButton checkValidity() {
    invokeAsync("checkValidity");
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
  public SlButton setCustomValidity(String message) {
    invokeAsync("setCustomValidity", message);
    return this;
  }

  /**
   * Add a click listener to the button.
   * 
   * @param listener
   * @return the button
   */
  public SlButton addClickListener(EventListener<SlButtonClickEvent> listener) {
    addWebComponentEventListener("click", SlButtonClickEvent.class, listener);
    return this;
  }

  /**
   * Remove a click listener from the button.
   * 
   * @param listener
   * @return the button
   */
  public SlButton removeClickListener(EventListener<SlButtonClickEvent> listener) {
    removeWebComponentEventListener("click", SlButtonClickEvent.class, listener);
    return this;
  }

  /**
   * Add a focus listener to the button.
   * 
   * @param listener
   * @return the button
   */
  public SlButton addFocusListener(EventListener<SlButtonFocusEvent> listener) {
    addWebComponentEventListener("sl-focus", SlButtonFocusEvent.class, listener);
    return this;
  }

  /**
   * Remove a focus listener from the button.
   * 
   * @param listener
   * @return the button
   */
  public SlButton removeFocusListener(EventListener<SlButtonFocusEvent> listener) {
    removeWebComponentEventListener("sl-focus", SlButtonFocusEvent.class, listener);
    return this;
  }

  /**
   * Add a blur listener to the button.
   * 
   * @param listener
   * @return the button
   */
  public SlButton addBlurListener(EventListener<SlButtonBlurEvent> listener) {
    addWebComponentEventListener("sl-blur", SlButtonBlurEvent.class, listener);
    return this;
  }

  /**
   * Remove a blur listener from the button.
   * 
   * @param listener
   * @return the button
   */
  public SlButton removeBlurListener(EventListener<SlButtonBlurEvent> listener) {
    removeWebComponentEventListener("sl-blur", SlButtonBlurEvent.class, listener);
    return this;
  }
}
