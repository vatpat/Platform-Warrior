import java.awt.event.MouseEvent;
import java.io.File;
import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;

/**
 * The PlatformGame class creates and launches the Graphics Application for the Platform Game
 * 
 * @author vatrp
 */
public class PlatformGame extends PApplet {
  private PImage backgroundImage;
  private Player player; // Reference to the Player's GameObject
  private ArrayList<Enemy> enemies; // Reference to the List of Enemies that the Player must defeat
  private ArrayList<Platform> platforms; // ArrayList of Platforms to be displayed
  private boolean[] keys; // Tracks whether 'w', 'a', 's', 'd', and 'f' have been pressed

  /**
   * Sets the size of the Application Window
   */
  @Override
  public void settings() {
    setSize(1000, 1000);
  }

  /**
   * Initializes environment properties, loads background, loads the Player, the Enemies, and the
   * corresponding Projectiles, and loads the platforms
   */
  @Override
  public void setup() {
    // Set the processing field
    GameObject.setProcessing(this);

    // Set title of window
    this.getSurface().setTitle("Action Platform Game");

    // Load background image
    this.backgroundImage = loadImage("images" + File.separator + "background.png");

    // Initialize the Player and its Projectile
    Projectile playerProjectile = new Projectile("playerProjectile", 30);
    this.player = new Player("player", 50, 705, 100, playerProjectile, "playerReverse");
    playerProjectile.setFigure(this.player);

    // Initialize the List of Enemies and their Projectiles
    this.initializeEnemies();

    // Initialize the List of Platforms
    this.initializePlatforms();

    // Initialize the keys pressed to false
    keys = new boolean[4];
    for (int i = 0; i < 4; i++) {
      keys[i] = false;
    }
  }

  /**
   * Updates the treasure hunt game display window
   */
  @Override
  public void draw() {
    frameRate(65);

    // draw background image at (0,0)
    image(this.backgroundImage, 0, 0);

    // display health
    textSize(21);
    fill(153);
    this.text("Health: " + this.player.getHealth() + " / 100", 215, 194);

    // If Player is not Active,
    if (!this.player.isActive()) {
      textSize(100);
      this.text("GAME OVER", 50, 500, 100);
      return;
    }

    // If there are no more enemies
    if (this.enemies.size() == 0) {
      textSize(100);
      this.text("YOU WIN!", 50, 500, 100);
      return;
    }

    // draw all of the Platforms
    this.drawPlatforms();

    // Check if game keys have been pressed:
    this.updateKeys();

    // Check if any of the Projectiles need to deal damage
    this.updateProjectiles();

    // Check if any of the Figures are not on Platforms, and apply gravity if they are not
    this.applyGravity();

    // Update every Figure
    this.player.update();
    for (Enemy e : this.enemies) {
      e.update();
    }
  }

  /**
   * Called when a key is pressed. The key pressed will be tracked and will be used to determine
   * Events in the PlatformGame
   */
  public void keyPressed() {
    // Check which key is pressed, call the appropriate action for each GameObject
    if (key == 'w') {
      keys[0] = true; // 'w' was pressed
    }
    if (key == 'a') {
      keys[1] = true; // 'a' was pressed
    }
    if (key == 'd') {
      keys[2] = true; // 'd' was pressed
    }
    if (key == 'f') {
      keys[3] = true; // 'f' was pressed
    }
  }

  /**
   * Called when a key is released. Signals the end of certain events (like movement)
   */
  public void keyReleased() {
    if (key == 'w') {
      keys[0] = false; // 'w' was released
    }
    if (key == 'a') {
      keys[1] = false; // 'a' was released
    }
    if (key == 'd') {
      keys[2] = false; // 'd' was released
    }
    if (key == 'f') {
      keys[3] = false; // 'f' was released
    }
  }

  /**
   * Displays all of the Platforms
   */
  public void drawPlatforms() {
    // Call every Platform's update() method, drawing it at its coordinates
    for (Platform p : this.platforms) {
      p.update();
    }
  }

