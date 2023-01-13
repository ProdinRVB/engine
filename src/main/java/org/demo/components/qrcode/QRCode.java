package org.demo.components.qrcode;

import java.awt.Color;

import org.dwcj.annotations.Attribute;
import org.dwcj.annotations.JavaScript;
import org.dwcj.webcomponent.PropertyDescriptor;
import org.dwcj.webcomponent.WebComponent;
import org.dwcj.webcomponent.annotations.NodeName;

/**
 * QRCode Generator using Shoelace QRCode component
 * 
 * @author Hyyan Abo Fakher
 */
@NodeName("sl-qr-code")
@JavaScript(id = "shoelace-qr-code", url = "https://cdn.jsdelivr.net/npm/@shoelace-style/shoelace@2.0.0-beta.87/dist/shoelace.js", attributes = {
    @Attribute(name = "type", value = "module")
})
public final class QRCode extends WebComponent<QRCode> {

  private static PropertyDescriptor<String> VALUE = PropertyDescriptor.property("value", "");
  private static PropertyDescriptor<Integer> SIZE = PropertyDescriptor.property("size", 200);
  private static PropertyDescriptor<String> COLOR = PropertyDescriptor.property("fill", "#000000");

  /**
   * Create a new QRCode
   */
  public QRCode() {
    super();
  }

  /**
   * Create a new QRCode with the given value
   * 
   * @param value the value of the QRCode
   */
  public QRCode(String value) {
    super();
    this.setValue(value);
  }

  /**
   * Create a new QRCode with the given value and size
   * 
   * @param value the value of the QRCode
   * @param size  the size of the QRCode
   */
  public QRCode(String value, int size) {
    super();
    this.setValue(value);
    this.setSize(size);
  }

  /**
   * Get the value of the QRCode
   * 
   * @return the value of the QRCode
   */
  public String getValue() {
    return get(VALUE);
  }

  /**
   * Set the value of the QRCode
   * 
   * @param value the value of the QRCode
   */
  public QRCode setValue(String value) {
    return set(VALUE, value);
  }

  /**
   * Get the size of the QRCode
   * 
   * @return the size of the QRCode
   */
  public int getSize() {
    return get(SIZE);
  }

  /**
   * Set the size of the QRCode
   * 
   * @param size the size of the QRCode
   */
  public QRCode setSize(int size) {
    return set(SIZE, size);
  }

  /**
   * Get the color of the QRCode
   * 
   * @return the color of the QRCode
   */
  public Color getColor() {
    String hex = get(COLOR);
    return Color.decode(hex);
  }

  /**
   * Set the color of the QRCode
   * 
   * @param color the color of the QRCode
   */
  public QRCode setColor(Color color) {
    String hex = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    return set(COLOR, hex);
  }
}