package model;

/**
 * Class Pixel which implements PixelInterface.
 */
public class Pixel {
  private int red;
  private int green;
  private int blue;

  private int alpha;

  private double hue;

  private double saturation;

  private double lightness;


  /**
   * Constructor for Pixel, represents integer objects red, green, and blue.
   *
   * @param red   equals red
   * @param green equals green
   * @param blue  equals blue
   */
  public Pixel(int red, int green, int blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.alpha = 255;
    convertRGBtoHSL();
  }

  /**
   * Constructor for Pixel, represents alpha and integer objects red, green, and blue.
   *
   * @param red   equals red
   * @param green equals green
   * @param blue  equals blue
   * @param alpha the amount of transparency
   */
  public Pixel(int red, int green, int blue, int alpha) {
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.alpha = alpha;
    convertRGBtoHSL();
  }


  /**
   * Constructor for Pixel, represents integer objects hue, saturation, and lightness.
   *
   * @param hue        equals hue
   * @param saturation equals saturation
   * @param lightness  equals lightness
   */
  public Pixel(double hue, double saturation, double lightness) {
    this.hue = hue;
    this.saturation = saturation;
    this.lightness = lightness;
    this.alpha = 255;
    convertHSLtoRGB();
  }

  /**
   * Constructor for Pixel, represents alpha and integer hue, saturation, and lightness.
   *
   * @param hue        equals hue
   * @param saturation equals saturation
   * @param lightness  equals lightness
   * @param alpha      amount for the total value
   */
  public Pixel(double hue, double saturation, double lightness, int alpha) {
    this.hue = hue;
    this.saturation = saturation;
    this.lightness = lightness;
    this.alpha = alpha;
    convertHSLtoRGB();
  }


  /**
   * Converts an RGB representation in the range 0-1 into an HSL
   * representation where.
   */
  private void convertRGBtoHSL() {
    int r = red;
    int g = green;
    int b = blue;

    double componentMax = Math.max(r, Math.max(g, b));
    double componentMin = Math.min(r, Math.min(g, b));
    double delta = componentMax - componentMin;

    double lightness = (componentMax + componentMin) / 2;
    double hue;
    double saturation;
    if (delta == 0) {
      hue = 0;
      saturation = 0;
    } else {
      saturation = delta / (1 - Math.abs(2 * lightness - 1));
      hue = 0;
      if (componentMax == r) {
        hue = (g - b) / delta;
        hue = hue % 6;
      } else if (componentMax == g) {
        hue = (b - r) / delta;
        hue += 2;
      } else if (componentMax == b) {
        hue = (r - g) / delta;
        hue += 4;
      }

      hue = hue * 60;
    }

    this.hue = hue;
    this.saturation = saturation / 256;
    this.lightness = lightness / 256;
  }


  /**
   * Convers an HSL representation where.
   */
  private void convertHSLtoRGB() {
    double r = convertFn(hue, saturation, lightness, 0) * 255;
    double g = convertFn(hue, saturation, lightness, 8) * 255;
    double b = convertFn(hue, saturation, lightness, 4) * 255;


    this.red = (int) (r * 256);
    this.green = (int) (g * 256);
    this.blue = (int) (b * 256);

  }


  /**
   * Method convertFn, Helper method that performs the translation from the HSL polygonal
   * model to the more familiar RGB model.
   *
   * @param hue        equals hue
   * @param saturation equals saturation
   * @param lightness  equals lightness
   * @param n          number amount
   * @return calculation for conversion, total amount
   */
  private double convertFn(double hue, double saturation, double lightness, int n) {
    double k = (n + (hue / 30)) % 12;
    double a = saturation * Math.min(lightness, 1 - lightness);

    return lightness - a * Math.max(-1, Math.min(k - 3, Math.min(9 - k, 1)));
  }


  /**
   * Method equals, represents total components.
   *
   * @param other object
   * @return pixels components
   */
  @Override
  public boolean equals(Object other) {
    Pixel otherPixel = (Pixel) other;
    return this.red == otherPixel.red && this.blue == otherPixel.blue
            && this.green == otherPixel.green && this.alpha == otherPixel.alpha;
  }


  /**
   * Method hashCode, run through each pixel's integer value.
   *
   * @return hashcode for the whole pixel value
   */
  @Override
  public int hashCode() {

    return Integer.hashCode(this.red) + Integer.hashCode(this.blue) + Integer.hashCode(this.green);
  }


  /**
   * Method getRed, gets red component.
   *
   * @return red
   */
  public int getRed() {
    return red;
  }

  /**
   * Method setRed, sets reds components.
   *
   * @param red red
   */
  public void setRed(int red) {
    this.red = red;
  }

  /**
   * Method getBlue, gets blue component.
   *
   * @return blue
   */
  public int getBlue() {
    return blue;
  }

  /**
   * Method setBlue, sets blue components.
   *
   * @param blue blue
   */
  public void setBlue(int blue) {
    this.blue = blue;
  }

  /**
   * Method getGreen, gets green component.
   *
   * @return green
   */
  public int getGreen() {
    return green;
  }

  /**
   * Method setGreen, sets green components.
   *
   * @param green green
   */
  public void setGreen(int green) {
    this.green = green;
  }

  /**
   * Method getAlpha,gets alpha value.
   *
   * @return alpha
   */
  public int getAlpha() {
    return alpha;
  }

  /**
   * Method setAlpha, sets alpha value.
   *
   * @param alpha aplpha
   */
  public void setAlpha(int alpha) {
    this.alpha = alpha;
  }

  /**
   * Method getValue, get the maximum value of the three components for each pixel.
   *
   * @return values of the image RGB
   */
  public int getValue() {
    return Math.max(red, Math.max(green, blue));
  }

  /**
   * Method getIntensity, gets the average of the three components for each pixel.
   *
   * @return total intensity  of the image RGB
   */
  public int getIntensity() {
    return (red + green + blue) / 3;
  }

  /**
   * Method getLume, gets the weighted sum. if
   * luma must be between 0 and 1, add "/255" after each color (ex: red/255).
   *
   * @return luma of the image RGB
   */
  public int getLuma() {
    return (int) (0.2126 * (float) red + 0.7152 * (float) green + 0.0722 * (float) blue);

  }

  /**
   * Method getHue, get the Hue value for the image.
   *
   * @return Hue for the image HSL  values
   */
  public double getHue() {
    return hue;
  }

  /**
   * Method getSaturation, get the Saturation value for the image.
   *
   * @return Saturation for the image HSL  values
   */
  public double getSaturation() {
    return saturation;
  }

  /**
   * Method getLightness, get the Lightness value for the image.
   *
   * @return Lightness for the image HSL  values
   */
  public double getLightness() {
    return lightness;
  }

  /**
   * Method clone, creates a copy of the red green blue alpha values.
   *
   * @return RGBA values
   */
  @Override
  public Pixel clone() {
    return new Pixel(red, green, blue, alpha);
  }

  /**
   * Method toString, represents an objects structure.
   *
   * @return string representation of an object
   */
  @Override
  public String toString() {
    return "(" + this.getRed() + ", " + this.getGreen() + ", " + this.getBlue() + ")";
  }
}
