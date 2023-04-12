package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import controller.ImageController;

/**
 * Class Project which implements ProjectInterface.
 */
public class Project implements ProjectInterface {
  private ArrayList<Layer> layers;
  private int width;
  private int height;


  /**
   * Constructor for Project, represents objects width and height.
   *
   * @param width  of the image
   * @param height of the image
   */
  public Project(int height, int width) {
    this.width = width;
    this.height = height;
    layers = new ArrayList<Layer>();
    layers.add(new Layer(height, width, "background"));
  }

  private static int lowestByte(int num) {
    return (num & 0xFF);
  }

  private static Layer convertBufferedImageToLayer(BufferedImage img, String name) {
    Layer layer = new Layer(img.getHeight(), img.getWidth(), name);
    for (int r = 0; r < layer.getHeight(); r++) {
      for (int c = 0; c < layer.getWidth(); c++) {
        int p = img.getRGB(c, r);

        int blue = lowestByte(p);
        p >>= 8;
        int green = lowestByte(p);
        p >>= 8;
        int red = lowestByte(p);
        p >>= 8;
        int alpha = lowestByte(p);

        Pixel pix = new Pixel(red, green, blue, alpha);
        layer.setPixelAt(r, c, pix);
      }

    }
    return layer;
  }

  /**
   * loadProjectFromImage, loads an entire project to an image.
   *
   * @param filename file name
   * @return filename
   */
  public static Project loadProjectFromImage(String filename) {
    try {
      File file = new File(filename);
      BufferedImage img = ImageIO.read(file);
      Layer layer = convertBufferedImageToLayer(img, file.getName());
      Project project = new Project(layer.getHeight(), layer.getWidth());
      project.addLayer(layer);
      return project;
    } catch (IOException e) {
      System.out.println("project could not load");

    }
    return null;
  }

  /**
   * Method getLayer for image/project.
   *
   * @param index amount
   * @return layer index
   */
  @Override
  public Layer getLayer(int index) {
    if (index < 0 || index >= this.layers.size()) {
      throw new IllegalArgumentException("Wrong index.");
    }
    return this.layers.get(index);
  }

  /**
   * Method getLayer, represents tpe of layer.
   *
   * @param name of layer type
   * @return layer or null for incorrect layer type
   */
  public Layer getLayer(String name) {
    for (Layer l : this.layers) {
      if (l.getName().equals(name)) {
        return l;
      }
    }
    return null;
  }

  /**
   * Method addLayer, add layer to Image/project.
   *
   * @param l layer
   */
  @Override
  public void addLayer(Layer l) {
    if (l != null) {
      this.layers.add(l);
    } else {
      throw new IllegalArgumentException("Layer is null");
    }
  }

  /**
   * Method getWidth, gets the width of the project.
   *
   * @return width
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * Method getHeight, gets the height of the project.
   *
   * @return height
   */
  public int getHeight() {
    return this.height;
  }

  /**
   * Method getNumberLayers, represents Number of layers.
   *
   * @return number of layers
   */
  public int getNumberLayers() {
    return this.layers.size();
  }

  /**
   * method combineAllLayers, Combines all layers of the pixal vaules together.
   *
   * @return overall pixal value
   */
  public Layer combineAllLayers() {
    Layer bg = getLayer(0);


    int maxHeight = 0;
    int maxWidth = 0;
    for (int l = 0; l < getNumberLayers(); l++) {
      maxWidth = Math.max(maxWidth, getLayer(l).getWidth());
      maxHeight = Math.max(maxHeight, getLayer(l).getHeight());
    }
    Layer finalLayer = new Layer(maxHeight, maxWidth, "final");
    for (int l = 0; l < this.getNumberLayers(); l++) {
      Layer currentLayer = getLayer(l);
      for (int r = 0; r < currentLayer.getHeight(); r++) {
        for (int c = 0; c < currentLayer.getWidth(); c++) {

          Pixel oldPixel = finalLayer.getPixelAt(r, c);
          Pixel newPixel = currentLayer.getPixelAt(r, c);

          Pixel combined = new Pixel(
                  ImageController.clamp(oldPixel.getRed() + newPixel.getRed()),
                  ImageController.clamp(oldPixel.getGreen() + newPixel.getGreen()),
                  ImageController.clamp(oldPixel.getBlue() + newPixel.getBlue()),
                  ImageController.clamp(oldPixel.getAlpha() + newPixel.getAlpha()
                  ));
          finalLayer.setPixelAt(r, c, combined);
        }
      }
    }
    return finalLayer;
  }

  /**
   * Method addLayerFromImage represents, Adds Layer to filename.
   *
   * @param filename name of file
   */
  public void addLayerFromImage(String filename) {
    try {
      File file = new File(filename);
      BufferedImage img = ImageIO.read(file);
      Layer layer = convertBufferedImageToLayer(img, file.getName());
      addLayer(layer);
    } catch (IOException e) {
      System.out.println("layer could not load");

    }


  }


}
