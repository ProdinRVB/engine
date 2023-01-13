package org.dwcj.webcomponent;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.dwcj.Environment;
import org.dwcj.controls.AbstractControl;
import org.dwcj.controls.htmlcontainer.HtmlContainer;
import org.dwcj.controls.htmlcontainer.events.HtmlContainerJavascriptEvent;
import org.dwcj.controls.panels.AbstractDwcjPanel;
import org.dwcj.webcomponent.annotations.NodeAttribute;
import org.dwcj.webcomponent.annotations.NodeName;
import org.dwcj.webcomponent.events.Event;
import org.dwcj.webcomponent.events.EventDispatcher;
import org.dwcj.webcomponent.events.EventListener;

/**
 * This class is used to create web components that can
 * be used in the DWCJ framework. The web component class must be annotated with
 * {@link NodeName} annotation to specify the tag name of the web component and
 * must extend this class.
 * 
 * To include assets (JavaScript, CSS, etc.) for the web component, the class
 * can
 * be annotated with assets annotations like the {@link JavaScript} and
 * {@link InlineStyleSheet} annotations.
 * 
 * @author Hyyan Abo Fakher
 */
public abstract class WebComponent<T extends WebComponent<T>> extends AbstractControl {
  /**
   * The filter to check if the event target is the same node
   * as the component node.
   * 
   * @see #addWebComponentEventListener(String, EventListener, String, String)
   */
  protected static final String FILTER_SAME_NODE = "event.target.isSameNode(component)";
  private final HtmlContainer hv;
  private final String uuid = UUID.randomUUID().toString().substring(0, 8);
  private final Map<String, Object> properties = new HashMap<>();
  private final Map<String, String> attributes = new HashMap<>();
  private final ArrayList<String> asyncScripts = new ArrayList<>();
  private final ArrayList<String> registeredClientEvents = new ArrayList<>();
  private final HashMap<String, Class<? extends Event<?>>> clientEventMap = new HashMap<>();
  private final Map<String, Entry<AbstractDwcjPanel, Boolean>> slots = new HashMap<>();
  private EventDispatcher eventDispatcher;
  private AbstractDwcjPanel panel;

  /**
   * Create a new web component
   */
  public WebComponent() {
    super();

    eventDispatcher = new EventDispatcher();
    hv = new HtmlContainer("");
    hv.setAttribute("bbj-hv", getUUID());
    hv.setTabTraversable(false);
    hv.setFocusable(false);
    hv.onJavascriptEvent(this::handleJavascriptEvents);
  }

  /**
   * Get the UUID of the component.
   * 
   * @return the UUID of the component.
   */
  public String getUUID() {
    return uuid;
  }

  /**
   * Check if the web component is attached to a panel.
   * 
   * @return true if the web component is attached to a panel, false otherwise
   */
  public boolean isAttached() {
    return panel != null;
  }

  /**
   * A function that is called when the web component is attached to the panel
   * and directly before any scripts are injected.
   * 
   * @param panel the panel that the web component is attached to
   */
  protected void onAttach(AbstractDwcjPanel panel) {
  }

  /**
   * A function that is called when the web component is attached to the panel
   * and directly after all scripts are injected.
   * 
   * @param panel the panel that the web component is detached from
   */
  protected void onFlush(AbstractDwcjPanel panel) {
    for (Map.Entry<String, Entry<AbstractDwcjPanel, Boolean>> entry : slots.entrySet()) {
      AbstractDwcjPanel slotPanel = entry.getValue().getKey();
      if (slotPanel != null) {
        slotPanel.setVisible(true);
      }
    }
  }

  /**
   * Get the panel that the web component is attached to or null if the web
   * component is not attached to any panel.
   * 
   * @return the panel instance or null
   */
  protected AbstractDwcjPanel getPanel() {
    return panel;
  }

  /**
   * Get the tag name of the web component
   * 
   * @return the tag name of the web component
   */
  protected String getComponentTagName() {
    if (getClass().isAnnotationPresent(NodeName.class)) {
      return getClass().getAnnotation(NodeName.class).value();
    } else {
      throw new RuntimeException("The web component class must be annotated with @WebComponentTag");
    }
  }

  /**
   * Get the event dispatcher
   * 
   * @return the event dispatcher
   */
  protected EventDispatcher getEventDispatcher() {
    return eventDispatcher;
  }

  /**
   * Get the HTML container
   * 
   * @return the HTML container
   */
  protected HtmlContainer getHtmlContainer() {
    return hv;
  }

