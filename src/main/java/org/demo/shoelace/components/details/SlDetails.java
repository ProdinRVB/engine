package org.demo.shoelace.components.details;

import org.demo.shoelace.components.SlComponent;
import org.demo.shoelace.components.details.events.SlDetailsCloseEvent;
import org.demo.shoelace.components.details.events.SlDetailsOpenEvent;
import org.dwcj.interfaces.HasControlText;
import org.dwcj.webcomponent.PropertyDescriptor;
import org.dwcj.webcomponent.annotations.NodeName;
import org.dwcj.webcomponent.events.EventListener;

/**
 * SlDetails component.
 * 
 * @see <a href="https://shoelace.style/components/Shoelace Details">Shoelace
 * @author Hyyan Abo Fakher
 * @since 1.0.0
 */
@NodeName("sl-details")
public final class SlDetails extends SlComponent implements HasControlText {

  private final PropertyDescriptor<Boolean> OPEN = PropertyDescriptor.property("open", false);
  private final PropertyDescriptor<String> SUMMARY = PropertyDescriptor.property("summary", "");
  private final PropertyDescriptor<Boolean> DISABLED = PropertyDescriptor.property("disabled", false);

  /**
   * Creates a new SlDetails component.
   * 
   * @param summary the summary
   * @param text    the text
   */
  public SlDetails(String summary, String text) {
    super();
    setSummary(summary);
    setText(text);
  }

  /**
   * Creates a new SlDetails component.
   */
  public SlDetails() {
    this("", "");
  }

  /**
   * Set the summary to show in the header.
   * 
   * @param summary the summary
   * @return details
   */
  public SlDetails setSummary(String summary) {
    set(SUMMARY, summary);
    return this;
  }

  /**
   * Get the summary to show in the header.
   * 
   * @return the summary
   */
  public String getSummary() {
    return get(SUMMARY);
  }

  /**
   * Open the details.
   * 
   * @return details
   */
  public SlDetails open() {
    set(OPEN, true);
    return this;
  }

  /**
   * Check if the details is open.
   * 
   * @return true, if is open
   */
  public boolean isOpen() {
    return get(OPEN, true, Boolean.class);
  }

  /**
   * Close the details.
   * 
   * @return details
   */
  public SlDetails close() {
    set(OPEN, false);
    return this;
  }

  /**
   * Toggle the details.
   * 
   * @return details
   */
  public SlDetails toggle() {
    set(OPEN, !isOpen());
    return this;
  }

  /**
   * Disable the details.
   * 
   * @return details
   */
  public SlDetails disable() {
    set(DISABLED, true);
    return this;
  }

  /**
   * Check if the details is disabled.
   * 
   * @return true, if is disabled
   */
  public boolean isDisabled() {
    return get(DISABLED);
  }

  /**
   * {@inheritDoc}
   */
  public SlDetails setText(String text) {
    addRawSlot(text);
    return this;
  }

  /**
   * {@inheritDoc}
   */
  public String getText() {
    return getRawSlot();
  }

  /**
   * Add a listener to the details open event.
   * 
   * @param listener the listener
   * @return the details
   */
  public SlDetails addOpenListener(EventListener<SlDetailsOpenEvent> listener) {
    addEventListener(SlDetailsOpenEvent.class, listener);
    return this;
  }

  /**
   * Remove a listener from the details open event.
   * 
   * @param listener the listener
   * @return the details
   */
  public SlDetails removeOpenListener(EventListener<SlDetailsOpenEvent> listener) {
    removeEventListener(SlDetailsOpenEvent.class, listener);
    return this;
  }

  /**
   * Add a listener to the details close event.
   * 
   * @param listener the listener
   * @return the details
   */
  public SlDetails addCloseListener(EventListener<SlDetailsCloseEvent> listener) {
    addEventListener(SlDetailsCloseEvent.class, listener);
    return this;
  }

  /**
   * Remove a listener from the details close event.
   * 
   * @param listener the listener
   * @return the details
   */
  public SlDetails removeCloseListener(EventListener<SlDetailsCloseEvent> listener) {
    removeEventListener(SlDetailsCloseEvent.class, listener);
    return this;
  }
}
