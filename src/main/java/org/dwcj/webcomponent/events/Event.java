package org.dwcj.webcomponent.events;

import java.util.EventObject;
import java.util.Map;

import org.dwcj.controls.AbstractControl;
import org.dwcj.interfaces.ControlEvent;
import org.dwcj.webcomponent.WebComponent;

/**
 * The Class WcEvent. This class is the base class for all events fired by the
 * web components.
 * 
 * @param <T>
 *            the generic type
 */
public class Event<T extends WebComponent<T>> extends EventObject implements ControlEvent {

  private Map<String, Object> eventMap;

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public Event(T control, Map<String, Object> eventMap) {
    super(control);
    this.eventMap = eventMap;
  }

  /**
   * @return the event map
   */
  public Map<String, Object> getData() {
    return eventMap;
  }

  /**
   * Alias for {@link #getData()} method.
   * 
   * @return the event map
   */
  public Map<String, Object> getEventMap() {
    return getData();
  }

  /**
   * Gets the control.
   * 
   * @return the control
   */
  @Override
  public AbstractControl getControl() {
    return (AbstractControl) getSource();
  }

  /**
   * Gets the web component.
   * 
   * @return the web component
   */
  public T getWebComponent() {
    return (T) getSource(); // NOSONAR
  }
}