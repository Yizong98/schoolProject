/* Created by: Jiayan Dong, cs8afqa
 * Partner: Yijian Zong, cs8afhp
 * Date: 11/05/2017
 */

/*--------PROGRAM DESCRIPTION----------
 [Part A: TestCollage]
 * This programs is designed to test the method collage and create a beautiful collage which contains multiple chosen 
 * pictures with the exact same width and height. This programs takes in a list of pictures and turn them into 
 * a collage.
 [Part B: TestFlip]
 * This programs is designed to test the methods of flipping a certain rectangle area vertically and horizontally. 
 * If the width and height are too big, the system would exit, interactions pane would reset, and an print statement
 * of error would be shown. If both methods are implemented correctly, the screen will print four statements all end
 * with "true".
 [Part C: Picture]
 * This is a class for holding all the functions the picture object could do. You can say it is a library to look up 
 * what Pictures can do and what can do to Pictures.
 */
import java.awt.Color;
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
  
  /*
   * Flip the picture horizontally. 
   * Input: nothing
   * Returns: nothing
   */
  public void flipHorizontal()
  {
    Color tempColor;
    int limit1 = this.getWidth();
    int limit2 = this.getHeight();
    //loop through left half pixels in the calling object.
    for(int x = 0; x < limit1/2; x++)
    {
      for(int y = 0; y < limit2; y++)
      {
        Pixel leftPixel = this.getPixel(x,y);
        Pixel rightPixel = this.getPixel(limit1-1-x,y);
        tempColor = rightPixel.getColor();
        rightPixel.setColor(leftPixel.getColor());
        leftPixel.setColor(tempColor);
      }
    }
    
  }
  
  /*
   * This method flips the picture vertically. 
   * Input: nothing
   * Returns: nothing
   */
  public void flipVertical()
  {
    Color tempColor;
    int limit1 = this.getWidth();
    int limit2 = this.getHeight();
    Pixel upperPixel, lowerPixel;
    //loop through upper half pixels in the calling object.
    for(int x = 0; x < limit1; x++)
    {
      for(int y = 0; y < limit2/2; y++)
      {
        upperPixel = this.getPixel(x,y);
        lowerPixel = this.getPixel(x,limit2-1-y);
        tempColor = upperPixel.getColor();
        upperPixel.setColor(lowerPixel.getColor());
        lowerPixel.setColor(tempColor);
      }
    }
    
  }
  
  /*
   * This method sets all pixels of the image into gray. 
   * Input: nothing
   * Returns: nothing
   */
  
  public void grayscale()
  {
    Pixel[] originPixel = this.getPixels();
    int colorIntensity =0;
    //loop through all pixels in the calling object.
    for(int index=0;index<originPixel.length;index++){
      colorIntensity = (int) ((originPixel[index].getRed() + originPixel[index].getGreen() 
                                 +originPixel[index].getBlue()) / 3);
      originPixel[index].setGreen(colorIntensity);
      originPixel[index].setBlue(colorIntensity);
      originPixel[index].setRed(colorIntensity);   
    }
    
  }
  
  /*
   * This Method creates a collage of pictures. 
   * Input: an array of pictures
   * Returns: nothing
   */
  public void collage(Picture [] pictures)
  { 
    //loop through the picture array one by one
    for(int i = 0; i < pictures.length; i++)
    { 
      //loop through the all pixels in the current picture
      for(int x = 0; x< pictures[i].getWidth();x++)
      {
        for(int y = 0; y< pictures[i].getHeight();y++)
        {
          this.getPixel(x+i*pictures[i].getWidth(),y).setColor(pictures[i].getPixel(x,y).getColor());
        }
        
      }
    }
  }
  /*
   * This Method flips vertically a given rectangle area of a picture centered at (x,y) with dimension(width,height)
   * Input: horizontal coordinate x, vertical coordinate y, the rectangle width and height.
   * Returns: nothing
   */
  public void flipVerticalRectangle(int x, int y, int width, int height){
    Color tempColor;
    Pixel upperPixel,lowerPixel;
    //check if the rectangle flipped is outside the picture.
    if (x-width/2+width-1>=this.getWidth()||y-height/2+height-1>=this.getHeight()){
      System.out.println("Sorry you the rectangle flipped is not valid");
      System.exit(0);
    }
    //loop through pixels in the upper half of the rectangle.
    for (int startx= x-width/2;startx <=x-width/2+width-1;startx++){
      for (int starty = y-height/2;starty<y;starty++){
        upperPixel = this.getPixel(startx,starty);
        lowerPixel = this.getPixel(startx,y - height/2+ height - 1-(starty-y + height/2));
        tempColor = upperPixel.getColor();
        upperPixel.setColor(lowerPixel.getColor());
        lowerPixel.setColor(tempColor);
        
      }
    }
    
    
  }
  
  /*
   * This Method flips horizontally a given rectangle area of a picture centered at (x,y) with dimension(width,height)
   * Input: horizontal coordinate x, vertical coordinate y, the rectangle width and height.
   * Returns: nothing
   */
  public void flipHorizontalRectangle(int x, int y, int width, int height){
    Color tempColor;
    Pixel leftPixel,rightPixel;
    //check if the rectangle flipped is outside the picture.
    if (x-width/2+width-1>=this.getWidth()||y-height/2+height-1>=this.getHeight()){
      System.out.println("Sorry you the rectangle flipped is not valid");
      System.exit(0);
    }
    //loop through pixels in the left half of the rectangle.
    for (int startx= x-width/2;startx < x;startx++){
      for (int starty = y-height/2;starty<=y-height/2+height-1;starty++){
        
        leftPixel = this.getPixel(startx,starty);
        rightPixel = this.getPixel(x - width/2+ width - 1-(startx-x + width/2),starty);
        tempColor = rightPixel.getColor();
        rightPixel.setColor(leftPixel.getColor());
        leftPixel.setColor(tempColor);
        
      }
    }
    
  }
  /**
   * Test the two flip rectangle methods by comparing two pictures
   * Input: result - The picture to compare to
   * Returns: true if the pictures match
   *          false if they do not
   */
  public boolean testFlipRectangle(Picture result)
  {
    //loop through all pixels in the calling object and parameter 
    //picture using nested loops on x and y
    for(int x=0; x<result.getWidth();x++){
      for(int y=0; y<result.getHeight();y++){
        Pixel sP = this.getPixel(x, y);
        Pixel tP = result.getPixel(x, y);
        if( sP.getRed() != tP.getRed())
          return false;
        if( sP.getBlue() != tP.getBlue())
          return false;
        if( sP.getGreen() != tP.getGreen())
          return false;
      }
    }
    //Similarly for blue and green. 
    //outside the nested loop, we return true as if we reach here
    //it is guaranteed that all pixels between the calling obj and
    //the parameter picture are the same.
    
    return true;
  }
  
  
  
  
  
  
  
} // this } is the end of class Picture, put all new methods before this

