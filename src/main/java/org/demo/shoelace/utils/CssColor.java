package org.demo.shoelace.utils;

import java.awt.Color;

/**
 * CssColor utility class.
 * 
 * @author Hyyan Abo Fakher
 */
public final class CssColor {

  public enum Format {
    HEX("hex"),
    RGB("rgb"),
    RGBA("rgba"),
    HSL("hsl"),
    HSLA("hsla"),
    HSV("hsv"),
    HSVA("hsva");

    private final String value;

    private Format(String value) {
      this.value = value;
    }

    /**
     * @return the value
     */
    public String getValue() {
      return value;
    }

    /**
     * @param value the value to set
     */
    @Override
    public String toString() {
      return value;
    }

    /**
     * @param value the value to set
     * @return the format
     */
    public static Format fromString(String value) {
      for (Format format : Format.values()) {
        if (format.value.equalsIgnoreCase(value)) {
          return format;
        }
      }
      return null;
    }
  }

  // prevent instantiation
  private CssColor() {
  }

  /**
   * Using the given css color, return a java.awt.Color object.
   * 
   * @param value css rgb or rgba value
   * @return java.awt.Color object
   */
  public static Color fromCssString(String value) {
    // RGB
    if (value.startsWith("rgb(") && value.endsWith(")")) {
      String[] rgb = value.substring(4, value.length() - 1).split(",");
      if (rgb.length == 3) {
        int r = (int) Float.parseFloat(rgb[0].trim());
        int g = (int) Float.parseFloat(rgb[1].trim());
        int b = (int) Float.parseFloat(rgb[2].trim());

        return new Color(r, g, b);
      }
    }
    // RGBA
    else if (value.startsWith("rgba(") && value.endsWith(")")) {
      String[] rgba = value.substring(5, value.length() - 1).split(",");
      if (rgba.length == 4) {
        int r = (int) Float.parseFloat(rgba[0].trim());
        int g = (int) Float.parseFloat(rgba[1].trim());
        int b = (int) Float.parseFloat(rgba[2].trim());
        int a = (int) Float.parseFloat(rgba[3].trim());

        return new Color(r, g, b, a);
      }
    }
    // HEX
    else if (value.startsWith("#")) {
      if (value.length() == 4) {
        int r = Integer.parseInt(value.substring(1, 2), 16);
        int g = Integer.parseInt(value.substring(2, 3), 16);
        int b = Integer.parseInt(value.substring(3, 4), 16);

        return new Color(r, g, b);
      } else if (value.length() == 7) {
        int r = Integer.parseInt(value.substring(1, 3), 16);
        int g = Integer.parseInt(value.substring(3, 5), 16);
        int b = Integer.parseInt(value.substring(5, 7), 16);

        return new Color(r, g, b);
      } else if (value.length() == 9) {
        int r = Integer.parseInt(value.substring(1, 3), 16);
        int g = Integer.parseInt(value.substring(3, 5), 16);
        int b = Integer.parseInt(value.substring(5, 7), 16);
        int a = Integer.parseInt(value.substring(7, 9), 16);

        return new Color(r, g, b, a);
      }
    }
    // HSL
    else if (value.startsWith("hsl(") && value.endsWith(")")) {
      String[] hsl = value.substring(4, value.length() - 1).split(",");
      if (hsl.length == 3) {
        int h = Integer.parseInt(hsl[0].trim());
        float s = Float.parseFloat(hsl[1].trim().substring(0, hsl[1].trim().length() - 1)) / 100;
        float l = Float.parseFloat(hsl[2].trim().substring(0, hsl[2].trim().length() - 1)) / 100;

        return new Color(Color.getHSBColor(h, s, l).getRGB());
      }
    }
    // HSLA
    else if (value.startsWith("hsla(") && value.endsWith(")")) {
      String[] hsla = value.substring(5, value.length() - 1).split(",");
      if (hsla.length == 4) {
        int h = Integer.parseInt(hsla[0].trim());
        float s = Float.parseFloat(hsla[1].trim().substring(0, hsla[1].trim().length() - 1)) / 100;
        float l = Float.parseFloat(hsla[2].trim().substring(0, hsla[2].trim().length() - 1)) / 100;
        float a = Float.parseFloat(hsla[3].trim());
        Color rgb = new Color(Color.getHSBColor(h, s, l).getRGB());

        return new Color(rgb.getRed(), rgb.getGreen(), rgb.getBlue(), (int) (a * 255));
      }
    }
    // HSV
    else if (value.startsWith("hsv(") && value.endsWith(")")) {
      String[] hsb = value.substring(4, value.length() - 1).split(",");
      if (hsb.length == 3) {
        int h = Integer.parseInt(hsb[0].trim());
        float s = Float.parseFloat(hsb[1].trim().substring(0, hsb[1].trim().length() - 1)) / 100;
        float b = Float.parseFloat(hsb[2].trim().substring(0, hsb[2].trim().length() - 1)) / 100;

        return new Color(Color.getHSBColor(h, s, b).getRGB());
      }
    }
    // HSVA
    else if (value.startsWith("hsva(") && value.endsWith(")")) {
      String[] hsba = value.substring(5, value.length() - 1).split(",");
      if (hsba.length == 4) {
        int h = Integer.parseInt(hsba[0].trim());
        float s = Float.parseFloat(hsba[1].trim().substring(0, hsba[1].trim().length() - 1)) / 100;
        float b = Float.parseFloat(hsba[2].trim().substring(0, hsba[2].trim().length() - 1)) / 100;
        float a = Float.parseFloat(hsba[3].trim());
        Color rgb = new Color(Color.getHSBColor(h, s, b).getRGB());

        return new Color(rgb.getRed(), rgb.getGreen(), rgb.getBlue(), (int) (a * 255));
      }
    }
    // Named color
    else {
      return Color.getColor(value);
    }

    return null;
  }

  /**
   * Using the given java.awt.Color object, return a css color string
   * 
   * @param color  java.awt.Color object
   * @param format css color format
   * 
   * @return css color string
   */
  public static String toCssString(Color color, Format format) {
    // RGB
    if (format == Format.RGB) {
      return "rgb(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ")";
    }
    // RGBA
    else if (format == Format.RGBA) {
      return "rgba(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ","
          + color.getAlpha() + ")";
    }
    // HEX
    else if (format == Format.HEX) {
      return "#" + toHex(color.getRed()) + toHex(color.getGreen()) + toHex(color.getBlue());
    }
    // HSL
    else if (format == Format.HSL) {
      float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
      return "hsl(" + hsb[0] + "," + hsb[1] + "%," + hsb[2] + "%)";
    }
    // HSLA
    else if (format == Format.HSLA) {
      float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
      return "hsla(" + hsb[0] + "," + hsb[1] + "%," + hsb[2] + "%," + color.getAlpha() + ")";
    }
    // HSV
    else if (format == Format.HSV) {
      float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
      return "hsv(" + hsb[0] + "," + hsb[1] + "%," + hsb[2] + "%)";
    }
    // HSVA
    else if (format == Format.HSVA) {
      float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
      return "hsva(" + hsb[0] + "," + hsb[1] + "%," + hsb[2] + "%," + color.getAlpha() + ")";
    } else {
      return null;
    }
  }

  /**
   * Convert an integer to a hex string
   * 
   * @param value integer value
   * 
   * @return hex string
   */
  public static String toHex(int value) {
    String hex = Integer.toHexString(value);
    if (hex.length() == 1) {
      hex = "0" + hex;
    }

    return hex;
  }
}
