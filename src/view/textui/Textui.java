package view.textui;


import java.io.IOException;
import java.util.Scanner;

import controller.ImageController;

import static java.lang.System.out;

/**
 * Textui class.
 */
public class Textui implements TextuiIn {
  private Scanner scan;
  private String command;
  private String currentCommand;
  private ImageController ic;


  /**
   * Constructor for Textui.
   *
   * @param ic equals ImageController
   * @throws IOException invalided input
   */
  public Textui(ImageController ic) throws IOException {
    this.ic = ic;
  }

  /**
   * Getter method getCurrentCommand,allows user to select the current commands.
   *
   * @return currentCommand
   */
  public String getCurrentCommand() {
    return this.currentCommand;
  }

  /**
   * Method readCommand, reader for the layer/ filter type commands.
   *
   * @param line input command
   * @throws IOException found error
   */
  public void readCommand(String line) throws IOException {
    String[] words = line.split(" ");
    this.currentCommand = words[0];
    switch (words[0]) {
      case "new-project":

        if (words.length > 2) {
          ic.newProject(Integer.parseInt(words[1]), Integer.parseInt(words[2]));
        } else {
          ic.newProject(600, 800);

        }
        break;
      case "add-layer":
        ic.addLayer(words[1]);
        break;
      case "add-image-to-layer":
        String layerName = words[1];
        String imageName = words[2];
        int xPos = 0;
        int yPos = 0;
        if (words.length > 3) {
          xPos = Integer.parseInt(words[3]);
          yPos = Integer.parseInt(words[4]);
        }

        ic.addImageToLayer(layerName, imageName, xPos, yPos);
        break;
      case "set-filter":
        String layer = words[1];
        String filterOption = words[2];
        if (filterOption.equals("darkenBlending") || filterOption.equals("inversionBlending") ||
                filterOption.equals("brightenBlending")) {
          String otherLayerName = words[3];
          ic.setFilter(layer, filterOption, otherLayerName);
        } else {
          ic.setFilter(layer, filterOption, null);
        }
        break;
      case "save-image":
        if (words.length > 1) {
          ic.saveImage(words[1]);
        } else {
          throw new IllegalArgumentException("Too few arguments, need filename");
        }
        break;
      case "save-project":
        if (words.length > 1) {
          ic.saveProject(words[1]);
        } else {
          ic.saveProject();
        }
        break;
      case "load-project":
        System.out.println(words[1]);
        ic.loadProject(words[1]);
        break;
      case "quit":
      case "QUIT":
        ic.quit();
        break;
      default:
        out.println("Invalid-command");
        this.currentCommand = "Invalid-command";
    }
  }


}
