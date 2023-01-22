package org.demo.shoelace.components.breadcrumb;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.demo.shoelace.components.SlComponent;
import org.dwcj.exceptions.DwcRuntimeException;
import org.dwcj.interfaces.HasControlText;
import org.dwcj.webcomponent.PropertyDescriptor;
import org.dwcj.webcomponent.annotations.NodeName;

/**
 * A breadcrumb component.
 * 
 * @see <a href="https://shoelace.style/components/breadcrumb">Shoelace
 *      Breadcrumb</a>
 * @author Hyyan Abo Fakher
 * @since 1.0.0
 */
@NodeName("sl-breadcrumb")
public final class SlBreadcrumb extends SlComponent implements HasControlText {

  private List<SlBreadcrumbItem> items = new ArrayList<>();

  // Properties
  private final PropertyDescriptor<String> LABEL = PropertyDescriptor.property("labe", "");

  /**
   * Create a new breadcrumb
   */
  public SlBreadcrumb() {
    super();
  }

  /**
   * Create a new breadcrumb
   * 
   * @param label the label to use for the breadcrumb control
   */
  public SlBreadcrumb(String label) {
    this();
    setLabel(label);
  }

  /**
   * Set The label to use for the breadcrumb control. This will not be shown on
   * the screen, but it will be announced by screen readers and other assistive
   * devices to provide more context for users.
   * 
   * 
   * @param label The label to use for the breadcrumb control.
   * @return the breadcrumb
   */
  public SlBreadcrumb setLabel(String label) {
    set(LABEL, label);
    return this;
  }

  /**
   * Get The label to use for the breadcrumb control. This will not be shown on
   * the screen, but it will be announced by screen readers and other assistive
   * devices to provide more context for users.
   * 
   * 
   * @return The label to use for the breadcrumb control.
   */
  public String getLabel() {
    return get(LABEL);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public SlBreadcrumb setText(String text) {
    addRawSlot(text);
    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getText() {
    return getRawSlot();
  }

  /**
   * Add an item to the breadcrumb
   * 
   * @param item the item to add
   * @return the breadcrumb
   */
  public SlBreadcrumb add(SlBreadcrumbItem item) {
    items.add(item);

    // call addPropertyChangeListener with reflection
    // to keep track of changes in the breadcrumb item
    Method method = null;
    try {
      method = item.getClass().getDeclaredMethod("addPropertyChangeListener", PropertyChangeListener.class);
      method.setAccessible(true);
      method.invoke(item, new SlBreadcrumbItemChangeListener());
    } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
        | InvocationTargetException e) {
      throw new DwcRuntimeException("Error while adding property change listener to tab", e);
    }

    updateInClient(item);

    return this;
  }

  /**
   * Add an item to the breadcrumb
   * 
   * @param text the text to add
   * @return the breadcrumb
   */
  public SlBreadcrumb add(String text) {
    add(new SlBreadcrumbItem(text));

    return this;
  }

  /**
   * Add an item to the breadcrumb
   * 
   * @param text the text to add
   * @param href the href to add
   * @return the breadcrumb
   */
  public SlBreadcrumb add(String text, String href) {
    add(new SlBreadcrumbItem(text, href));
    return this;
  }

  /**
   * Add items to the breadcrumb
   * 
   * @param items the items to add
   * @return the breadcrumb
   */
  public SlBreadcrumb add(SlBreadcrumbItem... items) {
    for (SlBreadcrumbItem item : items) {
      add(item);
    }
    return this;
  }

  /**
   * Add items to the breadcrumb
   * 
   * @param items the items to add
   * @return the breadcrumb
   */
  public SlBreadcrumb add(String... items) {
    for (String item : items) {
      add(item);
    }
    return this;
  }

  /**
   * Reset the items in the breadcrumb
   * 
   * @param items the items to add
   * @return the breadcrumb
   */
  public SlBreadcrumb setItems(SlBreadcrumbItem... items) {
    this.items.clear();
    return add(items);
  }

  /**
   * Find the item at the given index
   * 
   * @param index the index of the item
   * @return the item at the given index
   */
  public SlBreadcrumbItem get(int index) {
    return items.get(index);
  }

  /**
   * Check if the breadcrumb has the given item
   * 
   * @param item the item to check
   * @return true if the breadcrumb has the given item
   */
  public boolean contains(SlBreadcrumbItem item) {
    return items.contains(item);
  }

  /**
   * Check if the breadcrumb has the given item
   * 
   * @param text the text of the item to check
   * @return true if the breadcrumb has the given item
   */
  public boolean contains(String text) {
    for (SlBreadcrumbItem item : items) {
      if (item.getText().equals(text)) {
        return true;
      }
    }

    return false;
  }

  /**
   * Remove the given item from the breadcrumb
   * 
   * @param item the item to remove
   * @return the breadcrumb
   */
  public SlBreadcrumb remove(SlBreadcrumbItem item) {
    removeInClient(item);
    items.remove(item);
    return this;
  }

  /**
   * Remove the given item from the breadcrumb
   * 
   * @param text the text of the item to remove
   * @return the breadcrumb
   */
  public SlBreadcrumb remove(String text) {
    for (SlBreadcrumbItem item : items) {
      if (item.getText().equals(text)) {
        remove(item);
        break;
      }
    }

    return this;
  }

  /**
   * Remove the item at the given index
   * 
   * @param index the index of the item to remove
   * @return the breadcrumb
   */
  public SlBreadcrumb remove(int index) {
    remove(items.get(index));
    return this;
  }

  /**
   * Add the given item in the client
   * 
   * @param item the item to add
   */
  private void updateInClient(SlBreadcrumbItem item) {
    int index = items.indexOf(item);
    StringBuilder js = new StringBuilder();

    js.append("let item = component.querySelector('sl-breadcrumb-item:nth-child(").append(index + 1).append(")');");
    js.append("if (!item) {");
    js.append(" item = document.createElement('sl-breadcrumb-item');");
    js.append(" component.appendChild(item);");
    js.append("}");
    js.append("item.href = '").append(item.getHref()).append("';");
    js.append("item.rel = '").append(item.getRel()).append("';");
    js.append("item.target = '").append(item.getTarget()).append("';");
    js.append("item.innerHTML = \\`").append(item.getText()).append("\\`;");
    js.append("return;"); // to avoid auto wrapping

    invokeAsync("Function", js.toString());
  }

  /**
   * Remove the given item from the client
   * 
   * @param item the item to remove
   */
  private void removeInClient(SlBreadcrumbItem item) {
    int index = items.indexOf(item);
    StringBuilder js = new StringBuilder();

    js.append("let item = component.querySelector('sl-breadcrumb-item:nth-child(").append(index + 1).append(")');");
    js.append("if (item) {");
    js.append(" component.removeChild(item);");
    js.append("}");
    js.append("return;"); // to avoid auto wrapping

    invokeAsync("Function", js.toString());
  }

  /**
   * An item change listener. When a property of a breadcrumb item changes, this
   * listener will update the corresponding property of the sl-breadcrumb-item
   * element.
   * 
   * @author Hyyan Abo Fakher
   */
  private class SlBreadcrumbItemChangeListener implements PropertyChangeListener {

    /**
     * {@inheritDoc}
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
      updateInClient((SlBreadcrumbItem) evt.getSource());
    }
  }
}