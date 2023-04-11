package view.gui;

import org.testng.annotations.Test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import controller.ImageController;


import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNull;

/**
 * testers for ImagePanel class.
 */
public class ImagePanelTest {

  /**
   * Tester for getImage method.
   *
   * @throws IOException if given invalided image selection
   */
  @Test
  public void getImage() throws IOException {
    BufferedImage img = ImageIO.read(new File("images/tako.ppm"));
    ImageController ic = new ImageController();
    ic.newProject(800, 800);
    ImagePanel panel = new ImagePanel(ic);
    assertEquals(panel.getImage(), ImageIO.read(new File("images/tako.ppm")));
    assertNull(panel.getImage());
  }

  /**
   * Tester for paintComponent method.
   */
  @Test
  public void paintComponent() throws IOException {
    BufferedImage img = ImageIO.read(new File("images/tako.ppm"));
    ImageController ic = new ImageController();
    ic.newProject(800, 800);
    ImagePanel panel = new ImagePanel(ic);
    assertEquals(panel.getImage(), img);
    if (panel != null && img != null) {
      assertEquals(panel.getImage().getWidth(), img.getWidth());
    }
  }

}
