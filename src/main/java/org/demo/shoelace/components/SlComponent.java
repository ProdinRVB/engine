package org.demo.shoelace.components;

import org.dwcj.App;
import org.dwcj.controls.panels.AbstractDwcjPanel;
import org.dwcj.environment.ObjectTable;
import org.dwcj.interfaces.HasAttribute;
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
public class SlComponent extends WebComponent implements HasClassName, HasStyle, HasAttribute {

  private static boolean attachedStylesheets = false;

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
  public SlComponent addClassName(String className) {
    addComponentClassName(className);
    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public SlComponent removeClassName(String className) {
    removeComponentClassName(className);
    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public SlComponent setStyle(String style, String value) {
    setComponentStyle(style, value);
    return this;
  }

  /**
   * {@inheritDoc}
   */
  public String getAttribute(String attribute) {
    return getComponentAttribute(attribute);
  }

  /**
   * {@inheritDoc}
   */
  public SlComponent setAttribute(String attribute, String value) {
    setComponentAttribute(attribute, value);
    return this;
  }

  /**
   * {@inheritDoc}
   */
  protected void create(AbstractDwcjPanel panel) {
    if (this.isAttached())
      return; // prevent multiple calls

    super.create(panel);

    // attach the stylesheets
    String key = "dwcj.styles.__attacheSLStyleSheets__";
    boolean attached = ObjectTable.contains(key);
    if (!attached) {
      App.addInlineStyleSheet(getStylesheets(), false, "id=sl-component-styles");
      ObjectTable.put(key, true);
    }
  }

  private String getStylesheets() {
    return "[sl-component]{overflow: visible}" +
        "[sl-component] .BBjHtmlView-content{overflow: visible}";
  }
}
