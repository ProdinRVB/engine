package org.dwcj.webcomponent;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.function.Function;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.dwcj.Environment;

import org.dwcj.controls.AbstractControl;
import org.dwcj.controls.AbstractDwcControl;
import org.dwcj.controls.htmlcontainer.HtmlContainer;
import org.dwcj.controls.htmlcontainer.events.HtmlContainerJavascriptEvent;
import org.dwcj.controls.panels.AbstractDwcjPanel;

import org.dwcj.exceptions.DwcControlDestroyed;
import org.dwcj.exceptions.DwcRuntimeException;

import org.dwcj.webcomponent.annotations.NodeAttribute;
import org.dwcj.webcomponent.annotations.EventExpressions;
import org.dwcj.webcomponent.annotations.EventName;
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
  private final HtmlContainer hv;
  private final String uuid = UUID.randomUUID().toString().substring(0, 8);
  private final Map<String, Object> properties = new HashMap<>();
  private final Map<String, String> attributes = new HashMap<>();
  private final ArrayList<String> asyncScripts = new ArrayList<>();
  private final ArrayList<String> registeredClientEvents = new ArrayList<>();
  private final HashMap<String, Class<? extends Event<?>>> clientEventMap = new HashMap<>();
  private final Map<String, String> rawSlots = new HashMap<>();
  private final Map<String, Entry<AbstractDwcjPanel, Boolean>> slots = new HashMap<>();
  private final Map<AbstractControl, Entry<String, Boolean>> controls = new HashMap<>();
  private final EventDispatcher dispatcher = new EventDispatcher();
  private final Function<String, String> wrap = (body) -> {
    if (!body.contains("return")) {
      body = "return " + body;
    }

    return body;
  };
  private AbstractDwcjPanel panel;

  /**
   * Create a new web component
   */
  public WebComponent() {
    super();

    hv = new HtmlContainer("");
    hv.setAttribute("bbj-hv", getUUID());
    hv.setAttribute("bbj-remove", "true");
    hv.setAttribute(getComponentTagName(), "");
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
   * @return true if the web component is attached to a panel and not destroyed,
   *         false otherwise
   */
  public boolean isAttached() {
    return panel != null && !isDestroyed();
  }

  /**
   * A function that is called when the web component is attached to the panel
   * and directly before any scripts are injected.
   * 
   * @param panel the panel that the web component is attached to
   * @throws DwcControlDestroyed if the web component is destroyed
   */
  protected void onAttach(AbstractDwcjPanel panel) {
    assertNotDestroyed();
  }

  /**
   * A function that is called when the web component is attached to the panel
   * and directly after all scripts are injected.
   * 
   * @param panel the panel that the web component is detached from
   * @throws DwcControlDestroyed if the web component is destroyed
   */
  protected void onFlush(AbstractDwcjPanel panel) {
    assertNotDestroyed();

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
   * @return the panel instance or null if the web component is not attached to
   *         any panel or destroyed
   */
  protected AbstractDwcjPanel getPanel() {
    if (!isDestroyed()) {
      return panel;
    }

    return null;
  }

  /**
   * Get the event dispatcher
   * 
   * @return the event dispatcher
   */
  protected EventDispatcher getEventDispatcher() {
    return dispatcher;
  }

  /**
   * Get the HTML container
   * 
   * @return the HTML container or null if the web component is destroyed
   */
  protected HtmlContainer getHtmlContainer() {
    if (!isDestroyed()) {
      return hv;
    }

    return null;
  }

  /**
   * Get the tag name of the web component
   * 
   * @return the tag name of the web component
   * @throws DwcRuntimeException if the web component class is not annotated
   *                             with @NodeName
   */
  protected String getComponentTagName() {
    if (getClass().isAnnotationPresent(NodeName.class)) {
      return getClass().getAnnotation(NodeName.class).value();
    } else {
      throw new DwcRuntimeException(
          "The web component class must be annotated with @NodeName");
    }
  }

  /**
   * Get the default html view of the web component
   * 
   * @return the default html view of the web component or empty string if the web
   *         component is destroyed
   * 
   * @throws DwcRuntimeException if the web component class is not annotated
   *                             with @NodeName
   */
  protected String getView() {
    if (isDestroyed()) {
      return "";
    }

    String name = getComponentTagName();

    // parse NodeAttribute annotations
    NodeAttribute[] attrs = getClass().getAnnotationsByType(NodeAttribute.class);
    StringBuilder attr = new StringBuilder();
    for (NodeAttribute a : attrs) {
      attr.append(" ").append(a.name()).append("=\"").append(a.value()).append("\"");
    }

    attr.append(" bbj-component=\"").append(getUUID()).append("\"");

    StringBuilder view = new StringBuilder();
    view.append("<").append(name).append(attr).append(">")
        .append("</").append(name).append(">");

    return view.toString();
  }

  /**
   * Invoke a client method on the web component asynchronously
   * 
   * @param method the method name
   * @param args   the method arguments
   * 
   * @return the web component
   * @throws DwcControlDestroyed if the web component is destroyed
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
   * @return The web component
   * @throws DwcControlDestroyed if the web component is destroyed
   * @see #invokeAsync(String, Object...)
   */
  protected T callAsyncFunction(String functionName, Object... args) {
    return invokeAsync(functionName, args);
  }

  /**
   * Wrap the expression in a function and invoke it asynchronously on the client
   * 
   * @param expression the expression to execute
   * 
   * @return the web component
   * @throws DwcControlDestroyed if the web component is destroyed
   * @see #invokeAsync(String, Object...)
   */
  protected T executeAsyncExpression(String expression) {
    return invokeAsync("Function", expression);
  }

  /**
   * Invoke a client method on the web component
   * 
   * @param method the method name
   * @param args   the method arguments
   * 
   * @return The result of the method
   * @throws DwcControlDestroyed if the web component is destroyed
   */
  protected Object invoke(String method, Object... args) {
    return doInvoke(false, method, args);
  }

  /**
   * Invoke a client method on the web component
   * 
   * @param method the method name
   * @param args   the method arguments
   * 
   * @return The result of the method
   * @throws DwcControlDestroyed if the web component is destroyed
   * @see #invoke(String, Object...)
   */
  protected Object callFunction(String functionName, Object... args) {
    return invoke(functionName, args);
  }

  /**
   * Wrap the expression in a function and invoke it on the client
   * 
   * @param expression the expression to execute
   * 
   * @return the result of the expression
   * @throws DwcControlDestroyed if the web component is destroyed
   * @see #invoke(String, Object...)
   */
  protected Object executeExpression(String expression) {
    return invoke("Function", expression);
  }

  /**
   * Add an event listener
   * 
   * @param <K>        the event class
   * @param eventClass the event class
   * @param listener   the event listener
   * 
   * @return the web component
   * @throws DwcControlDestroyed if the web component is destroyed
   */
  protected <K extends Event<?>> T addEventListener(
      Class<K> eventClass,
      EventListener<K> listener) {
    assertNotDestroyed();

    String eventName = null;

    if (eventClass.isAnnotationPresent(EventName.class)) {
      eventName = eventClass.getAnnotation(EventName.class).value();
    } else {
      throw new DwcRuntimeException(
          "The event class must be annotated with @NodeEvent");
    }

    dispatcher.addEventListener(eventClass, listener);
    clientEventMap.put(eventName, eventClass);

    // register the event on the client side
    if (!registeredClientEvents.contains(eventName)) {
      StringBuilder js = new StringBuilder();
      js.append("new Function('event', `")
          .append("const hv = document.querySelector('[bbj-hv=\"").append(getUUID()).append("\"]');")
          .append("const component = hv.querySelector('[bbj-component=\"").append(getUUID()).append("\"]');")
          .append("if(!hv || !hv.basisDispatchCustomEvent) return;")

          .append("let isPreventDefault = false;")
          .append("let isStopPropagation = false;")
          .append("let isStopImmediatePropagation = false;")
          .append("let isAccepted = true;");

      String filter = null;
      String detail = null;
      String preventDefault = null;
      String stopPropagation = null;
      String stopImmediatePropagation = null;

      if (eventClass.isAnnotationPresent(EventExpressions.class)) {
        EventExpressions eventConfig = eventClass.getAnnotation(EventExpressions.class);
        filter = eventConfig.filter();
        detail = eventConfig.detail();
        preventDefault = eventConfig.preventDefault();
        stopPropagation = eventConfig.stopPropagation();
        stopImmediatePropagation = eventConfig.stopImmediatePropagation();
      }

      // apply prevent default if one is provided in the event listener config
      if (preventDefault != null && !preventDefault.isEmpty()) {
        js.append("const preventDefaultFunc = new Function('event', 'component','hv', \\`")
            .append(wrap.apply(preventDefault))
            .append("\\`);") // end of function
            .append("if(preventDefaultFunc(event, component, hv)) {")
            .append("event.preventDefault();")
            .append("isPreventDefault = true;")
            .append("}");
      }

      // apply stop propagation if one is provided in the event listener config
      if (stopPropagation != null && !stopPropagation.isEmpty()) {
        js.append("const stopPropagationFunc = new Function('event', 'component','hv', \\`")
            .append(wrap.apply(stopPropagation))
            .append("\\`);") // end of function
            .append("if(stopPropagationFunc(event, component, hv)) {")
            .append("event.stopPropagation();")
            .append("isStopPropagation = true;")
            .append("}");
      }

      // apply stop immediate propagation if one is provided in the event listener
      // config
      if (stopImmediatePropagation != null && !stopImmediatePropagation.isEmpty()) {
        js.append("const stopImmediatePropagationFunc = new Function('event', 'component','hv', \\`")
            .append(wrap.apply(stopImmediatePropagation))
            .append("\\`);") // end of function
            .append("if(stopImmediatePropagationFunc(event, component, hv)) {")
            .append("event.stopImmediatePropagation();")
            .append("isStopImmediatePropagation = true;")
            .append("}");
      }

      // apply a filter if one is provided to the event listener config
      if (filter != null && !filter.isEmpty()) {
        js.append("const filterFunc = new Function('event', 'component','hv', \\`")
            .append(wrap.apply(filter))
            .append("\\`);") // end of function
            .append("if(!filterFunc(event, component, hv)) {")
            .append("isAccepted = false;")
            .append("}");
      }

      js.append("if(!isAccepted) return;");

      // apply a data builder if one is provided to the event listener config
      if (detail != null && !detail.isEmpty()) {
        js.append("const detailBuilder = new Function('event', 'component','hv', \\`")
            .append(wrap.apply(detail))
            .append("\\`);") // end of function
            .append("const eventData = detailBuilder(event, component, hv);");
      }

      // stringifyEvent
      // this function is used to convert the event object to a JSON string
      // it will also convert the event target to the node name
      js.append("const stringifyEvent = (e) => {")
          .append("const obj = {};")
          .append("for (let k in e) {")
          .append("obj[k] = e[k];")
          .append("}") // end of for
          .append("return JSON.stringify(obj, (k, v) => {")
          .append("if (v instanceof Node) return v.nodeName;")
          .append("if (v instanceof Window) return 'Window';")
          .append("return v;")
          .append("}, ' ');")
          .append("};"); // end of stringifyEvent

      // dispatch the event to the server
      js.append("hv.basisDispatchCustomEvent(hv, {type: '")
          .append(eventName)
          .append("', detail: JSON.parse(stringifyEvent(event))});") // end basisDispatchCustomEvent call
          .append("`)"); // end of new Function

      // register the event listener in the client side
      invokeAsync(
          "addEventListener",
          eventName,
          new JsRawParam(js.toString()));

      registeredClientEvents.add(eventName);
    }

    @SuppressWarnings("unchecked")
    T result = (T) this;
    return result;
  }

  /**
   * Remove an event listener
   * 
   * @param <K>        the event class
   * @param eventClass the event class
   * @param listener   the event listener
   * 
   * @return the web component
   * @throws DwcControlDestroyed if the web component is destroyed
   */
  protected <K extends Event<?>> T removeEventListener(Class<K> eventClass,
      EventListener<K> listener) {
    assertNotDestroyed();
    dispatcher.removeEventListener(eventClass, listener);

    @SuppressWarnings("unchecked")
    T result = (T) this;
    return result;
  }

  /**
   * Get the added control with the given uuid
   * 
   * @param uuid the uuid
   * @return the control
   */
  protected AbstractControl getControl(String uuid) {
    return controls.entrySet().stream()
        .filter(e -> e.getValue().getKey().equals(uuid))
        .map(e -> e.getKey())
        .findFirst()
        .orElse(null);
  }

  /**
   * Add a control to the web component
   * 
   * @param control the control to add
   * @return the uuid of the control
   * @throws DwcControlDestroyed      if the web component is destroyed
   * @throws IllegalArgumentException if the control is null, the control is the
   *                                  web
   *                                  component itself or the control is
   *                                  destroyed.
   */
  protected String addControl(AbstractControl control) {
    assertNotDestroyed();

    if (control == null) {
      throw new IllegalArgumentException("Cannot add a null as a control");
    }

    if (control.equals(this)) {
      throw new IllegalArgumentException("Cannot add a web component to itself");
    }

    if (control.isDestroyed()) {
      throw new IllegalArgumentException("Cannot add a destroyed control");
    }

    if (controls.containsKey(control)) {
      return controls.get(control).getKey();
    }

    // assign a uuid to the control
    String uuid = UUID.randomUUID().toString().substring(0, 8);
    controls.put(control, new SimpleEntry<>(uuid, false));

    // add wc-link attribute to the control to link it to the web component
    if (control instanceof AbstractDwcControl) {
      ((AbstractDwcControl) control).setAttribute("wc-link", uuid);
    }

    if (control instanceof WebComponent) {
      MethodHandle method;
      try {
        // look up the getHtmlContainer method with MethodHandles

        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType mt = MethodType.methodType(HtmlContainer.class);
        method = lookup.findVirtual(control.getClass(), "getHtmlContainer", mt);
        HtmlContainer container = (HtmlContainer) method.invoke(control);
        container.setAttribute("wc-link", uuid);
      } catch (Throwable e) {
        // pass
        Environment.logError("Failed to set web component attribute. " + e.getMessage());
      }
    }

    // attach the control to the webcomponent's panel
    if (isAttached()) {
      getPanel().add(control);
      // mark as attached to the panel
      controls.get(control).setValue(true);
    }

    // move the control to the web component in the client side
    StringBuilder js = new StringBuilder();
    js.append("const selector='[wc-link=\"").append(uuid).append("\"]';")
        .append("const control = document.querySelector(selector);")
        .append("if(control)")
        .append(" component.appendChild(control);")
        .append("return;"); // avoid auto wrapping

    invokeAsync("Function", js.toString());

    return uuid;
  }

  /**
   * Remove a control from the web component
   * 
   * @param String the uuid of the control to remove
   * @return the web component
   * @throws DwcControlDestroyed if the web component is destroyed
   */
  protected T removeControl(String uuid) {
    assertNotDestroyed();

    if (uuid != null) {
      AbstractControl control = getControl(uuid);
      if (control != null) {
        controls.remove(control);
        control.destroy();
      }
    }

    @SuppressWarnings("unchecked")
    T result = (T) this;
    return result;
  }

  /**
   * Get the raw slot value (the html content)
   * 
   * @param slot the slot name
   * @return the raw slot value if the web component is not destroyed, an empty
   *         string otherwise
   */
  protected String getRawSlot(String slot) {
    if (isDestroyed()) {
      return "";
    }

    return rawSlots.get(slot);
  }

  /**
   * Get the default raw slot value (the html content)
   * 
   * @return the raw slot value if the web component is not destroyed, an empty
   *         string otherwise.
   */
  protected String getRawSlot() {
    return getRawSlot("__EMPTY_SLOT__");
  }

  /**
   * Set the raw slot value (the html content)
   * 
   * @param slot  the slot name
   * @param value the raw slot value
   * @return the web component
   * @throws DwcControlDestroyed      if the web component is destroyed
   * @throws IllegalArgumentException if the slot is already defined as a slot
   */
  protected T addRawSlot(String slot, String value) {
    assertNotDestroyed();

    if (slots.containsKey(slot)) {
      throw new IllegalArgumentException("The slot " + slot + " is already defined as a slot");
    }

    rawSlots.put(slot, value);

    String selector = "";
    if (slot != "__EMPTY_SLOT__") {
      selector += "[slot='" + slot + "'][bbj-slot='" + getUUID() + "']";
    } else {
      selector += "[bbj-default-slot='true'][bbj-slot='" + getUUID() + "']";
    }

    // add the slot to the DOM
    StringBuilder js = new StringBuilder();

    // check if the component has a span node with bbj-${slot} attribute
    js.append("var span = component.querySelector(\"" + selector + "\"); ")
        // if the component has a span node with bbj-${slot} attribute
        .append("if (span) {")
        // replace the text of the span node
        .append("span.innerHTML = \\`").append(value).append("\\`; ")
        .append("} else {")
        // if the component does not have a span node with bbj-${slot} attribute
        // create a new span node and append it to the component
        .append("span = document.createElement('span');")
        .append("span.setAttribute('bbj-slot', '" + getUUID() + "');");

    if (slot.equals("__EMPTY_SLOT__")) {
      js.append("span.setAttribute('bbj-default-slot', 'true');");
    } else {
      js.append("span.setAttribute('slot', '" + slot + "');");
    }

    js.append("span.innerHTML = \\`").append(value).append("\\`; ")
        .append("component.appendChild(span);")
        .append("}")
        .append("return '';"); // to avoid auto wrapping

    invokeAsync("Function", js.toString());

    @SuppressWarnings("unchecked")
    T result = (T) this;
    return result;
  }

  /**
   * Remove a raw slot
   * 
   * @param slot the slot name
   * @param html the html content
   * @return the web component
   * @throws DwcControlDestroyed      if the web component is destroyed
   * @throws IllegalArgumentException if the slot is already defined as a slot
   */
  protected T removeRawSlot(String slot) {
    assertNotDestroyed();

    if (slots.containsKey(slot)) {
      throw new IllegalArgumentException("The slot " + slot + " is already defined as a slot");
    }

    if (rawSlots.containsKey(slot)) {
      rawSlots.remove(slot);

      // attach the panel in the client side
      String selector = "";
      if (slot != "__EMPTY_SLOT__") {
        selector += "[slot='" + slot + "'][bbj-slot='" + getUUID() + "']";
      } else {
        selector += "[bbj-default-slot='true'][bbj-slot='" + getUUID() + "']";
      }

      // remove the slot from the DOM
      StringBuilder js = new StringBuilder();
      // check if the component has a span node with bbj-${slot} attribute
      js.append("var span = component.querySelector(\"" + selector + "\"); ")
          // if the component has a span node with bbj-${slot} attribute
          .append("if(span){")
          // remove the span node
          .append("span.remove();")
          .append("}")
          .append("return '';"); // to avoid auto wrapping

      invokeAsync("Function", js.toString());
    }

    @SuppressWarnings("unchecked")
    T result = (T) this;
    return result;
  }

  /**
   * Remove the default raw slot
   * 
   * @param html the html content
   * @return the web component
   * @throws DwcControlDestroyed      if the web component is destroyed
   * @throws IllegalArgumentException if the slot is already defined as a slot
   */
  protected T removeRawSlot() {
    return removeRawSlot("__EMPTY_SLOT__");
  }

  /**
   * Set a default raw slot value (the html content)
   * 
   * @param value the raw slot value
   * @return the web component
   * @throws DwcControlDestroyed      if the web component is destroyed
   * @throws IllegalArgumentException if the slot is already defined as a slot
   */
  protected T addRawSlot(String value) {
    return addRawSlot("__EMPTY_SLOT__", value);
  }

  /**
   * Get the slot panel
   * 
   * @param slot the slot name
   * 
   * @return the slot panel
   * @throws DwcControlDestroyed if the web component is destroyed
   */
  protected AbstractDwcjPanel getSlot(String slot) {
    if (isDestroyed()) {
      return null;
    }

    if (slots.containsKey(slot)) {
      return slots.get(slot).getKey();
    }

    return null;
  }

  /**
   * Get the default slot panel
   * 
   * @return the default slot panel
   * @throws DwcControlDestroyed if the web component is destroyed
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
   * @throws DwcControlDestroyed      if the web component is destroyed
   * @throws IllegalArgumentException if the slot is already defined as a raw slot
   */
  protected T addSlot(String slot, AbstractDwcjPanel panel, boolean destroy) {
    assertNotDestroyed();

    if (rawSlots.containsKey(slot)) {
      throw new IllegalArgumentException("The slot " + slot + " is already defined as a raw slot");
    }

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
   * @throws DwcControlDestroyed      if the web component is destroyed
   * @throws IllegalArgumentException if the slot is already defined as a raw slot
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
   * @throws DwcControlDestroyed      if the web component is destroyed
   * @throws IllegalArgumentException if the slot is already defined as a raw slot
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
   * @throws DwcControlDestroyed      if the web component is destroyed
   * @throws IllegalArgumentException if the slot is already defined as a raw slot
   */
  protected T removeSlot(String slot, boolean destroy) {
    assertNotDestroyed();

    if (rawSlots.containsKey(slot)) {
      throw new IllegalArgumentException("The slot " + slot + " is already defined as a raw slot");
    }

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
   * @throws DwcControlDestroyed      if the web component is destroyed
   * @throws IllegalArgumentException if the slot is already defined as a raw slot
   */
  protected T removeSlot(String slot) {
    return removeSlot(slot, true);
  }

  /**
   * Detach the default slot from the web component
   * 
   * @return the web component
   * @throws DwcControlDestroyed      if the web component is destroyed
   * @throws IllegalArgumentException if the slot is already defined as a raw slot
   */
  protected T removeSlot() {
    return removeSlot("__EMPTY_SLOT__");
  }

  /**
   * Get component attribute
   * 
   * @param name         the name of the attribute
   * @param defaultValue the default value of the attribute
   * @param fromClient   true if the attribute should be read from the client
   * 
   * @return the value of the attribute or the default value if the attribute is
   *         not set or the web component is destroyed
   */
  protected String getComponentAttribute(String name, String defaultValue, boolean fromClient) {
    if (isDestroyed()) {
      return defaultValue;
    }

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
   * @return the value of the attribute or the default value if the attribute is
   *         not set or the web component is destroyed.
   */
  protected String getComponentAttribute(String name, String defaultValue) {
    return getComponentAttribute(name, defaultValue, false);
  }

  /**
   * Get component attribute from the client
   * 
   * @param name the name of the attribute
   * 
   * @return the value of the attribute or null if the attribute is not set or the
   *         web component is destroyed.
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
   * @throws DwcControlDestroyed if the web component is destroyed
   */
  protected T setComponentAttribute(String name, String value) {
    invokeAsync("setAttribute", name, value);
    attributes.put(name, value);

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
   * @throws DwcControlDestroyed if the web component is destroyed
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
   * @return the value of the property or the default value if the property is not
   *         set or the web component is destroyed.
   */
  protected Object getComponentProperty(String name, Object defaultValue, boolean fromClient) {
    if (isDestroyed()) {
      return defaultValue;
    }

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
   * @return the value of the property or the default value if the property is not
   *         set or the web component is destroyed.
   */
  protected Object getComponentProperty(String name, Object defaultValue) {
    return getComponentProperty(name, defaultValue, false);
  }

  /**
   * Get a property of the web component from the server
   * 
   * @param name the name of the property
   * 
   * @return the value of the property or null if the property is not set or the
   *         web component is destroyed.
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
   * @throws DwcControlDestroyed if the web component is destroyed
   */
  protected T setComponentProperty(String name, Object value) {
    invokeAsync("this", name, value);
    properties.put(name, value);

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
   * @throws DwcControlDestroyed if the web component is destroyed
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
   * @return the value of the property or attribute or the default value if the
   *         property or attribute is not set or the web component is destroyed.
   */
  protected <V> V get(PropertyDescriptor<V> property, boolean fromClient, Type type) {
    if (isDestroyed()) {
      return property.getDefaultValue();
    }

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
   * @return the value of the property or attribute or the default value if the
   *         property or attribute is not set or the web component is destroyed.
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
   * @throws DwcControlDestroyed if the web component is destroyed
   */
  protected <V> T set(PropertyDescriptor<V> property, V value) {
    assertNotDestroyed();

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
   * @throws DwcControlDestroyed if the web component is destroyed
   */
  protected <V> T set(PropertyDescriptor<V> property) {
    return set(property, property.getDefaultValue());
  }

  /**
   * Add a class name to the web component
   * 
   * @param className the class name
   * @return the web component
   * @throws DwcControlDestroyed if the web component is destroyed
   */
  protected T addComponentClassName(String className) {
    return invokeAsync("classList.add", className);
  }

  /**
   * Remove a class name from the web component
   * 
   * @param className the class name
   * @return the web component
   * @throws DwcControlDestroyed if the web component is destroyed
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
   * @throws DwcControlDestroyed if the web component is destroyed
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
   * @return the value of the style or null if the web component is destroyed or
   *         if the component is not attached to the DOM.
   */
  protected String getComponentComputedStyle(String name) {
    Object result = invoke("Function", "window.getComputedStyle(component).getPropertyValue(\\'" + name + "\\')");
    return String.valueOf(result);
  }

  /**
   * Create the control
   * 
   * @param panel the parent panel
   * @throws DwcControlDestroyed if the web component is destroyed
   */
  protected void create(AbstractDwcjPanel panel) {
    assertNotDestroyed();

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

    // loop over the controls and add them to the web component panel
    for (Entry<AbstractControl, Entry<String, Boolean>> entry : controls.entrySet()) {
      AbstractControl control = entry.getKey();
      boolean isAttached = entry.getValue().getValue();

      if (control != null && !isAttached) {
        panel.add(control);
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
   * @throws DwcControlDestroyed if the web component is destroyed
   */
  private Object doInvoke(boolean async, String method, Object... args) {
    assertNotDestroyed();

    // TODO: Ask Jim to add support for async calls using executeScript
    StringBuilder js = new StringBuilder();
    js.append("(() => {")
        .append("const hv = document.querySelector(`[bbj-hv='").append(getUUID()).append("']`);")
        .append("const component = document.querySelector").append("(`[bbj-component='").append(getUUID())
        .append("']`);")
        .append("  if (component) {");

    // set or get property
    if (method == "this") {
      int len = args.length;
      if (len == 1) {
        // get property
        js.append("return component.").append(args[0]).append(";");
      } else if (len == 2) {
        // set property
        js.append("component.").append(args[0]).append(" = ").append(new Gson().toJson(args[1])).append(";")
            .append("return null;");
      }
    }

    // invoke a custom function
    else if (method == "Function") {
      js.append("return new Function('component','hv', `")
          .append(wrap.apply((String) args[0]))
          .append("`)(component);");
    }

    // invoke a method on the component
    else {
      js.append("return component.").append(method).append("(");

      // add arguments
      for (int i = 0; i < args.length; i++) {
        if (i > 0) {
          js.append(", ");
        }

        if (args[i] instanceof WebComponent.JsRawParam) {
          js.append(String.valueOf(args[i]));
        } else {
          js.append(new Gson().toJson(args[i]));
        }
      }

      js.append(");"); // end of method call
    }

    js.append("  }"); // end of if (component)
    js.append("})()"); // end of function

    if (hv.getCaughtUp()) {
      if (async) {
        hv.executeAsyncScript(js.toString());
      } else {
        return hv.executeScript(js.toString());
      }
    } else {
      asyncScripts.add(js.toString());
    }

    return null;
  }

  /**
   * Handle javascript events
   * 
   * @param htmlContainerJavascriptEvent
   */
  private void handleJavascriptEvents(HtmlContainerJavascriptEvent htmlContainerJavascriptEvent) {
    if (isDestroyed()) {
      return;
    }

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

      // is not inner class
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
   * {@inheritDoc}
   */
  @Override
  public void destroy() {
    if (isDestroyed()) {
      return;
    }

    getHtmlContainer().destroy();
    properties.clear();
    attributes.clear();
    asyncScripts.clear();
    registeredClientEvents.clear();
    clientEventMap.clear();
    slots.clear();
    rawSlots.clear();
    dispatcher.clear();

    super.destroy();
  }

  /**
   * Assert that the web component is not destroyed
   */
  private void assertNotDestroyed() {
    if (isDestroyed()) {
      throw new DwcControlDestroyed(
          String.format("WebComponent %s [id=%s] is destroyed", getComponentTagName(), getUUID()));
    }
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
