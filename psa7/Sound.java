/* Filename: Sound.java 
 * Created by: Yijian Zong, cs8afhp, Jiayan Dong, cs8afqa
 * Date: November 26 2017
 */ 

/**
 * Class that represents a sound.  This class is used by the students
 * to extend the capabilities of SimpleSound.
 *
 * Copyright Georgia Institute of Technology 2004
 * @author Barbara Ericson ericson@cc.gatech.edu
 */



/*----------- Program Description: ------------
 [Part A: PSA7PictureTester]
 * In the file PSA7PictureTester.java, we are selecting a picture and creating a 
 * Picture object. It then shows the original picture. We are entering an (x, y) 
 * coordinate and a size. After each x, y coordinate and size entered, we are 
 * performing a horizontal flip on that part of the image and return a new 
 * Picture. If the region to be flipped is out of bounds, we are only flipping 
 * the region that is within bounds of the picture, ignoring the regions that go 
 * out of bounds. The x and y coordinate are the bottom left corner of the region
 * to be flipped.
 [Part B: Sound]
 * In the file Sound.java, we are simulating plucking a guitar string using the 
 * Karplus-Strong Algorithm. After the string is plucked, the string vibrates. 
 * The pluck causes a displacement that spreads wave-like over time. We are 
 * generating the sound by first generating a white noise sound based on a 
 * frequency. Then we use the white noise sound to generate the actual plucking
 * sound of a guitar string.
 * We are also testing the two methods that we implemented above. We are writing
 * a method that compares if two Sounds are the same. Once we are done with our 
 * code and test it properly, we can use the provided graphical user interface to
 * play music.
 **/

import java.util.Random;
public class Sound extends SimpleSound
{
  
  /////////////// consructors ////////////////////////////////////
  
  /**
   * Constructor that takes a file name
   * @param fileName the name of the file to read the sound from
   */
  public Sound(String fileName)
  {
    // let the parent class handle setting the file name
    super(fileName);
  }
  
  /**
   * Constructor that takes the number of samples in
   * the sound
   * @param numSamples the number of samples desired
   */
  public Sound (int numSamples)
  {
    // let the parent class handle this
    super(numSamples);
  }
  
  /**
   * Constructor that takes the number of samples that this
   * sound will have and the sample rate
   * @param numSamples the number of samples desired
   * @param sampleRate the number of samples per second
   */
  public Sound (int numSamples, int sampleRate)
  {
    // let the parent class handle this
    super(numSamples,sampleRate);
  }
  
  /**
   * Constructor that takes a sound to copy
   */
  public Sound (Sound copySound)
  {
    // let the parent class handle this
    super(copySound);
  }
  
  ////////////////// methods ////////////////////////////////////
  
  /**
   * Method to return the string representation of this sound
   * @return a string with information about this sound
   */
  public String toString()
  {
    String output = "Sound";
    String fileName = getFileName();
    
    // if there is a file name then add that to the output
    if (fileName != null)
      output = output + " file: " + fileName;
    
    // add the length in frames
    output = output + " number of samples: " + getLengthInFrames();
    
    return output;
  }
  
  /**
   * Method to generate a white noise sound based on a frequency. 
   * Input: frequency
   * Returns: a Sound
   */
  public static Sound whitenoise(int frequency){
    int arrayLength = (int)(22050.0/frequency+1);
    Random generator = new Random(); 
    Sound whitenoise= new Sound(arrayLength);
    for (int i=0;i<arrayLength;i++){
      int randomValue = generator.nextInt(32768+32767)-32768;
      whitenoise.getSamples()[i].setValue(randomValue);
      
    }
    return whitenoise;
    
  }
  
  
  /**
   * Method to generate the sound with the help of the whitenoise sound from the
   * whitenoise method.
   * Input: soundLength
   * Returns: a Sound
   */
  public Sound pluck (int soundLength){
    Sound result = new Sound(soundLength);
    SoundSample[]sampleArray = result.getSamples();
    SoundSample[]noiseArray = this.getSamples();
    for (SoundSample s:sampleArray){
      s.setValue(noiseArray[0].getValue());
      int ksValue = (int)(((s.getValue()+noiseArray[1].getValue())*0.996)/2);
      for(int i=0;i <noiseArray.length-1;i++){
        noiseArray[i].setValue(noiseArray[i+1].getValue());
        System.out.print(noiseArray[i].getValue() + " " );
        System.out.print(sampleArray[i].getValue() + " ");
      }
      noiseArray[noiseArray.length-1].setValue(ksValue);
      
      
      
    }
    return result;
    
    
    
  }
  
  public Sound plucktest (int soundLength){
    Sound result = new Sound(soundLength);
    SoundSample[]sampleArray = result.getSamples();
    SoundSample[]noiseArray = this.getSamples();
    for (SoundSample s:sampleArray){
      s = noiseArray[0];
      int ksValue = (int)(((s.getValue()+noiseArray[1].getValue())*0.996)/2);
      for(int i=0;i <noiseArray.length-1;i++){
        noiseArray[i].setValue(noiseArray[i+1].getValue());
        System.out.print(noiseArray[i].getValue() + " ");
        System.out.print(sampleArray[i] + " ");

      }
      noiseArray[noiseArray.length-1].setValue(ksValue);
      
      
    }
    return result;
  }
  
  /**
   * Method to test the two methods that we implemented above. Compares if two 
   * Sounds are the same. 
   * Input: Sound to be compared.
   * Returns: a boolean value indicating true or false
   */
  public boolean sameSound(Sound s){
    SoundSample[]testingArray = s.getSamples();
    SoundSample[]testedArray = this.getSamples();
    for (int i=0;i<testingArray.length;i++){
      if(testingArray[i].getValue() != testedArray[i].getValue()){
        return false;
      }
      
    }
    return true;
  }
  
  public static void main( String[] args )throws SoundException
  {    
    //Sound sound = new Sound( FileChooser.pickAFile());
    //Sound whitenoise = new Sound( FileChooser.pickAFile());
//    Sound afterPluck = whitenoise.pluck(10000);
//    System.out.println("Through Comparison, the result is " + afterPluck.sameSound(sound));
//    afterPluck.writeToFile("afterPluck1.wav");
//    //afterPluck.play();
//    Sound noise = whitenoise(440);
//    noise.writeToFile("noise.wav");
//    Sound afterPluck2 = noise.pluck(10000);
//    afterPluck2.play();
//    Sound testSound = new Sound(3);
//    SoundSample [] testSamples = testSound.getSamples();
//    testSamples[0].setValue(100);
//    testSamples[1].setValue(-200);
//    testSamples[2].setValue(300);
    //System.out.print(noiseArray[i].getValue() + " ");
    Sound testSound = new Sound(3);
    SoundSample [] testSamples = testSound.getSamples();
    testSamples[0].setValue(100);
    testSamples[1].setValue(-200);
    testSamples[2].setValue(300);
    
    testSound.pluck(5);
    testSound.plucktest(5);
     
    
  }
  
} // this } is the end of class Sound, put all new methods before this
