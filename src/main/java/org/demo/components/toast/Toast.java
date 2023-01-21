package org.demo.components.toast;

import java.util.HashMap;

import org.demo.components.toast.events.ToastButtonClickedEvent;
import org.demo.components.toast.events.ToastClosedEvent;
import org.demo.components.toast.events.ToastOpenedEvent;
import org.dwcj.interfaces.HasClassName;
import org.dwcj.webcomponent.PropertyDescriptor;
import org.dwcj.webcomponent.WebComponent;
import org.dwcj.webcomponent.annotations.NodeName;
import org.dwcj.webcomponent.events.EventListener;

/**
 * The toast component.
 * 
 * A toast is a small notification that appears at the bottom of the screen.
 * It is used to display a message to the user.
 * 
 * The toast can be opened and closed by the user or by the server and it can
 * have
 * buttons to perform actions.
 * 
 * @author Hyyan Abo Fakher
 **/
@NodeName("bbj-toast")
public final class Toast extends WebComponent implements HasClassName {

  /**
   * The theme of the toast
   */
  public enum Theme {
    DEFAULT("default"), DANGER("danger"), GRAY("gray"), INFO("info"), PRIMARY("primary"), SUCCESS("success"),
    WARNING("warning");

    private String value;

    /**
     * @param value the value
     */
    private Theme(String value) {
      this.value = value;
    }

    /**
     * @return the value
     */
    public String getValue() {
      return value;
    }

    /**
     * @return the value
     */
    public String toString() {
      return value;
    }
  }

  /**
   * The placement of the toast
   */
  public enum Placement {
    TOP_LEFT("top-left"), TOP("top"), TOP_RIGHT("top-right"), BOTTOM_LEFT("bottom-left"), BOTTOM("bottom"),
    BOTTOM_RIGHT("bottom-right");

    private String value;

    /**
     * @param value the value
     */
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
     * @return the value
     */
    public String toString() {
      return value;
    }
  }

  /**
   * The buttons to add to the toast where the key is the button id and the value
   * is the button text
   */
  private final HashMap<String, String> buttons = new HashMap<>();

  // Property descriptors
  private static PropertyDescriptor<Boolean> OPENED = PropertyDescriptor.property("opened", false);
  private static PropertyDescriptor<String> MESSAGE = PropertyDescriptor.property("message", "");
  private static PropertyDescriptor<Integer> DURATION = PropertyDescriptor.property("duration", 5000);
  private static PropertyDescriptor<String> THEME = PropertyDescriptor.property("theme", Theme.GRAY.getValue());
  private static PropertyDescriptor<String> PLACEMENT = PropertyDescriptor.property("placement",
      Placement.BOTTOM.getValue());

  /**
   * Create a new toast
   */
  public Toast() {
    super();

    // Make sure to append the toast to the end of the body to avoid
    // any stacking issues.
    // This call should be made very early in the lifecycle of the component
    // before any events are added because moving the element will remove all
    // the event listeners
    invokeAsync("Function", "document.body.appendChild(component)");
  }

  /**
   * Set the theme of the toast
   * 
   * @param theme the theme
   * @return the toast
   * @see Theme
   */
  public Toast setTheme(Theme theme) {
    set(THEME, theme.getValue());
    return this;
  }

  /**
   * Get the theme of the toast
   * 
   * @return the theme
   * @see Theme
   */
  public Theme getTheme() {
    return Theme.valueOf(get(THEME).toUpperCase());
  }

  /**
   * Set the placement of the toast
   * 
   * @param placement the placement
   * @return the toast
   * @see Placement
   */
  public Toast setPlacement(Placement placement) {
    set(PLACEMENT, placement.getValue());
    return this;
  }

  /**
   * Get the placement of the toast
   * 
   * @return the placement
   * @see Placement
   */
  public Placement getPlacement() {
    return Placement.valueOf(get(PLACEMENT).toUpperCase());
  }

  /**
   * Check if the toast is opened
   * 
   * @return true if the toast is opened
   */
  public boolean isOpened() {
    // opened is fetch always from the client side
    // because the toast can be closed automatically.
    return get(OPENED, true, Boolean.class);
  }

  /**
   * Show a toast with the given message and duration
   * 
   * @param message  the message to show
   * @param duration the duration to show the message
   * 
   * @return the toast
   */
  public Toast show(String message, int duration) {
    set(MESSAGE, message);
    set(DURATION, duration);
    set(OPENED, true);
    return this;
  }

