package org.demo.shoelace.components;

import org.dwcj.annotations.InlineStyleSheet;
import org.dwcj.interfaces.HasClassName;
import org.dwcj.interfaces.HasStyle;
import org.dwcj.webcomponent.WebComponent;

/**
 * Base class for all Shoelace components
 * 
 * @param <T> the type of the component
 * 
 * @author Hyyan Abo Fakher
 */
@InlineStyleSheet(id = "sl-common-styles", once = true, value = "" +
    "[sl-component]{overflow: visible}" +
    "[sl-component] .BBjHtmlView-content{overflow: visible}")
public abstract class SlComponent<T extends WebComponent<T>> extends WebComponent<T> implements HasClassName, HasStyle {

  /**
   * Creates a new Shoelace component
   */
  public SlComponent() {
    super();
    getHtmlContainer().setAttribute("sl-component", "");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public SlComponent<T> addClassName(String className) {
    addComponentClassName(className);
    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public SlComponent<T> removeClassName(String className) {
    removeComponentClassName(className);
    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public SlComponent<T> setStyle(String style, String value) {
    setComponentStyle(style, value);
    return this;
  }
}
