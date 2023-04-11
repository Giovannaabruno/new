package view.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JFileChooser;

import controller.ImageController;

/**
 * A class representing a panel with commands for processing an image.
 */
public class CommandPanel extends JPanel {

  private ImageController ic;
  private DefaultListModel<String> listModel;

  /**
   * Constructor for the CommandPanel class.
   */
  public CommandPanel(ImageController ic, DefaultListModel<String> listModel) {
    super();
    this.ic = ic;

    this.listModel = listModel;
    this.setBorder(BorderFactory.createTitledBorder("Commands:"));
    String[] options = {"None", "Add Layer",
            "Set Filter", "Add Image to Layer",
            "Save Project"};
    JComboBox<String> comboBox = new JComboBox<>();
    for (String option : options) {
      comboBox.addItem(option);
    }
    comboBox.addItemListener(new ItemChangeListener());
    this.add(comboBox);
  }

  protected void executeCommand(String command) {
    String layerName;
    switch (command) {
      case "None":
        break;
      case "New Project":
        try {
          ic.newProject(800, 600);
          JOptionPane.showMessageDialog(this,
                  "Successfully created new project");
        } catch (Exception e) {
          JOptionPane.showMessageDialog(this,
                  "Failed creating new project");
        }

        break;
      case "Add Layer":
        try {
          layerName = JOptionPane.showInputDialog(this,
                  "Enter layer name.");
          ic.addLayer(layerName);
          JOptionPane.showMessageDialog(this,
                  "Successfully added a layer.");
          listModel.addElement(layerName);
        } catch (Exception e) {
          JOptionPane.showMessageDialog(this,
                  "Failed adding new layer");
        }
        break;
      case "Set Filter":
        try {
          layerName = JOptionPane.showInputDialog(this, "Enter layer name");
          String filterOption = JOptionPane.showInputDialog(this,
                  "Enter filter option");
          if (filterOption.equals("darkenBlending") || filterOption.equals("inversionBlending") ||
                  filterOption.equals("brightenBlending")) {
            String otherName = JOptionPane.showInputDialog(this,
                    "Enter other layer");
            ic.setFilter(layerName, filterOption, otherName);
          } else {
            ic.setFilter(layerName, filterOption, null);
          }
          JOptionPane.showMessageDialog(this,
                  "Successfully set filter");
        } catch (Exception e) {
          JOptionPane.showMessageDialog(this,
                  "Failed setting filter");
        }

        break;
      case "Add Image to Layer":
        try {

          layerName = JOptionPane.showInputDialog(this, "Enter layer name");
          String imageName = JOptionPane.showInputDialog(this,
                  "Enter image name");
          int xPos = Integer.parseInt(JOptionPane.showInputDialog(this,
                  "Enter x Position"));
          int yPos = Integer.parseInt(JOptionPane.showInputDialog(this,
                  "Enter y Position"));
          ic.addImageToLayer(layerName, imageName, xPos, yPos);
          JOptionPane.showMessageDialog(this,
                  "Successfully added image to layer.");
        } catch (Exception e) {
          JOptionPane.showMessageDialog(this,
                  "Failed adding image to layer.");
        }
        break;
      case "Save Image":
        JFrame currentWindow = new JFrame();
        JFileChooser fileChooser = new JFileChooser(".");
        int returnValue = fileChooser.showSaveDialog(currentWindow);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
          File file = fileChooser.getSelectedFile();
          ic.saveImage(String.valueOf(file));
        }
        break;
      case "Save Project":
        try {
          String fileName = JOptionPane.showInputDialog(this,
                  "Enter file name");
          ic.saveProject(fileName);
          JOptionPane.showMessageDialog(this,
                  "Successfully saved project");
        } catch (Exception e) {
          JOptionPane.showMessageDialog(this,
                  "Failed to save Project.");
        }

        break;
      default:
        System.out.println("incorrect command");
    }
  }

  /**
   * Returns a string for the command selected by the user from the combo boxes.
   *
   * @param event the event triggered by selecting an option from the combo boxes
   * @return a string version of the command chosen
   */
  public String getComboBoxChoice(ActionEvent event) {
    if (event.getSource() instanceof JComboBox) {
      String choice = (String) ((JComboBox<String>) event.getSource()).getSelectedItem();
      System.out.println(choice);
    }
    throw new IllegalStateException("Something went wrong.");
  }

  class ItemChangeListener implements ItemListener {

    @Override
    public void itemStateChanged(ItemEvent e) {
      if (e.getStateChange() == ItemEvent.SELECTED) {
        String command = (String) e.getItem();
        executeCommand(command);
      }
    }
  }

}