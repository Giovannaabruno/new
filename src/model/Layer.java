package model;

/**
 * Class for Layer which implements LayerInterface.
 */
public class Layer implements LayerInterface<Pixel> {
  private int height;
  private int width;
  private Pixel[][] grid;

  private String name;

  /**
   * store the type of filter.
   */
  private String filter;

  /**
   * Stores whether this layer needs the layer behind it to work:red-component, brighten don't,
   * but blendingDarkness and blendingInversion do.
   */
  private boolean blending;


  /**
   * store the intensity of the filter.
   */
  private int amount;

  /**
   * Constructor for Layer, represents objects height, width, and name.
   *
   * @param height of the layer
   * @param width  of the layer
   * @param name   of the type of layer
   */
  public Layer(int height, int width, String name) {
    this.height = height;
    this.width = width;
    this.name = name;
    this.grid = new Pixel[height][width];
    for (int row = 0; row < grid.length; row++) {
      for (int colum = 0; colum < grid[0].length; colum++) {
        grid[row][colum] = new Pixel(0, 0, 0);
      }
    }
  }

  /**
   * Constructor for Layer, represents the objects for grid and name.
   *
   * @param grid of the layer
   * @param name of the layer
   */
  public Layer(Pixel[][] grid, String name) {
    this.height = grid.length;
    this.width = grid[0].length;
    this.grid = grid;
    this.name = name;
  }

  /**
   * Method getHeight, gets the height of the layer.
   *
   * @return height of layer
   */
  public int getHeight() {
    return height;
  }

  /**
   * Method getWidth, gets the width of the layer.
   *
   * @return width of layer
   */
  public int getWidth() {
    return width;
  }

  /**
   * Method getWidth, gets the name of the type of layer.
   *
   * @return name of layer
   */
  public String getName() {
    return name;
  }

  /**
   * Method getGrid, gets the grid of the layer.
   *
   * @return grid of layer
   */
  public Pixel[][] getGrid() {
    return grid;
  }

  /**
   * Method getPixelAt, get the coordinate of a certain pixel on the grid.
   *
   * @param row   of the grid for the layer
   * @param colum of the grid for the layer
   * @return row and colum for the grid for layer
   */
  public Pixel getPixelAt(int row, int colum) {
    return grid[row][colum];
  }

  /**
   * Method setPixelAt, sets the coordinate of a certain pixel on the grid.
   *
   * @param row   of the grid for the layer
   * @param colum of the grid for the layer
   * @param pixel selected pixel on the grid
   */
  public void setPixelAt(int row, int colum, Pixel pixel) {
    grid[row][colum] = pixel;
  }

  /**
   * Method setFilter, check whether filter is a valid choice, and the amount of
   * transparency for that layer.
   *
   * @param filter type
   * @param amount of transparency
   */
  public void setFilter(String filter, int amount) {
    this.amount = amount;
    this.filter = filter;
    switch (filter) {
      case "red-component":
      case "green-component":
      case "blue-component":
      case "brighten-value":
      case "brighten-intensity":
      case "brighten-luma":
      case "darken-value":
      case "darken-intensity":
      case "darken-luma":
        this.filter = filter;
        blending = false;
        break;
      case "darkenBlending"://REPLACE (RELOOK AT THIS)
      case "inversionBlending":
      case "brightenBlending":
        this.filter = filter;
        blending = true;
        break;
      default:
        System.out.println("Invalid filter");

    }
  }

  /**
   * Method applyFilter, allows the users to apply the selscted filter to the layer.
   *
   * @param other layers
   */
  public void applyFilter(Layer other) {
    if (this.filter.equals("darkenBlending") || this.filter.equals("inversionBlending") ||
            this.filter.equals("brightenBlending")) {
      this.grid = getFilteredGrid(other);
    } else {
      System.out.println("applying " + this.filter);
      this.grid = getFilteredGrid();
    }

  }

  /**
   * Getter method getFilter, gets the type of filter on the image.
   *
   * @return filter type
   */
  public String getFilter() {
    return this.filter;
  }

  /**
   * NEW: INCOMPLETE (MAKE A TESTER FOR THIS).
   * Method getFilteredGrid,check whether filter is a valid choice, and the amount of
   * transparency for that layer.
   *
   * @param other layer type
   * @return layer type of null for incorrect layer
   */
  public Pixel[][] getFilteredGrid(Layer other) {
    switch (filter) {
      case "darkenBlending":
        return darkenBlending(other);
      case "inversionBlending":
        return inversionBlending(other);
      case "brightenBlending":
        return brighteningBlending(other);
      default:
        System.out.println("Invalid filter");
        return null;
    }
  }

