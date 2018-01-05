import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.Arrays;

import javax.imageio.ImageIO;

import java.awt.*;

public class PlanetReader {
  
  private static final String DELIMITERS = " ";
  
  private Body[] planets;
  private double scale;
  private double timestep;
  private double totalTime;
  
  private double[] xPositions;
  private double[] yPositions;
  private double[] xVelocities;
  private double[] yVelocities;
  private double[] masses;
  private Picture[] images;
  
  /**
   * Constructor that reads in a file and instantiates all Planet obejcts.
   * 
   * @param filename The file to read from.
   */
  public PlanetReader(String filename)
  {
    FileInputStream input;
    try {
      input = new FileInputStream(new File(filename));
    } catch (FileNotFoundException e) {
      System.out.println("Could not find file: " + filename);
      return;
    }
    
    // The first line is the number of planets, and the second is the universe's
    // scale.
    Scanner scanner = new Scanner(input);
    
    this.planets = new Body[scanner.nextInt()];
    this.xPositions = new double[planets.length];
    this.yPositions = new double[planets.length];
    this.xVelocities = new double[planets.length];
    this.yVelocities = new double[planets.length];
    this.masses = new double[planets.length];
    this.images = new Picture[planets.length];
    
    this.scale = scanner.nextDouble();
    this.timestep = scanner.nextDouble();
    this.totalTime = scanner.nextDouble();
    
    // Read a subsequent line for every Planet, and call the constructor that
    // best matches the values in the line.
    for (int i = 0; i < planets.length; i++)
    {
      xPositions[i] = scanner.nextDouble() + this.scale;
      yPositions[i] = scanner.nextDouble() + this.scale;
      xVelocities[i] = scanner.nextDouble();
      yVelocities[i] = scanner.nextDouble();
      masses[i] = scanner.nextDouble();
      images[i] = new Picture(scanner.next());
      
      planets[i] = new Body(xPositions[i], yPositions[i], xVelocities[i],
                            yVelocities[i], masses[i], images[i]);
      
    }
    
    // Always close any files you open.
    scanner.close();
    try {
      input.close();
    } catch (IOException e) {
      System.out.println(e);
    }
    
  }
  
  /**
   * Getter for the Planets loaded from the file.
   * 
   * @returns An array of Planet objects, some of which are null.
   */
  public Body[] getPlanets()
  {
    return this.planets;
  }
  
  /**
   * Getter for the scaling factor for the universe.
   * 
   * @return The universe's radius.
   */
  public double getUniverseSize()
  {
    return this.scale * 2;
  }
  
  /**
   * Getter for the time delta in which each frame should occur.
   * 
   * @return The amount of time that passes between frames.
   */
  public double getTimestep()
  {
    return this.timestep;
  }
  
  /**
   * Getter for the total time to run through for the system.
   * 
   * @return The total time of the simulation.
   */
  public double getTotalTime()
  {
    return this.totalTime;
  }
  
  /**
   * Getter for the x positions of each planet.
   * 
   * @return All starting x positions in array format.
   */
  public double[] getXPositions()
  {
    return this.xPositions;
  }
  
  /**
   * Getter for the y positions of each planet.
   * 
   * @return All starting y positions in array format.
   */
  public double[] getYPositions()
  {
    return this.yPositions;
  }
  
  /**
   * Getter for the x velocities of each planet.
   * 
   * @return All starting x velocities in array format.
   */
  public double[] getXVelocities()
  {
    return this.xVelocities;
  }
  
  
  /**
   * Getter for the y velocities of each planet.
   * 
   * @return All starting y velocities in array format.
   */
  public double[] getYVelocities()
  {
    return this.yVelocities;
  }
  
  /**
   * Getter for the masses of each planet.
   * 
   * @return All masses in array format.
   */
  public double[] getMasses()
  {
    return this.masses;
  }
  
  /**
   * Getter for the image of each planet.
   * 
   * @return All images in array format.
   */
  public Picture[] getImages()
  {
    return this.images;
  }
  
}
