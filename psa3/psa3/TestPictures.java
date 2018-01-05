/* Filename: TestPictures.java
 * Created by: Yijian Zong, cs8afhp
 * Date: 10/28/2017
 */
public class TestPictures
{
    //The line below is magic, you don't have to understand it (yet)
    public static void main (String[] args)
    { 
     //test the methods scaleColor, alphaBlending.
//     Picture sthDope = new Picture(FileChooser.pickAFile());
//     Picture copy = new Picture(sthDope);
//     sthDope.explore();
//     copy.scaleColor(1.12, 1.2, 0.5);
//     copy.explore();
     Picture source = new Picture("8Arocks.png");
     Picture target = new Picture("bear.jpg");
     Picture correct = new Picture("blending.png");
     source.alphaBlending(250,350,target);
     System.out.println("Compare with correct answer. Result = " + target.testAlphaBlending(correct));
     target.explore();
     for (int x = 0; x < sthDope.getWidth(); x++){
       for (int y = 0; y < sthDope.getHeight(); y++){
         Pixel pix = sthDope.getPixel(x, y);
         
         if ( x < 100 && x >= sthDope.getWidth() - 100) {
           
         }  
       }
     }
    }
}
