/* Filename: Turtle.java 
 * Created by: Yijian Zong, cs8afhp
 * Date: 10/11/2017
 */ 
import java.util.Random;

public class CreateTriangles
{
    //The line below is magic, you don't have to understand it (yet)
    public static void main (String[] args) 
    { //Create world and three turtle objects.
      World world = new World(800,800);
      Turtle cris1 = new Turtle(400,400,world);
      Turtle cris2 = new Turtle(600,400,world);
      Turtle cris3 = new Turtle(600,400,world);
      //Draw random turtles.
      cris1.drawTriangle(new Random(),200,200,80);
      cris2.drawTriangle(new Random(),400,200,80);     
      cris3.drawTriangle(new Random(),600,200,80);    
    } 
}
//Scenario1: two turtles which draw triangles from the same X location.
//Detailed Description: I set the x starting position of two turtles to 200, then the outcome 
//shows that two triangles are overlapped, which is not the expected output. 

//Scenario2: when the limit parameter is way too big, the World won't be capable of containing the triangle. 
//Detailed Description: I set the limit parameter to 100000, and the resulting triangle is too big to show 
//on the screen. 