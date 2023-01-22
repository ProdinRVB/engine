package org.demo.shoelace.components.tabs;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

import org.demo.shoelace.components.SlComponent;
import org.demo.shoelace.components.tabs.events.SlTabCloseEvent;
import org.demo.shoelace.components.tabs.events.SlTabHideEvent;
import org.demo.shoelace.components.tabs.events.SlTabShowEvent;
import org.dwcj.controls.panels.AbstractDwcjPanel;
import org.dwcj.controls.panels.Div;
import org.dwcj.exceptions.DwcRuntimeException;
import org.dwcj.webcomponent.PropertyDescriptor;
import org.dwcj.webcomponent.annotations.NodeName;
import org.dwcj.webcomponent.events.EventListener;

/**
 * A Shoelace tabs.
 * 
 * @author Hyyan Abo Fakher
 * @since 1.0.0
 */
@NodeName("sl-tab-group")
public final class SlTabs extends SlComponent {

  /** The placement of the tabs. */
  public enum Placement {
    TOP("top"),
    BOTTOM("bottom"),
    START("start"),
    END("end");

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

  /**
   * When set to auto, navigating tabs with the arrow keys will instantly show the
   * corresponding tab panel. When set to manual, the tab will receive focus but
   * will not show until the user presses spacebar or enter.
   */
  public enum ActivationBehavior {
    AUTO("auto"), MANUAL("manual");

    private final String value;

    private ActivationBehavior(String value) {
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

  private Map<SlTab, Div> tabs = new LinkedHashMap<>();
  private SlTab activeTab;

  // Properties
  private final PropertyDescriptor<String> PLACEMENT = PropertyDescriptor.property("placement",
      Placement.TOP.getValue());
  private final PropertyDescriptor<String> ACTIVATION_BEHAVIOR = PropertyDescriptor.property("activation",
      ActivationBehavior.AUTO.getValue());
  private final PropertyDescriptor<Boolean> NO_SCROLL_CONTROLS = PropertyDescriptor.property("noScrollControls",
      false);

  /**
   * Create a new tabs.
   */
  public SlTabs() {
    super();

    addShowListener((SlTabShowEvent event) -> {
      SlTab tab = getTab(event.getId());
      if (tab != null && contains(tab)) {
        this.activeTab = tab;
      }
    });
  }

  /**
   * Set the placement of the tabs.
   * 
   * @param placement the placement
   * @return the tabs
   */
  public SlTabs setPlacement(Placement placement) {
    set(PLACEMENT, placement.getValue());
    return this;
  }

  /**
   * Get the placement of the tabs.
   * 
   * @return the placement
   */
  public Placement getPlacement() {
    return Placement.valueOf(get(PLACEMENT));
  }

  /**
   * Set the activation behavior of the tabs.
   * 
   * @param activationBehavior the activation behavior
   * @return the tabs
   */
  public SlTabs setActivationBehavior(ActivationBehavior activationBehavior) {
    set(ACTIVATION_BEHAVIOR, activationBehavior.getValue());
    return this;
  }

  /**
   * Get the activation behavior of the tabs.
   * 
   * @return the activation behavior
   */
  public ActivationBehavior getActivationBehavior() {
    return ActivationBehavior.valueOf(get(ACTIVATION_BEHAVIOR));
  }

  /**
   * Set whether to hide the scroll controls.
   * 
   * @param noScrollControls whether to hide the scroll controls
   * @return the tabs
   */
  public SlTabs setNoScrollControls(boolean noScrollControls) {
    set(NO_SCROLL_CONTROLS, noScrollControls);
    return this;
  }

  /**
   * Get whether to hide the scroll controls.
   * 
   * @return whether to hide the scroll controls
   */
  public boolean isNoScrollControls() {
    return get(NO_SCROLL_CONTROLS);
  }

  /**
   * Add a show listener to the tabs
   * 
   * @param listener
   * @return the tabs
   */
  public SlTabs addShowListener(EventListener<SlTabShowEvent> listener) {
    addEventListener(SlTabShowEvent.class, listener);
    return this;
  }

  /**
   * Remove a show listener from the tabs
   * 
   * @param listener
   * @return the tabs
   */
  public SlTabs removeShowListener(EventListener<SlTabShowEvent> listener) {
    removeEventListener(SlTabShowEvent.class, listener);
    return this;
  }

  /**
   * Add a hide listener to the tabs
   * 
   * @param listener
   * @return the tabs
   */
  public SlTabs addHideListener(EventListener<SlTabHideEvent> listener) {
    addEventListener(SlTabHideEvent.class, listener);
    return this;
  }

  /**
   * Remove a hide listener from the tabs
   * 
   * @param listener
   * @return the tabs
   */
  public SlTabs removeHideListener(EventListener<SlTabHideEvent> listener) {
    removeEventListener(SlTabHideEvent.class, listener);
    return this;
  }

  /**
   * Add a close listener to the tabs
   * 
   * @param listener
   * @return the tabs
   */
  public SlTabs addCloseListener(EventListener<SlTabCloseEvent> listener) {
    addEventListener(SlTabCloseEvent.class, listener);
    return this;
  }

  /**
   * Remove a close listener from the tabs
   * 
   * @param listener
   * @return the tabs
   */
  public SlTabs removeCloseListener(EventListener<SlTabCloseEvent> listener) {
    removeEventListener(SlTabCloseEvent.class, listener);
    return this;
  }

  /**
   * Add the given tab and its content to the tabs.
   * 
   * @param tab     the tab
   * @param content the content or null if if the tab has no div associated with
   * @return the tabs
   * @throws DwcRuntimeException if the tab is null
   */
  public SlTabs add(SlTab tab, Div content) {
    if (tab == null) {
      throw new DwcRuntimeException("Tab cannot be null");
    }

    // check if the tab is already added
    if (contains(tab)) {
      return this;
    }

    if (content != null) {
      content.setVisible(true);
      content.setAttribute("sl-panel", tab.getId());
      if (!content.getCaughtUp() && !content.isDestroyed() && isAttached()) {
        getPanel().add(content);
      } else {
        content.setVisible(false);
      }
    }

    // add the tab and its content to server side
    tabs.put(tab, content);

    // call addPropertyChangeListener with reflection
    // to keep track of changes in the tab
    Method method = null;
    try {
      method = tab.getClass().getDeclaredMethod("addPropertyChangeListener", PropertyChangeListener.class);
      method.setAccessible(true);
      method.invoke(tab, new SlTabChangeListener());
    } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
        | InvocationTargetException e) {
      throw new DwcRuntimeException("Error while adding property change listener to tab", e);
    }

    // schedule a command to add the tab to the client side.
    updateInClient(tab);

    return this;
  }