  /**
   * Method getFilteredGrid, selects what each filter type command is called.
   *
   * @return filter or null if given a Invalid filter
   */
  public Pixel[][] getFilteredGrid() {
    switch (filter) {
      case "red-component":
        return redComponent();
      case "green-component":
        return greenComponent();
      case "blue-component":
        return blueComponent();
      //still not sure how brighten value is different from
      // brighten intensity or brighten luma
      case "brighten-value":
        return brightenValue();
      case "brighten-intensity":
        return brighten();
      case "brighten-luma":
        return brightenLuma();
      //darken
      case "darken-value":
        return darkenValue();
      case "darken-intensity":
        return darken();
      case "darken-luma":
        return darkenLuma();
      default:
        System.out.println("Invalid filter");
        return null;

    }
  }

  /**
   * Method is isBlending, creates the blending effect.
   *
   * @return blending effects
   */
  public boolean isBlending() {
    return blending;
  }


  /**
   * Method darken, removes the brightness value
   * pixel by pixel according to value from the corresponding pixel
   * on the current layer.
   *
   * @return result amount
   */
  private Pixel[][] darken() {
    Pixel[][] result = new Pixel[height][width];
    System.out.println("amount " + this.amount);
    for (int row = 0; row < grid.length; row++) {
      for (int colum = 0; colum < grid[0].length; colum++) {
        int red = grid[row][colum].getRed();
        int green = grid[row][colum].getGreen();
        int blue = grid[row][colum].getBlue();

        int resultRed = Math.max(0, red - this.amount);
        int resultGreen = Math.max(0, green - this.amount);
        int resultBlue = Math.max(0, blue - this.amount);

        result[row][colum] = new Pixel(resultRed, resultGreen, resultBlue);
        //assume full opaqueness/full alpha
      }
    }
    return result;
  }

  /**
   * Method darkenValue, removes the brightness value
   * pixel by pixel according to value from the corresponding pixel
   * on the current layer.
   *
   * @return result amount
   */
  private Pixel[][] darkenValue() {
    Pixel[][] result = new Pixel[height][width];

    for (int row = 0; row < grid.length; row++) {
      for (int colum = 0; colum < grid[0].length; colum++) {
        int red = grid[row][colum].getRed();
        int green = grid[row][colum].getGreen();
        int blue = grid[row][colum].getBlue();


        if (red >= green && red >= blue) {
          red -= amount;
        } else if (green >= red && green >= blue) {
          green -= amount;
        } else if (blue >= green && red >= red) {
          blue -= amount;
        }
        result[row][colum] = new Pixel(red, green, blue);
        //assume full opaqueness/full alpha
      }
    }
    return result;
  }


  /**
   * Method darkenLuma, removes the brightness luma
   * pixel by pixel according to luma from the corresponding pixel
   * on the current layer.
   *
   * @return result amount
   */
  private Pixel[][] darkenLuma() {
    Pixel[][] result = new Pixel[height][width];

    for (int row = 0; row < grid.length; row++) {
      for (int colum = 0; colum < grid[0].length; colum++) {
        int red = (int) (grid[row][colum].getRed() * 0.2126);
        int green = (int) (grid[row][colum].getGreen() * 0.7152);
        int blue = (int) (grid[row][colum].getBlue() * 0.0722);

        int resultRed = Math.max(0, red - amount);
        int resultGreen = Math.max(0, green - amount);
        int resultBlue = Math.max(0, blue - amount);

        result[row][colum] = new Pixel(resultRed, resultGreen, resultBlue);

      }
    }
    return result;
  }


  /**
   * Method brighten represents, adds the brightness value pixel
   * by pixel according to value from the corresponding pixel on the current layer.
   *
   * @return result amount
   */
  private Pixel[][] brighten() {
    Pixel[][] result = new Pixel[height][width];

    for (int row = 0; row < grid.length; row++) {
      for (int colum = 0; colum < grid[0].length; colum++) {
        int red = grid[row][colum].getRed();
        int green = grid[row][colum].getGreen();
        int blue = grid[row][colum].getBlue();

        int resultRed = Math.min(255, red + amount);
        int resultGreen = Math.min(255, green + amount);
        int resultBlue = Math.min(255, blue + amount);

        result[row][colum] = new Pixel(resultRed, resultGreen, resultBlue);

      }
    }
    return result;
  }

  /**
   * Method brightenValue, represents, adds the brightness value pixel
   * by pixel according to value from the corresponding pixel on the current layer.
   *
   * @return result amount
   */
  private Pixel[][] brightenValue() {
    Pixel[][] result = new Pixel[height][width];

    for (int row = 0; row < grid.length; row++) {
      for (int colum = 0; colum < grid[0].length; colum++) {
        int red = grid[row][colum].getRed();
        int green = grid[row][colum].getGreen();
        int blue = grid[row][colum].getBlue();


        if (red >= green && red >= blue) {
          red += amount;
        } else if (green >= red && green >= blue) {
          green += amount;
        } else if (blue >= green && red >= red) {
          blue += amount;
        }
        result[row][colum] = new Pixel(red, green, blue);
        //assume full opaqueness/full alpha
      }
    }
    return result;
  }


