package org.demo.components.drawer;

import org.demo.components.drawer.events.DrawerClosedEvent;
import org.demo.components.drawer.events.DrawerOpenedEvent;
import org.dwcj.controls.panels.AbstractDwcjPanel;
import org.dwcj.controls.panels.Div;
import org.dwcj.interfaces.HasClassName;
import org.dwcj.webcomponent.PropertyDescriptor;
import org.dwcj.webcomponent.WebComponent;
import org.dwcj.webcomponent.annotations.NodeName;
import org.dwcj.webcomponent.events.EventListener;

@NodeName("bbj-drawer")
public class Drawer extends WebComponent<Drawer> implements HasClassName {

  /**
   * The drawer placement.
   */
  public enum Placement {
    TOP("top"), TOP_CENTER("top-center"),
    BOTTOM("bottom"), BOTTOM_CENTER("bottom-center"),
    LEFT("left"),
    RIGHT("right");

    /** The drawer placement value. */
    private final String value;

    /**
     * Instantiates a new drawer placement.
     *
     * @param value the value
     */
    Placement(String value) {
      this.value = value;
    }

    /**
     * Gets the value.
     *
     * @return the value
     */
    public String getValue() {
      return value;
    }

    /**
     * Gets the drawer placement value as string.
     * 
     * @return the string
     */
    @Override
    public String toString() {
      return value;
    }
  }

  private static PropertyDescriptor<Boolean> AUTO_FOCUS = PropertyDescriptor.property("autoFocus", false);
  private static PropertyDescriptor<String> LABEL = PropertyDescriptor.property("label", "Drawer");
  private static PropertyDescriptor<String> MAX_SIZE = PropertyDescriptor.property("maxSize", "100%");
  private static PropertyDescriptor<Boolean> OPENED = PropertyDescriptor.property("opened", false);
  private static PropertyDescriptor<String> PLACEMENT = PropertyDescriptor.property("placement",
      Placement.LEFT.getValue());
  private static PropertyDescriptor<String> SIZE = PropertyDescriptor.property("size", "16EM");

  /**
   * Instantiates a new drawer.
   */
  public Drawer() {
    super();
    setContent(new Div());
  }

  /**
   * Set the drawer content
   * 
   * @param content The drawer content.
   * @return the drawer
   */
  public Drawer setContent(Div content) {
    addSlot(content);
    return this;
  }

  /**
   * Get the drawer content.
   * 
   * @return the drawer content
   */
  public AbstractDwcjPanel getContent() {
    return getSlot();
  }

  /**
   * Set Drawer auto focus.
   * 
   * @param autoFocus When true then automatically focus the first focusable
   *                  element in the drawer.
   * @return the drawer
   */
  public Drawer setAutoFocus(boolean autoFocus) {
    set(AUTO_FOCUS, autoFocus);
    return this;
  }

  /**
   * Get Drawer auto focus.
   * 
   * @return the drawer auto focus
   */
  public boolean isAutoFocus() {
    return get(AUTO_FOCUS);
  }

  /**
   * Set Drawer label. (Used for accessibility)
   * 
   * @param label The drawer label.
   * @return the drawer
   */
  public Drawer setLabel(String label) {
    set(LABEL, label);
    return this;
  }

  /**
   * Get Drawer label.
   * 
   * @return the drawer label
   */
  public String getLabel() {
    return get(LABEL);
  }

  /**
   * Set Drawer max size.
   * 
   * @param maxSize The Drawer max width. Max width in case placement is `left` or
   *                `right` or max height in case placement is `top` or `bottom`.
   * @return the drawer
   */
  public Drawer setMaxSize(String maxSize) {
    set(MAX_SIZE, maxSize);
    return this;
  }

  /**
   * Get Drawer max size.
   * 
   * @return the drawer max size
   */
  public String getMaxSize() {
    return get(MAX_SIZE);
  }

  /**
   * Open or close the drawer.
   * 
   * @param opened When true, the drawer is shown.
   * @return the drawer
   */
  public Drawer toggle(boolean opened) {
    set(OPENED, opened);
    return this;
  }

  /**
   * Open the drawer.
   * 
   * @return the drawer
   */
  public Drawer open() {
    return toggle(true);
  }

  /**
   * Close the drawer.
   * 
   * @return the drawer
   */
  public Drawer close() {
    return toggle(false);
  }

  /**
   * Get Drawer opened.
   * 
   * @return the drawer opened
   */
  public boolean isOpened() {
    return get(OPENED, true, Boolean.class);
  }

  /**
   * Set Drawer placement.
   * 
   * @param placement The drawer placement.
   * @return the drawer
   */
  public Drawer setPlacement(Placement placement) {
    set(PLACEMENT, placement.getValue());
    return this;
  }

  /**
   * Get Drawer placement.
   * 
   * @return the drawer placement
   */
  public Placement getPlacement() {
    return Placement.valueOf(get(PLACEMENT));
  }

  /**
   * Set Drawer size.
   * 
   * @param size The Drawer size. Width in case placement is `left` or `right` or
   *             height in case placement is `top` or `bottom`.
   * @return the drawer
   */
  public Drawer setSize(String size) {
    set(SIZE, size);
    return this;
  }

  /**
   * Get Drawer size.
   * 
   * @return the drawer size
   */
  public String getSize() {
    return get(SIZE);
  }

  /**
   * Add Drawer opened listener.
   * 
   * @param listener the listener
   * @return the drawer
   */
  public Drawer addOpenedListener(EventListener<DrawerOpenedEvent> listener) {
    return addEventListener("bbj-drawer-opened", DrawerOpenedEvent.class, listener, FILTER_SAME_NODE);
  }

  /**
   * Remove Drawer opened listener.
   * 
   * @param listener the listener
   * @return the drawer
   */
  public Drawer removeOpenedListener(EventListener<DrawerOpenedEvent> listener) {
    return removeEventListener("bbj-drawer-opened", DrawerOpenedEvent.class, listener);
  }

  /**
   * Add Drawer closed listener.
   * 
   * @param listener the listener
   * @return the drawer
   */
  public Drawer addClosedListener(EventListener<DrawerClosedEvent> listener) {
    return addEventListener("bbj-drawer-closed", DrawerClosedEvent.class, listener, FILTER_SAME_NODE);
  }

  /**
   * Remove Drawer closed listener.
   * 
   * @param listener the listener
   * @return the drawer
   */
  public Drawer removeClosedListener(EventListener<DrawerClosedEvent> listener) {
    return removeEventListener("bbj-drawer-closed", DrawerClosedEvent.class, listener);
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
