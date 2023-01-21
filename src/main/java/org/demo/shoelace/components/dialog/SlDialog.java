package org.demo.shoelace.components.dialog;

import org.demo.shoelace.components.SlComponent;
import org.demo.shoelace.components.dialog.events.SlDialogCloseEvent;
import org.demo.shoelace.components.dialog.events.SlDialogOpenEvent;
import org.dwcj.controls.panels.AbstractDwcjPanel;
import org.dwcj.controls.panels.Div;
import org.dwcj.webcomponent.PropertyDescriptor;
import org.dwcj.webcomponent.annotations.NodeName;
import org.dwcj.webcomponent.events.EventListener;

/**
 * The dialog component.
 * 
 * @see <a href="https://shoelace.style/components/dialog">Shoelace Dialog</a>
 * @author Hyyan Abo Fakher
 * @since 1.0.0
 */
@NodeName("sl-dialog")
public class SlDialog extends SlComponent {

  private AbstractDwcjPanel content;
  private AbstractDwcjPanel footer;

  // Properties
  private static PropertyDescriptor<Boolean> OPEN = PropertyDescriptor.property("open", false);
  private static PropertyDescriptor<String> LABEL = PropertyDescriptor.property("label", "");
  private static PropertyDescriptor<Boolean> NO_HEADER = PropertyDescriptor.property("noHeader", false);

  /**
   * Create a new dialog.
   */
  public SlDialog() {
    super();

    this.setContent(new Div());
    this.setFooter(new Div());
  }

  /**
   * Create a new dialog with the given label.
   * 
   * @param label the label
   */
  public SlDialog(String label) {
    this();
    this.setLabel(label);
  }

  /**
   * Set the dialog's content panel.
   * 
   * @param content the content panel
   * @return the dialog
   */
  public SlDialog setContent(AbstractDwcjPanel content) {
    this.content = content;
    addSlot(content);
    return this;
  }

  /**
   * Get the content panel instance.
   * 
   * @return the content panel
   */
  public AbstractDwcjPanel getContent() {
    return content;
  }

  /**
   * Set the dialog's footer panel.
   * 
   * @param footer the footer panel
   * @return the dialog
   */
  public SlDialog setFooter(AbstractDwcjPanel footer) {
    this.footer = footer;
    addSlot("footer", footer);
    return this;
  }

  /**
   * Get the footer panel instance.
   * 
   * @return the footer panel
   */
  public AbstractDwcjPanel getFooter() {
    return footer;
  }

  /**
   * Open the dialog.
   * 
   * @return the dialog
   */
  public SlDialog open() {
    set(OPEN, true);
    return this;
  }

  /**
   * Check if the dialog is open.
   * 
   * @return true if the dialog is open
   */
  public boolean isOpened() {
    return get(OPEN, true, Boolean.class);
  }

  /**
   * Close the dialog.
   * 
   * @return the dialog
   */
  public SlDialog close() {
    set(OPEN, false);
    return this;
  }

  /**
   * The dialog's label as displayed in the header. You should always include a
   * relevant label even when using no-header, as it is required for proper
   * accessibility.
   * 
   * @param label the label
   * @return the dialog
   */
  public SlDialog setLabel(String label) {
    set(LABEL, label);
    return this;
  }

  /**
   * Get The dialog's label as displayed in the header.
   * 
   * @return the label
   */
  public String getLabel() {
    return get(LABEL);
  }

  /**
   * Disables the header. This will also remove the default close button, so
   * please ensure you provide an easy, accessible way for users to dismiss the
   * dialog.
   * 
   * @param noHeader true to disable the header
   * @return the dialog
   */
  public SlDialog setNoHeader(boolean noHeader) {
    set(NO_HEADER, noHeader);
    return this;
  }

  /**
   * Get the no-header property.
   * 
   * @return true if the header is disabled
   */
  public boolean isNoHeader() {
    return get(NO_HEADER);
  }

  /**
   * Add a listener to the dialog open event.
   * 
   * @param listener the listener
   * @return the dialog
   */
  public SlDialog addOpenListener(EventListener<SlDialogOpenEvent> listener) {
    addEventListener(SlDialogOpenEvent.class, listener);
    return this;
  }

  /**
   * Remove a listener from the dialog open event.
   * 
   * @param listener the listener
   * @return the dialog
   */
  public SlDialog removeOpenListener(EventListener<SlDialogOpenEvent> listener) {
    removeEventListener(SlDialogOpenEvent.class, listener);
    return this;
  }

  /**
   * Add a listener to the dialog close event.
   * 
   * @param listener the listener
   * @return the dialog
   */
  public SlDialog addCloseListener(EventListener<SlDialogCloseEvent> listener) {
    addEventListener(SlDialogCloseEvent.class, listener);
    return this;
  }

  /**
   * Remove a listener from the dialog close event.
   * 
   * @param listener the listener
   * @return the dialog
   */
  public SlDialog removeCloseListener(EventListener<SlDialogCloseEvent> listener) {
    removeEventListener(SlDialogCloseEvent.class, listener);
    return this;
  }

}
