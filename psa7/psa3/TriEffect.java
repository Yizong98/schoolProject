/* Filename: TriEffect.java
 * Created by: Yijian Zong, cs8afhp
 * Date: 10/28/2017
 */
public class TriEffect
{
  //The line below is magic, you don't have to understand it (yet)
  public static void main (String[] args)
  {
    //exert methods complement, grayscale,myFilter to the target picture. 
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