  /**
   * Get the default html view of the web component
   * 
   * @return the default html view of the web component
   */
  protected String getView() {
    String name = getComponentTagName();

    // parse NodeAttribute annotations
    NodeAttribute[] attrs = getClass().getAnnotationsByType(NodeAttribute.class);
    StringBuilder attr = new StringBuilder();
    for (NodeAttribute a : attrs) {
      attr.append(" ").append(a.name()).append("=\"").append(a.value()).append("\"");
    }

    attr.append(" bbj-component=\"").append(getUUID()).append("\"");

    StringBuilder view = new StringBuilder();
    view.append("<").append(name).append(attr).append(">");
    view.append("</").append(name).append(">");

    return view.toString();
  }

  /**
   * Invoke a client method on the web component asynchronously
   * 
   * @param method the method name
   * @param args   the method arguments
   * 
   * @return the web component
   */
  protected T invokeAsync(String method, Object... args) {
    doInvoke(true, method, args);

    @SuppressWarnings("unchecked")
    T result = (T) this;
    return result;
  }

  /**
   * Invoke a client method on the web component
   * 
   * @param method the method name
   * @param args   the method arguments
   * 
   * @return The result of the method
   */
  protected Object invoke(String method, Object... args) {
    return doInvoke(false, method, args);
  }

  /**
   * Add an event listener
   * 
   * @param <K>              the event class
   * @param eventName        the event name as defined in the web component
   * @param eventClass       the event class
   * @param listener         the event listener
   * @param isAccepted       the javascript function to filter the event in the
   *                         client side
   * @param eventDataBuilder the javascript function to build the event data in
   *                         the client side
   * 
   * @return the web component
   */
  protected <K extends Event<?>> T addWebComponentEventListener(
      String eventName,
      Class<K> eventClass,
      EventListener<K> listener,
      String isAccepted,
      String eventDataBuilder) {
    eventDispatcher.addEventListener(eventClass, listener);
    clientEventMap.put(eventName, eventClass);

    if (!registeredClientEvents.contains(eventName)) {
      StringBuilder jsListener = new StringBuilder();
      jsListener.append("new Function('event', `");
      jsListener.append("const hv = document.querySelector('[bbj-hv=\"").append(getUUID())
          .append("\"]');");
      jsListener.append("const component = hv.querySelector('[bbj-component=\"").append(getUUID())
          .append("\"]');");
      jsListener.append("if(!hv || !hv.basisDispatchCustomEvent) return;");

      // apply a filter if one is provided to the event listener
      if (isAccepted != null) {
        String body = isAccepted;
        if (!body.contains("return")) {
          body = "return " + body;
        }

        jsListener.append("const filterFunc = new Function('event', 'component','hv', \\`").append(body)
            .append("\\`);");
        jsListener.append("if(!filterFunc(event, component, hv)) return;");
      }

      // build the event data if one is provided to the event listener
      if (eventDataBuilder != null) {
        String body = eventDataBuilder;
        if (!body.contains("return")) {
          body = "return " + body;
        }

        jsListener.append("const eventDataBuilder = new Function('event', 'component','hv', \\`").append(body)
            .append("\\`);");
        jsListener.append("const eventData = eventDataBuilder(event, component, hv);");
      }

      // stringifyEvent
      jsListener.append("const stringifyEvent = (e) => {");
      jsListener.append("const obj = {};");
      jsListener.append("for (let k in e) {");
      jsListener.append("obj[k] = e[k];");
      jsListener.append("}"); // end of for
      jsListener.append("return JSON.stringify(obj, (k, v) => {");
      jsListener.append("if (v instanceof Node) return v.nodeName;");
      jsListener.append("if (v instanceof Window) return 'Window';");
      jsListener.append("return v;");
      jsListener.append("}, ' ');");
      jsListener.append("};"); // end of stringifyEvent
      jsListener.append("hv.basisDispatchCustomEvent(hv, {type: '").append(eventName)
          .append("', detail: JSON.parse(stringifyEvent(event))});");
      jsListener.append("`)"); // end of new Function

      invokeAsync("addEventListener", eventName, new JsRawParam(jsListener.toString()));
      registeredClientEvents.add(eventName);
    }

    @SuppressWarnings("unchecked")
    T result = (T) this;
    return result;
  }