  /**
   * Method brightenLuma, represents, adds the brightness luma pixel
   * by pixel according to luma from the corresponding pixel on the current layer.
   *
   * @return result amount
   */
  private Pixel[][] brightenLuma() {
    Pixel[][] result = new Pixel[height][width];

    for (int row = 0; row < grid.length; row++) {
      for (int colum = 0; colum < grid[0].length; colum++) {
        int red = (int) (grid[row][colum].getRed() * 0.2126);
        int green = (int) (grid[row][colum].getGreen() * 0.7152);
        int blue = (int) (grid[row][colum].getBlue() * 0.0722);

        int resultRed = Math.max(0, red + amount);
        int resultGreen = Math.max(0, green + amount);
        int resultBlue = Math.max(0, blue + amount);

        result[row][colum] = new Pixel(resultRed, resultGreen, resultBlue);

      }
    }
    return result;
  }

  /**
   * Method redComponent, represents the red portion of the RGB.
   *
   * @return result amount
   */
  private Pixel[][] redComponent() {
    Pixel[][] result = new Pixel[height][width];

    for (int row = 0; row < grid.length; row++) {
      for (int colum = 0; colum < grid[0].length; colum++) {
        int red = grid[row][colum].getRed();

        result[row][colum] = new Pixel(red, 0, 0);

      }
    }
    return result;
  }

  /**
   * Method greenComponent, represents the green portion of the RGB.
   *
   * @return result amount
   */
  private Pixel[][] greenComponent() {
    Pixel[][] result = new Pixel[height][width];

    for (int row = 0; row < grid.length; row++) {
      for (int colum = 0; colum < grid[0].length; colum++) {
        int green = grid[row][colum].getGreen();

        result[row][colum] = new Pixel(0, green, 0);

      }
    }
    return result;
  }

  /**
   * Method blueComponent, represents the blue portion of the RGB.
   *
   * @return result amount
   */
  private Pixel[][] blueComponent() {
    Pixel[][] result = new Pixel[height][width];

    for (int row = 0; row < grid.length; row++) {
      for (int colum = 0; colum < grid[0].length; colum++) {
        int blue = grid[row][colum].getBlue();

        result[row][colum] = new Pixel(0, 0, blue);

      }
    }
    return result;
  }

  /**
   * Method inversionBlending, represent the inversionBlending effect.
   *
   * @param other differences
   * @return inversionBlending result
   */
  private Pixel[][] inversionBlending(Layer other) {
    Pixel[][] result = new Pixel[height][width];
    for (int row = 0; row < grid.length; row++) {
      for (int colum = 0; colum < grid[0].length; colum++) {
        int red = Math.abs(grid[row][colum].getRed() - other.grid[row][colum].getRed());
        int green = Math.abs(grid[row][colum].getGreen() - other.grid[row][colum].getGreen());
        int blue = Math.abs(grid[row][colum].getBlue() - other.grid[row][colum].getBlue());

        result[row][colum] = new Pixel(red, green, blue);

      }
    }
    return result;
  }

  /**
   * Method darkenBlending, represent the inversionBlending effect.
   *
   * @param other differences
   * @return darkenBlending result
   */
  private Pixel[][] darkenBlending(Layer other) {
    Pixel[][] result = new Pixel[height][width];
    for (int row = 0; row < grid.length; row++) {
      for (int colum = 0; colum < grid[0].length; colum++) {
        double hue = grid[row][colum].getHue();
        double saturation = grid[row][colum].getSaturation();
        double lightness = grid[row][colum].getLightness() * other.grid[row][colum].getLightness();

        result[row][colum] = new Pixel(hue, saturation, lightness);
      }
    }
    return result;

  }

  /**
   * Method brighteningBlending, represent the inversionBlending effect.
   *
   * @param other differences
   * @return brighteningBlending result
   */
  private Pixel[][] brighteningBlending(Layer other) {
    Pixel[][] result = new Pixel[height][width];
    for (int row = 0; row < grid.length; row++) {
      for (int colum = 0; colum < grid[0].length; colum++) {
        double hue = grid[row][colum].getHue();
        double saturation = grid[row][colum].getSaturation();
        //  Lightness
        double l = grid[row][colum].getLightness();
        double dL = other.grid[row][colum].getLightness();
        double lightness = (1 - ((1 - l) * (1 - dL)));

        result[row][colum] = new Pixel(hue, saturation, lightness);
      }

    }
    return result;


  }

}


