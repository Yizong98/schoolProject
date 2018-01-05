/* Filename: TestFlip.java 
* Created by: CSE 8A Staff
* Date: Fall 2017
*/ 

public class TestFlip {
 public static void main(String [] args)
 {
   String sourceFile = FileChooser.pickAFile();
   Picture sourcePicture = new Picture(sourceFile);
   
   //Copy your Picture objects
   Picture copy1 = new Picture("coding.jpg");
   Picture copy2 = new Picture("coding.jpg");
   Picture copy3 = new Picture("coding.jpg");
   Picture copy4 = new Picture("coding.jpg");
   copy1.flipVerticalRectangle(200,200,100,300);
   copy2.flipVerticalRectangle(100,100,200,200);
   copy3.flipHorizontalRectangle(200,200,100,300);
   copy4.flipHorizontalRectangle(100,100,200,200);
   Picture verticalResult1= new Picture("vertical-rectangle-200-200-100-300.bmp");
   Picture verticalResult2= new Picture("vertical-rectangle-100-100-200-200.bmp");
   Picture horizontalResult1= new Picture("horizontal-rectangle-200-200-100-300.bmp");
   Picture horizontalResult2= new Picture("horizontal-rectangle-100-100-200-200.bmp");
   //Call flipVerticalRectangle and flipHorizontalRectangle on your copies
   //copy1.show();
   //verticalResult1.show();
   //copy2.show();
   //verticalResult2.show();
   //copy3.show();
   //horizontalResult1.show();
   //copy4.show();
   //horizontalResult2.show();
  
   //sourcePicture.explore();
   //Show both copies!
   System.out.println("Compare flipVerticalRectangle(100, 100, 200, 200) with correct answer = "+ 
                      copy2.testFlipRectangle(verticalResult2));
   System.out.println("Compare flipVerticalRectangle(200, 200, 100, 300) with correct answer = "+ 
                      copy1.testFlipRectangle(verticalResult1));
   System.out.println("Compare flipHorizontalRectangle(200, 200, 100, 300) with correct answer = "+ 
                      copy3.testFlipRectangle(horizontalResult1));
   System.out.println("Compare flipHorizontalRectangle(100,100,200,200) with correct answer = "+ 
                            copy4.testFlipRectangle(horizontalResult2));

   
 }  
  
}