  /**
   * Add an event listener
   * 
   * @param <K>        the event class
   * @param eventName  the event name as defined in the web component
   * @param eventClass the event class
   * @param listener   the event listener
   * @param isAccepted the javascript function to filter the event in the
   *                   client side
   * 
   * @return the web component
   */
  protected <K extends Event<?>> T addWebComponentEventListener(
      String eventName,
      Class<K> eventClass,
      EventListener<K> listener,
      String isAccepted) {
    return addWebComponentEventListener(eventName, eventClass, listener, isAccepted, null);
  }

  /**
   * Add an event listener
   * 
   * @param <K>        the event class
   * @param eventName  the event name as defined in the web component
   * @param eventClass the event class
   * @param listener   the event listener
   * 
   * @return the web component
   */
  protected <K extends Event<?>> T addWebComponentEventListener(
      String eventName,
      Class<K> eventClass,
      EventListener<K> listener) {
    return addWebComponentEventListener(eventName, eventClass, listener, null, null);
  }

  /**
   * Remove an event listener
   * 
   * @param <K>        the event class
   * @param eventName  the event name as defined in the web component
   * @param eventClass the event class
   * @param listener   the event listener
   * 
   * @return the web component
   */
  protected <K extends Event<?>> T removeWebComponentEventListener(String eventName, Class<K> eventClass,
      EventListener<K> listener) {
    eventDispatcher.removeEventListener(eventClass, listener);

    @SuppressWarnings("unchecked")
    T result = (T) this;
    return result;
  }

  /**
   * Get the slot panel
   * 
   * @param slot the slot name
   * 
   * @return the slot panel
   */
  protected AbstractDwcjPanel getSlot(String slot) {
    if (slots.containsKey(slot)) {
      return slots.get(slot).getKey();
    }

    return null;
  }

  /**
   * Get the default slot panel
   * 
   * @return the default slot panel
   */
  protected AbstractDwcjPanel getSlot() {
    return getSlot("__EMPTY_SLOT__");
  }

  /**
   * Attach a slot to the web component
   * 
   * @param slot    the slot name
   * @param panel   the panel to attach
   * @param destroy if true, and the slot is assigned to another panel, the old
   *                panel will be destroyed (removed from the DOM)
   * 
   * @return the web component
   */
  protected T addSlot(String slot, AbstractDwcjPanel panel, boolean destroy) {
    if (slots.containsKey(slot)) {
      Entry<AbstractDwcjPanel, Boolean> entry = slots.get(slot);
      AbstractDwcjPanel oldPanel = entry.getKey();

      // if the new panel is different from the old one, detach the old one
      if (!oldPanel.equals(panel)) {
        removeSlot(slot);
      }
    }

    // Start tracking the slot
    slots.put(slot, new SimpleEntry<>(panel, false));

    // bbj-remove is a special attribute in DWC.
    // If the child element isn't contained in the specified parent, go ahead and
    // remove it anyway if either the child or actual parent contains the attribute
    // "bbj-remove".
    panel.setAttribute("bbj-remove", "true");

    // mark the panel with the slot name and the web component uuid
    // to be able to find it in the client side
    panel.setAttribute("bbj-slot", getUUID());
    if (slot != "__EMPTY_SLOT__") {
      // assign the slot name to the panel
      panel.setAttribute("slot", slot);
    } else {
      // mark the panel as default slot
      panel.setAttribute("bbj-default-slot", "true");
    }

    // attach the panel directly if the web component is already attached
    if (isAttached()) {
      getPanel().add(panel);
      slots.get(slot).setValue(true);
    } else {
      // hide the panel until the web component is attached
      panel.setVisible(false);
    }

    // attach the panel in the client side
    String selector = "";
    if (slot != "__EMPTY_SLOT__") {
      selector += "[slot='" + slot + "'][bbj-slot='" + getUUID() + "']";
    } else {
      selector += "[bbj-default-slot='true'][bbj-slot='" + getUUID() + "']";
    }

    invokeAsync("Function", "component.appendChild(document.querySelector(\"" + selector + "\"));");

    @SuppressWarnings("unchecked")
    T result = (T) this;
    return result;
  }

  /**
   * Attach a slot to the web component
   * 
   * @param slot  the slot name
   * @param panel the panel to attach
   * 
   * @return the web component
   */
  protected T addSlot(String slot, AbstractDwcjPanel panel) {
    return addSlot(slot, panel, true);
  }

  /**
   * Attach the default slot to the web component
   * 
   * @param panel the panel to attach
   * 
   * @return the web component
   */
  protected T addSlot(AbstractDwcjPanel panel) {
    return addSlot("__EMPTY_SLOT__", panel);
  }

