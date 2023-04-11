package view.gui;

import org.testng.annotations.Test;

import java.awt.image.BufferedImage;

import controller.ImageController;
import model.Layer;
import model.Pixel;



import static org.testng.Assert.assertNotEquals;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;


/**
 * Testers for JFrameView class.
 */
public class JFrameViewTest {

  /**
   * Tester for runsWithoutException method.
   */
  @Test
  public void testRunsWithoutException() {
    ImageController ic = new ImageController();
    JFrameView view = new JFrameView(ic);
    assertNotNull(ic);
    assertNotNull(view);

  }

  /**
   * Tester for PpmImageToBufferedImage method.
   */
  @Test
  public void testPpmImageToBufferedImage() {
    ImageController ic = new ImageController();
    JFrameView view = new JFrameView(ic);
    ic.newProject(800,600);
    Layer layer = ic.loadImage("images/tako.ppm", "tako");
    BufferedImage img = view.ppmImageToBufferedImage(layer);
    int topLeftRGB = img.getRGB(0, 0);

    Pixel topLeft = layer.getPixelAt(0, 0);

    int argb = topLeft.getAlpha() << 24;
    argb |= topLeft.getRed() << 16;
    argb |= topLeft.getGreen() << 8;
    argb |= topLeft.getBlue();
    assertEquals(topLeftRGB,  argb);
    assertNotEquals(topLeftRGB, 0);
  }

}