import java.io.File;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * The GameObject class maintains the creation and usage of objects that will appear in the game.
 * 
 * @author vatrp
 */
public class GameObject {
  private final String NAME; // Name of the GameObject
  private PImage image; // Graphical Representation of the GameObject
  private int xPosition; // x-coordinate of the GameObject
  private int yPosition; // y-coordinate of the GameObject
  private static PApplet processing = null;

  public GameObject(String name, int x, int y) {
    this.NAME = name;
    this.xPosition = x;
    this.yPosition = y;
    this.image = GameObject.processing.loadImage("images" + File.separator + name + ".png");
  }

  /**
   * Returns true if the GameObject's name is equal to the name parameter, false otherwise
   * 
   * @param name The name being checked against the GameObject's NAME
   * @return true if name and NAME are equal, false otherwise
   */
  public boolean hasName(String name) {
    if (this.NAME == null || !this.NAME.equals(name)) {
      return false;
    }
    return true;
  }

  /**
   * Draws the GameObject at the correct location
   */
  public void update() {
    GameObject.processing.image(this.image, this.xPosition, this.yPosition);
  }

  /**
   * Moves the GameObject by the specified distances
   * 
   * @param dx The distance to move the GameObject in the horizontal direction (+ vals move right)
   * @param dy The distance to move the GameObject in the vertical direction (+ vals move up)
   */
  public void move(int dx, int dy) {
    this.xPosition += dx;
    this.yPosition += dy;
  }

  /**
   * Accessor method for the GameObject's x-position
   * 
   * @return The x-position of the GameObject
   */
  public int getXPosition() {
    return this.xPosition;
  }

  /**
   * Accessor method for the GameObject's y-position
   * 
   * @return The y-position of the GameObject
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
   * Initializes the processing field
   * 
   * @param processing The initial processing field
   */
  public static void setProcessing(PApplet processing) {
    GameObject.processing = processing;
  }

  /**
   * Accessor method for the static processing field
   * 
   * @return The static processing field
   */
  protected static PApplet getProcessing() {
    return GameObject.processing;
  }
}
