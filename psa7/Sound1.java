/* Filename: Sound.java 
* Created by: Jiayan Dong, cs8afqa
* Date: November 26 2017
*/ 
/**
 * Class that represents a sound.  This class is used by the students
 * to extend the capabilities of SimpleSound.
 *
 * Copyright Georgia Institute of Technology 2004
 * @author Barbara Ericson ericson@cc.gatech.edu
 */

/* Program Descriptinon:
* In the file PSA7PictureTester.java, we are selecting a picture and creating a 
* Picture object. It then shows the original picture. We are entering an (x, y) 
* coordinate and a size. After each x, y coordinate and size entered, we are 
* performing a horizontal flip on that part of the image and return a new 
* Picture. If the region to be flipped is out of bounds, we are only flipping 
* the region that is within bounds of the picture, ignoring the regions that go 
* out of bounds. The x and y coordinate are the bottom left corner of the region
* to be flipped.
* 
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
*/

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
   *
   */
  public static Sound whitenoise(int frequency)


  /**
   *
   */
  public Sound pluck (int soundLength)


  /**
   *
   */
      public Sound plucktest (int soundLength){
    Sound result = new Sound(soundLength);
    SoundSample[]sampleArray = result.getSamples();
    SoundSample[]noiseArray = this.getSamples();
    Sound testSound = new Sound(3);
    SoundSample [] testSamples = testSound.getSamples();
    testSamples[0].setValue(100);
    testSamples[1].setValue(-200);
    testSamples[2].setValue(300);
    for (SoundSample s:sampleArray){
      s = noiseArray[0];
      int ksValue = (int)(((s.getValue()+noiseArray[1].getValue())*0.996)/2);
      for(int i=0;i <noiseArray.length-1;i++){
        noiseArray[i].setValue(noiseArray[i+1].getValue());
        System.out.print(noiseArray[i].getValue() + " ");
      }
      noiseArray[noiseArray.length-1].setValue(ksValue);
      
      
    }
    return result;
    
    
    
  }
  public boolean sameSound(Sound s)
} // this } is the end of class Sound, put all new methods before this