  /**
   * Show a toast with the given message for the default duration
   * 
   * @param message the message to show
   * @return the toast
   */
  public Toast show(String message) {
    return show(message, DURATION.getDefaultValue());
  }

  /**
   * Hide the toast
   * 
   * @return the toast
   */
  public Toast hide() {
    set(OPENED, false);
    return this;
  }

  /**
   * Add a button to the toast
   * 
   * @param id    the id of the button
   * @param label the label of the button
   * 
   * @return the toast
   */
  public Toast addButton(String id, String label) {
    buttons.put(id, label);

    // Build the buttons html
    StringBuilder html = new StringBuilder();
    html.append("<button ").append(" class=\"bbj-toast-button\" data-action=\"")
        .append(id).append("\">");
    html.append(label);
    html.append("</button>");

    // Build the script to add the buttons
    StringBuilder script = new StringBuilder();
    script.append("(function() {");
    script.append("const template = document.createElement(\"template\");");
    script.append("template.innerHTML = \\`").append(html.toString()).append("\\`;");
    script.append("const firstChild = template.content.firstChild;");
    script.append("component.appendChild(firstChild);");
    script.append("})();");

    // Add the buttons
    invokeAsync("Function", script.toString());

    return this;
  }

  /**
   * Add a close `X` button to the toast
   * 
   * The method will add a button with the id "close" and the label
   * <bbj-icon name="x"></bbj-icon> which is the default close button icon.
   * 
   * @return the toast
   * @see #addButton(String, String)
   */
  public Toast addCloseButton() {
    return addButton("close", "<bbj-icon name=\"x\"></bbj-icon>");
  }

  /**
   * Remove a button from the toast
   * 
   * @param id the id of the button
   * @return the toast
   */
  public Toast removeButton(String id) {
    buttons.remove(id);

    // Build the script to remove the button
    StringBuilder script = new StringBuilder();
    script.append("(function() {");
    script.append("const button = component.querySelector(\"[data-action=\\'").append(id)
        .append("\\']\");");
    script.append("if (button) {");
    script.append("component.removeChild(button);");
    script.append("}");
    script.append("})();");

    // Remove the button
    invokeAsync("Function", script.toString());

    return this;
  }

  /**
   * Remove all the buttons from the toast
   * 
   * @return the toast
   */
  public Toast removeAllButtons() {
    buttons.clear();

    // Build the script to remove all the buttons
    StringBuilder script = new StringBuilder();
    script.append("(function() {");
    script.append("const buttons = component.querySelectorAll(\".bbj-toast-button\");");
    script.append("for (let i = 0; i < buttons.length; i++) {");
    script.append("component.removeChild(buttons[i]);");
    script.append("}");
    script.append("})();");

    // Remove all the buttons
    invokeAsync("Function", script.toString());

    return this;
  }

  /**
   * Add a listener for the opened event
   * 
   * @param listener the listener
   * @return the toast
   */
  public Toast addOpenedListener(EventListener<ToastOpenedEvent> listener) {
    addEventListener(ToastOpenedEvent.class, listener);
    return this;
  }

  /**
   * Remove a listener of the opened event
   * 
   * @param listener the listener
   * @return the toast
   */
  public Toast removeOpenedListener(EventListener<ToastOpenedEvent> listener) {
    removeEventListener(ToastOpenedEvent.class, listener);
    return this;
  }

  /**
   * Add a listener for the closed event
   * 
   * @param listener the listener
   * @return the toast
   */
  public Toast addClosedListener(EventListener<ToastClosedEvent> listener) {
    addEventListener(ToastClosedEvent.class, listener);
    return this;
  }

  /**
   * Remove a listener of the closed event
   * 
   * @param listener the listener
   * @return the toast
   */
  public Toast removeClosedListener(EventListener<ToastClosedEvent> listener) {
    removeEventListener( ToastClosedEvent.class, listener);
    return this;
  }

  /**
   * Add a listener for the button clicked event
   * 
   * @param listener the listener
   * @return the toast
   */
  public Toast addButtonClickedListener(EventListener<ToastButtonClickedEvent> listener) {
    addEventListener(ToastButtonClickedEvent.class, listener);
    return this;
  }

  /**
   * Remove a clicked event listener
   * 
   * @param listener the listener
   * @return the toast
   */
  public Toast removeButtonClickedListener(EventListener<ToastButtonClickedEvent> listener) {
    removeEventListener(ToastButtonClickedEvent.class, listener);
    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public HasClassName addClassName(String className) {
    addComponentClassName(className);
    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public HasClassName removeClassName(String className) {
    removeComponentClassName(className);
    return null;
  }
}