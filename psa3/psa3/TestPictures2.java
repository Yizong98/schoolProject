import java.awt.Color;
public class TestPictures2
{
    //The line below is magic, you don't have to understand it (yet)
    public static void main (String[] args)
    { 
     //test the methods scaleColor, alphaBlending.
     Picture sthDope = new Picture(FileChooser.pickAFile());
     Picture copy = new Picture(sthDope);
     //sthDope.explore();
     //copy.edgeDetection(10);
     //copy.explore();
     
//loop through the picture
//loop through the picture
     for (int x = 0; x < copy.getWidth(); x++){
       for (int y = 0; y < copy.getHeight(); y++){
         Pixel pix = copy.getPixel(x, y);
         if (x % 4 == 0) {
           pix.setColor( new Color(0, 0, 0));
         }
       }
     }
     copy.explore();
     sthDope.explore();
     
     
    }
}

