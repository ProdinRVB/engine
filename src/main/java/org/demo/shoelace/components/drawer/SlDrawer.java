package org.demo.shoelace.components.drawer;

import org.demo.shoelace.components.SlComponent;
import org.demo.shoelace.components.drawer.events.SlDrawerCloseEvent;
import org.demo.shoelace.components.drawer.events.SlDrawerOpenEvent;
import org.dwcj.controls.panels.Div;
import org.dwcj.webcomponent.PropertyDescriptor;
import org.dwcj.webcomponent.annotations.NodeName;
import org.dwcj.webcomponent.events.EventListener;

/**
 * Shoelace Drawer
 * 
 * @see <a href="https://shoelace.style/components/drawer">Shoelace Drawer</a>
 * @author Hyyan Abo Fakher
 * @since 1.0.0
 */
@NodeName("sl-drawer")
public class SlDrawer extends SlComponent {

  /** The direction from which the drawer will open. */
  public enum Placement {
    // 'top' | 'end' | 'bottom' | 'start'
    TOP("top"), END("end"), BOTTOM("bottom"), START("start");

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
     * @param value the value to set
     */
    @Override
    public String toString() {
      return value;
    }

    /**
     * @param value the value to set
     */
    public static Placement fromString(String value) {
      for (Placement p : Placement.values()) {
        if (p.value.equalsIgnoreCase(value)) {
          return p;
        }
      }
      return null;
    }
  }

  private String size = "25rem";
  private Div headerActions;
  private Div content;
  private Div footer;

  // Properties
  private final PropertyDescriptor<Boolean> OPEN = PropertyDescriptor.property("open", false);
  private final PropertyDescriptor<String> LABEL = PropertyDescriptor.property("label", "");
  private final PropertyDescriptor<String> PLACEMENT = PropertyDescriptor.property("placement",
      Placement.END.getValue());
  private final PropertyDescriptor<Boolean> CONTAINED = PropertyDescriptor.property("contained", false);
  private final PropertyDescriptor<Boolean> NO_HEADER = PropertyDescriptor.property("no-header", false);

  /**
   * Create new instance of drawer.
   */
  public SlDrawer() {
    super();
    setContent(new Div());
    setFooter(new Div());
    setHeaderActions(new Div());
  }

  /**
   * Use the passed panel in header slot.
   * 
   * @param content the header panel
   * @return the drawer
   */
  public SlDrawer setHeaderActions(Div content) {
    this.headerActions = content;
    addSlot("header-actions", content);
    return this;
  }

  /**
   * Get the header panel instance.
   * 
   * @return the header panel
   */
  public Div getHeaderActions() {
    return headerActions;
  }

  /**
   * Use the passed panel in content slot.
   * 
   * @param content the content panel
   * @return the drawer
   */
  public SlDrawer setContent(Div content) {
    this.content = content;
    addSlot(content);
    return this;
  }

  /**
   * Get the content panel instance.
   * 
   * @return the content panel
   */
  public Div getContent() {
    return content;
  }

  /**
   * Use the passed panel in footer slot.
   * 
   * @param footer the footer panel
   * @return the drawer
   */
  public SlDrawer setFooter(Div footer) {
    this.footer = footer;
    addSlot("footer", footer);
    return this;
  }

  /**
   * Get the footer panel instance.
   * 
   * @return the footer panel
   */
  public Div getFooter() {
    return footer;
  }

  /**
   * Check if the drawer is open.
   * 
   * @return true if the drawer is open
   */
  public boolean isOpen() {
    return get(OPEN, false, Boolean.class);
  }

  /**
   * Open the drawer.
   * 
   * @return the drawer
   */
  public SlDrawer open() {
    set(OPEN, true);
    return this;
  }

  /**
   * Close the drawer.
   * 
   * @return the drawer
   */
  public SlDrawer close() {
    set(OPEN, false);
    return this;
  }

  /**
   * The drawer's label as displayed in the header. You should always include a
   * relevant label even when using no-header, as it is required for proper
   * accessibility.
   * 
   * @param label the label
   * @return the drawer
   */
  public SlDrawer setLabel(String label) {
    set(LABEL, label);
    return this;
  }

  /**
   * The drawer's label as displayed in the header. You should always include a
   * relevant label even when using no-header, as it is required for proper
   * accessibility.
   * 
   * @return the label
   */
  public String getLabel() {
    return get(LABEL);
  }

  /**
   * The direction from which the drawer will open.
   * 
   * @param placement the placement
   * @return the drawer
   */
  public SlDrawer setPlacement(Placement placement) {
    set(PLACEMENT, placement.getValue());
    return this;
  }

  /**
   * The direction from which the drawer will open.
   * 
   * @return the placement
   */
  public Placement getPlacement() {
    return Placement.fromString(get(PLACEMENT));
  }

  /**
   * By default, the drawer slides out of its containing block (usually the
   * viewport). To make the drawer slide out of its parent element, set this
   * attribute and add position: relative to the parent.
   * 
   * @param contained true to make the drawer slide out of its parent element
   * @return the drawer
   */
  public SlDrawer setContained(boolean contained) {
    set(CONTAINED, contained);
    return this;
  }

  /**
   * By default, the drawer slides out of its containing block (usually the
   * viewport). To make the drawer slide out of its parent element, set this
   * attribute and add position: relative to the parent.
   * 
   * @return true if the drawer slides out of its parent element
   */
  public boolean isContained() {
    return get(CONTAINED, false, Boolean.class);
  }

  /**
   * Removes the header. This will also remove the default close button, so please
   * ensure you provide an easy, accessible way for users to dismiss the drawer.
   * 
   * @param noHeader true to remove the header
   * @return the drawer
   */
  public SlDrawer setNoHeader(boolean noHeader) {
    set(NO_HEADER, noHeader);
    return this;
  }

  /**
   * Removes the header. This will also remove the default close button, so please
   * ensure you provide an easy, accessible way for users to dismiss the drawer.
   * 
   * @return true if the header is removed
   */
  public boolean isNoHeader() {
    return get(NO_HEADER, false, Boolean.class);
  }

  /**
   * Set the size of the drawer. The default size is 25rem
   * 
   * @param size
   * @return
   */
  public SlDrawer setSize(String size) {
    this.size = size;
    setComponentStyle("--size", size);
    return this;
  }

  /**
   * Get the size of the drawer
   * 
   * @return the size
   */
  public String getSize() {
    return size;
  }

  /**
   * Add open listener to the drawer
   * 
   * @param listener
   * @return the drawer
   */
  public SlDrawer addOpenListener(EventListener<SlDrawerOpenEvent> listener) {
    addEventListener(SlDrawerOpenEvent.class, listener);
    return this;
  }

  /**
   * Remove open listener from the drawer
   * 
   * @param listener
   * @return the drawer
   */
  public SlDrawer removeOpenListener(EventListener<SlDrawerOpenEvent> listener) {
    removeEventListener(SlDrawerOpenEvent.class, listener);
    return this;
  }

  /**
   * Add close listener to the drawer
   * 
   * @param listener
   * @return the drawer
   */
  public SlDrawer addCloseListener(EventListener<SlDrawerCloseEvent> listener) {
    addEventListener(SlDrawerCloseEvent.class, listener);
    return this;
  }

  /**
   * Remove close listener from the drawer
   * 
   * @param listener
   * @return the drawer
   */
  public SlDrawer removeCloseListener(EventListener<SlDrawerCloseEvent> listener) {
    removeEventListener(SlDrawerCloseEvent.class, listener);
    return this;
  }
}
