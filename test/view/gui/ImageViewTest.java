package view.gui;

import org.testng.annotations.Test;

import java.io.IOException;


import static org.testng.Assert.assertNotEquals;
import static org.testng.AssertJUnit.assertEquals;


/**
 * Testers for ImageView class.
 */
public class ImageViewTest {
  Appendable out;

  /**
   * Test rendering message.
   */
  @Test
  public void testRenderMessage() throws IOException {
    out = new StringBuffer();
    CollagingView view = new CollagingCommandView(out);
    view.renderMessage("Testing Message!");
    assertEquals("Testing Message!", out.toString());
    assertNotEquals("Testing Mess ", out.toString());
  }


}