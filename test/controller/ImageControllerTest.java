package controller;


import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import model.Layer;
import model.Pixel;
import view.textui.Textui;

import static org.testng.Assert.assertNotEquals;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Testers for ImageController class.
 */
public class ImageControllerTest {

  /**
   * Tester that test LoadImage method.
   */
  @Test
  public void testLoadImage() {
    ImageController ic = new ImageController();
    ic.newProject(800,600);
    Layer l = ic.loadImage("images/tako.ppm", "layer1");
    assertNotNull(l);

  }

  /**
   * Test Load Image - with Valid PPM file format - check Height.
   */
  @Test
  public void testLoadImageValidFormatHeight() {
    ImageController imgCtr = new ImageController();
    imgCtr.newProject(800,600);
    Layer l1 = imgCtr.loadImage("images/tako.ppm", "dark");
    assertEquals(800, l1.getHeight());
  }

  /**
   * Test Load Image - with Valid PPM file format - check Width.
   */
  @Test
  public void testLoadImageValidFormatWidth() {
    ImageController imgCtr = new ImageController();
    imgCtr.newProject(800,600);
    Layer l1 = imgCtr.loadImage("images/tako.ppm", "dark");
    assertEquals(600, l1.getWidth());
  }

  /**
   * Test Load Image - with invalid PPM file format.
   */
  @Test
  public void testLoadImageWrongFormat() {
    ImageController imgCtr = new ImageController();
    assertEquals(null,
            imgCtr.loadImage("images/wrong_format.ppm", "dark"));
  }


  /**
   * Tester that test NewProject method with dimensions - check width.
   *
   * @throws IOException invalid width
   */
  @Test
  public void testNewProjectCheckWidth() throws IOException {
    ImageController imgCtr = new ImageController();
    Textui textui = new Textui(imgCtr);
    textui.readCommand("new-project 800 600");
    assertEquals(600, imgCtr.img.getWidth());
    assertNotEquals(800, imgCtr.img.getWidth());
  }

  /**
   * Tester that test NewProject method with dimensions - check height.
   *
   * @throws IOException invalid height
   */
  @Test
  public void testNewProjectCheckHeight() throws IOException {
    ImageController imgCtr = new ImageController();
    Textui textui = new Textui(imgCtr);
    textui.readCommand("new-project 800 600");
    assertEquals(800, imgCtr.img.getHeight());
  }


  /**
   * Tester for GetProject.
   */
  @Test
  public void testGetProject() {
    ImageController ic = new ImageController();
    ic.newProject(800, 600);
    ic.getProject();
    assertEquals(600, ic.getProject().getWidth());
    assertEquals(800, ic.getProject().getHeight());
  }

  /**
   * Tester that test SaveProject method.
   */
  @Test
  public void testSaveProject() {
    ImageController ic = new ImageController();
    ic.newProject(800, 600);
    ic.saveProject("tako.ppm.collager");
    File file = new File("tako.ppm.collager");
    assertTrue(file.exists());
  }

  /**
   * Tester that test TestSaveProject method. this one is testing it is using the
   * correct format. MAY NEED TO BE RECHECKED.
   */
  @Test
  public void testTestSaveProject() {
    ImageController ic = new ImageController();
    ic.newProject(800, 600);
    ic.saveProject();
    File file = new File("tako.ppm.collager");
    assertTrue(file.exists());
  }

  /**
   * Tester that test AddLayer method.
   */
  @Test
  public void testAddLayer() {
    ImageController ic = new ImageController();
    ic.newProject(600, 600);
    ic.addLayer("layer2");
    assertNotNull(ic.getProject().getLayer("layer2"));


  }

  /**
   * Tester that test AddImageToLayer method.
   */
  @Test
  public void testAddImageToLayer() {
    ImageController ic = new ImageController();
    ic.newProject(800, 600);
    ic.addLayer("layer1");
    ic.addImageToLayer("layer1", "images/tako.ppm", 0, 0);
    Pixel topLeft = ic.getProject().getLayer("layer1").getGrid()[0][0];
    assertEquals(173, topLeft.getRed());
    assertEquals(179, topLeft.getGreen());
    assertEquals(151, topLeft.getBlue());
    Pixel bottomRigth = ic.getProject().getLayer("layer1").getGrid()[799][599];
    assertEquals(bottomRigth.getRed(), 167);
    assertEquals(bottomRigth.getGreen(), 106);
    assertEquals(bottomRigth.getBlue(), 25);
  }

  /**
   * Tester that test SetFilter method.
   */
  @Test
  public void testSetFilter() {
    ImageController ic = new ImageController();
    ic.newProject(600, 800);
    ic.addLayer("layer1");
    ic.setFilter("layer1", "red-component", null);
    assertEquals("red-component", ic.getProject().getLayer("layer1").getFilter());
    assertNotEquals("recomponent", ic.getProject().getLayer("layer1").getFilter());
    ic.addLayer("layer2");
    ic.setFilter("layer2", "green-component", null);
    assertEquals("green-component", ic.getProject().getLayer("layer2").getFilter());
    assertNotEquals("grcomponent", ic.getProject().getLayer("layer2").getFilter());

  }

  /**
   * Tester that test SaveImage method.
   */
  @Test
  public void testSaveImage() {
    ImageController ic = new ImageController();
    ic.newProject(800, 800);
    ic.saveImage("tako.ppm");
    File file = new File("tako.ppm");
    assertTrue(file.exists());
  }

  /**
   * Tester that test Quit method.
   */
  @Test
  public void testQuit() throws IOException {
    ImageController imgCtr = new ImageController();
    imgCtr.quit();
    assertEquals(null, imgCtr.img);
  }

  /**
   * Tester that test loadDarkenAndSaveImage.
   */
  @Test
  public void testLoadDarkenAndSaveImage() {
    ImageController ic = new ImageController();
    ic.newProject(800, 800);
    ic.addImageToLayer("background", "./images/tako.ppm", 0, 0);
    ic.setFilter("background", "darken", null);
    ic.saveImage("newTako.ppm");
    File file = new File("newTako.ppm");
    assertTrue(file.exists());
  }

}