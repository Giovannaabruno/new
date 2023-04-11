package model;



import org.testng.annotations.Test;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertThrows;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Testers for Project class.
 */
public class ProjectTest {

  /**
   * TestAddLayer, add s layer effect to the original pixel.
   */
  @Test
  public void testAddLayer() {
    Project image = new Project(800, 600);
    Layer l1 = new Layer(new Pixel[800][600], "layer1");
    image.addLayer(l1);
    l1 = image.getLayer(1);
    assertEquals(l1.getName(), "layer1");

    Layer l2 = new Layer(new Pixel[800][600], "layer2");
    image.addLayer(l2);
    l2 = image.getLayer(2);
    assertEquals(l2.getName(), "layer2");
  }

  /**
   * TestGetLayer, getting the background for the image (index).
   */
  @Test
  public void testGetLayer() {
    Project image = new Project(800, 600);

    Layer l = image.getLayer(0);
    assertEquals(l.getName(), "background");
  }

  /**
   * Tester for getlayer, get the string name of the layer.
   */
  @Test
  public void getlayer() {
    Project image = new Project(800, 600);
    Layer layer = image.getLayer("background");
    assertEquals(layer.getName(), "background");
    assertNotEquals(layer.getName(), "layer-1");
  }

  /**
   * Tester for testGetNumberLayers method.
   */
  @Test
  public void testGetNumberLayers() {
    Project image = new Project(800, 600);
    image.addLayer(new Layer(800, 600, "layer1"));
    image.addLayer(new Layer(800, 600, "layer2"));
    image.addLayer(new Layer(800, 600, "layer3"));
    int num = image.getNumberLayers();
    assertEquals(4, num);
    assertNotEquals(5, num);
  }

  /**
   * TestNotGetLayer, tester when the image has a null layer.
   */
  @Test
  public void testNotGetLayer() {
    Project image = new Project(800, 600);
    assertThrows(IllegalArgumentException.class, () -> {
      Layer l = image.getLayer(-1);
    });
  }

  /**
   * Tester that test getWidth method.
   */
  @Test
  public void testGetWidth() {
    Project image = new Project(800, 600);
    assertEquals(image.getWidth(), 600);
    assertNotEquals(image.getWidth(), 800);


  }

  /**
   * Tester that test getHeight method.
   */
  @Test
  public void testGetHeight() {
    Project image = new Project(800, 600);
    assertEquals(image.getHeight(), 800);
    assertNotEquals(image.getHeight(), 700);

  }

  /**
   * Tester for combineAllLayers method.
   */
  @Test
  public void combineAllLayers() {
    Project image = new Project(800, 600);
    image.addLayer(new Layer(800, 600, "layer1"));
    image.addLayer(new Layer(800, 600, "layer2"));

    image.getLayer(1).setPixelAt(0, 0, new Pixel(30, 30, 30));
    image.getLayer(2).setPixelAt(0, 0, new Pixel(30, 30, 30));

    Layer layer = image.combineAllLayers();
    System.out.println(layer.getPixelAt(0, 0).getRed());
    assertTrue(layer.getPixelAt(0, 0).equals(new Pixel(60, 60, 60)));


  }

  /**
   * Tester that test darken method.
   */
  @Test
  public void testDarken() {
    Project image = new Project(800, 600);
    Layer l = image.getLayer(0);
    l.setPixelAt(0, 0, new Pixel(255, 255, 255));
    Pixel oldPixel = l.getPixelAt(0, 0).clone();
    l.setFilter("darken-intensity", 20);
    Pixel[][] grid = l.getFilteredGrid();
    Pixel newPixel = grid[0][0];
    assertTrue(newPixel.getRed() <= oldPixel.getRed());
    assertTrue(newPixel.getGreen() <= oldPixel.getGreen());
    assertTrue(newPixel.getBlue() <= oldPixel.getBlue());
    assertFalse(newPixel.getRed() > oldPixel.getRed());
    assertFalse(newPixel.getGreen() > oldPixel.getGreen());
    assertFalse(newPixel.getBlue() > oldPixel.getBlue());

  }

