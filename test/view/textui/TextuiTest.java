package view.textui;

import org.testng.annotations.Test;

import java.io.IOException;

import controller.ImageController;

import static org.testng.Assert.assertNotEquals;
import static org.testng.AssertJUnit.assertEquals;


/**
 * tester for Textui class.
 */
public class TextuiTest {

  /**
   * Tester that test GetCurrentCommand method.
   */
  @Test
  public void testGetCurrentCommand() throws IOException {
    ImageController ic = new ImageController();
    Textui textui = new Textui(ic);

    ic.newProject(800, 600);
    textui.readCommand("add-layer layer1");
    assertEquals(textui.getCurrentCommand(), "add-layer");
    textui.readCommand("set-filter layer1 blue-component");
    assertEquals(textui.getCurrentCommand(), "set-filter");
  }

  /**
   * Tester that test ReadCommand method.
   */
  @Test
  public void testReadCommand() throws IOException {
    ImageController ic = new ImageController();
    Textui textui = new Textui(ic);

    // command 1
    textui.readCommand("new-project 800 600");
    assertEquals(textui.getCurrentCommand(), "new-project");
    assertEquals(800, ic.getProject().getHeight());
    assertEquals(600, ic.getProject().getWidth());
    assertNotEquals(600, ic.getProject().getHeight());
    assertNotEquals(800, ic.getProject().getWidth());
    // command 2
    textui.readCommand("add-layer layerName");
    assertEquals(textui.getCurrentCommand(), "add-layer");
    assertEquals(ic.getProject().getNumberLayers(), 2);
    assertNotEquals(textui.getCurrentCommand(), 0);

    //commdand 3
    textui.readCommand("add-image-to-layer layerName images/tako.ppm");
    assertEquals(textui.getCurrentCommand(), "add-image-to-layer");
    assertNotEquals(textui.getCurrentCommand(), "add layers to pic");

    //command 4
    textui.readCommand("set-filter layerName red-component");
    assertEquals(textui.getCurrentCommand(), "set-filter");
    assertNotEquals(textui.getCurrentCommand(), "red");

    //command 5
    textui.readCommand("save-image image.ppm");
    assertEquals(textui.getCurrentCommand(), "save-image");
    assertNotEquals(textui.getCurrentCommand(), "savePic");

    //command 6
    textui.readCommand("save-project");
    assertEquals(textui.getCurrentCommand(), "save-project");
    assertNotEquals(textui.getCurrentCommand(), "savProject");

    //command 7
    // its breaking and running out of input
    textui.readCommand("load-project image.Project");
    assertEquals(textui.getCurrentCommand(), "load-project");
    assertNotEquals(textui.getCurrentCommand(), "loadProject");

    //command 8
    textui.readCommand("quit");
    assertEquals(textui.getCurrentCommand(), "quit");
    assertNotEquals(textui.getCurrentCommand(), "Out");

    /// invalied comanied
    textui.readCommand("Invalid-command");
    assertEquals(textui.getCurrentCommand(), "Invalid-command");
    assertNotEquals(textui.getCurrentCommand(), "Out");

  }
}