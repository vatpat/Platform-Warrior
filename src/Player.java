
/**
 * The Player class manages the creation and use of the Player's Figure, which is a Figure that the
 * user controls using the WASD keys (basic movement handled in the PlatformGame class)
 * 
 * @author vatrp
 */
public class Player extends Figure {
  private int toJump; // How much the Player has yet to Jump
  private boolean standing; // Whether the Player is standing on a Platform
  
  /**
   * Constructor for a Player object
   * 
   * @param name    The identifying name of the Player
   * @param x       The initial x-coordinate of the Player
   * @param y       The initial y-coordinate of the Player
   * @param health  The initial health of the Player
   * @param p       The Projectile that the Player shoots
   * @param reverse Reference to the image file that contains the reversed image of the Player
   */
  public Player(String name, int x, int y, int health, Projectile p, String reverse) {
    super(name, x, y, health, p, reverse);
    this.toJump = 0;
    this.standing = false;
  }
  
  /**
   * Indicates that the Player is standing on a Platform, so standing is set to true
   */
  public void stand() {
    this.standing = true;
  }
  
  /**
   * Indicates that the Player is no longer standing on a Platform
   */
  public void unStand() {
    this.standing = false;
  }
  
  /**
   * The Player jumps, setting toJump so that the y-coordinate of the Player is continuosuly
   * incremented in update()
   */
  public void jump() {
    if(this.standing) {
      this.unStand();
      this.toJump += 150;
    }
  }
  
  /**
   * The Player moves right, shifting the Player's x-coordinate after facing right
   */
  public void moveRight() {
    this.faceRight();
    this.move(3, 0);
  }
  
  /**
   * The Player moves left, shifting the Player's x-coordinate after facing left
   */
  public void moveLeft() {
    this.faceLeft();
    this.move(-3, 0);
  }
  
  /**
   * Overrides Figure's update() to properly update the Figure's y-coordinate if the character is
   * jumping
   */
  public void update() {
    // If toJump is greater than 0, then decrement toJump and increment the y-coordinate
    if(this.toJump > 0) {
      this.move(0, -10);
      this.toJump -= 10;
    }
    super.update();
  }
}
