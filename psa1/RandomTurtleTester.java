/* Filename: RandomTurtleTester.java 
 * Created by: Yijian Zong, cs8afhp
 * Date: 10/21/2017
 */ 

import java.util.Random;
/**
 * This is a tester class to test the validity of methods written in Turtle class.
 */
public class RandomTurtleTester
{
    //The line below is magic, you don't have to understand it (yet)
    public static void main (String[] args) 
    { 
      //Create world and turtles. 
      World world = new World(800,800);
      //Create a new Turtle at the bottom center of this world.
      Turtle cris1 = new Turtle(400,800,world);
      //Call takeStep with argument 1.
      cris1.takeStep(1,new Random());
      //Call takeStep with argument 1.
      cris1.takeStep(-1,new Random());
      World world2 = new World(800,800);
      Turtle cris2 = new Turtle(400,400,world2);
      //Call rwPosition and print the final signed displacement of Turtle
      System.out.println( cris2.rwPosition(10, new Random()));
      World world3 = new World(800,800);
      Turtle cris3 = new Turtle(400,800,world3);
      //Call countSteps and print number of steps the Turtle took.
      System.out.println(cris3.countSteps(6, new Random()));
      
    } 
}