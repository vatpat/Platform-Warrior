import java.io.File;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * The Figure class maintains the creation and usage of Figures, which are GameObjects that shoot a
 * Projectile objects and are active when they are alive (health > 0)
 * 
 * @author vatrp
 */
public class Figure extends GameObject {
  private int health; // The Figure's health
  private Projectile projectile; // The Projectile that the Figure shoots (or null if none)
  private boolean facingRight; // Whether the Figure is facing right or not
  private PImage reverseImage; // The left-facing image of the Figure

  /**
   * Constructor for a Figure object
   * 
   * @param name    The identifying name of the Figure
   * @param x       The initial x-coordinate of the Figure
   * @param y       The initial y-coordinate of the Figure
   * @param health  The initial health of the Figure
   * @param p       The Projectile that the Figure shoots
   * @param reverse Reference to the image file that contains the reversed image of the Figure
   */
  public Figure(String name, int x, int y, int health, Projectile p, String reverse) {
    super(name, x, y);
    // If health is not greater than 0, throw IllegalArgumentException
    if (health <= 0) {
      throw new IllegalArgumentException("Initial Health must be greater than 0");
    }
    this.health = health;
    this.projectile = p;
    PApplet processing = GameObject.getProcessing();
    this.reverseImage = processing.loadImage("images" + File.separator + reverse + ".png");
    this.facingRight = true; // By default, the GameObject will face right
  }

  /**
   * Inflicts damage to the Figure, deactivating it if health <= 0 after damage has been registered
   * 
   * @param damage The amount of damage that the Figure takes
   */
  public void inflictDamage(int damage) {
    this.health -= damage;
    if (this.health <= 0) {
      this.deactivate();
    }
  }

  /**
   * Accessor method for the Characte's Projectile
   * 
   * @return A reference to the Figure's Projectile
   */
  public Projectile getProjectile() {
    return this.projectile;
  }

  /**
   * Sets the Figure to facing right
   */
  public void faceRight() {
    this.facingRight = true;
  }

  /**
   * Sets the Figure to facing left
   */
  public void faceLeft() {
    this.facingRight = false;
  }

  /**
   * Returns whether the Figure is facing right
   * 
   * @return True if the Figure is facing right, false otherwise
   */
  public boolean facingRight() {
    return this.facingRight;
  }

  /**
   * Overrides GameObject's update() method to display the Figure facing the correct direction, and
   * displaying the Projectile if it is active at its current location
   */
  public void update() {
    PApplet processing = GameObject.getProcessing();

    // If the Figure is facing right, then use GameObject's update()
    if (this.facingRight) {
      super.update();
    } else {
      // Otherwise, the Figure is facing left. If it is active, then draw the reverseImage
      if(this.isActive()) {
        processing.image(this.reverseImage, this.getXPosition(), this.getYPosition());
      }
    }
    // Check if the Figure's Projectile is active. If it is, then draw it to the appropriate
    // position, and travel a short distance
    if (this.projectile != null && this.projectile.isActive()) {
      Projectile p = this.projectile;
      processing.image(p.getImage(), p.getXPosition(), p.getYPosition());
      p.travel(8);
    }
  }
}
