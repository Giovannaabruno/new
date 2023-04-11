package model;


import org.testng.annotations.Test;


import static org.testng.Assert.assertNotEquals;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;


/**
 * Testers for Pixel class.
 */
public class PixelTest {

  /**
   * Tester for Equals for Pixal.
   */
  @Test
  public void testEquals() {
    Pixel pixel = new Pixel(255, 255, 255);
    Pixel otherPixel = new Pixel(255, 255, 255);
    assertTrue(pixel.equals(otherPixel));

  }

  /**
   * Tester for HashCode for Coord.
   */
  @Test
  public void testHashCode() {
    Pixel pixel = new Pixel(255, 255, 255);
    int hashCode = Integer.hashCode(255) + Integer.hashCode(255)
            + Integer.hashCode(255);
    assertEquals(pixel.hashCode(), 765);
    assertNotEquals(pixel.hashCode(), 600);
  }

  /**
   * Tester to getRed component.
   */
  @Test
  public void testGetRed() {
    Pixel pixel = new Pixel(255, 255, 255);
    assertEquals(pixel.getRed(), 255);

    pixel = new Pixel(0, 0, 0);
    assertEquals(pixel.getRed(), 0);

    pixel = new Pixel(64, 255, 255);
    assertEquals(pixel.getRed(), 64);

    pixel = new Pixel(0, 255, 255);
    assertEquals(pixel.getRed(), 0);

    pixel = new Pixel(100, 255, 255);
    assertNotEquals(pixel.getRed(), 50);
  }


  /**
   * Tester to getBlue component.
   */
  @Test
  public void testGetBlue() {
    Pixel pixel = new Pixel(255, 255, 255);
    assertEquals(pixel.getBlue(), 255);

    pixel = new Pixel(0, 0, 0);
    assertEquals(pixel.getBlue(), 0);

    pixel = new Pixel(64, 255, 64);
    assertEquals(pixel.getBlue(), 64);

    pixel = new Pixel(0, 255, 76);
    assertEquals(pixel.getBlue(), 76);

    pixel = new Pixel(100, 255, 255);
    assertNotEquals(pixel.getBlue(), 50);
  }

  /**
   * Tester to getGreen component.
   */
  @Test
  public void testGetGreen() {
    Pixel pixel = new Pixel(255, 255, 255);
    assertEquals(pixel.getGreen(), 255);

    pixel = new Pixel(128, 128, 255);
    assertEquals(pixel.getGreen(), 128);

    pixel = new Pixel(64, 64, 255);
    assertEquals(pixel.getGreen(), 64);

    pixel = new Pixel(0, 40, 255);
    assertEquals(pixel.getGreen(), 40);

    pixel = new Pixel(100, 255, 255);
    assertNotEquals(pixel.getGreen(), 50);
  }

  /**
   * Tester for getAlpha method.
   */
  @Test
  public void testGetAlpha() {
    Pixel pixel = new Pixel(255, 255, 255, 255);
    assertEquals(pixel.getAlpha(), 255);

    pixel = new Pixel(128, 255, 255, 0);
    assertEquals(pixel.getAlpha(), 0);

    pixel = new Pixel(40, 255, 255, 64);
    assertEquals(pixel.getAlpha(), 64);

    pixel = new Pixel(0, 255, 255, 100);
    assertEquals(pixel.getAlpha(), 100);

    pixel = new Pixel(100, 255, 255, 200);
    assertNotEquals(pixel.getAlpha(), 50);
  }

  /**
   * Tester that test getValue method.
   */
  @Test
  public void testGetValue() {
    Pixel pixel = new Pixel(255, 40, 200, 70);
    assertEquals(pixel.getValue(), 255);

    pixel = new Pixel(40, 128, 200, 70);
    assertEquals(pixel.getValue(), 200);

    pixel = new Pixel(40, 128, 250, 70);
    assertEquals(pixel.getValue(), 250);

    pixel = new Pixel(0, 20, 80, 100);
    assertEquals(pixel.getValue(), 80);

    pixel = new Pixel(0, 20, 80, 100);
    assertNotEquals(pixel.getValue(), 0);


  }

