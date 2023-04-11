package model;

/**
 * Interface for LayerInterface.
 *
 * @param <T> pixel
 */
public interface LayerInterface<T> {

  /**
   * Method getHeight, gets the height of the layer.
   *
   * @return height of layer
   */
  int getHeight();

  /**
   * Method getWidth, gets the width of the layer.
   *
   * @return width of layer
   */
  int getWidth();

  /**
   * Method getWidth, gets the name of the type of layer.
   *
   * @return name of layer
   */
  String getName();

  /**
   * Method getGrid, gets the grid of the layer.
   *
   * @return grid of layer
   */
  Pixel[][] getGrid();

  /**
   * Method getPixelAt, get the coordinate of a certain pixel on the grid.
   *
   * @param row   of the grid for the layer
   * @param colum of the grid for the layer
   * @return row and colum for the grid for layer
   */
  Pixel getPixelAt(int row, int colum);

  /**
   * Method setPixelAt, sets the coordinate of a certain pixel on the grid.
   *
   * @param row   of the grid for the layer
   * @param colum of the grid for the layer
   * @param pixel selected pixel on the grid
   */
  void setPixelAt(int row, int colum, Pixel pixel);

  /**
   * Method setFilter, check whether filter is a valid choice, and the amount of
   * transparency for that layer.
   *
   * @param filter type
   * @param amount of transparency
   */
  void setFilter(String filter, int amount);

  /**
   * Getter method getFilter, gets the type of filter on the image.
   *
   * @return filter type
   */
  String getFilter();


  /**
   * Method applyFilter, allows the users to apply the selscted filter to the layer.
   *
   * @param other layers
   */
  void applyFilter(Layer other);

  /**
   * NEW: INCOMPLETE (MAKE A TESTER FOR THIS).
   * Method getFilteredGrid,check whether filter is a valid choice, and the amount of
   * transparency for that layer.
   *
   * @param other layer type
   * @return layer type of null for incorrect layer
   */
  Pixel[][] getFilteredGrid(Layer other);

  /**
   * Method getFilteredGrid, selects what each filter type command is called.
   *
   * @return filter or null if given a Invalid filter
   */
  Pixel[][] getFilteredGrid();

  /**
   * Method is isBlending, creates the blending effect.
   *
   * @return blending effects
   */
  boolean isBlending();
}