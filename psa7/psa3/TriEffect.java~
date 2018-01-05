/* Filename: TriEffect.java
* Created by: CSE 8A Staff
* Date: Fall 2017
*/
public class TriEffect
{
    //The line below is magic, you don't have to understand it (yet)
    public static void main (String[] args)
    {
      //Put all your code to test the picture subtraction here
      //Then REMOVE THIS COMMENT.
      Picture sthDope = new Picture(FileChooser.pickAFile());
      Picture copy = new Picture(sthDope);
      sthDope.explore();
      Pixel[] copypix = copy.getPixels();
      int copy_length = copypix.length;
      copy.complement(0,copy_length/3);
      copy.grayscale(copy_length/3,2*copy_length/3);
      copy.myFilter(2*copy_length/3,copy_length-1);
      copy.explore();
    }
}