  /**
   * Detach a slot from the web component
   * 
   * @param slot    the slot name
   * @param destroy if true, the panel will be destroyed (removed from the DOM)
   *                if false , the panel will be hidden without removing it from
   *                the DOM and then it is up to developer to destroy it later.
   * 
   * @return the web component
   */
  protected T removeSlot(String slot, boolean destroy) {
    if (slots.containsKey(slot)) {
      Entry<AbstractDwcjPanel, Boolean> entry = slots.get(slot);
      AbstractDwcjPanel panelToRemove = entry.getKey();

      // remove the slot attributes from the panel
      // Currently, BBjControl.removeAttributes() is not implemented
      // so we just change the attribute values without reaching the DOM
      // TODO: reimplement this when BBjControl.removeAttributes() is implemented
      // Even though if the panel is destroyed. we should remove the attributes
      if (slot != "__EMPTY_SLOT__") {
        panelToRemove.setAttribute("slot", "__detached__");
      } else {
        panelToRemove.setAttribute("bbj-default-slot", "false");
      }

      // detach the panel from the web component
      slots.remove(slot);
      if (destroy) {
        panelToRemove.destroy();
      } else {
        panelToRemove.setVisible(false);
      }
    }

    @SuppressWarnings("unchecked")
    T result = (T) this;
    return result;
  }

  /**
   * Detach a slot from the web component
   * 
   * @param slot the slot name
   * @return the web component
   */
  protected T removeSlot(String slot) {
    return removeSlot(slot, true);
  }

  /**
   * Get component attribute
   * 
   * @param name         the name of the attribute
   * @param defaultValue the default value of the attribute
   * @param fromClient   true if the attribute should be read from the client
   * 
   * @return the value of the attribute
   */
  protected String getComponentAttribute(String name, String defaultValue, boolean fromClient) {
    Object result = null;

    if (fromClient) {
      // try to get the attribute from the client
      // if the attribute is not set on the client or the client is not connected
      // the default value will be returned
      result = invoke("getAttribute", name);
      if (result == null) {
        return defaultValue;
      }
    } else {
      // try to get the attribute from the server
      // if the attribute is not set on the server the default value will be returned
      result = attributes.get(name);
      if (result == null) {
        return defaultValue;
      }
    }

    return result.toString();
  }

  /**
   * Get component attribute from the server
   * 
   * @param name         the name of the attribute
   * @param defaultValue the default value of the attribute
   * 
   * @return the value of the attribute
   */
  protected String getComponentAttribute(String name, String defaultValue) {
    return getComponentAttribute(name, defaultValue, false);
  }

  /**
   * Get component attribute from the client
   * 
   * @param name the name of the attribute
   * 
   * @return the value of the attribute
   */

  protected String getComponentAttribute(String name) {
    return getComponentAttribute(name, null, true);
  }

  /**
   * Set an attribute of the web component
   * 
   * @param name  the name of the attribute
   * @param value the value of the attribute
   * 
   * @return the web component
   */
  protected T setComponentAttribute(String name, String value) {
    attributes.put(name, value);
    invokeAsync("setAttribute", name, value);

    @SuppressWarnings("unchecked")
    T result = (T) this;
    return result;
  }

  /**
   * Set an attribute of the web component
   * 
   * @param name the name and the value of the attribute
   * 
   * @return the web component
   */
  protected T setComponentAttribute(String name) {
    return setComponentAttribute(name, name);
  }

  /**
   * Get a property of the web component
   * 
   * @param name         the name of the property
   * @param defaultValue the default value of the property
   * @param fromClient   true if the property should be read from the client
   * 
   * @return the value of the property
   */
  protected Object getComponentProperty(String name, Object defaultValue, boolean fromClient) {
    Object result = null;

    if (fromClient) {
      // try to get the property from the client
      // if the property is not set on the client or the client is not connected
      // the default value will be returned
      result = invoke("this", name);
      if (result == null) {
        return defaultValue;
      }
    } else {
      // try to get the property from the server
      // if the property is not set on the server the default value will be returned
      result = properties.get(name);
      if (result == null) {
        return defaultValue;
      }
    }

    return result;
  }

  /**
   * Get a property of the web component from the server
   * 
   * @param name         the name of the property
   * @param defaultValue the default value of the property
   * 
   * @return the value of the property
   */
  protected Object getComponentProperty(String name, Object defaultValue) {
    return getComponentProperty(name, defaultValue, false);
  }

  /**
   * Get a property of the web component from the server
   * 
   * @param name the name of the property
   * 
   * @return the value of the property
   */
  protected Object getComponentProperty(String name) {
    return getComponentProperty(name, null, false);
  }

