import processing.core.PImage;

/**
 * The Platform class maintains the creation and usage of Platforms, which are objects that
 * Figures and Enemies can stand on.
 * 
 * @author vatrp
 */
public class Platform extends GameObject {

  /**
   * Constructor for a Platform object
   * 
   * @param name The identifying name of the Platform object
   * @param x    The x-coordinate of the Platform
   * @param y    The y-coordinate of the Platform
   */
  public Platform(String name, int x, int y) {
    super(name, x, y);
  }
  
  /**
   * Returns true if the Figure is graphically standing on this Platform
   * @param c The Figure being checked against this Platform
   * @return True if the Figure is standing on this Platform, false otherwise
   */
  public boolean isStanding(Figure f) {
    // Check if the Figure's mid-x value is located within the width of the platform
    PImage figure = f.getImage();
    int figureMidX = f.getXPosition() + (figure.width / 2);
    int leftSide = this.getXPosition();
    int rightSide = leftSide + this.getImage().width;
    // If the mid-x of the Figure is less than the leftSide of the Platform or greater than the
    // right side of the Platform, then the Figure is not standing on the Platform
    if(figureMidX < leftSide || figureMidX > rightSide) {
      return false;
    }
    
    // Check the vertical distance between the Figure and the Platform
    int topSide = this.getYPosition();
    int figureBottomY = f.getYPosition() + figure.height;
    // If the distance between the top of the Platform and the bottom of the Figure is greater than
    // 5, the Figure is not standing on the Platform
    if(topSide - figureBottomY > 3 || topSide - figureBottomY < 0) {
      return false;
    }
    
    // Otherwise, the Figure is standing on the Platform
    f.setYPosition(topSide - figure.height);
    return true;
    
  }

}
