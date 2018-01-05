/* Filename: Chromakey.java
* Created by: CSE 8A Staff
* Date: Fall 2017
* 
* Questsion Answering:
* 1. What would happen if the tolerance for distinguishing the background and you is set too high? Too low? 
* 
* Answer: If it is too high, not only the background but also the figure would be changed. If too low, parts of the 
* background would not be covered. 
* 
* 2. Why should the program make comparisons to the original picture with the solid green background when replacing
* the shirt's color with the shirt's new background instead of the resulting picture from part A 
* (with the green background replaced)?
* 
* Answer: Because when the background is changed, it is not a mono-tone green color anymore, makeing the method hard to 
* modify the picture in a desirable way.
*/

/**
 * A class used to show off Chromakey special effect on a picture of me originally taken with a purely green
 * background.
 */

import java.awt.*;
public class Chromakey
{
    //The line below is magic, you don't have to understand it (yet)
    public static void main (String[] args)
    {
      String myPicture = "pic0.jpg";
      String background = "alien.jpg";
      Picture myselfSourceImage = new Picture(myPicture);
      Picture backgroundImage = new Picture(background);
      Color black = new Color(0,0,0);
      Color shirtColor = new Color(170,170,170);
      //Change the shirt color to black using a helper method designed by me. 
      myselfSourceImage= myselfSourceImage.setShirtRGB(shirtColor,200, 733, 650, 515,210);
      Color green = new Color(0,255,0);
      myselfSourceImage.explore();
      String shirt = "pic2.jpg";
      Picture myShirtSourceImage  = new Picture(shirt);
      //Change the background Color
      Picture backgroundChange = myselfSourceImage.chromakeyBackgroundChange(backgroundImage,green,190.0);
      //Change the Shirt
      Picture shirtChange  = backgroundChange.chromakeyShirtChange(myShirtSourceImage, myselfSourceImage,black, 
                                                                   90, 733, 834, 515,80.0);
      shirtChange.explore();
    }
}
