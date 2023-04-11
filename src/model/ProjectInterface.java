package model;

/**
 * interface ProjectInterface that represents Project class.
 */
public interface ProjectInterface {


  /**
   * Method getLayer for image/project.
   *
   * @param index amount
   * @return layer index
   */
  Layer getLayer(int index);

  /**
   * Method getLayer, represents tpe of layer.
   *
   * @param name of layer type
   * @return layer or null for incorrect layer type
   */
  Layer getLayer(String name);

  /**
   * Method addLayer, add layer to Image/project.
   *
   * @param l layer
   */
  void addLayer(Layer l);

  /**
   * Method getWidth, gets the width of the project.
   *
   * @return width
   */
  int getWidth();

  /**
   * Method getHeight, gets the height of the project.
   *
   * @return height
   */
  int getHeight();

  /**
   * Method getNumberLayers, represents Number of layers.
   *
   * @return number of layers
   */
  int getNumberLayers();


  /**
   * Combines all layers.
   *
   * @return combination of multiple layers
   */
  Layer combineAllLayers();

  /**
   * adds Layer to filename.
   *
   * @param filename name of file
   */
  void addLayerFromImage(String filename);
}

