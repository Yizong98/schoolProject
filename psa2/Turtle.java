/* Filename: Turtle.java 
 * Created by: Yijian Zong, cs8afhp
 * Date: 10/11/2017
 * Easy introduction of three programs:
 *  A) RandomTurtleTester:
 * This is tool to test whether our methods written in the program Turtle.java would work, you can imagine this as a 
 * soccer training ground for you to see whether the skills learned would work. In this proram, we test whether the 
 * turtle can move to the right and left, and whether the computer can display the random movement of turtle and
 * calculate the correct displacement, which is the horizontal distance from the end point to the origin, by printing 
 * the displacement on the screen. Finally, we check whether the computer could display the movements of turtle and 
 * count the steps the turtle takes before it hits the boundary we set for it.
 *  B) RandomTurtleTester2:
 * This is a tool to explore the average displacement of turtle by changing the chance of direction it moves and the 
 * number of steps it takes.
 */ 

import java.util.*;
import java.awt.*;

/**
 * Class that represents a turtle which is similar to a Logo turtle.
 * This class inherts from SimpleTurtle and is for students
 * to add methods to.
 *
 * Copyright Georgia Institute of Technology 2004
 * @author Barb Ericson ericson@cc.gatech.edu
 */
public class Turtle extends SimpleTurtle {
  ////////////////// constructors ///////////////////////
  
  /** Constructor that takes the x and y and a picture to
    * draw on
    * @param x the starting x position
    * @param y the starting y position
    * @param picture the picture to draw on
    */
  public Turtle (int x, int y, Picture picture) {
    // let the parent constructor handle it
    super(x,y,picture);
  }
  
  /** Constructor that takes the x and y and a model
    * display to draw it on
    * @param x the starting x position
    * @param y the starting y position
    * @param modelDisplayer the thing that displays the model
    */
  public Turtle (int x, int y, ModelDisplay modelDisplayer) {
    // let the parent constructor handle it
    super(x,y,modelDisplayer);
  }
  
  /** Constructor that takes the model display
    * @param modelDisplay the thing that displays the model
    */
  public Turtle (ModelDisplay modelDisplay) {
    // let the parent constructor handle it
    super(modelDisplay);
  }
  
  /**
   * Constructor that takes a picture to draw on
   * @param p the picture to draw on
   */
  public Turtle (Picture p) {
    // let the parent constructor handle it
    super(p);
  }  
  
  /////////////////// methods ///////////////////////
  public void drawShape(int size, int thickness) { 
    //Put all your commands to use the size and thickness parameters 
    //to make one copy of your shape. BE CREATIVE! 
    //Then REMOVE THESE COMMENTS. \
    int number_of_side = 6;
    double angle = 360.0 / number_of_side;
    for(int i= number_of_side;i>0;i--){
      this.penDown();
      this.setPenWidth(thickness);
      this.forward(size);
      this.turn(angle);}
    
    
  }
  public void drawTriangle(Random random, int x, int y, int limit) 
  { 
    //Create an int variable with keyword final
    final int angle = 60;
    this.penUp();
    this.moveTo(x,y);
    //to prevent the constructed from being too small, add 50 to each value.
    int value1 = random.nextInt(limit)+50;
    int value2 = random.nextInt(limit)+50;
    //Draw triangle
    this.penDown();
    this.forward(value1);
    this.turn(angle);
    this.forward(value2);
    this.moveTo(x,y);
    this.penUp();
  }
  
  // this } is the end of class Turtle, put all new methods before this
  /** Determine how large of a step the turtle takes  
    * Parameters: generator: a Random object that will help to generate random number.
    * Return value: A random number between 1 and 3.
    * */
  public int generateOffset(Random generator){
    int value = 0;
    generator = new Random();  
    value = generator.nextInt(4)+1;
    return value;
  }
  /** Display a random step of the turtle.  
    * Parameters:  direction: -1 for step to the left or 1 for step to the right.
    *         generator: a Random object that will help to generate random number.
    * Return value: How large of a step (the offset 1-3)
    * */
  public int takeStep( int direction, Random generator){
    int stepsize = generateOffset(new Random());
    this.turn(direction*45);
    this.forward(stepsize*20);
    this.turn(-direction*45);
    return stepsize;
  }
  /**
   * Return whether or not the turtle should take a step to the left
   * or the right
   * Parameters: probabilityToRight: the probability the turtle moves to the right.
   *                                 Should between 0-1.
   *             generator: a Random object that will help to generate random number.
   * Output: -1 to represent a step to the left, 1 to represent a step to the right.
   */
  public int getRandomStep(double probabilityToRight, Random generator) {
    // Choose either 0 or 1 (note that next int is NON inclusive 
    // of its argument).
    double choice = generator.nextDouble();
    if (choice < probabilityToRight) {
      return 1;
    } else {
      return -1;
    }
  } 
  /**
   * Return the displacement of the turtle and display its movement.
   * Parameters: nSteps: number of steps taken by turtle.
   *             generator: a Random object that will help to generate random number.
   * Output: the displacement from the original position at turtle's final position.
   */
  public int rwPosition(int nSteps, Random generator){
    int displacement =0;
    //construct a for loop to update the displacement.
    for(int count=0;count<nSteps;count++){
      int direction_input=getRandomStep(0.5,new Random());
      int stepSize= this.takeStep(direction_input,new Random());
      displacement += stepSize*direction_input;
    }
    return displacement;
    
  }
  /**
   * Return the displacement of the turtle without displaying its movement.
   * Parameters: nSteps: number of steps taken by turtle.
   *             generator: a Random object that will help to generate random number.
   * Output: the displacement from the original position at turtle's final position.
   */
    public int rwPositionPlain(int nSteps, Random generator){
    int displacement =0;
    //construct a for loop to update the displacement without moving the turtle.
    for(int count=0;count<nSteps;count++){
      int direction_input=getRandomStep(0.3,new Random());
      int stepSize= this.generateOffset(new Random());
      displacement += stepSize*direction_input;
    }
    return displacement;
    
  }
  /**
   * Return number of steps the turtle takes before it hits the maximum displacement limit.
   * Display the movement of the turtle.
   * Parameters: maxiDisplacement: the maximum displacement set for the turtle movement.
   *             generator: a Random object that will help to generate random number.
   * Output: the number of steps the turtle takes.
   */
  public int countSteps(int maxDisplacement, Random generator){
    int abs_displacement_trace = 0;
    int steps= 0;
    int displacement_trace =0;
    //Construct a while loop to keep track of the steps the turtle takes.
    while(abs_displacement_trace < maxDisplacement){
      int direction_input=getRandomStep(0.5,new Random());
      int stepSize= this.takeStep(direction_input,new Random());
      displacement_trace += stepSize*direction_input;
      abs_displacement_trace = Math.abs(displacement_trace);
      steps++;
    }
      return steps;
  }
}