  /**
   * Tester that test getIntensity method.
   */
  @Test
  public void testGetIntensity() {
    Pixel pixel = new Pixel(255, 40, 200, 70);
    assertEquals(pixel.getIntensity(), 165);

    pixel = new Pixel(40, 128, 200, 70);
    assertEquals(pixel.getIntensity(), 122);

    pixel = new Pixel(40, 128, 250, 70);
    assertEquals(pixel.getIntensity(), 139);

    pixel = new Pixel(0, 20, 80, 100);
    assertEquals(pixel.getIntensity(), 33);

    pixel = new Pixel(0, 20, 80, 100);
    assertNotEquals(pixel.getIntensity(), 0);

  }

  /**
   * Tester that test getLuma method.
   */
  @Test
  public void testGetLuma() {
    Pixel pixel = new Pixel(255, 40, 200, 70);
    assertEquals(pixel.getLuma(), (int) (0.2126 * (float) 255 + 0.7152 *
            (float) 40 + 0.0722 * (float) 200));

    pixel = new Pixel(40, 128, 200, 70);
    assertEquals(pixel.getLuma(), (int) (0.2126 * (float) 40 + 0.7152 *
            (float) 128 + 0.0722 * (float) 200));

    pixel = new Pixel(40, 128, 250, 70);
    assertEquals(pixel.getLuma(), (int) (0.2126 * (float) 40 + 0.7152 *
            (float) 128 + 0.0722 * (float) 250));

    pixel = new Pixel(0, 20, 80, 100);
    assertEquals(pixel.getLuma(), (int) (0.2126 * (float) 0 + 0.7152 *
            (float) 20 + 0.0722 * (float) 80));

    pixel = new Pixel(0, 20, 80, 100);
    assertNotEquals(pixel.getLuma(), (int) (0.2126 * (float) 0 + 0.7152 *
            (float) 20 + 0.0722 * (float) 70));
  }

  /**
   * Tester that test SetRed method.
   */
  @Test
  public void testSetRed() {
    Pixel pixel = new Pixel(255, 255, 255);
    pixel.setRed(127);
    assertEquals(pixel.getRed(), 127);

    pixel = new Pixel(0, 0, 0);
    pixel.setRed(70);
    assertEquals(pixel.getRed(), 70);

    pixel = new Pixel(64, 255, 255);
    pixel.setRed(0);
    assertEquals(pixel.getRed(), 0);

    pixel = new Pixel(0, 255, 255);
    pixel.setRed(100);
    assertEquals(pixel.getRed(), 100);

    pixel = new Pixel(100, 255, 255);
    pixel.setRed(90);
    assertNotEquals(pixel.getRed(), 100);

  }

  /**
   * Tester that test SetGreen method.
   */
  @Test
  public void testSetGreen() {
    Pixel pixel = new Pixel(255, 255, 255);
    pixel.setGreen(127);
    assertEquals(pixel.getGreen(), 127);

    pixel = new Pixel(0, 0, 0);
    pixel.setGreen(70);
    assertEquals(pixel.getGreen(), 70);

    pixel = new Pixel(64, 255, 255);
    pixel.setGreen(0);
    assertEquals(pixel.getGreen(), 0);

    pixel = new Pixel(0, 255, 255);
    pixel.setGreen(100);
    assertEquals(pixel.getGreen(), 100);

    pixel = new Pixel(100, 255, 255);
    pixel.setGreen(90);
    assertNotEquals(pixel.getGreen(), 100);
  }

