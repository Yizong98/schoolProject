/* Filename: Turtle.java 
 * Created by: Yijian Zong, cs8afhp
 * Date: 10/11/2017
 */  
public class CreateShapes{
    //The line below is magic, you don't have to understand it (yet)
    public static void main (String[] args) 
    { 
      //Create world and four turtle objects.
      World world = new World(600,600);
      Turtle cris1 = new Turtle(200,300,world);
      Turtle cris2 = new Turtle(250,275,world);
      Turtle cris3 = new Turtle(225,290,world);
      Turtle cris4 = new Turtle(210,295,world);
      //Draw Shapes.
      cris1.drawShape(100,3);
      cris2.drawShape(40,4);
      cris3.drawShape(70,5);
      cris4.drawShape(90,6);

      
    } 
}