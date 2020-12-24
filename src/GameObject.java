import java.io.File;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * The GameObject class maintains the creation and usage of objects that will appear in the game
 * 
 * @author vatrp
 */
public class GameObject {
  private PImage image; // Graphical representation of the GameObject
  private int xPosition; // x-coordinate of the GameObject
  private int yPosition; // y-coordinate of the GameObject
  public boolean active; // whether the GameObject is active (whether it should be displayed)
  private static PApplet processing = null; // Processing field, to be accessed by child classes

  /**
   * Constructor for a GameObject
   * 
   * @param name The identifying name of the GameObject (name of image file)
   * @param x    Starting x-coordinate of the GameObject
   * @param y    Starting y-coordinate of the GameObject
   */
  public GameObject(String name, int x, int y) {
    this.image = GameObject.processing.loadImage("images" + File.separator + name + ".png");
    this.xPosition = x;
    this.yPosition = y;
    this.active = true;
  }

  /**
   * Accessor method for the GameObject's x-coordinate
   * 
   * @return The x-coordinate of the GameObject
   */
  public int getXPosition() {
    return this.xPosition;
  }

  /**
   * Accessor method for the GameObject's y-coordinate
   * 
   * @return The y-coordinate of the GameObject
   */
  public int getYPosition() {
    return this.yPosition;
  }

  /**
   * Accessor method for the GameObject's image
   * 
   * @return A reference to the GameObject's image
   */
  public PImage getImage() {
    return this.image;
  }

  /**
   * Returns whether the GameObject is active
   * 
   * @return True if the GameObject is active, false otherwise
   */
  public boolean isActive() {
    return this.active;
  }

  /**
   * Deactivates the GameObject
   */
  public void deactivate() {
    this.active = false;
  }

  /**
   * Draws the GameObject at the current location
   */
  public void update() {
    if (this.active) {
      GameObject.processing.image(this.image, this.xPosition, this.yPosition);
    }
  }
  
  public void move(int dx, int dy) { // Test 1
    this.xPosition += dx;
    this.yPosition += dy;
  }
  public void setYPosition(int newY) {
    this.yPosition = newY;
  }

  /**
   * Initializes the processing field
   * 
   * @param processing The processing field
   */
  public static void setProcessing(PApplet processing) {
    GameObject.processing = processing;
  }

  /**
   * Accessor method for the static processing field
   * 
   * @return The static processing field
   */
  public static PApplet getProcessing() {
    return GameObject.processing;
  }
}
