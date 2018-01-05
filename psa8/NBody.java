/* Filename: NBody.java 
 * Created by: Yijian Zong, cs8afhp
 * Date: December fourth 2017
 */ 
/*----------- Program Description: ------------
 [Part A: Body.java]
 * This is a program to set up interesting functions for a single planet body in the universe, all the methods written
 * here are for moving the body according to law of Physics(I know it's intimidating xD) and drawing the trace of the
 * movement. 
 [Part B: NBody.java]
 * This is a program to simulate our wonderful universe simulations through only four easy methods: Main Method, 
 * moveBodies Method, drawBodies Method, and pause Method. 
 **/
import java.awt.*;

/**
 * This is the primary class that will drive the universe simulation.
 */
public class NBody {
  
  public static final int CANVAS_WIDTH = 512;
  public static final int CANVAS_HEIGHT = 512;
  
  // This can be changed to modify the amount of delay between frames.
  private static final int SLEEP_TIME = 10;
  
  /**
   * Main program driver.
   * 
   * @param args Arguments passed in from the command line.
   */
  public static void main(String[] args) {
    Picture universe = new Picture(CANVAS_WIDTH, CANVAS_HEIGHT);
    Picture background = new Picture("images/starfield.jpg");
    
    String file = FileChooser.pickAFile();
    PlanetReader newPlanet = new PlanetReader(file);
    Body.UNIVERSE_SIZE = newPlanet.getUniverseSize();
    Body.TIME_STEP = newPlanet.getTimestep();
    Body[] bodyArray = newPlanet.getPlanets();
    double totalTime = newPlanet.getTotalTime();
    Sound theme2001 = new Sound("2001.wav");
    theme2001.play();
    drawBodies(bodyArray,universe,background);
    universe.show();
    for(double currentTime = 0; currentTime<=totalTime; currentTime+=Body.TIME_STEP){
      pause();
      moveBodies(bodyArray);
      drawBodies(bodyArray,universe,background);
      universe.repaint();
    }
    
  }
  
  /**
   * Helper method that runs the physics simulation on all planets.
   * 
   * @param planets Array of all bodies in the system.
   */
  public static void moveBodies(Body[] bodies) {
    for(int i=0;i<bodies.length;i++){
      for(int i2=0;i2<bodies.length;i2++){
        
        if (bodies[i].getXPosition()!=bodies[i2].getXPosition()&&bodies[i].getYPosition()!=bodies[i2].getYPosition())
        {
          
          bodies[i].updateVelocity(bodies[i2]);
        }
      }
    }
    for(int i3=0;i3<bodies.length;i3++){
      bodies[i3].move();
    }
  }
  
  
  /**
   * Helper method that resets the picture of the universe and draws all
   * planets onto it.
   * 
   * @param bodies Array of all bodies in the system.
   * @param universe A picture of the universe that wil be shown to the user.
   * @param background The background of the universe.
   */
  public static void drawBodies(Body[] bodies, Picture universe,
                                Picture background) {
    Pixel backgroundPixel = null;
    Pixel universePixel = null;
    Color bgColor;
    for(int x=0;x<background.getWidth();x++){
      for(int y=0;y<background.getHeight();y++){
        backgroundPixel= background.getPixel(x,y);
        universePixel = universe.getPixel(x,y);
        bgColor = backgroundPixel.getColor();
        universePixel.setColor(bgColor);
      }
    }
    for(int i=0;i<bodies.length;i++){
      bodies[i].draw(universe);
    }
    universe.repaint();
    
  }
  
  /**
   * You can use this to pause between frames.
   */
  private static void pause() {
    try {
      Thread.sleep(SLEEP_TIME);
    } catch (InterruptedException interrupt) {}
  }
}
