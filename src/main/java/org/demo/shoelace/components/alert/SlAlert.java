package org.demo.shoelace.components.alert;

import org.demo.shoelace.components.SlComponent;
import org.demo.shoelace.components.alert.events.SlAlertCloseEvent;
import org.demo.shoelace.components.alert.events.SlAlertOpenEvent;
import org.dwcj.interfaces.HasControlText;
import org.dwcj.webcomponent.PropertyDescriptor;
import org.dwcj.webcomponent.annotations.NodeName;
import org.dwcj.webcomponent.events.EventListener;

/**
 * The input component.
 * 
 * @see <a href="https://shoelace.style/components/alert">Shoelace Alert</a>
 * @author Hyyan Abo Fakher
 * @since 1.0.0
 */
@NodeName("sl-alert")
public final class SlAlert extends SlComponent implements HasControlText {

  /**
   * The alert's theme variant.
   */
  public enum Variant {
    PRIMARY("primary"),
    SUCCESS("success"),
    NEUTRAL("neutral"),
    WARNING("warning"),
    DANGER("danger");

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

    /**
     * @param value the value as string
     * @return the variant
     */
    public static Variant fromString(String value) {
      for (Variant variant : Variant.values()) {
        if (variant.value.equals(value)) {
          return variant;
        }
      }
      return null;
    }
  }

  // Properties
  private final PropertyDescriptor<Boolean> OPEN = PropertyDescriptor.property("open", false);
  private final PropertyDescriptor<Boolean> CLOSABLE = PropertyDescriptor.property("closable", false);
  private final PropertyDescriptor<String> VARIANT = PropertyDescriptor.property("variant",
      Variant.PRIMARY.getValue());
  private final PropertyDescriptor<Integer> DURATION = PropertyDescriptor.property("duration", Integer.MAX_VALUE);

  /**
   * Create a new alert.
   */
  public SlAlert() {
    super();
  }

  /**
   * Create a new alert with the given label.
   * 
   * @param text the text
   */
  public SlAlert(String text) {
    this();
    this.setText(text);
  }

  /**
   * Open the alert
   * 
   * @return the alert
   */
  public SlAlert open() {
    set(OPEN, true);
    return this;
  }

  /**
   * Check if the alert is open
   * 
   * @return true if open
   */
  public boolean isOpen() {
    return get(OPEN, true, Boolean.class);
  }

  /**
   * Close the alert
   * 
   * @return the alert
   */
  public SlAlert close() {
    set(OPEN, false);
    return this;
  }

  /**
   * Set the alert's closable state.
   * 
   * Enables a close button that allows the user to dismiss the alert.
   * 
   * @param closable true if closable
   * @return the alert
   */
  public SlAlert setClosable(boolean closable) {
    set(CLOSABLE, closable);
    return this;
  }

  /**
   * Check if the alert is closable.
   * 
   * @return true if closable
   */
  public boolean isClosable() {
    return get(CLOSABLE, false, Boolean.class);
  }

  /**
   * Set the alert's variant.
   * 
   * @param variant the variant
   * @return the alert
   */
  public SlAlert setVariant(Variant variant) {
    set(VARIANT, variant.getValue());
    return this;
  }

  /**
   * Get the alert's variant.
   * 
   * @return the variant
   */
  public Variant getVariant() {
    return Variant.fromString(get(VARIANT));
  }

  /**
   * Set the alert's duration.
   * 
   * The length of time, in milliseconds, the alert will show before closing
   * itself. If the user interacts with the alert before it closes (e.g. moves the
   * mouse over it), the timer will restart. Defaults to Infinity, meaning the
   * alert will not close on its own.
   * 
   * @param duration the duration
   * @return the alert
   */
  public SlAlert setDuration(int duration) {
    set(DURATION, duration);
    return this;
  }

  /**
   * Get the alert's duration.
   * 
   * @return the duration
   */
  public int getDuration() {
    return get(DURATION);
  }

  /**
   * {`@inheritDoc`}
   */
  public SlAlert setText(String text) {
    addRawSlot(text);
    return this;
  }

  /**
   * {`@inheritDoc`}
   */
  public String getText() {
    return getRawSlot();
  }

  /**
   * Displays the alert as a toast notification. This will move the alert out of
   * its position in the DOM and, when dismissed, it will be removed from the DOM
   * completely. By storing a reference to the alert, you can reuse it by calling
   * this method again. The returned promise will resolve after the alert is
   * hidden.
   * 
   * @return the alert
   */
  public SlAlert toast() {
    callAsyncFunction("toast");
    return this;
  }

  /**
   * Set the prefix slot
   * 
   * @param prefix the prefix
   * @return the alert
   */
  public SlAlert setPrefix(String prefix) {
    addRawSlot("icon", prefix);
    return this;
  }

  /**
   * Get the prefix slot
   * 
   * @return the prefix
   */
  public String getPrefix() {
    return getRawSlot("icon");
  }

  /**
   * Add a listener for the open event.
   * 
   * @param listener the listener
   * @return the alert
   */
  public SlAlert addOpenListener(EventListener<SlAlertOpenEvent> listener) {
    addEventListener(SlAlertOpenEvent.class, listener);
    return this;
  }

  /**
   * Remove a listener for the open event.
   * 
   * @param listener the listener
   * @return the alert
   */
  public SlAlert removeOpenListener(EventListener<SlAlertOpenEvent> listener) {
    removeEventListener(SlAlertOpenEvent.class, listener);
    return this;
  }

  /**
   * Add a listener for the close event.
   * 
   * @param listener the listener
   * @return the alert
   */
  public SlAlert addCloseListener(EventListener<SlAlertCloseEvent> listener) {
    addEventListener(SlAlertCloseEvent.class, listener);
    return this;
  }

  /**
   * Remove a listener for the close event.
   * 
   * @param listener the listener
   * @return the alert
   */
  public SlAlert removeCloseListener(EventListener<SlAlertCloseEvent> listener) {
    removeEventListener(SlAlertCloseEvent.class, listener);
    return this;
  }
}
