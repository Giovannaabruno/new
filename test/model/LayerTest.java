package model;


import org.testng.annotations.Test;

import static org.testng.Assert.assertNotEquals;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;


/**
 * Tester for Layer class.
 */
public class LayerTest {

  /**
   * Tester that test getHeight method.
   */
  @Test
  public void testGetHeight() {
    Layer ll = new Layer(800, 600, "image/tako.pmm");
    assertEquals(ll.getHeight(), 800);
    assertNotEquals(ll.getHeight(), 500);
  }

  /**
   * Tester that test getWidth method.
   */
  @Test
  public void testGetWidth() {
    Layer ll = new Layer(800, 600, "image/tako.pmm");
    assertEquals(ll.getWidth(), 600);
    assertNotEquals(ll.getWidth(), 500);
  }

  /**
   * Tester that test getName method.
   */
  @Test
  public void testGetName() {
    Layer ll = new Layer(800, 600, "image/tako.pmm");
    assertEquals(ll.getName(), "image/tako.pmm");
    assertNotEquals(ll.getName(), "mage.tako.pmm");

  }

  /**
   * Tester that test getGrid method.
   */
  @Test
  public void testGetGrid() {
    Layer ll = new Layer(800, 600, "image/tako.pmm");
    Pixel[][] grid = ll.getGrid();

    Pixel p1 = grid[0][0];
    assertEquals(p1.getRed(), 0);
    assertEquals(p1.getGreen(), 0);
    assertEquals(p1.getBlue(), 0);
    Pixel p2 = grid[ll.getHeight() / 2][ll.getWidth() / 2];
    assertEquals(p2.getRed(), 0);
    assertEquals(p2.getGreen(), 0);
    assertEquals(p2.getBlue(), 0);
    Pixel p3 = grid[ll.getHeight() - 1][ll.getWidth() - 1];
    assertEquals(p3.getRed(), 0);
    assertEquals(p3.getGreen(), 0);
    assertEquals(p3.getBlue(), 0);

    assertNotEquals(grid[ll.getHeight() / 2][ll.getWidth() / 2],
            new Pixel(255, 128, 255, 255));
  }

  /**
   * Tester that test getPixelAt method.
   */
  @Test
  public void testGetPixelAt() {
    Layer ll = new Layer(800, 600, "image/tako.pmm");
    Pixel[][] grid = ll.getGrid();
    Pixel p1 = grid[0][0];
    assertEquals(p1.getRed(), 0);
    assertEquals(p1.getGreen(), 0);
    assertEquals(p1.getBlue(), 0);

    Pixel p2 = grid[ll.getHeight() / 2][ll.getWidth() / 2];
    assertEquals(p2.getRed(), 0);
    assertEquals(p2.getGreen(), 0);
    assertEquals(p2.getBlue(), 0);

    Pixel p3 = grid[ll.getHeight() - 1][ll.getWidth() - 1];
    assertEquals(p3.getRed(), 0);
    assertEquals(p3.getGreen(), 0);
    assertEquals(p3.getBlue(), 0);

    assertNotEquals(grid[ll.getHeight() / 2][ll.getWidth() / 2],
            new Pixel(255, 128, 255, 255));


  }

  /**
   * Tester for SetPixelAt method.
   */
  @Test
  public void testSetPixelAt() {
    Layer ll = new Layer(800, 600, "image/tako.pmm");
    ll.setPixelAt(0, 0, new Pixel(255, 255, 255, 255));
    ll.setPixelAt(0, 1, new Pixel(0, 255, 255, 255));
    ll.setPixelAt(0, 2, new Pixel(100, 200, 255, 255));
    ll.setPixelAt(1, 0, new Pixel(0, 255, 100, 20));
    assertEquals(ll.getPixelAt(0, 0),
            new Pixel(255, 255, 255, 255));
    assertEquals(ll.getPixelAt(0, 1),
            new Pixel(0, 255, 255, 255));
    assertEquals(ll.getPixelAt(0, 2),
            new Pixel(100, 200, 255, 255));
    assertEquals(ll.getPixelAt(1, 0),
            new Pixel(0, 255, 100, 20));
    assertNotEquals(ll.getPixelAt(0, 0),
            new Pixel(255, 128, 255, 255));
  }


  /**
   * Tester that test setFilter method.
   */
  @Test
  public void setFilter() {
    Layer ll = new Layer(800, 600, "image.tako.pmm");
    ll.setFilter("red-component", 80);
    assertEquals(ll.getFilter(), "red-component");
    assertNotEquals(ll.getFilter(), "red");

    ll.setFilter("green-component", 80);
    assertEquals(ll.getFilter(), "green-component");
    assertNotEquals(ll.getFilter(), "green");
    ll.setFilter("blue-component", 80);
    assertNotEquals(ll.getFilter(), "blue");
  }

  /**
   * Tester that test SetFilter method.
   */
  @Test
  public void testSetFilter() {
    Layer ll = new Layer(800, 600, "image/tako.pmm");
    ll.setFilter("red-component", 80);
    assertEquals(ll.getFilter(), "red-component");
    assertNotEquals(ll.getFilter(), "red");

    ll.setFilter("green-component", 80);
    assertEquals(ll.getFilter(), "green-component");
    assertNotEquals(ll.getFilter(), "green");

    ll.setFilter("blue-component", 80);
    assertNotEquals(ll.getFilter(), "blue");

  }

