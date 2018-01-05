/* Filename: Body.java 
 * Created by: Yijian Zong, cs8afhp
 * Date: December fourth 2017
 */ 
import java.awt.*;

/**
 * This is a class that represents a single planet, particle, or actor in a
 * system.
 */
public class Body {
  
  private static final double GRAVITATIONAL_CONSTANT = 6.67E-11;
  
  public static double TIME_STEP;
  public static double UNIVERSE_SIZE;
  public static double widthUniverse = 512; 
  private double xPosition;
  private double yPosition;
  private double xVelocity;
  private double yVelocity;
  private double mass;
  private Picture image;
  
  /**
   * Full constructor for this body.
   * 
   * @param xPosition The starting x coordinate in the universe.
   * @param yPosition The starting y coordinate in the universe.
   * @param xVelocity The starting momentum in the x direction.
   * @param yVelocity The starting momentum in the y direction.
   * @param mass The mass of the body, used to apply gravity.
   * @param image The picture to draw for this body.
   */
  public Body(double xPosition, double yPosition, double xVelocity,
              double yVelocity, double mass, Picture image) {
    this.xPosition = xPosition;
    this.yPosition = yPosition;
    this.xVelocity = xVelocity;
    this.yVelocity = yVelocity;
    this.mass = mass;
    this.image = image;
  }
  
  /**
   * Getter for this body's x position.
   * 
   * @return The body's x position, in the universe.
   */ 
  public double getXPosition()
  {
    
    return xPosition;
  }
  
  /**
   * Getter for this body's y position.
   * 
   * @return The body's y position, in the universe.
   */
  public double getYPosition()
  {
    
    return yPosition;
  }
  
  /**
   * Getter for the mass of this body.
   * 
   * @return The body's mass.
   */
  public double getMass()
  {
    return mass;
  }
  
  /**
   * Get the distance to another body.
   * 
   * @param otherBody The body to compare for distance to this one.
   * @return The distance to the other body.
   */
  public double getDistance(Body otherBody)
  {
    double otherX = otherBody.getXPosition();
    double otherY = otherBody.getYPosition();
    double xDistance = otherX - this.getXPosition();
    double yDistance = otherY - this.getYPosition();
    double distance = Math.hypot(xDistance,yDistance);
    return distance;
  }
  
  /**
   * Calculate the magnitude of the acceleration between this body and another.
   * 
   * @param otherBody The other body to accelerate towards.
   * @return The magnitude of acceleration to the other body.
   */
  public double getAcceleration(Body otherBody)
  {
    double doubleMass = otherBody.getMass();
    double distance = this.getDistance(otherBody);
    double acceleration = GRAVITATIONAL_CONSTANT*doubleMass/(Math.pow(distance,2));
    return acceleration;
  }
  
  /**
   * Apply the acceleration due to gravity on this body from another.
   * 
   * @param otherBody The body to accelerate towards.
   */
  public void updateVelocity(Body otherBody)
  {
    
    double x2 = otherBody.getXPosition();
    double x1 = this.getXPosition();
    double y2 = otherBody.getYPosition();
    double y1 = this.getYPosition();
    double r = this.getDistance(otherBody);
    double a = this.getAcceleration(otherBody);
    double aX = a*(x2-x1)/r;
    double aY = a*(y2-y1)/r;
    xVelocity = xVelocity+aX*TIME_STEP;
    yVelocity = yVelocity+aY*TIME_STEP;
  }
  
  /**
   * Move the body based on its current velocity.
   */ 
  public void move()
  {
    xPosition = xPosition + xVelocity*TIME_STEP;
    yPosition = yPosition + yVelocity*TIME_STEP;
  }
  
  /**
   * Calculate the x pixel location where the center of this body will be drawn.
   * 
   * @return The x pixel location of the center of this body.
   */
  public int centerXToPixelPosition()
  {
    
    int xLocation = (int)(xPosition/UNIVERSE_SIZE * widthUniverse) ;
    return xLocation;
  }
  
  /**
   * Calculate the y pixel location where the center of this body will be drawn.
   * 
   * @return The y pixel location of the center of this body.
   */
  public int centerYToPixelPosition()
  {
    
    int yLocation = (int)(yPosition/UNIVERSE_SIZE * widthUniverse) ;
    return yLocation;
  }
  
  
  
  /**
   * Draw the planet for the current frame on the image.
   *
   * @param universe The picture on which to draw the planet.
   */
  public void draw(Picture universe)
  {
    int x = this.centerXToPixelPosition()-image.getWidth()/2;
    int y = this.centerYToPixelPosition()-image.getHeight()/2;
    image.alphaBlending(x,y,universe);
  }
  
  /**
   * Serialize this planet such that it can be read and converted to a planet
   * object from a text file.
   * 
   * @return A line that contains all the information about this planet.
   */ 
  public String toString()
  {
    String serialization = xPosition + " " + yPosition + " " +
      xVelocity +  " " + yVelocity + " " + mass;
    serialization += " " + image.getFileName();
    return serialization;
  }
}
