package org.demo.shoelace.components;

import org.dwcj.annotations.InlineStyleSheet;
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
public abstract class SlComponent<T extends WebComponent<T>> extends WebComponent<T> {

  /**
   * Creates a new Shoelace component
   */
  public SlComponent() {
    super();
    getHtmlContainer().setAttribute("sl-component", "");
  }
}
