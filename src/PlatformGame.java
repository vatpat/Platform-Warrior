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
  private ArrayList<GameObject> objects;
  // GameObject player; //Test 1

  /**
   * Sets the size of the Application Window
   */
  @Override
  public void settings() {
    size(1000, 1000);
  }

  /**
   * Initializes environment properties, loads background, loads game characters/enemies, and
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
    
    // Initialize the GameObjects ArrayList
    this.objects = new ArrayList<GameObject>();
    
    
    // GameObject player = new GameObject("player", 30, 30);
    // this.objects.add(player);
    
    // initialize player
    // player = new GameObject("player", 30, 30); //Test 1
  }

  /**
   * Updates the treasure hunt game display window
   */
  @Override
  public void draw() {
    // draw background image at (0,0)
    image(this.backgroundImage, 0, 0);
    
    /*
    GameObject object = this.objects.get(0);
    object.move(1, 1);
    if(object.isActive()) {
      object.update();
    }
    */
    
    /*
    // Create and draw a GameObject     Test 1
    player.move(1, 1);
    if(player.getXPosition() > 50) {
      player.deactivate();
    }
    if(player.isActive()) {
      player.update();
    }
    */
  }
  
  public void keyPressed() {
    // Check which key is pressed, call the appropriate action for each GameObject
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
