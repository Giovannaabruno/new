package model;

/**
 * interface for PixelInterface.
 */
public interface PixelInterface {

  /**
   * Method equals, represents total components.
   *
   * @param other object
   * @return pixels components
   */
  boolean equals(Object other);

  /**
   * Method hashCode, run through each pixel's integer value.
   *
   * @return hashcode for the whole pixel value
   */
  int hashCode();


  /**
   * Method getRed, gets red component.
   *
   * @return red
   */
  int getRed();

  /**
   * Method setRed, sets reds components.
   *
   * @param red red
   */
  void setRed(int red);

  /**
   * Method getBlue, gets blue component.
   *
   * @return blue
   */
  int getBlue();

  /**
   * Method setBlue, sets blue components.
   *
   * @param blue blue
   */
  void setBlue(int blue);

  /**
   * Method getGreen, gets green component.
   *
   * @return green
   */
  int getGreen();

  /**
   * Method setGreen, sets green components.
   *
   * @param green green
   */
  void setGreen(int green);

  /**
   * Method getAlpha,gets alpha value.
   *
   * @return alpha
   */
  int getAlpha();

  /**
   * Method setAlpha, sets alpha value.
   *
   * @param alpha aplpha
   */
  void setAlpha(int alpha);

  /**
   * Method getValue, get the maximum value of the three components for each pixel.
   *
   * @return values of the image RGB
   */
  int getValue();

  /**
   * Method getIntensity, gets the average of the three components for each pixel.
   *
   * @return total intensity  of the image RGB
   */
  int getIntensity();

  /**
   * Method getLume, gets the weighted sum. if
   * luma must be between 0 and 1, add "/255" after each color (ex: red/255).
   *
   * @return luma of the image RGB
   */
  int getLuma();


  /**
   * Method getHue, get the Hue value for the image.
   *
   * @return Hue for the image HSL  values
   */
  double getHue();

  /**
   * Method getSaturation, get the Saturation value for the image.
   *
   * @return Saturation for the image HSL  values
   */
  double getSaturation();

  /**
   * Method getLightness, get the Lightness value for the image.
   *
   * @return Lightness for the image HSL  values
   */
  double getLightness();

  /**
   * Method clone, creates a copy of the red green blue alpha values.
   *
   * @return RGBA values
   */
  Pixel clone();

  /**
   * Method toString, represents an objects structure.
   *
   * @return string representation of an object
   */
  String toString();


}
