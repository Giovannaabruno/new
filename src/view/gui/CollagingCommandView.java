package view.gui;

import java.io.IOException;

/**
 * visual representation of a model of Collaring.
 */
public class CollagingCommandView implements CollagingView {
  private final Appendable ap;

  /**
   * Constructor, creates a view Object for a command line based Collaging project.
   */
  public CollagingCommandView() {
    this.ap = System.out;
  }

  /**
   * Constructor,that represents the appendable.
   *
   * @param ap equals the appendable
   */
  public CollagingCommandView(Appendable ap) {
    this.ap = ap;
  }

  /**
   * Renders a given message to the data output in the implementation.
   *
   * @param message the message to be printed
   * @throws IOException if the transmission of the message to the data output fails
   */
  @Override
  public void renderMessage(String message) throws IOException {
    ap.append(message);
  }

  /**
   * Method getAppendable, represents the convertRGBtoHSLappendable.
   *
   * @return object appendable
   */
  public Appendable getAppendable() {
    return this.ap;
  }

}
