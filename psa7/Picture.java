/* Filename: Picture.java
 * Created by: Yijian Zong, cs8afhp, Jiayan Dong, cs8afqa
 * Date: November 26 2017
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
  
  /**
   * Horizontal flip method
   * In this method, I've given you the basic structure of how things will look.  There are a few incomplete assignments inside of the method.  It is your job to figure out how to complete those assignments!
   * This method works almost like the horizontal mirroring method you saw in your text book (pg 135), but instead of mirroring on the middle of the image, we want to flip it all the way.
   * Hint: Why do we need tempColor in this case?  I encourage you to reflect on this question if you find your method not working the way it should!**/
  public void flipHorizontal()
  {
    Color tempColor;
    int limit1 = this.getWidth();
    int limit2 = this.getHeight();
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
  
  /**
   * Vertical flip method
   * No hints for this one! **/
  public void flipVertical()
  {
    Color tempColor;
    int limit1 = this.getWidth();
    int limit2 = this.getHeight();
    Pixel upperPixel, lowerPixel;
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
   * Create the gray equivalent of each pixel between the provided indices
   * Input: start - the index of the first pixel to be modified (inclusive)
   *        end - the index of the last pixel to be modified (inclusive)
   * Returns: nothing
   */
  
  public void grayscale()
  {
    Pixel[] originPixel = this.getPixels();
    int colorIntensity =0;
    //loop through all pixels in the calling object and parameter 
    for(int index=0;index<originPixel.length;index++){
      colorIntensity = (int) ((originPixel[index].getRed() + originPixel[index].getGreen() 
                                 +originPixel[index].getBlue()) / 3);
      originPixel[index].setGreen(colorIntensity);
      originPixel[index].setBlue(colorIntensity);
      originPixel[index].setRed(colorIntensity);   
    }
    
  }
  
  /**The collage method
    * This method will create the collage of your modified pictures.  
    * Hint 1: Inside of the for loop here will be another 2 nested for loops, giving you a grand total of 3 nested for loops in this method
    * Hint 2: If you're clever about the way you decide to draw your pixels onto the canvas, you may only have to write ONE LINE OF CODE inside the inner-most for loop to achieve the desired collage effect!
    * However, more than one line inside of the nested for loops is perfectly fine, of course! **/
  public void collage(Picture [] pictures)
  { 
    for(int i = 0; i < pictures.length; i++)
    {
      for(int x = 0; x< pictures[i].getWidth();x++)
      {
        for(int y = 0; y< pictures[i].getHeight();y++)
        {
          this.getPixel(x+i*pictures[i].getWidth(),y).setColor(pictures[i].getPixel(x,
                                                                                    y).getColor());
        }
        
      }
    }
  }
  /*
   * TODO:
   * Write flipVerticalRectangle, flipHorizontalRectangle, and testFlipRectangle methods here **/
  public void flipVerticalRectangle(int x, int y, int width, int height){
    Color tempColor;
    Pixel upperPixel,lowerPixel;
    if (x-width/2+width-1>=this.getWidth()||y-height/2+height-1>=this.getHeight()){
      System.out.println("Sorry you the rectangle flipped is not valid");
    }
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
  
  
  public void flipHorizontalRectangle(int x, int y, int width, int height){
    Color tempColor;
    Pixel leftPixel,rightPixel;
    if (x-width/2+width-1>=this.getWidth()||y-height/2+height-1>=this.getHeight()){
      System.out.println("Sorry you the rectangle flipped is not valid");
      return;
    }
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
  
  public Picture flipHorizontalSquare(int x, int y, int size){
    Picture result = new Picture(this);
    Color tempColor;
    Pixel leftPixel,rightPixel;
    int startPointX=x;
    int startPointY=y-size+1;
    int endPointX=x+size/2;
    int endPointY=y;
    int startX = x;
    int startY = y-size+1;
    int endX = x+size-1;
    int endY =y;
    
    if (x>=0&&y-size+1>=0&&y<this.getHeight()&&x+size-1<this.getWidth()){
      for (int startx= startPointX;startx < endPointX;startx++){
        for (int starty = startPointY;starty<=endPointY;starty++){
          
          leftPixel = result.getPixel(startx,starty);
          rightPixel = result.getPixel(x+size-1-(startx-x),starty);
          tempColor = rightPixel.getColor();
          rightPixel.setColor(leftPixel.getColor());
          leftPixel.setColor(tempColor);
        }
      }
      return result;
    
  }
  
    else{
    
      if (x+size-1<0||y<0||x>=this.getWidth()||y-size+1>=this.getHeight()){
        System.out.println("Sorry you the square flipped is not valid");
        return result;
      }
      
      else{
        if(x+size-1>=this.getWidth()){
          endX = this.getWidth()-1;
        }
        if (x<0){
          startX= 0;
        }
        if(y-size<=0){
          startY = 0;
        }
        if(y>=this.getHeight()){
          endY = this.getHeight()-1;
        }
      }
      int width = endX - startX +1;
      int height = endY - startY +1;
      x = startX + width/2;
      y = startY + height/2;
      result.flipHorizontalRectangle(x, y, width, height);
      System.out.println("the x is "+ x + " the y is " + y + " height is " + height + " width is " + width + 
                         " endY is " + endY + " startY is " + startY);
      return result;
    }
  }
    
    
    



  
  
} // this } is the end of class Picture, put all new methods before this

