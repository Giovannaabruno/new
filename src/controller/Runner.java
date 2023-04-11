package controller;


import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import view.gui.CollagingView;
import view.gui.JFrameView;
import view.textui.Textui;

/**
 * Runner class.
 */
public class Runner {

  /**
   * Main Methode, the entry point of an executable program;
   * it is where the program control starts and ends.
   *
   * @param args arguments
   */
  public static void main(String[] args) throws IOException {
    String command;
    ImageController imgCtr = new ImageController();
    Scanner scan = null;
    if (args.length == 0) {
      /// run a GUI
      CollagingView view = new JFrameView(imgCtr);
      return;
    } else if (args.length == 1 && args[0].equals("-text")) {
      // interactive mode
      scan = new Scanner(System.in);
    } else if (args.length == 2 && args[0].equals("-file")) {
      // run in file modes
      scan = new Scanner(new File(args[1]));
    }
    if (scan != null) {
      Textui textui = new Textui(imgCtr);
      while (scan.hasNextLine()) {
        command = scan.nextLine();
        textui.readCommand(command);
      }
    }
  }
}