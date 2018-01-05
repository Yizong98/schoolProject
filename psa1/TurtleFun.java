/* Filename: TurtleFun.java 
 * Created by: Yijian Zong, cs8afhp
 * Date: 10/11/2017
 */ 

public class TurtleFun
{
    //The line below is magic, you don't have to understand it (yet)
    public static void main (String[] args) 
    { 
      //Create world and turtles. 
      World world = new World(800,800);
      Turtle cris1 = new Turtle(400,400,world);
      Turtle cris2 = new Turtle(600,400,world);
      //Assign cris3 to cris1
      Turtle cris3 = cris1;
      //Draw the three shapes.
      cris1.drawShape(100,3);
      cris2.drawShape(100,3);
      cris3.penUp();
      cris3.turnLeft();
      cris3.forward(200);
      cris3.turnRight();
      cris3.drawShape(100,3);
    } 
}