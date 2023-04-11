package view.gui;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import model.Layer;


/**
 * Interface JFrameViewInterface.
 */
public interface JFrameViewInterface {

  /**
   * Method actionPerformed, creates the action sequences.
   *
   * @param e the event to be processed
   */
  void actionPerformed(ActionEvent e);


  /**
   * Method ppmImageToBufferedImage, it will conver a ppm image to a
   * buffer image in order to draw it on the screen.
   *
   * @param layer layer
   * @return find Image
   */
  BufferedImage ppmImageToBufferedImage(Layer layer);

  /**
   * Renders a given message to the GUI Application.
   *
   * @param message the message to be printed
   * @throws IOException if the transmission of the message to the data output fails
   */
  void renderMessage(String message);
}

