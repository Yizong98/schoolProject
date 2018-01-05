/* Filename: Picture.java 
 * Created by: Yijian Zong, cs8afhp
 * Date: 10/28/2017
 * Easy introduction of three programs:
 *  A) TestPictures.java:
 * This is tool to test whether our methods written in the program Picture.java would work, you can imagine this as a 
 * a keyboard with a headset which allows only you to listen to you playing the digital piano in order to see whether 
 * you are prepared to perform in front of the public. In this proram, we test whether the method scaleColor by 
 * comparing the original photo and photo after the method, and use testAlphaBlending to check whether the method 
 * alphaBlending would work by printing out the result on the screen.
 *  B) TriEffect.java:
 * This is a program to have a special effect onto the user's selected photo by dividing the photo into three parts and
 * each third of the photo is changed to a different style of color, done by the magic methods myFilter, grayscale,
 * complement.
 *  C) Picture.java:
 * This is a class for holding all the functions the picture object could do. You can say it is a library to look up 
 * what Pictures can do and what can do to Pictures.
 */ 

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
   * Scale the given values from the appropriate colors.
   * Input: rScale - the amount of red value that should be scaled 
   *    gScale - the amount of blue value that should be scaled 
   *    bScale - the amount of green value that should be scaled 
   * Returns: nothing
   */
  
  public void scaleColor(double rScale, double gScale, double bScale)
  {
    
    Pixel[] originPixel = this.getPixels();
    //loop through all pixels in the calling object and parameter 
    for(Pixel pixelobj:originPixel){
      pixelobj.setGreen((int)((double)pixelobj.getGreen()*gScale));
      pixelobj.setBlue((int)((double)pixelobj.getBlue()*bScale));
      pixelobj.setRed((int)((double)pixelobj.getRed()*rScale));
      //check conditions and set ceiling and floor for values above and below them.
      if(pixelobj.getGreen()>255){
        pixelobj.setGreen(255);
      }
      if(pixelobj.getRed()>255){
        pixelobj.setRed(255);
      }
      if(pixelobj.getBlue()>255){
        pixelobj.setBlue(255);
      }
      if(pixelobj.getBlue()<0){
        pixelobj.setBlue(0);
      }
      if(pixelobj.getRed()<0){
        pixelobj.setRed(0);
      }
      if(pixelobj.getGreen()<0){
        pixelobj.setGreen(0);
      }
    }
    
    
  }
  
  
  /**
   * Create the complement of each pixel between the provided indices
   * Input: start - the index of the first pixel to be modified 
   * (inclusive)
   * end - the index of the last pixel to be modified (inclusive)
   * Returns: nothing
   */
  public void complement(int start, int end)
  {
    Pixel[] originPixel = this.getPixels();
    //loop through all pixels in the calling object and parameter 
    for(int index=start;index<=end;index++){
      originPixel[index].setGreen(255-originPixel[index].getGreen());
      originPixel[index].setBlue(255-originPixel[index].getBlue());
      originPixel[index].setRed(255-originPixel[index].getRed());   
    }
    
    
    
  }
  
  /*
   * Create the gray equivalent of each pixel between the provided indices
   * Input: start - the index of the first pixel to be modified (inclusive)
   *        end - the index of the last pixel to be modified (inclusive)
   * Returns: nothing
   */
  
  public void grayscale(int start, int end)
  {
    Pixel[] originPixel = this.getPixels();
    int colorIntensity =0;
    //loop through all pixels in the calling object and parameter 
    for(int index=start;index<=end;index++){
      colorIntensity = (int) ((originPixel[index].getRed() + originPixel[index].getGreen() 
                                 +originPixel[index].getBlue()) / 3);
      originPixel[index].setGreen(colorIntensity);
      originPixel[index].setBlue(colorIntensity);
      originPixel[index].setRed(colorIntensity);   
    }
    
  }
  
  
  /*
   * set blue component to the value of red component, green component to the value of blue component,
   * and red component to the value of green component,of each pixel between the provided indices
   * Input: start - the index of the first pixel to be modified (inclusive)
   *        end - the index of the last pixel to be modified (inclusive)
   * Returns: nothing
   */
  public void myFilter(int start, int end)
  {
    Pixel[] originPixel = this.getPixels();
    //loop through all pixels in the calling object and parameter 
    for(int index=start;index<=end;index++){
      originPixel[index].setGreen(originPixel[index].getBlue());
      originPixel[index].setBlue(originPixel[index].getRed());
      originPixel[index].setRed(originPixel[index].getGreen());   
    }
  }
  
  /**
   * Blend the calling object pic into the parameter Picture
   * object with the upper left corner at (x, y)
   * Input: x - The background's top left corner x coordinate
   *        y - The background's top left corner y coordinate
   *        background - The background picture to blend into
   * Returns: nothing
   */
  public void alphaBlending(int x, int y, Picture background)
  {
    double alpha =0;
    int targetX =0;
    int targetY=0;
    int SourceX;
    int SourceY;
    Pixel originPixel=null;
    Pixel blendPixel=null;
    if (x<0){
      x=0;
    }
    if (x>=this.getWidth()){
      x = this.getWidth()-1;
    }
    if(y<0){
      y=0;
    }
    if (y>=this.getHeight()){
      y = this.getHeight()-1;
    }
    
    //loop through the the specifed region of background and the source photo to blend two together. 
    for(SourceX =0,targetX=x;SourceX<this.getWidth();targetX++,SourceX++){
      for(SourceY=0,targetY=y;SourceY<this.getHeight();targetY++,SourceY++){
        originPixel = background.getPixel(SourceX,SourceY);
        blendPixel = this.getPixel(targetX,targetY);
        alpha = blendPixel.getAlpha();
        originPixel.setRed((int)(alpha / 255 * blendPixel.getRed() + (1 - alpha / 255) * originPixel.getRed()));
        originPixel.setBlue((int)(alpha / 255 * blendPixel.getBlue() + (1 - alpha / 255) * originPixel.getBlue()));
        originPixel.setGreen((int)(alpha / 255 * blendPixel.getGreen() + (1 - alpha / 255) * originPixel.getGreen()));
      }
    }
  }
    
    /**
     * Test the alphaBlending method by comparing two pictures
     * Input: result - The picture to compare to
     * Returns: true if the pictures match
     *          false if they do not
     */
    public boolean testAlphaBlending(Picture result)
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
    /**
     * Method to do a simple edge detection by comparing the
     * absolute value of the difference between the color
     * intensities (average of the color values) between a
     * pixel and the pixel below it. If the absolute value
     * of the difference between the color intensities is
     * less than a passed amount the top pixel color
     * will be set to white.  Otherwise it is set to black.
     * @param amount if the absolute value of the differences
     * in the color average is less than this
     * set the color to white, else black
     */
    public void edgeDetection(double amount) {
      Pixel topPixel = null;
      Pixel bottomPixel = null;
      double topAverage = 0.0;
      double bottomAverage = 0.0;
      int endY = this.getHeight() - 1;
      
      /* loop through y values from 0 to height - 1
       * (since compare to below pixel) */
      for (int y = 0; y < endY; y++) {
        
        // loop through the x values from 0 to width
        for (int x = 0; x < this.getWidth(); x++) {
          
          // get the top and bottom pixels
          topPixel = this.getPixel(x,y);
          bottomPixel = this.getPixel(x,y+1);
          
          // get the color averages for the two pixels
          topAverage = topPixel.getAverage();
          bottomAverage = bottomPixel.getAverage();
          
          /* check if the absolute value of the difference
           * is less than the amount */
          if (Math.abs(topAverage - bottomAverage) < amount) {
            topPixel.setColor(Color.WHITE);
            // else set the color to black
          } else {
            topPixel.setColor(Color.BLACK);
          }
        }
      }
    }
    
  
    
    
  } // this } is the end of class Picture, put all new methods before this
  