  /**
   * Tester for applyFilter.
   */
  @Test
  public void applyFilter() {
    Layer ll = new Layer(800, 600, "image/tako.pmm");
    ll.setFilter("red-component", 80);
    ll.applyFilter(null);
    Pixel p = ll.getPixelAt(0, 0);
    assertEquals(p.getGreen(), 0);
    assertEquals(p.getBlue(), 0);
    // Proves it not equals
    p = ll.getPixelAt(100, 100);
    assertEquals(p.getGreen(), 0);
    assertEquals(p.getBlue(), 0);


  }

  /**
   * Tester that test GetFilter method.
   */
  @Test
  public void testGetFilter() {
    Layer ll = new Layer(800, 600, "image/tako.pmm");
    ll.setFilter("red-component", 80);
    assertEquals(ll.getFilter(), "red-component");
    assertNotEquals(ll.getFilter(), "red");

    ll.setFilter("green-component", 80);
    assertEquals(ll.getFilter(), "green-component");
    assertNotEquals(ll.getFilter(), "green");

    ll.setFilter("blue-component", 80);
    assertNotEquals(ll.getFilter(), "blue");


  }

  /**
   * Tester that test FilteredGrid method. PART 1.
   */
  @Test
  public void testGetFilteredGrid() {
    Layer ll = new Layer(800, 600, "image/tako.pmm");
    int amount = 30;
    ll.setFilter("red-component", amount);
    Pixel[][] grid = ll.getFilteredGrid();

    Pixel p1 = grid[0][0];
    assertEquals(p1.getGreen(), 0);
    assertEquals(p1.getBlue(), 0);

    Pixel p2 = grid[ll.getHeight() / 2][ll.getWidth() / 2];
    assertEquals(p2.getGreen(), 0);
    assertEquals(p2.getBlue(), 0);

    Pixel p3 = grid[ll.getHeight() - 1][ll.getWidth() - 1];
    assertEquals(p3.getGreen(), 0);
    assertEquals(p3.getBlue(), 0);

    assertNotEquals(grid[ll.getHeight() / 2][ll.getWidth() / 2],
            new Pixel(855, 0, 0, 255));

  }

  /**
   * Tester for isBlending method.
   */
  @Test
  public void testisBlending() {
    Layer ll = new Layer(800, 600, "image/tako.pmm");
    ll.setFilter("brightenBlending", 10);
    assertTrue(ll.isBlending());
  }

  /**
   * Tester that test FilteredGrid method. PART combineAllLayers2.
   */
  @Test
  public void GetFilteredGrid() {
    Layer ll = new Layer(800, 600, "image/tako.pmm");
    int amount = 30;
    Layer l2 = new Layer(800, 600, "image/tako.pmm");
    ll.setFilter("inversionBlending", amount);
    Pixel[][] grid = ll.getFilteredGrid(l2);
    Pixel topLeft = grid[0][0];
    assertEquals(topLeft.getRed(), 0);
    assertEquals(topLeft.getBlue(), 0);
    assertEquals(topLeft.getGreen(), 0);

  }


  /**
   * Tester for InversionBlending.
   */
  @Test
  public void testInversionBlending() {
    Layer l1 = new Layer(800, 600, "image/tako.pmm");
    Layer l2 = new Layer(800, 600, "image/tako.pmm");
    l1.setFilter("inversionBlending", 40);
    Pixel[][] mix = l1.getFilteredGrid(l2);
    assertEquals(0, mix[0][0].getRed());
    assertEquals(0, mix[0][0].getGreen());
    assertEquals(0, mix[0][0].getBlue());
    assertNotEquals(1, mix[1][6].getRed());
    assertNotEquals(1, mix[2][1].getGreen());
    assertNotEquals(1, mix[4][0].getBlue());


  }

  /**
   * Tester for testing darkenBlending method.
   */
  @Test
  public void testdarkenBlending() {
    Layer l1 = new Layer(800, 600, "image/tako.pmm");
    Layer l2 = new Layer(800, 600, "image/tako.pmm");
    l1.setFilter("darkenBlending", 80);

    double lightness = l1.getPixelAt(0, 0).getLightness();
    double lightnessD = l2.getPixelAt(0, 0).getLightness();
    lightness *= lightnessD;

    Pixel[][] mix = l1.getFilteredGrid(l2);
    assertEquals(0.0, mix[0][0].getLightness(), 0.01);

    lightness = l1.getPixelAt(1, 0).getLightness();
    lightnessD = l2.getPixelAt(1, 0).getLightness();
    lightness *= lightnessD;
    assertEquals(0.0, mix[0][0].getLightness(), 0.01);


  }

  /**
   * Tester for testing brighteningBlending method.
   */
  @Test
  public void testbrighteningBlending() {
    Layer l = new Layer(800, 600, "image/tako.pmm");
    Layer ld = new Layer(800, 600, "image/tako.pmm");
    l.setFilter("brightenBlending", 80);

    double lightness = l.getPixelAt(0, 0).getLightness();
    double lightnessD = ld.getPixelAt(0, 0).getLightness();
    double newLightness = (1 - ((1 - lightness) * (1 - lightnessD)));
    System.out.println(newLightness);
    Pixel[][] mix = l.getFilteredGrid(ld);
    assertEquals(newLightness, mix[0][0].getLightness(), 0.001);

    lightness = l.getPixelAt(1, 0).getLightness();
    lightnessD = ld.getPixelAt(1, 0).getLightness();
    newLightness = (1 - ((1 - lightness) * (1 - lightnessD)));
    assertEquals(newLightness, mix[1][0].getLightness(), 0.001);
  }
}