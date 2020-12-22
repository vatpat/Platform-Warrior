import java.io.File;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * The Character class maintains and updates the use of 'Characters', which are all GameObjects that
 * shoot some Projectile
 * 
 * @author vatrp
 */
public class Character extends GameObject {
  private int health; // The health of the Character
  private boolean alive; // Boolean representing the state of the Character
  private Projectile projectile; // The Projectile that the Character shoots

  /**
   * Constructor for a Character object
   * 
   * @param name       The identifying name of the Character
   * @param x          The initial x-coordinate of the Character
   * @param y          The initial y-coordinate of the Character
   * @param health     The initial health of the Character (must be at least 1)
   * @param projectile The damaging Projectile that the Character shoots
   */
  public Character(String name, int x, int y, int health, Projectile projectile) {
    super(name, x, y);
    // If initial health <= 0, throw Exception
    if (health <= 0) {
      throw new IllegalArgumentException("Initial Health must be greater than 0");
    }
    // Otherwise, initialize values
    this.health = health;
    this.alive = true;
    this.projectile = projectile;
    this.projectile.setCharacter(this);
  }

  /**
   * Inflicts damage to the Character. If the damage exceeds the remaining health, then the
   * Character is no longer alive.
   * 
   * @param damageAmount The amount of damage done to the Character.
   */
  public void damage(int damageAmount) {
    this.health -= damageAmount;
    if(this.health <= 0) {
      this.alive = false;
    }
  }

  /**
   * Returns true if the Character is alive, false otherwise
   * 
   * @return True if the Character is alive
   */
  public boolean isAlive() {
    return this.alive;
  }

  /**
   * Accessor method for this Character's projectile
   * 
   * @return A reference to the Character's projectile
   */
  public Projectile getProjectile() {
    return this.projectile;
  }

  /**
   * Override's GameObject's update() method. Only draws the Character if it is alive
   */
  @Override
  public void update() {
    if (this.alive) {
      PApplet processing = GameObject.getProcessing();
      processing.image(this.getImage(), this.getXPosition(), this.getYPosition());
    }
  }
}
