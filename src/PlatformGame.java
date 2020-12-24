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
  private ArrayList<Figure> figures; // ArrayList of Figures to be displayed
  private ArrayList<Platform> platforms; // ArrayList of Platforms to be displayed
  private boolean[] keys; // Tracks whether 'w', 'a', 's', and 'd' have been pressed
  Player player; // Test 1
  Enemy enemy;
  Projectile fire;
  Projectile fire2;

  /**
   * Sets the size of the Application Window
   */
  @Override
  public void settings() {
    size(1000, 1000);
  }

  /**
   * Initializes environment properties, loads background, loads game Figures/enemies, and
   * initializes instance fields at the program's start
   */
  @Override
  public void setup() {
    // Set the processing field
    GameObject.setProcessing(this);

    // Set title of window
    this.getSurface().setTitle("Action Platform Game");

    // Load background image
    this.backgroundImage = loadImage("images" + File.separator + "background.png");

    // Initialize the keys pressed to false
    keys = new boolean[5];
    for (int i = 0; i < 5; i++) {
      keys[i] = false;
    }

    // Initialize the Figures ArrayList
    this.figures = new ArrayList<Figure>();
    
    this.platforms = new ArrayList<Platform>();
    Platform plat = new Platform("platform", 100, 670);
    this.platforms.add(plat);
    Platform large = new Platform("largePlatform", 0, 805);
    this.platforms.add(large);

    // GameObject player = new GameObject("player", 30, 30);
    // this.objects.add(player);

    // initialize player
    fire = new Projectile("projectile2", 15);
    player = new Player("player2", 30, 50, 30, fire, "player2Reverse"); // Test 1
    fire.setFigure(player);
    fire2 = new Projectile("projectile2", 15);
    enemy = new Enemy("player2", 500, 725, 150, fire2, "player2Reverse", 50);
    fire2.setFigure(enemy);
  }

  /**
   * Updates the treasure hunt game display window
   */
  @Override
  public void draw() {
    // draw background image at (0,0)
    image(this.backgroundImage, 0, 0);

    if (keys[0] == true) {
      player.jump();
    }
    if (keys[1] == true) {
      player.faceLeft();
      player.move(-2, 0);
    }
    if (keys[2] == true) {
      boolean found = false;
      for(Platform p: this.platforms) {
        if(p.isStanding(this.player)) {
          found = true;
        }
      }
      if(!found) {
        player.move(0, 2);
      }
    }
    if (keys[3] == true) {
      player.faceRight();
      player.move(2, 0);
    }
    if (keys[4] == true) {
      fire.fire();
      keys[4] = false;
    }
    if (player.getProjectile().isOver(enemy)) {
      fire.deactivate();
      enemy.inflictDamage(player.getProjectile().getDamage());
    }
    if (enemy.getProjectile().isOver(player)) {
      fire2.deactivate();
      player.inflictDamage(enemy.getProjectile().getDamage());
    }
    
    boolean f = false;
    for(Platform p: this.platforms) {
      if(p.isStanding(enemy)) {
        f = true;
      }
    }
    if(!f) {
      enemy.move(0, 5);
    }
    boolean a = false;
    for(Platform p: this.platforms) {
      if(p.isStanding(player)) {
        player.stand();
        a = true;
      }
    }
    if(!a) {
      player.move(0, 5);
    }
    player.update();
    enemy.update();
    for(Platform p: this.platforms) {
      p.update();
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
    if (key == 's') {
      keys[2] = true; // 's' was pressed
    }
    if (key == 'd') {
      keys[3] = true; // 'd' was pressed
    }
    if (key == 'f') {
      keys[4] = true; // 'f' was pressed - fire
    }
  }

  /**
   * Called when a key is released. Signals the end of certain events (like movement)
   */
  public void keyReleased() {
    if (key == 'w') {
      keys[0] = false; // 'w' was pressed
    }
    if (key == 'a') {
      keys[1] = false; // 'a' was pressed
    }
    if (key == 's') {
      keys[2] = false; // 's' was pressed
    }
    if (key == 'd') {
      keys[3] = false; // 'd' was pressed
    }
    if (key == 'f') {
      keys[4] = false; // 'f' was released
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
