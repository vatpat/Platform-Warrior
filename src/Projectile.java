import java.io.File;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * The Projectile class defines 'Projectile' objects, which are shot by Characters and deal damage
 * to other characters on impact
 * 
 * @author vatrp
 */
public class Projectile {
  private Character character;
  private PImage image;
  private int damage;
  
  public void setCharacter(Character character) {
    this.character = character;
  }
}