  /**
   * Tester that test darkenValue method.
   */
  @Test
  public void testDarkenValue() {
    Project image = new Project(800, 600);
    Layer l = image.getLayer(0);
    l.setPixelAt(0, 0, new Pixel(0, 255, 0));
    Pixel oldPixel = l.getPixelAt(0, 0).clone();
    l.setFilter("darken-value", 20);
    Pixel[][] grid = l.getFilteredGrid();
    Pixel newPixel = grid[0][0];
    assertTrue(newPixel.getGreen() < oldPixel.getGreen());
    assertFalse(newPixel.getGreen() > oldPixel.getGreen());
  }

  /**
   * Tester that test darkenLuma method.
   */
  @Test
  public void testDarkenLuma() {
    Project image = new Project(800, 600);
    Layer l = image.getLayer(0);
    l.setPixelAt(0, 0, new Pixel(255, 255, 255));
    Pixel oldPixel = l.getPixelAt(0, 0).clone();
    l.setFilter("darken-luma", 20);
    Pixel[][] grid = l.getFilteredGrid();
    Pixel newPixel = grid[0][0];
    assertTrue(newPixel.getRed() < oldPixel.getRed());
    assertTrue(newPixel.getGreen() < oldPixel.getGreen());
    assertTrue(newPixel.getBlue() < oldPixel.getBlue());

    assertFalse(newPixel.getRed() > oldPixel.getRed());
    assertFalse(newPixel.getGreen() > oldPixel.getGreen());
    assertFalse(newPixel.getBlue() > oldPixel.getBlue());
  }

  /**
   * Tester that test darkenBrightening method.
   */
  @Test
  public void testBrightening() {
    Project image = new Project(800, 600);
    Layer l = image.getLayer(0);
    l.setPixelAt(0, 0, new Pixel(0, 0, 0));
    Pixel oldPixel = l.getPixelAt(0, 0).clone();
    l.setFilter("brighten-intensity", 20);
    Pixel[][] grid = l.getFilteredGrid();
    Pixel newPixel = grid[0][0];
    assertTrue(newPixel.getRed() > oldPixel.getRed());
    assertTrue(newPixel.getGreen() > oldPixel.getGreen());
    assertTrue(newPixel.getBlue() > oldPixel.getBlue());
    assertFalse(newPixel.getRed() < oldPixel.getRed());
    assertFalse(newPixel.getGreen() < oldPixel.getGreen());
    assertFalse(newPixel.getBlue() < oldPixel.getBlue());
  }

  /**
   * Tester that test brighteningValue method.
   */
  @Test
  public void testBrighteningValue() {
    Project image = new Project(800, 600);
    Layer l = image.getLayer(0);
    l.setPixelAt(0, 0, new Pixel(255, 0, 255));
    Pixel oldPixel = l.getPixelAt(0, 0).clone();
    l.setFilter("brighten-value", 20);
    Pixel[][] grid = l.getFilteredGrid();
    Pixel newPixel = grid[0][0];
    assertTrue(newPixel.getGreen() >= oldPixel.getGreen());
    assertFalse(newPixel.getGreen() < oldPixel.getGreen());

  }

  /**
   * Tester that test brighteningLuma method.
   */
  @Test
  public void testBrighteningLuma() {
    Project image = new Project(800, 600);
    Layer l = image.getLayer(0);
    l.setPixelAt(0, 0, new Pixel(0, 0, 0));
    Pixel oldPixel = l.getPixelAt(0, 0).clone();
    l.setFilter("brighten-luma", 20);
    Pixel[][] grid = l.getFilteredGrid();
    Pixel newPixel = grid[0][0];
    System.out.println(newPixel);
    System.out.println(oldPixel);
    assertTrue(newPixel.getRed() >= oldPixel.getRed());
    assertTrue(newPixel.getGreen() >= oldPixel.getGreen());
    assertTrue(newPixel.getBlue() >= oldPixel.getBlue());

    assertFalse(newPixel.getRed() < oldPixel.getRed());
    assertFalse(newPixel.getGreen() < oldPixel.getGreen());
    assertFalse(newPixel.getBlue() < oldPixel.getBlue());
  }

