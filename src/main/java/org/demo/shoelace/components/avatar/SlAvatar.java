package org.demo.shoelace.components.avatar;

import org.demo.shoelace.components.SlComponent;
import org.dwcj.webcomponent.PropertyDescriptor;
import org.dwcj.webcomponent.annotations.NodeName;

/**
 * The avatar component.
 * 
 * @see <a href="https://shoelace.style/components/avatar">Shoelace Avatar</a>
 * @author Hyyan Abo Fakher
 * @since 1.0.0
 */
@NodeName("sl-avatar")
public final class SlAvatar extends SlComponent<SlAvatar> {

  /**
   * Indicates how the browser should load the image.
   */
  public enum LoadingStrategy {
    EAGER("eager"),
    LAZY("lazy");

    private final String value;

    LoadingStrategy(String value) {
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
   * The shape of the avatar.
   */
  public enum Shape {
    // 'circle' | 'square' | 'rounded'
    CIRCLE("circle"),
    SQUARE("square"),
    ROUNDED("rounded");

    private final String value;

    Shape(String value) {
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

  private String size = "3rem";

  // Properties
  private static PropertyDescriptor<String> IMAGE = PropertyDescriptor.property("image", "");
  private static PropertyDescriptor<String> LABEL = PropertyDescriptor.property("label", "");
  private static PropertyDescriptor<String> INITIALS = PropertyDescriptor.property("initials", "");
  private static PropertyDescriptor<String> LOADING = PropertyDescriptor.property("loading",
      LoadingStrategy.EAGER.getValue());
  private static PropertyDescriptor<String> SHAPE = PropertyDescriptor.property("shape", Shape.CIRCLE.getValue());

  /**
   * Create new avatar.
   */
  public SlAvatar() {
    super();
  }

  /**
   * Create new avatar with the given image source.
   * 
   * @param image the image source
   */
  public SlAvatar(String image) {
    this();
    setImage(image);
  }

  /**
   * The image source to use for the avatar.
   * 
   * @param image the image source
   * @return the avatar
   */
  public SlAvatar setImage(String image) {
    set(IMAGE, image);
    return this;
  }

  /**
   * The image source to use for the avatar.
   * 
   * @return the image source
   */
  public String getImage() {
    return get(IMAGE);
  }

  /**
   * A label to use to describe the avatar to assistive devices.
   * 
   * @param label the label
   * @return the avatar
   */
  public SlAvatar setLabel(String label) {
    set(LABEL, label);
    return this;
  }

  /**
   * A label to use to describe the avatar to assistive devices.
   * 
   * @return the label
   */
  public String getLabel() {
    return get(LABEL);
  }

  /**
   * Initials to use as a fallback when no image is available (1-2 characters max
   * recommended).
   * 
   * @param initials the initials
   * @return the avatar
   */
  public SlAvatar setInitials(String initials) {
    set(INITIALS, initials);
    return this;
  }

  /**
   * Initials to use as a fallback when no image is available (1-2 characters max
   * recommended).
   * 
   * @return the initials
   */
  public String getInitials() {
    return get(INITIALS);
  }

  /**
   * Indicates how the browser should load the image.
   * 
   * @param loading the loading strategy
   * @return the avatar
   */
  public SlAvatar setLoading(LoadingStrategy loading) {
    set(LOADING, loading.getValue());
    return this;
  }

  /**
   * Indicates how the browser should load the image.
   * 
   * @return the loading strategy
   */
  public LoadingStrategy getLoading() {
    return LoadingStrategy.valueOf(get(LOADING));
  }

  /**
   * The shape of the avatar.
   * 
   * @param shape the shape
   * @return the avatar
   */
  public SlAvatar setShape(Shape shape) {
    set(SHAPE, shape.getValue());
    return this;
  }

  /**
   * The shape of the avatar.
   * 
   * @return the shape
   */
  public Shape getShape() {
    return Shape.valueOf(get(SHAPE));
  }

  /**
   * Set the avatar size.
   * 
   * @param size the size
   * @return the avatar
   */
  public SlAvatar setSize(String size) {
    this.size = size;
    setComponentStyle("--size", size);
    return this;
  }

  /**
   * Get the avatar size.
   * 
   * @return the size
   */
  public String getSize() {
    return size;
  }
}
