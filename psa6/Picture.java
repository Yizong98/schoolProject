/* Filename: Picture.java 
 * Created by: YijianZong cs8afhp
 * Date: 11/17/2017
 */ 

/*----------- Program Description: ------------
 [Part A: Picture]
 * This programs is designed to create state-of-the-art striking methods that help us create our Steganography methods,
 * which are used to hide important messages.
 * The Main Methods are hideSecretMessage2Bits and recoverSecretMessage2Bits, and the other are either derived from 
 * these methods or helping to achieve these methods. 
 [Part B: PSA6]
 * This program is designed to put the pieces together and finally demonstrate the effect the methods make in 
 * Picture.java, which is to hide important messages inside a picture, recover it, and save it. 
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

  public Picture (String tn)
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    tn = tn;
  }
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
//  public Picture(String fileName)
//  {
//    // let the parent class handle this fileName
//    super(fileName);
//  }
  
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
   * This method  preserve the 2 most significant (i.e. leftmost) bits in each 8-bit number
   * Example: The most significant 2 bits of 250 (11111010) are 11 in binary which is equal to 3 in decimal. 
   */
  public static int mostSignificant2( int num )
  {
    return num >> 6;
  }  
  
  
  /* 
   * This method  preserve the N most significant (i.e. leftmost) bits in each 8-bit number
   * Example: If N=3 ,the most significant 3 bits of 250 (11111010) are 111 in binary which is equal to 7 in decimal. 
   */
  public static int mostSignificantN( int num, int N )
  {
    return num >> (8-N);  
  }
  
  public static int shift2BitsTo8( int num )
  {
    return num << 6;
  }
  
  /* 
   * This method  converts num with N bits into 8 bits
   * Example: If if num is 2 and N is 3, it means num is 010 in binary. This method will convert it into 01000000,
   * which is eual to 64 in decimal.
   */
  public static int shiftNBitsTo8( int num, int N )
  {
    return num << (8-N);  // You must implement this
  }
  
  
  /*
   * This method creates a copy of picture passed in the parameter. It changes the red, blue and green components 
   * of each pixel in copied picture. It uses the method mostSignificant2 to convert the 8-bit values into 2 bit values
   *
   *  Hence if you call this method, it will give you a copy of the picture passed in the parameter. But the copied picture has only 2 bits of 
   * information per color. For example, if blue color value for  any pixel in the picture is 01000000 (64 in decimal), the correspoding value 
   * of red color in copied picuture would be just 00000001 (1 in decinal)
   *
   */
  public static Picture degradeColors2Bits(Picture sourcePicture) {
    Picture picCopy = new Picture( sourcePicture.getWidth(), sourcePicture.getHeight() );
    for ( int x = 0; x < sourcePicture.getWidth(); x++ ) {
      for ( int y = 0; y < sourcePicture.getHeight(); y++ ) {
        Pixel source = sourcePicture.getPixel( x, y );
        Pixel target = picCopy.getPixel( x, y );
        
        int red = source.getRed();
        int green = source.getGreen();
        int blue = source.getBlue();
        
        red = mostSignificant2( red );
        green = mostSignificant2( green );
        blue = mostSignificant2( blue );
        
        red = shift2BitsTo8( red);
        green = shift2BitsTo8( green);
        blue = shift2BitsTo8( blue);
        
        target.setRed( red );
        target.setGreen( green );
        target.setBlue( blue );
      }
    }
    return picCopy;
    
  }  
  
  /*
   This method creates a copy of the picture passed in the parameter. It changes the red, blue and green components 
   of each pixel in copied picture. It uses the method mostSignificantN to convert the 8-bit values into N bit values
   and then shift it back to 8 bit values.
   Hence if you call this method with the Picture as one of the parameters, it will give you a copy of the picture. 
   But the copied picture has only N bits of information per color.
   */
  public static Picture degradeColorsNBits(Picture sourcePicture,int N) {
    Picture picCopy = new Picture( sourcePicture.getWidth(), sourcePicture.getHeight() );
    for ( int x = 0; x < sourcePicture.getWidth(); x++ ) {
      for ( int y = 0; y < sourcePicture.getHeight(); y++ ) {
        Pixel source = sourcePicture.getPixel( x, y );
        Pixel target = picCopy.getPixel( x, y );
        int red = source.getRed();
        int green = source.getGreen();
        int blue = source.getBlue();
        
        red = mostSignificantN( red, N );
        green = mostSignificantN( green, N);
        blue = mostSignificantN( blue, N);
        
        red = shiftNBitsTo8( red, N );
        green = shiftNBitsTo8( green, N);
        blue = shiftNBitsTo8( blue, N);
        
        target.setRed( red );
        target.setGreen( green );
        target.setBlue( blue );
      }
    }
    return picCopy;
  } 
  
  /**
   * Method to return a picture with a hidden message
   * Input: the context picture, message picture, number of most significant bits to save in message, 
   * starting position x and y. 
   * Returns: A copy of the context with its message hidden. 
   */
  public static Picture hideSecretMessageNBits(Picture context, Picture message, int N, int x, int y){
    Picture canvas = new Picture(context);
    Pixel messagePixel = null;
    Pixel contextPixel = null;
    Pixel canvasPixel = null;
    int widthEndpoint= 0;
    int heightEndpoint=0;
    //if statement
    if(message.getWidth()>context.getWidth()&&message.getHeight()>context.getHeight()){
      widthEndpoint=context.getWidth();
      heightEndpoint = context.getHeight();
    }
    else if(message.getWidth()>context.getWidth()){
      widthEndpoint=context.getWidth();
      heightEndpoint = message.getHeight();
    }
    else if(message.getHeight()>context.getHeight()){
      widthEndpoint=message.getWidth();
      heightEndpoint = context.getHeight();
    }
    else{
      widthEndpoint=message.getWidth()+x;
      heightEndpoint = message.getHeight()+y;
    }
    for (int x2=x; x2<widthEndpoint;x2++){
      for(int y2=y;y2<heightEndpoint;y2++){
        messagePixel=message.getPixel(x2-x,y2-y);
        contextPixel = context.getPixel(x2,y2);
        canvasPixel = canvas.getPixel(x2,y2);
        int mRed = messagePixel.getRed();
        int mGreen = messagePixel.getGreen();
        int mBlue = messagePixel.getBlue();
        
        mRed = mostSignificantN( mRed,N);
        mBlue= mostSignificantN( mBlue,N);
        mGreen= mostSignificantN( mGreen,N);
        
        int cRed = contextPixel.getRed();
        int cGreen= contextPixel.getGreen();
        int cBlue = contextPixel.getBlue();
        
        int finalRed = embedDigitsN(cRed,mRed,N);
        int finalGreen =embedDigitsN(cGreen,mGreen,N);
        int finalBlue =embedDigitsN(cBlue,mBlue,N);
        
        canvasPixel.setRed(finalRed);
        canvasPixel.setGreen(finalGreen);
        canvasPixel.setBlue(finalBlue);
        
        
        
        
      }
    }
    return canvas;
  }
  
  /**
   * Method to return a picture with a hidden message by saving the two most significant bits in message. 
   * Input: the context picture, message picture, starting position x and y. 
   * Returns: A copy of the context with its message hidden. 
   */
  public static Picture hideSecretMessage2Bits(Picture context, Picture message, int x, int y){
    Picture canvas = new Picture(context);
    Pixel messagePixel = null;
    Pixel canvasPixel = null;
    int widthEndpoint= 0;
    int heightEndpoint=0;
    //if statement
    if(message.getWidth()>context.getWidth()&&message.getHeight()>context.getHeight()){
      widthEndpoint=context.getWidth();
      heightEndpoint = context.getHeight();
    }
    else if(message.getWidth()>context.getWidth()){
      widthEndpoint=context.getWidth();
      heightEndpoint = message.getHeight();
    }
    else if(message.getHeight()>context.getHeight()){
      widthEndpoint=message.getWidth();
      heightEndpoint = context.getHeight();
    }
    else{
      widthEndpoint=message.getWidth()+x;
      heightEndpoint = message.getHeight()+y;
    }
    for (int x2=x; x2<widthEndpoint;x2++){
      for(int y2=y;y2<heightEndpoint;y2++){
        messagePixel=message.getPixel(x2-x,y2-y);
        canvasPixel = canvas.getPixel(x2,y2);
        int mRed = messagePixel.getRed();
        int mGreen = messagePixel.getGreen();
        int mBlue = messagePixel.getBlue();
        
        mRed = mostSignificant2( mRed);
        mBlue= mostSignificant2( mBlue);
        mGreen= mostSignificant2( mGreen);
        
        int cRed = canvasPixel.getRed();
        int cGreen= canvasPixel.getGreen();
        int cBlue = canvasPixel.getBlue();
        
        int finalRed = embedDigits2(cRed,mRed);
        int finalGreen =embedDigits2(cGreen,mGreen);
        int finalBlue =embedDigits2(cBlue,mBlue);
        
        canvasPixel.setRed(finalRed);
        canvasPixel.setGreen(finalGreen);
        canvasPixel.setBlue(finalBlue);
        
        
        
        
      }
    }
    return canvas;
  }
  /**
   * Method to recover a picture to show a hidden message
   * Input: the context picture with hidden message, number of most significant bits saved from message, 
   * starting position x and y. 
   * Returns: A copy of the context with its hidden message shown.
   */
  public static Picture recoverSecretMessageNBits(Picture context, int N, int x, int y){
    Picture result = new Picture(context);
    Pixel resultPixel= null;
    Pixel messagePixel = null;
    for ( int x2 = x; x2 < context.getWidth(); x2++ ) {
      for ( int y2 = y; y2 < context.getHeight(); y2++ ) {
        resultPixel = result.getPixel(x2,y2);
        messagePixel = context.getPixel(x2,y2);
        int rRed = getLeastSignificantN(messagePixel.getRed(),N);
        int rGreen = getLeastSignificantN(messagePixel.getGreen(),N);
        int rBlue = getLeastSignificantN(messagePixel.getBlue(),N);
        
        int r2Red = shiftNBitsTo8(rRed,N);
        int r2Green = shiftNBitsTo8(rGreen,N);
        int r2Blue = shiftNBitsTo8(rBlue,N);
        
        resultPixel.setRed(r2Red);
        resultPixel.setGreen(r2Green);
        resultPixel.setBlue(r2Blue);
        
      }
    }
    return result;
  }
  
  /**
   * Method to recover a picture to show a hidden message with 2 most significant bits saved from message.
   * Input: the picture with message, starting position x and y. 
   * Returns: A copy of the picture with its hidden message shown.
   */
  public static Picture recoverSecretMessage2Bits(Picture picWithMessage, int x, int y){
    Picture result = new Picture(picWithMessage);
    Pixel resultPixel= null;
    Pixel messagePixel = null;
    for ( int x2 = x; x2 < picWithMessage.getWidth(); x2++ ) {
      for ( int y2 = y; y2 < picWithMessage.getHeight(); y2++ ) {
        resultPixel = result.getPixel(x2,y2);
        messagePixel = picWithMessage.getPixel(x2,y2);
        int rRed = getLeastSignificant2(messagePixel.getRed());
        int rGreen = getLeastSignificant2(messagePixel.getGreen());
        int rBlue = getLeastSignificant2(messagePixel.getBlue());
        
        int r2Red = shiftNBitsTo8(rRed,2);
        int r2Green = shiftNBitsTo8(rGreen,2);
        int r2Blue = shiftNBitsTo8(rBlue,2);
        
        resultPixel.setRed(r2Red);
        resultPixel.setGreen(r2Green);
        resultPixel.setBlue(r2Blue);
        
      }
    }
    return result;
  }
  /**
   * Method to embed 2 digits in the context value from message value.
   * Input: context value, message value
   * Returns: An output value with the least significant two bits of context value replaced by message value.
   */
  public static int embedDigits2( int contextVal, int messageVal ){
    int outputVal;
    outputVal = ((contextVal>>2)<<2)+messageVal;
    return outputVal;
  }
  
  /**
   * Method to embed N digits in the context value from message value.
   * Input: context value, message value, number of digits to embed
   * Returns: An output value with the least significant N bits of context value replaced by message value.
   */
  public static int embedDigitsN( int contextVal, int messageVal, int N ){
    int outputVal;
    outputVal = ((contextVal>>N)<<N)+messageVal;
    return outputVal;
  }
  
  /**
   * Method to get the least significant two bits of a value. 
   * Input: a value
   * Returns: the least significant two bits of that value.
   */
  public static int getLeastSignificant2( int num ){
    int LSB=0;
    LSB = num%4;
    return LSB;
  }
  
  /**
   * Method to get the least significant N bits of a value. 
   * Input: a value, least significant N bits
   * Returns: the least significant N bits of that value.
   */
  public static int getLeastSignificantN( int num, int N ){
    int LSB=0;
    LSB = num%((int)Math.pow(2,N));
    return LSB;
  }
  
  
    private int tn=7;
    public void shit (int tn)
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */ 
    tn = tn;
  }
  
}// this } is the end of class Picture, put all new methods before this

