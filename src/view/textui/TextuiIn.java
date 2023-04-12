package view.textui;

import java.io.IOException;

/**
 * interfaces for Textui.
 */
public interface TextuiIn {

  /**
   * Getter method getCurrentCommand,allows user to select the current commands.
   *
   * @return currentCommand
   */
  String getCurrentCommand();

  /**
   * Method readCommand, reader for the layer/ filter type commands.
   *
   * @param line input command
   * @throws IOException found error
   */
  void readCommand(String line) throws IOException;
}