  /**
   * Add the given tab to the tabs.
   * 
   * @param tab the tab
   * @return the tabs
   * @throws DwcRuntimeException if the tab is null
   */
  public SlTabs add(SlTab tab) {
    return add(tab, null);
  }

  /**
   * Remove the given tab from the tabs.
   * 
   * @param tab the tab
   * @return the tabs
   */
  public SlTabs remove(SlTab tab) {
    // check if the tab is already added
    if (tab == null || !contains(tab)) {
      return this;
    }

    Div content = tabs.get(tab);
    if (content != null) {
      content.destroy();
    }

    int index = getTabIndex(tab);
    // remove the tab from the server side
    tabs.remove(tab);

    // schedule a command to remove the tab from the client side
    removeInClient(tab);

    if (activeTab == tab) {
      activeTab = null;
    }

    // activate the next tab if the active tab was removed
    if (index < tabs.size()) {
      show(getTab(index));
    } else if (index > 0) {
      show(getTab(index - 1));
    }

    return this;
  }

  /**
   * Remove the tab with the given id from the tabs.
   * 
   * @param id the id
   * @return the tabs
   */
  public SlTabs remove(String id) {
    SlTab tab = getTab(id);
    if (tab != null) {
      remove(tab);
    }

    return this;
  }

  /**
   * Remove the tab with the given index from the tabs.
   * 
   * @param index the index
   * @return the tabs
   */
  public SlTabs remove(int index) {
    SlTab tab = getTab(index);
    if (tab != null) {
      remove(tab);
    }

    return this;
  }

  /**
   * Remove all tabs from the tabs.
   * 
   * @return the tabs
   */
  public SlTabs removeAll() {
    tabs.clear();
    activeTab = null;
    removeAllInClient();

    return null;
  }

  /**
   * Get the index of the given tab.
   * 
   * @param tab the tab
   */
  public int getTabIndex(SlTab tab) {
    int i = 0;
    for (SlTab t : tabs.keySet()) {
      if (t == tab) {
        return i;
      }
      i++;
    }

    return -1;
  }

  /**
   * Get the tab with the given index.
   * 
   * @param index the index
   * @return the tab or null if not found
   */
  public SlTab getTab(int index) {
    int i = 0;
    for (SlTab tab : tabs.keySet()) {
      if (i == index) {
        return tab;
      }
      i++;
    }

    return null;
  }

  /**
   * Get the tab with the given id.
   * 
   * @param id the id
   * @return the tab or null if not found
   */
  public SlTab getTab(String id) {
    for (SlTab tab : tabs.keySet()) {
      if (tab.getId().equals(id)) {
        return tab;
      }
    }

    return null;
  }

  /**
   * Get the tab panel with the given tab.
   * 
   * @param tab the tab
   * @return the tab panel or null if not found
   */
  public Div getTabPanel(SlTab tab) {
    return tabs.get(tab);
  }

  /**
   * Get the tab panel with the given id.
   * 
   * @param id the id
   * @return the tab panel or null if not found
   */
  public Div getTabPanel(String id) {
    SlTab tab = getTab(id);
    if (tab != null) {
      return tabs.get(tab);
    }

    return null;
  }

  /**
   * Get the tab panel with the given index.
   * 
   * @param index the index
   * @return the tab panel or null if not found
   */
  public Div getTabPanel(int index) {
    SlTab tab = getTab(index);
    if (tab != null) {
      return tabs.get(tab);
    }

    return null;
  }

