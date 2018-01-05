/* Filename: Picture.java
 * Created by: CSE 8A Staff
 * Date: Fall 2017
 */

/*----------- Program Description: ------------
 [Part A: Picture]
 * This programs is designed to create state-of-the-art striking methods that help us create a scene of movie that 
 * takes us to anywhere we want with just a few lines of code, out of a photo taken behind a purely gcreen canvas!
 * The main methods chromakeyBackgroundChange and chromakeyShirtChange would do the trick, the first one changes the 
 * green background to any wonderful scenes like space, wonderland , or the horrifying aliens place. The second one 
 * changes the image on the shirt of the person to any setting, which in this case is an angry xenomorph attacking
 * on a plane.
 [Part B: Chromakey]
 * This program is designed to put the pieces together and finally demonstrate the effect the methods make in 
 * Picture.java.
 **/

import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from
 * SimplePicture and allows the student to add functionality to
 * the Picture class.
 *
 * Copyright Georgia Institute of Technology 2004-2005
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor
     */
    super();
  }
  
  /**
   * Constructor that takes a file name and creates the picture
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param width the width of the desired picture
   * @param height the height of the desired picture
   */
  public Picture(int width, int height)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a
   * copy of that picture
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() +
      " height " + getHeight()
      + " width " + getWidth();
    return output;
  }
  
  /**
   * Method to return a picture with its background changed.
   * Input: the target background picture, the color to be replaced, the tolerance set for the colorDistance.
   * Returns: A copy of the photo with its background changed. 
   */
  public Picture chromakeyBackgroundChange(Picture background,
                                           Color replaceColor, double tolerance)
  {  
    Pixel copyPixel = null;
    Pixel newPixel = null;
    Picture copy = new Picture(this);
    // loop through the columns
    for (int x=0; x<getWidth(); x++)
    {
      
      // loop through the rows
      for (int y=0; y<getHeight(); y++)
      {
        
        // get the current pixel
        copyPixel = copy.getPixel(x,y);
        
        if ( copyPixel.colorDistance(replaceColor)<tolerance)
        {
          newPixel = background.getPixel(x,y);
          copyPixel.setColor(newPixel.getColor());
        }
      }
    }
    return copy;
    
  }
  /**
   * Extra helper method to make the original shirt of different color the same color.
   * Input: the replacing color, starting coordinates X and Y, the width and height of the shirt of the photo, 
   * and the the tolerance set for the colorDistance.
   * Returns: A copy of the photo with its shirt of the same color.
   */
  public Picture setShirtRGB(Color replaceColor,int startX, int startY, int width, 
                             int height, double tolerance)
  {  
    Pixel currentPixel = null;
    Pixel newPixel = null;
    Picture newPhoto = new Picture(this);
    // loop through the columns
    for (int x=startX; x<startX+width; x++)
    {
      
      // loop through the rows
      for (int y=startY; y<startY+height; y++)
      {
        
        // get the current pixel
        currentPixel = newPhoto.getPixel(x,y);
        
        if ( currentPixel.colorDistance(replaceColor)<tolerance)
        {
          currentPixel.setColor(new Color(0,0,0));
        }
      }
    }
    return newPhoto;
  }
  
  /**
   * Method to return a picture with its character's shirt changed.
   * Input: the target shirt picture, the original picture,the shirt color which will be changed, the starting 
   * coordinates x,y, the width and height of the shirt in the picture, the tolerance set for the colorDistance.
   * Returns: A copy of the photo with its shirt changed. 
   */
  public Picture chromakeyShirtChange(Picture shirt, Picture original,
                                      Color oldShirtColor, int startX, int startY, int width, 
                                      int height, double tolerance)
  {
    Pixel shirtPixel = null;
    Pixel originalPixel = null;
    Pixel copyPixel = null;
    Picture copy = new Picture(this);
    // loop through the columns
    for (int x=startX; x<startX+width; x++)
    {
      
      // loop through the rows
      for (int y=startY; y<startY+height; y++)
      {
        
        // get the original pixel
        originalPixel = original.getPixel(x,y);
        copyPixel = copy.getPixel(x,y);
        
        if ( originalPixel.colorDistance(oldShirtColor)<tolerance)
        {
          shirtPixel = shirt.getPixel(x-startX,y-startY);
          copyPixel.setColor(shirtPixel.getColor());
          
          
        }
      }
    }
    return copy;
    
  }
  
} // this } is the end of class Picture, put all new methods before this
