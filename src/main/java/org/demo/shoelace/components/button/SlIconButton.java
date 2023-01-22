package org.demo.shoelace.components.button;

import org.demo.shoelace.components.SlComponent;
import org.demo.shoelace.enums.SlTarget;
import org.dwcj.webcomponent.PropertyDescriptor;
import org.dwcj.webcomponent.annotations.NodeName;

/**
 * Shoelace Icon Button
 * 
 * @see <a href="https://shoelace.style/components/icon-button">Shoelace Icon
 *      Button</a>
 * @author Hyyan Abo Fakher
 * @since 1.0.0
 */
@NodeName("sl-icon-button")
public final class SlIconButton extends SlComponent {

  // name The name of the icon to draw. Available names depend on the icon library
  // being used. string | undefined -
  // library The name of a registered custom icon library. string | undefined -
  // src An external URL of an SVG file. Be sure you trust the content you are
  // including, as it will be executed as code and can result in XSS attacks.
  // string | undefined -
  // href When set, the underlying button will be rendered as an <a> with this
  // href instead of a <button>. string | undefined -
  // target Tells the browser where to open the link. Only used when href is set.
  // '_blank' | '_parent' | '_self' | '_top' | undefined -
  // download Tells the browser to download the linked file as this filename. Only
  // used when href is set. string | undefined -
  // label A description that gets read by assistive devices. For optimal
  // accessibility, you should always include a label that describes what the icon
  // button does. string ''
  // disabled Disables the button. boolean false

  // properties

  private final PropertyDescriptor<String> NAME = PropertyDescriptor.property("name", "");
  private final PropertyDescriptor<String> LIBRARY = PropertyDescriptor.property("library", "");
  private final PropertyDescriptor<String> SRC = PropertyDescriptor.property("src", "");
  private final PropertyDescriptor<String> HREF = PropertyDescriptor.property("href", "");
  private final PropertyDescriptor<String> TARGET = PropertyDescriptor.property("target", SlTarget.NONE.getValue());
  private final PropertyDescriptor<String> DOWNLOAD = PropertyDescriptor.property("download", "");
  private final PropertyDescriptor<String> LABEL = PropertyDescriptor.property("label", "");
  private final PropertyDescriptor<Boolean> DISABLED = PropertyDescriptor.property("disabled", false);

  /**
   * Set the name of the icon to draw. Available names depend on the icon library
   * being used.
   * 
   * @param name the name of the icon to draw
   */
  public SlIconButton setNAME(String name) {
    set(NAME, name);
    return this;
  }

  /**
   * Get the name of the icon to draw. Available names depend on the icon library
   * being used.
   * 
   * @return the name of the icon to draw
   */
  public String getNAME() {
    return get(NAME);
  }

  /**
   * Set the name of a registered custom icon library.
   * 
   * @param library the name of a registered custom icon library
   */
  public SlIconButton setLibrary(String library) {
    set(LIBRARY, library);
    return this;
  }

  /**
   * Get the name of a registered custom icon library.
   * 
   * @return the name of a registered custom icon library
   */
  public String getLibrary() {
    return get(LIBRARY);
  }

  /**
   * Set an external URL of an SVG file. Be sure you trust the content you are
   * including, as it will be
   * executed as code and can result in XSS attacks.
   * 
   * @param src an external URL of an SVG file
   */
  public SlIconButton setSrc(String src) {
    set(SRC, src);
    return this;
  }

  /**
   * Get an external URL of an SVG file. Be sure you trust the content you are
   * including, as it will be
   * executed as code and can result in XSS attacks.
   * 
   * @return an external URL of an SVG file
   */
  public String getSrc() {
    return get(SRC);
  }

  /**
   * Set when set, the underlying button will be rendered as an <a> with this href
   * instead of a
   * <button>.
   * 
   * @param href when set, the underlying button will be rendered as an <a> with
   *             this href instead of a
   *             <button>
   */
  public SlIconButton setHref(String href) {
    set(HREF, href);
    return this;
  }

  /**
   * Get when set, the underlying button will be rendered as an <a> with this href
   * instead of a
   * <button>.
   * 
   * @return when set, the underlying button will be rendered as an <a> with this
   *         href instead of a
   *         <button>
   */
  public String getHref() {
    return get(HREF);
  }

  /**
   * Set tells the browser where to open the link. Only used when href is set.
   * 
   * @param target tells the browser where to open the link
   */
  public SlIconButton setTarget(SlTarget target) {
    set(TARGET, target.getValue());
    return this;
  }

  /**
   * Get tells the browser where to open the link. Only used when href is set.
   * 
   * @return tells the browser where to open the link
   */
  public SlTarget getTarget() {
    return SlTarget.fromValue(get(TARGET));
  }

  /**
   * Set tells the browser to download the linked file as this filename. Only used
   * when href is set.
   * 
   * @param download tells the browser to download the linked file as this
   *                 filename
   */
  public SlIconButton setDownload(String download) {
    set(DOWNLOAD, download);
    return this;
  }

  /**
   * Get tells the browser to download the linked file as this filename. Only used
   * when href is set.
   * 
   * @return tells the browser to download the linked file as this filename
   */
  public String getDownload() {
    return get(DOWNLOAD);
  }

  /**
   * Set a description that gets read by assistive devices. For optimal
   * accessibility, you should
   * always include a label that describes what the icon button does.
   * 
   * @param label a description that gets read by assistive devices
   */
  public SlIconButton setLabel(String label) {
    set(LABEL, label);
    return this;
  }

  /**
   * Get a description that gets read by assistive devices. For optimal
   * accessibility, you should
   * always include a label that describes what the icon button does.
   * 
   * @return a description that gets read by assistive devices
   */
  public String getLabel() {
    return get(LABEL);
  }

  /**
   * Set disables the button.
   * 
   * @param disabled disables the button
   */
  public SlIconButton setDisabled(boolean disabled) {
    set(DISABLED, disabled);
    return this;
  }

  /**
   * Get disables the button.
   * 
   * @return disables the button
   */
  public boolean isDisabled() {
    return get(DISABLED, true, Boolean.class);
  }

}