  /**
   * Tester that test SetBlue method.
   */
  @Test
  public void testSetBlue() {
    Pixel pixel = new Pixel(255, 255, 255);
    pixel.setBlue(127);
    assertEquals(pixel.getBlue(), 127);

    pixel = new Pixel(0, 0, 0);
    pixel.setBlue(70);
    assertEquals(pixel.getBlue(), 70);

    pixel = new Pixel(64, 255, 255);
    pixel.setBlue(0);
    assertEquals(pixel.getBlue(), 0);

    pixel = new Pixel(0, 255, 255);
    pixel.setBlue(100);
    assertEquals(pixel.getBlue(), 100);

    pixel = new Pixel(100, 255, 255);
    pixel.setBlue(90);
    assertNotEquals(pixel.getBlue(), 100);
  }


  /**
   * Tester that test SetAlpha method.
   */
  @Test
  public void setAlpha() {
    Pixel pixel = new Pixel(255, 255, 255, 255);
    pixel.setAlpha(127);
    assertEquals(pixel.getAlpha(), 127);

    pixel = new Pixel(0, 0, 0, 0);
    pixel.setAlpha(70);
    assertEquals(pixel.getAlpha(), 70);

    pixel = new Pixel(64, 255, 255, 200);
    pixel.setAlpha(0);
    assertEquals(pixel.getAlpha(), 0);

    pixel = new Pixel(0, 255, 255, 210);
    pixel.setAlpha(100);
    assertEquals(pixel.getAlpha(), 100);

    pixel = new Pixel(100, 255, 255, 100);
    pixel.setAlpha(90);
    assertNotEquals(pixel.getAlpha(), 100);

  }

  /**
   * Tester that test Clone method.
   */
  @Test
  public void testClone() {
    Pixel pixel = new Pixel(1, 2, 3, 4);
    Pixel other = pixel.clone();

    assertEquals(pixel.getRed(), other.getRed());
    assertEquals(pixel.getGreen(), other.getGreen());
    assertEquals(pixel.getBlue(), other.getBlue());
    assertEquals(pixel.getAlpha(), other.getAlpha());

    assertFalse(pixel == other);
    other.setRed(100);
    assertNotEquals(100, pixel.getRed());
  }

  /**
   * Tester for testing getHue method.
   */
  @Test
  public void testGetHue() {
    Pixel pixel = new Pixel(50.5,0.5,0.8);
    assertEquals(50.5,pixel.getHue(), 0.0);
    assertNotEquals(0.5,pixel.getHue());


  }

  /**
   * Tester for testing getSaturation method.
   */
  @Test
  public void testGetSaturation() {
    Pixel pixel = new Pixel(50.5,0.5,0.8);
    assertEquals(0.5,pixel.getSaturation(),0.0);
    assertNotEquals(0.8,pixel.getSaturation());

  }

  /**
   * Tester for testing getLightness method.
   */
  @Test
  public void testGetLightness() {
    Pixel pixel = new Pixel(50.5,0.5,0.8);
    assertEquals(0.8,pixel.getLightness(),0.0);
    assertNotEquals(50.5,pixel.getLightness());

  }


  /**
   * Tester for toString.
   */
  @Test
  public void testToString() {
    Pixel pixel = new Pixel(255,255,255);
    assertEquals(pixel.toString(), "(" + 255 + ", " + 255 + ", " + 255 + ")");
    assertNotEquals(pixel.toString(), "(" + 100 + ", " + 255 + ", " + 255 + ")");
    Pixel pixel2 = new Pixel(0,0,0);
    assertEquals(pixel2.toString(), "(" + 0 + ", " + 0 + ", " + 0 + ")");
    assertNotEquals(pixel2.toString(), "(" + 100 + ", " + 50 + ", " + 25 + ")");
    Pixel pixel3 = new Pixel(50,100,20);
    assertEquals(pixel3.toString(), "(" + 50 + ", " + 100 + ", " + 20 + ")");
    assertNotEquals(pixel3.toString(), "(" + 100 + ", " + 50 + ", " + 25 + ")");
  }


}