  /**
   * Set a property of the web component
   * 
   * @param name  the name of the property
   * @param value the value of the property
   * 
   * @return the web component
   */
  protected T setComponentProperty(String name, Object value) {
    properties.put(name, value);
    invokeAsync("this", name, value);

    @SuppressWarnings("unchecked")
    T result = (T) this;
    return result;
  }

  /**
   * Set a property of the web component
   * 
   * @param name the name and the value of the property
   * 
   * @return the web component
   */
  protected T setComponentProperty(String name) {
    return setComponentProperty(name, name);
  }

  /**
   * Get a property or an attribute of the web component
   * 
   * @param <V>        the type of the property
   * @param property   the property
   * @param fromClient true if the property should be read from the client
   * @param type       the type of the property
   * 
   * @return the value of the property or attribute
   */
  protected <V> V get(PropertyDescriptor<V> property, boolean fromClient, Type type) {
    boolean isAttribute = property.isAttribute();

    if (!isAttribute) {
      if (fromClient) {
        // we need to convert the json string to the correct type.
        String json = String.valueOf(getComponentProperty(property.getName(), property.getDefaultValue(), fromClient));
        if (json == null) {
          return property.getDefaultValue();
        }

        // convert object to the correct type
        V result = new Gson().fromJson(json, type);
        return result;
      } else {
        @SuppressWarnings("unchecked")
        V result = (V) getComponentProperty(property.getName(), property.getDefaultValue(), fromClient);
        return result;
      }
    } else {
      @SuppressWarnings("unchecked")
      V result = (V) getComponentAttribute(property.getName(), String.valueOf(property.getDefaultValue()), fromClient);
      return result;
    }
  }

  /**
   * Get a property or an attribute of the web component
   * 
   * @param <V>      the type of the property
   * @param property the property
   * 
   * @return the value of the property or attribute
   */
  protected <V> V get(PropertyDescriptor<V> property) {
    return get(property, false, null);
  }

  /**
   * Set a property or an attribute of the web component
   * 
   * @param <V>      the type of the property
   * @param property the property
   * @param value    the value of the property
   * 
   * @return the web component
   */
  protected <V> T set(PropertyDescriptor<V> property, V value) {
    boolean isAttribute = property.isAttribute();

    if (!isAttribute) {
      setComponentProperty(property.getName(), value);
    } else {
      setComponentAttribute(property.getName(), value.toString());
    }

    @SuppressWarnings("unchecked")
    T result = (T) this;
    return result;
  }

  /**
   * Set a property or an attribute of the web component
   * 
   * @param <V>      the type of the property
   * @param property the property
   * 
   * @return the web component
   */
  protected <V> T set(PropertyDescriptor<V> property) {
    return set(property, property.getDefaultValue());
  }

  /**
   * Add a class name to the web component
   * 
   * @param className the class name
   * @return the web component
   */
  protected T addComponentClassName(String className) {
    return invokeAsync("classList.add", className);
  }

  /**
   * Remove a class name from the web component
   * 
   * @param className the class name
   * @return the web component
   */
  protected T removeComponentClassName(String className) {
    return invokeAsync("classList.remove", className);
  }

  /**
   * Set a style of the web component
   * 
   * @param name  the name of the style
   * @param value the value of the style
   * @return the web component
   */
  protected T setComponentStyle(String name, String value) {
    return invokeAsync("style.setProperty", name, value);
  }

  /**
   * Get a style of the web component
   * 
   * Note that if this method is called before the component is attached to the
   * DOM, it will return null.
   * 
   * @param name the name of the style
   * @return the value of the style
   */
  protected String getComponentComputedStyle(String name) {
    Object result = invoke("Function", "window.getComputedStyle(component).getPropertyValue(\\'" + name + "\\')");
    return String.valueOf(result);
  }

  /**
   * Create the control
   * 
   * @param panel the parent panel
   */
  protected void create(AbstractDwcjPanel panel) {
    this.panel = panel;
    super.create(panel);

    hv.setText(getView());
    panel.add(hv);

    // loop over the slots and add them to the web component panel
    for (Map.Entry<String, Entry<AbstractDwcjPanel, Boolean>> entry : slots.entrySet()) {
      AbstractDwcjPanel slotPanel = entry.getValue().getKey();
      boolean isAttached = entry.getValue().getValue();

      if (slotPanel != null && !isAttached) {
        panel.add(slotPanel);
      }
    }

    onAttach(panel);

    // execute scripts asynchronously
    for (String script : asyncScripts) {
      hv.executeAsyncScript(script);
    }

    onFlush(panel);
  }

