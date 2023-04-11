package controller;

import java.io.IOException;

import model.Layer;
import model.Project;


/**
 * Interface for ControllerInterface.
 */
public interface ControllerInterface {

  /**
   * LoadImage method, take module and loads it from file name.
   *
   * @param filename  equals selected filename which will be load from module
   * @param layerName name of the type of layer/filer
   * @return selected layer
   */
  Layer loadImage(String filename, String layerName);


  /**
   * Method newProject will create a new
   * project with the given name and given dimensions.
   * Every project has a white background layer by default.
   *
   * @param width  of the canvas
   * @param height of the canvas
   * @return
   */
  Project newProject(int width, int height);

  /**
   * Returns the current project.
   *
   * @return the current project
   */
  Project getProject();

  /**
   * Method loadProject, loads a project into the program.
   *
   * @param path to project file
   */
  void loadProject(String path);


  /**
   * Method saveProject, save the project as one file as described above.
   */
  void saveProject();

  /**
   * Method saveProject, save the project as one file as described above.
   *
   * @param fileName name of the file
   */
  void saveProject(String fileName);


  /**
   * Method addLayer,  adds a new layer with the given name to the top of the whole project.
   * This layer always has a fully transparent white image and the Normal filter on by default.
   * Any attempt at creating another layer with the same name reports an error to the user,
   * but continues the program.
   *
   * @param layerName Name of type of layer/ filter.
   */
  void addLayer(String layerName);

  /**
   * Method addImageToLayer,places an image on the layer such that the top
   * left corner of the image is at (x-pos, y-pos).
   *
   * @param layerName type of layer/filter type.
   * @param imageName name of the image
   * @param xPosition x positions of the canvas
   * @param yPosition y positions of the canvas
   */
  void addImageToLayer(String layerName, String imageName, int xPosition, int yPosition);

  /**
   * Method setFilter, sets the filter of the given layer where filter-option
   * is one of the following at the moment.
   *
   * @param layerName   name of layer/filter type.
   * @param filerOption what is the filter option called.
   */
  void setFilter(String layerName, String filerOption, String otherLayerName);

  /**
   * Method Save image, save the result of applying all filters on the image.
   *
   * @param fileName name of the file
   */
  void saveImage(String fileName);


  /**
   * Method quit, quits the project and loses all unsaved work.
   *
   * @throws IOException found error
   */
  void quit() throws IOException;


}
