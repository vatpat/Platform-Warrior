import java.io.File;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * The Projectile class defines 'Projectile' objects, which are shot by Figures and deal damage
 * to other Figures on impact
 * 
 * @author vatrp
 */
public class Projectile {
  private PImage image; // Graphical representation of the Projectile
  private Figure figure; // A reference to the Figure that shoots the Projectile
  private int damage; // The damage that the Projectile deals on impact
  private int maxDistance; // The maximum distance that the Projectile can travel
  private int toTravel; // The distance that the Projectile still needs to travel
  private int x; // The x-coordinate of the Projectile
  private int y; // The y-coordinate of the Projectile
  private boolean active; // Whether the Projectile is active (whether it should be displayed)
  public boolean facingRight; // Whether the Projectile is being fired to the right

  /**
   * Constructor for a Projectile Object
   * 
   * @param name The identifying name of the Projectile
   * @param c    A reference to the Figure that shoots this Projectile
   * @param dmg  The damage that this Projectile will deal upon impact with another Figure
   */
  public Projectile(String name, int dmg) {
    PApplet processing = GameObject.getProcessing();
    this.image = processing.loadImage("images" + File.separator + name + ".png");
    this.damage = dmg;
    this.maxDistance = 150;
    this.toTravel = 0;
    this.active = false;
  }

  /**
   * Sets a reference to the Figure that fires this Projectile
   * 
   * @param c A reference to the Figure that fires this Projectile
   */
  public void setFigure(Figure c) {
    this.figure = c;
  }

  /**
   * Returns whether the Projectile is active
   * 
   * @return True if the Projectile is active, false otherwise
   */
  public boolean isActive() {
    return this.active;
  }
  
  /**
   * Deactivates the Projectile
   */
  public void deactivate() {
    this.active = false;
  }

  /**
   * Returns the x-coordinate of the Projectile
   * 
   * @return The x-coordinate of the Projectile
   */
  public int getXPosition() {
    return this.x;
  }

  /**
   * Returns the y-coordinate of the Projectile
   * 
   * @return The y-coordinate of the Projectile
   */
  public int getYPosition() {
    return this.y;
  }

  /**
   * Accessor method for the damage that this Projectile inflicts
   * 
   * @return The damage that this Projectile inflicts
   */
  public int getDamage() {
    return this.damage;
  }

  /**
   * Accessor method for the Projectile's image
   * 
   * @return A reference to the Projectile's PImage
   */
  public PImage getImage() {
    return this.image;
  }

  /**
   * Sets the Projectile's (x,y) to the correct position in relation to the Figure. toTravel is
   * set to maxDistance and facingRight is set based on the Figure's current direction. Finally,
   * the Projectile is set to being active, which signals that it should be drawn.
   */
  public void fire() {
    // If the Projectile is currently active or the Figure is not active, do nothing
    if (this.active || !this.figure.isActive()) {
      return;
    }

    PImage charImage = this.figure.getImage();
    this.facingRight = this.figure.facingRight();

    // If the Figure is facing right, then the Projectile will be shot to the right
    if (this.figure.facingRight()) {
      this.x = this.figure.getXPosition() + charImage.width;
    } else {
      // Otherwise, Projectile will be shot to the left
      this.x = this.figure.getXPosition() - this.image.width;
    }
    this.y = this.figure.getYPosition() + (charImage.height / 4);
    this.toTravel = this.maxDistance;
    this.active = true;
  }

  /**
   * Records the Projectile traveling a specified distance. If the Projectile has travelled its max
   * distance, then it is deactivated.
   * 
   * @param distance The distance the Projectile has traveled
   */
  public void travel(int distance) {
    if (this.facingRight) {
      // If the Projectile is facing right, then increment x by distance
      this.x += distance;
    } else {
      // Otherwise, the Projectile is facing left, so x is decreased by distance
      this.x -= distance;
    }
    this.toTravel -= distance;
    // If toTravel is now less than or equal to 0, then the Projectile is done travelling
    if (this.toTravel <= 0) {
      this.active = false;
    }
  }

  /**
   * Returns true if this Projectile's image intersects the image of another Figure
   * 
   * @param other The Figure whose image is being compared against this Projectile
   * @return True if the images intersect, false otherwise
   */
  public boolean isOver(Figure other) {
    // If either the Projectile or the Figure is not active, return false
    if(!this.active || !other.isActive()) {
      return false;
    }
    
    PImage otherI = other.getImage();
    // if the objects do not overlap, they are separated by some vertical or horizontal distance
    // (or both)
    // checking vertical distance:
    if (other.getYPosition() + otherI.height < this.y
        || this.y + this.image.height < other.getYPosition()) {
      return false;
    }
    // checking horizontal distance:
    if (other.getXPosition() + otherI.width < this.x
        || this.x + this.image.width < other.getXPosition()) {
      return false;
    }
    // if the images are not separated by either, then they overlap:
    return true;
  }
}