  /**
   * Invoke a method of the web component
   * 
   * @param async  true if the method is async
   * @param method the method
   * @param args   the arguments
   * 
   * @return the result
   */
  private Object doInvoke(boolean async, String method, Object... args) {
    // TODO: Ask Jim to add support for async calls using executeScript
    StringBuilder script = new StringBuilder();
    script.append("(() => {");
    script.append("const hv = document.querySelector(\"[bbj-hv='").append(getUUID()).append("']\" );");
    script.append("const component = document.querySelector").append("(\"[bbj-component='").append(getUUID())
        .append("']\");");
    script.append("  if (component) {");
    // set or get property
    if (method == "this") {
      int len = args.length;
      if (len == 1) {
        // get property
        script.append("return component.").append(args[0]).append(";");
      } else if (len == 2) {
        // set property
        script.append("component.").append(args[0]).append(" = ").append(new Gson().toJson(args[1])).append(";");
        script.append("return null;");
      }
    }
    // invoke a custom function
    else if (method == "Function") {
      String body = (String) args[0];
      if (!body.contains("return")) {
        body = "return " + body;
      }
      script.append("return new Function('component','hv', `").append(body).append("`)(component);");
    }
    // invoke a method on the component
    else {
      // call method
      script.append("return component.").append(method).append("(");
      for (int i = 0; i < args.length; i++) {
        if (i > 0) {
          script.append(", ");
        }

        if (args[i] instanceof WebComponent.JsRawParam) {
          script.append(String.valueOf(args[i]));
        } else {
          script.append(new Gson().toJson(args[i]));
        }
      }
      script.append(");");
    }

    script.append("  }");
    script.append("})()");

    if (hv.getCaughtUp()) {
      if (async) {
        hv.executeAsyncScript(script.toString());
      } else {
        return hv.executeScript(script.toString());
      }
    } else {
      asyncScripts.add(script.toString());
    }

    return null;
  }

  /**
   * Handle javascript events
   * 
   * @param htmlContainerJavascriptEvent
   */
  private void handleJavascriptEvents(HtmlContainerJavascriptEvent htmlContainerJavascriptEvent) {
    Map<String, String> eventMap = htmlContainerJavascriptEvent.getEventMap();
    // the name of the server side event
    String type = (String) eventMap.get("type");

    // fire a custom event
    EventDispatcher eventDispatcher = getEventDispatcher();
    if (eventDispatcher != null && clientEventMap.containsKey(type)) {
      String detail = (String) eventMap.get("detail");
      Map<String, Object> data = new Gson().fromJson(
          detail,
          new TypeToken<Map<String, Object>>() {
          }.getType());
      Event<?> event = createEvent(clientEventMap.get(type), data);

      if (event != null) {
        eventDispatcher.dispatchEvent(event);
      }
    }
  }

  /**
   * Create a web component event
   * 
   * @param <E>        the type of the event
   * @param type       the type of the event
   * @param eventClass the class of the event
   * 
   * @return the event
   */
  private <E extends Event<?>> E createEvent(Class<E> eventClass, Map<String, Object> data) {
    E event = null;

    Constructor<?>[] constructors = eventClass.getDeclaredConstructors();
    for (Constructor<?> constructor : constructors) {
      Class<?>[] parameterTypes = constructor.getParameterTypes();
      if (parameterTypes.length == 2 &&
          WebComponent.class.isAssignableFrom(parameterTypes[0]) &&
          parameterTypes[1] == Map.class) {
        try {
          event = (E) constructor.newInstance(this, data); // NOSONAR
          break;
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
            | InvocationTargetException e) {
          Environment.logError("Failed to create webcomponent event", e);
        }
      }
    }

    return event;
  }

  /**
   * A parameter that is passed as a JavaScript expression.
   * 
   * @author Hyyan Abo Fakher
   */
  protected class JsRawParam {

    private String param;

    /**
     * Construct new instance of JsExpressionParam
     * 
     * @param param the JavaScript expression
     */
    public JsRawParam(String param) {
      this.param = param;
    }

    /**
     * Get the JavaScript expression
     * 
     * @return the JavaScript expression
     */
    public String getParam() {
      return param;
    }

    /**
     * Get the JavaScript expression as string
     * 
     * @return the JavaScript expression
     */
    @Override
    public String toString() {
      return param;
    }
  }
}
