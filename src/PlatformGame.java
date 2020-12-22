import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * PlatformGame creates and launches the graphics application for the Platform Shooter Game
 * 
 * @author vatrp
 */
public class PlatformGame extends PApplet {
  private PImage backgroundImage;
  // private ArrayList<GameObjects> objects;

  /**
   * Sets the size of the application window
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
    // Set the processing field:
    GameObject.setProcessing(this);
    
    this.getSurface().setTitle("Platform Game");
    this.backgroundImage = loadImage("images" + File.separator + "background.png");
  }

  /**
   * Updates the treasure hunt game display window
   */
  @Override
  public void draw() {
    // draw background image at (0,0)
    image(backgroundImage, 0, 0);
    
    // Checkpoint 1:
    //GameObject gamechar = new GameObject("player", 50, 700);
    //gamechar.update();
    
    // Checkpoint 2:
    // Character c1 = new Character("player",50,700,1);
    // c1.update();
    
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
