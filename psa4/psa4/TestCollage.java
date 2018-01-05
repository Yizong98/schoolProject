/* Created by: Jiayan Dong, cs8afqa
 * Partner: Yijian Zong, cs8afhp
 * Date: 11/05/2017
 */

/**
 * A class used to test the validity of the TestCollage method in Picture.java.
 */
public class TestCollage {
 public static void main(String [] args)
 {
   //Choose a picture file
   String sourceFile = FileChooser.pickAFile();
   Picture sourcePicture = new Picture(sourceFile);
   Picture sourcePicture2 = new Picture(sourceFile);
   Picture sourcePicture3 = new Picture(sourceFile);
   //Copy my Picture objects
   Picture copy1 = new Picture(sourcePicture);
 
   //set up pictures ransformtions here;
   sourcePicture.flipHorizontal(); 
   sourcePicture.show();
   copy1.show();
   sourcePicture2.flipVertical();
   sourcePicture2.show();
   sourcePicture3.grayscale();
   sourcePicture3.show();
   
   
   //Create array of Pictures
   Picture[] picArray = new Picture[3];
   picArray[0] = sourcePicture;
   picArray[1] = sourcePicture2;
   picArray[2] = sourcePicture3;
   //Create a picture object using the canvas which we will be drawing our collage on
   Picture canvas = new Picture(sourcePicture.getWidth()*3,sourcePicture.getHeight());
   
   //Call collage() with my canvas and show it!
   canvas.collage(picArray);
   canvas.show();
   
 }
 
}