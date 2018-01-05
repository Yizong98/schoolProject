/* Filename: RandomTurtleTester2.java 
 * Created by: Yijian Zong, cs8afhp
 * Date: 10/21/2017
 * 
 *  A) the average final signed-displacement for a random walker after 100 random steps given
 * the probability of 0.5 is 0.20422535211267606 .
 *   the average final signed-displacement for a random walker after 100 random steps given
 * the probability of 0.3 is -97.36619718309859 .  
 *   the average final signed-displacement for a random walker after 100 random steps given
 * the probability of 0.8 is 152.76760563380282 .    
 * 
 *  B)
 * when step number is 200:
 *   the average final signed-displacement for a random walker after 200 random steps given
 * the probability of 0.8 is 301.6901408450704 .
 *   the average final signed-displacement for a random walker after 200 random steps given
 * the probability of 0.5 is -4.985915492957746 .
 *   the average final signed-displacement for a random walker after 200 random steps given
 * the probability of 0.3 is -202.66901408450704.
 * when step number is 50:
 *   the average final signed-displacement for a random walker after 50 random steps given
 * the probability of 0.8 is  74.0 .
 *   the average final signed-displacement for a random walker after 50 random steps given
 * the probability of 0.5 is 1.091549295774648  .
 *   the average final signed-displacement for a random walker after 50 random steps given
 * the probability of 0.3 is -49.11267605633803  .
 */ 

import java.util.Random;
/**
 * This is a tester class to investigate the movements of turtle.
 */
public class RandomTurtleTester2
{
  //The line below is magic, you don't have to understand it (yet)
  public static void main (String[] args) 
  { 
    World w = new World();
    Turtle t = new Turtle( w );
    Random generator = new Random();  
    int numTrials = 0;
    double totalDisplacement = 0.0;
    while ( numTrials < 142 ) 
    {
      totalDisplacement = totalDisplacement + t.rwPositionPlain( 50, generator );
      numTrials = numTrials + 1;
    }
    // Find the average of total displacement.
    double average = totalDisplacement/numTrials;
    System.out.println("The average is "+average);
    
    
    
  } 
}