  /**
   * Tester that test redComponent method.
   */
  @Test
  public void testRedComponent() {
    Project image = new Project(800, 600);
    Layer l = image.getLayer(0);
    l.setPixelAt(0, 0, new Pixel(255, 255, 255));
    Pixel oldPixel = l.getPixelAt(0, 0).clone();
    l.setFilter("red-component", 30);
    Pixel[][] grid = l.getFilteredGrid();
    assertEquals(grid[0][0].getRed(), oldPixel.getRed());
    assertEquals(grid[0][0].getGreen(), 0);
    assertEquals(grid[0][0].getBlue(), 0);

    assertNotEquals(grid[0][0].getRed(), 1);
    assertNotEquals(grid[0][0].getGreen(), 1);
    assertNotEquals(grid[0][0].getBlue(), 1);
  }

  /**
   * Tester that test greenComponent method.
   */
  @Test
  public void testGreenComponent() {
    Project image = new Project(800, 600);
    Layer l = image.getLayer(0);
    l.setPixelAt(0, 0, new Pixel(255, 255, 255));
    Pixel oldPixel = l.getPixelAt(0, 0).clone();
    l.setFilter("green-component", 30);
    Pixel[][] grid = l.getFilteredGrid();
    assertEquals(grid[0][0].getRed(), 0);
    assertEquals(grid[0][0].getGreen(), oldPixel.getGreen());
    assertEquals(grid[0][0].getBlue(), 0);

    assertNotEquals(grid[0][0].getRed(), 1);
    assertNotEquals(grid[0][0].getGreen(), 1);
    assertNotEquals(grid[0][0].getBlue(), 1);

  }

  /**
   * Tester that test blueComponent method.
   */
  @Test
  public void testBlueComponent() {
    Project image = new Project(800, 600);
    Layer l = image.getLayer(0);
    l.setPixelAt(0, 0, new Pixel(255, 255, 255));
    Pixel oldPixel = l.getPixelAt(0, 0).clone();
    l.setFilter("blue-component", 30);
    Pixel[][] grid = l.getFilteredGrid();
    assertEquals(grid[0][0].getRed(), 0);
    assertEquals(grid[0][0].getGreen(), 0);
    assertEquals(grid[0][0].getBlue(), oldPixel.getBlue());

    assertNotEquals(grid[0][0].getRed(), 1);
    assertNotEquals(grid[0][0].getGreen(), 1);
    assertNotEquals(grid[0][0].getBlue(), 1);


  }
  @Test
  public void testLoadProjectFromImage() {
    Project image = Project.loadProjectFromImage("images/Koala.jpg");
    assertEquals(image.getNumberLayers(), 2);
    assertEquals(image.getLayer(1).getName(), "Koala.jpg");
    Layer layer = image.getLayer(1);
    Pixel topLeft = layer.getPixelAt(0, 0);
    assertEquals(topLeft.getRed(), 101);
    assertEquals(topLeft.getGreen(), 90);
    assertEquals(topLeft.getBlue(), 58);
    assertNotEquals(topLeft.getBlue(), 8);
    Pixel somewhere = layer.getPixelAt(layer.getHeight() - 1, layer.getWidth() - 1);
    assertEquals(somewhere.getRed(), 146);
    assertEquals(somewhere.getGreen(), 118);
    assertEquals(somewhere.getBlue(), 96);


  }
  @Test
  public void testAddLayerFromImage() {
    Project image = new Project(800, 600);
    image.addLayerFromImage("images/Koala.jpg");
    assertEquals(image.getNumberLayers(), 2);
    Layer layer = image.getLayer(1);
    assertEquals(layer.getName(), "Koala.jpg");
    Pixel topLeft = layer.getPixelAt(0, 0);
    assertEquals(topLeft.getRed(),101 );
    assertEquals(topLeft.getGreen(), 90);
    assertEquals(topLeft.getBlue(), 58);
    assertNotEquals(topLeft.getBlue(), 8);



  }


}