  /**
   * Check if the tabs contains the given tab.
   * 
   * @param tab the tab to check
   * @return true if the tabs contains the given tab
   */
  public boolean contains(SlTab tab) {
    return tabs.containsKey(tab);
  }

  /**
   * Activate the given tab
   * 
   * @param tab the tab to activate
   * @return the tabs
   */
  public SlTabs show(SlTab tab) {
    if (!contains(tab)) {
      throw new IllegalArgumentException("The tab does not exist");
    }

    activeTab = tab;
    updateInClient(tab);
    showInClient(tab);

    return this;
  }

  /**
   * Activate the given tab
   * 
   * @param tab the tab to activate
   * @return the tabs
   */
  public SlTabs show(String id) {
    return show(getTab(id));
  }

  /**
   * Activate the given tab
   * 
   * @param tab the tab to activate
   * @return the tabs
   */
  public SlTabs show(int index) {
    return show(getTab(index));
  }

  /**
   * Get the active tab
   * 
   * @return the active tab
   */
  public SlTab getActiveTab() {
    if (activeTab == null) {
      return getTab(0);
    }

    return activeTab;
  }

  /**
   * Attach tab panels
   */
  @Override
  protected void onAttach(AbstractDwcjPanel panel) {
    // loop over all tab panels and add them to the tabs control
    for (SlTab tab : tabs.keySet()) {
      Div content = tabs.get(tab);
      if (content != null) {
        content.setVisible(true);
        if (!content.getCaughtUp() && !content.isDestroyed()) {
          getPanel().add(content);
        }
      }
    }
  }

  /**
   * Update the given tab in the client side.
   * 
   * @param tab the tab to update
   */
  private void updateInClient(SlTab tab) {
    String panel = tab.getId();
    Div content = tabs.get(tab);

    StringBuilder js = new StringBuilder();
    js.append("let tab = component.querySelector('sl-tab[panel=\"").append(panel).append("\"]');")
        .append("if (!tab) {")
        .append(" tab = document.createElement('sl-tab');")
        .append(" tab.setAttribute('slot', 'nav');")
        .append(" tab.setAttribute('panel', '").append(panel).append("');")
        .append(" component.appendChild(tab);")
        .append("}")
        .append("tab.closable = ").append(tab.isClosable()).append(";")
        .append("tab.disabled = ").append(tab.isDisabled()).append(";")
        .append("tab.active = ").append(tab == activeTab).append(";")
        .append("tab.innerHTML = \\`").append(tab.getText()).append("\\`;");

    // create the associated sl-tab-panel
    if (content != null && !content.isDestroyed()) {
      js.append("let panel = component.querySelector('sl-tab-panel[name=\"").append(panel).append("\"]');")
          .append("if (!panel) {")
          .append(" panel = document.createElement('sl-tab-panel');")
          .append(" panel.setAttribute('name', '").append(panel).append("');")
          .append(" component.appendChild(panel);")
          .append("}");

      // search for the BBjWindow element and append to the sl-tab-panel
      js.append("let content = document.querySelector('[sl-panel=\"").append(panel).append("\"]');")
          .append("if (content) {")
          .append(" panel.appendChild(content);")
          .append("}");
    }

    js.append("return;"); // to avoid auto wrapping

    executeAsyncExpression(js.toString());
  }

  /**
   * Remove the given tab from the client side.
   * 
   * @param tab the tab to remove
   */
  private void removeInClient(SlTab tab) {
    String panel = tab.getId();
    Div content = tabs.get(tab);

    StringBuilder js = new StringBuilder();
    js.append("let tab = component.querySelector('sl-tab[panel=\"").append(panel).append("\"]');")
        .append("if (tab) {")
        .append(" component.removeChild(tab);")
        .append("}");

    // remove the associated sl-tab-panel
    if (content != null) {
      js.append("let panel = component.querySelector('sl-tab-panel[name=\"").append(panel).append("\"]');")
          .append("if (panel) {")
          .append(" component.removeChild(panel);")
          .append("}");
    }

    js.append("return;"); // to avoid auto wrapping

    executeAsyncExpression(js.toString());
  }

  /**
   * Remove all tabs from the client side.
   */
  public void removeAllInClient() {
    executeAsyncExpression("component.innerHTML = ''; return;");
  }

  /**
   * Show the given tab in the client
   * 
   * @param tab the tab to show
   */
  public void showInClient(SlTab tab) {
    StringBuilder js = new StringBuilder();

    js.append("try {")
        .append(" component.show('").append(tab.getId()).append("');")
        .append("} catch (e) {}")
        .append("return;"); // to avoid auto wrapping

    executeAsyncExpression(js.toString());
  }

  /**
   * An item change listener. When a property of a SlTab changes, this
   * listener will update the corresponding property of the sl-tab-item
   * element.
   * 
   * @author Hyyan Abo Fakher
   */
  private class SlTabChangeListener implements PropertyChangeListener {

    /**
     * {@inheritDoc}
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
      updateInClient((SlTab) evt.getSource());
    }
  }
}