  /**
   * Initializes the Enemies in the game, as well as their corresponding Projectiles
   */
  public void initializeEnemies() {
    this.enemies = new ArrayList<Enemy>();

    // First Enemy:
    Projectile enemy1Projectile = new Projectile("enemyProjectile", 15);
    Enemy enemy1 = new Enemy("enemy", 500, 735, 100, enemy1Projectile, "enemy", 50);
    enemy1Projectile.setFigure(enemy1);
    this.enemies.add(enemy1);

    // Second Enemy:
    Projectile enemy2Projectile = new Projectile("enemyProjectile", 20);
    Enemy enemy2 = new Enemy("enemy", 750, 505, 120, enemy2Projectile, "enemy", 50);
    enemy2Projectile.setFigure(enemy2);
    this.enemies.add(enemy2);

    // Third Enemy:
    Projectile enemy3Projectile = new Projectile("enemyProjectile", 25);
    Enemy enemy3 = new Enemy("enemy", 250, 400, 130, enemy3Projectile, "enemy", 50);
    enemy3Projectile.setFigure(enemy3);
    this.enemies.add(enemy3);

    // Fourth Enemy:
    Projectile enemy4Projectile = new Projectile("enemyProjectile", 35);
    Enemy enemy4 = new Enemy("enemy", 500, 250, 140, enemy4Projectile, "enemy", 50);
    enemy4Projectile.setFigure(enemy4);
    this.enemies.add(enemy4);
  }

  /**
   * Initializes the Platforms in the game
   */
  public void initializePlatforms() {
    this.platforms = new ArrayList<Platform>();

    // First Platform - Long Floor Platform
    Platform p1 = new Platform("largePlatform", 0, 805);
    this.platforms.add(p1);

    // Second Platform - Above First Enemy
    Platform p2 = new Platform("platform", 50, 670);
    this.platforms.add(p2);

    // Third Platform - Holds Second Enemy
    Platform p3 = new Platform("platform", 550, 590);
    this.platforms.add(p3);

    // Fourth Platform - Holds Third Enemy
    Platform p4 = new Platform("platform", 50, 510);
    this.platforms.add(p4);

    // Fifth Platform - Lead-up to Final Enemy
    Platform p5 = new Platform("platform", 550, 430);
    this.platforms.add(p5);

    // Sixth Platform - Final Enemy
    Platform p6 = new Platform("platform", 280, 350);
    this.platforms.add(p6);
  }

  /**
   * Check if any of the game keys have been pressed, and act accordingly
   */
  public void updateKeys() {
    // if 'w' has been pressed, the Player jumps
    if (keys[0] == true) {
      this.player.jump();
    }
    // if 'a' has been pressed, the Player moves left
    if (keys[1] == true && this.player.getXPosition() > 5) {
      this.player.moveLeft();
    }
    // if 'd' has been pressed, the Player moves right
    if (keys[2] == true && this.player.getXPosition() < 950) {
      this.player.moveRight();
    }
    // if 'f' has been pressed, the Player fires its Projectile
    if (keys[3] == true) {
      this.player.getProjectile().fire();
      // Sets the key to false so it dosen't continuously fire
      keys[3] = false;
    }
  }

  /**
   * Checks if any of the Projectiles need to deal damage
   */
  public void updateProjectiles() {
    ArrayList<Enemy> dead = new ArrayList<Enemy>();
    // For every enemy, check if its Projectile needs to deal damage to the Player, and check if
    // the Player's Projectile needs to deal damage to the Enemy
    for (Enemy e : this.enemies) {
      // Check the Player's Projectile
      if (this.player.getProjectile().isOver(e)) {
        // Deactivate the Player's Projectile and deal damage to the Enemy
        Projectile p = this.player.getProjectile();
        p.deactivate();
        e.inflictDamage(p.getDamage());
        // If the enemy is dead, note that it must be removed
        if(!e.isActive()) {
          dead.add(e);
        } 
      }
      // Check the Enemy's Projectile
      if (e.getProjectile().isOver(this.player)) {
        Projectile p = e.getProjectile();
        p.deactivate();
        this.player.inflictDamage(p.getDamage());
      }
    }
    // Remove all dead enemies
    for(Enemy e : dead) {
      this.enemies.remove(e);
    }
  }

  /**
   * Applies gravity to every Figure, shifting it down if it is not standing on a Platform
   */
  public void applyGravity() {
    // Check if the Player is on a Platform
    boolean playerStand = false;
    for (Platform p : this.platforms) {
      if (p.isStanding(this.player)) {
        this.player.stand();
        playerStand = true;
        break;
      }
    }
    // If the Player is not standing on a Platform, shift the Player down
    if (!playerStand) {
      this.player.unStand();
      this.player.applyGravity();
    }
    // For every Enemy, check if the Enemy is standing on a Platform
    for (Enemy e : this.enemies) {
      boolean enemyStand = false;
      for (Platform p : this.platforms) {
        if (p.isStanding(e)) {
          enemyStand = true;
          break;
        }
      }
      // If the Enemy is not standing on a Platform, shift the Enemy down
      if (!enemyStand) {
        e.applyGravity();
      }
    }
  }

  /**
   * Launches the Graphics Application
   * 
   * @param args Input Arguments
   */
  public static void main(String[] args) {
    PApplet.main("PlatformGame");
  }
}
