

/**
 * The Enemy class manages the creation and use of Enemy objects, which are Figures with automated
 * movement and shooting procedusres
 * 
 * @author vatrp
 *
 */
public class Enemy extends Figure {
  private int pace;
  private int netMovement;
  private boolean movingRight;
  private int increment;

  /**
   * Constructor for an Enemy object
   * 
   * @param name    The identifying name of the Enemy
   * @param x       The initial x-coordinate of the Enemy
   * @param y       The initial y-coordinate of the Enemy
   * @param health  The initial health of the Enemy
   * @param p       The Projectile that the Enemy shoots
   * @param reverse Reference to the image file that contains the reversed image of the Enemy
   */
  public Enemy(String name, int x, int y, int health, Projectile p, String reverse, int pace) {
    super(name, x, y, health, p, reverse);
    this.pace = pace;
    this.netMovement = 0;
    this.movingRight = true;
    this.increment = this.pace / 25;
  }

  /**
   * Overrides Figure's update() method to automate the Enemy's movement back-and-forth
   */
  public void update() {
    super.update();
    if(this.isActive()) {
      // If the Enemy is moving right and hasn't reached the max distance, continue moving right
      if(this.movingRight && this.netMovement < this.pace) {
        this.move(this.increment, 0);
        this.netMovement += this.increment;
      }
      // If the Enemy has reached or exceeded the max distance facing right, then change to moving
      // left
      if(this.movingRight && this.netMovement >= this.pace) {
        this.movingRight = false;
        this.faceLeft();
        this.getProjectile().fire();
        return;
      }
      // If the enemy is moving left and hasn't reached the max distance, continue moving left
      if(!this.movingRight && this.netMovement > (-1 * this.pace)) {
        this.move(-1 * this.increment, 0);
        this.netMovement -= this.increment;
      }
      // If the Enemy has reached or exceeded the max distance facing left, then change to moving
      // right
      if(!this.movingRight && this.netMovement <= (-1 * this.pace)) {
        this.movingRight = true;
        this.faceRight();
        this.getProjectile().fire();
        return;
      }
    }
  }
}
