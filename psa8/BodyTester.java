public class BodyTester {
  
  private FakePicture fake_100_100 = new FakePicture(100, 100);
  private FakePicture fake_50_100 = new FakePicture(50, 100);
  private FakePicture fake_50_50 = new FakePicture(50, 50);
  
  private Body b1;
  private Body b2;
  private Body b3;
  private Body b4;
  private Body b5;
  private Body b6;
  private Body b7;
  private Body b8;
  
  public static final void main(String[] args)
  {
    BodyTester tester = new BodyTester();
    tester.setUp();
    tester.testGetters();
    tester.setUp();
    tester.testGetDistance();
    tester.setUp();
    tester.testGetAcceleration();
    tester.setUp();
    tester.testMove();
    tester.setUp();
    tester.testUpdateVelocity();
    tester.setUp();
    tester.testCenterValues();
    tester.setUp();
    tester.testDraw();
  }
  
  public void setUp()
  {
    System.out.println();
    b1 = new Body(0.0, 0.0, 0.0, 0.0, 2.4E6, fake_100_100);
    b2 = new Body(-2.0E8, 0.0, 0.0, 0.0, 2.4E6, fake_50_100);
    b3 = new Body(2.0E8, 0.0, 0.0, 0.0, 2.4E6, fake_50_50);
    b4 = new Body(0.0, 2.0E8, 0.0, 0.0, 2.4E6, new Picture("images/mars.png"));
    b5 = new Body(0.0, 0.0, 4.0E7, 0.0, 1.2E6, new Picture("images/mars.png"));
    b6 = new Body(0.0, 0.0, 0.0, -4.7E7, 1.2E6, new Picture("images/mars.png"));
    b7 = new Body(2.0E8, 2.0E8, 0.0, 0.0, 2.4E6, new Picture("images/mars.png"));
    b8 = new Body(1.0, 0.0, 0.0, 0.0, 1.0, new Picture("images/mars.png"));
    Body.TIME_STEP = 2.5E15;
    Body.UNIVERSE_SIZE = 4.0E8;
  }
  
  public void testGetters() {
    if (b1.getXPosition() != 0.0) {
     System.out.println("Wrong X Position returned! Expected: " + 0.0 + " Got: " + b1.getXPosition());
    } else if (b2.getXPosition() != -2.0E8) {
      System.out.println("Wrong X Position returned! Expected: " + -2.0E8 + " Got: " + b2.getXPosition());
    } else if (b1.getYPosition() != 0.0) {
      System.out.println("Wrong Y Position returned! Expected: " + 0.0 + " Got: " + b1.getYPosition());
    } else if (b4.getYPosition() != 2.0E8) {
      System.out.println("Wrong Y Position returned! Expected: " + 2.0E8 + " Got: " + b4.getYPosition());
    } else if (b1.getMass() != 2.4E6) {
      System.out.println("Wrong mass returned! Expected: " + 2.4E6 + " Got: " + b1.getMass());
    } else {
      System.out.println("Testing Getters : PASSED");
      return;
    }
    System.out.println("Testing Getters : FAILED");
  }
  
  public void testGetDistance() {
    if (Math.abs(b2.getDistance(b1) - 2.0E8) > 2.0E5) {
      System.out.println("Wrong distance! Expected: " + 2.0E8 + " Got: " + b2.getDistance(b1));
    } else if (Math.abs(b1.getDistance(b4) - 2.0E8) > 2.0E5) {
      System.out.println("Wrong distance! Expected: " + 2.0E8 + " Got: " + b1.getDistance(b4));
    } else if (Math.abs(b1.getDistance(b7) - 2.0E8 * Math.sqrt(2)) > 2.0E5) {
      System.out.println("Wrong distance! Expected: " + 2.0E8 * Math.sqrt(2) +
                         " Got: " + b1.getDistance(b7));
    } else {
      System.out.println("Testing getDistance() : PASSED");
      return;
    }
    System.out.println("Testing getDistance : FAILED");
  }
  
  public void testGetAcceleration() {
    double accel1 = b2.getAcceleration(b1);
    double accel2 = b2.getAcceleration(b3);
    double ratio = accel1 / accel2;
    if (ratio < 3.9 || ratio > 4.1 || Double.isNaN(ratio)) {
      System.out.println("Bodies half the distance apart should have about four times the gravity! Your ratio " + ratio);
      System.out.println("Testing getAcceleration() : FAILED");
      return;
    }
    
    accel2 = b2.getAcceleration(b5);
    ratio = accel1 / accel2;
    if (ratio < 1.9 || ratio > 2.1 || Double.isNaN(ratio)) {
      System.out.println("Bodies with twice the mass should have twice the gravity! Your ratio: " + ratio);
      System.out.println("Testing getAcceleration() : FAILED");
      return;
    }
    
    if (Math.abs(b1.getAcceleration(b8) - 6.67E-11) > 1E-12) {
      System.out.println("The acceleration between two bodies should take the gravimetric constant into account!");
      System.out.println("Acceleration towards a body with m = 1, r = 1 (should be 6.67E-11) was: " +
                         b1.getAcceleration(b8));
      System.out.println("Testing getAcceleration() : FAILED");
      return;
    }
    
    System.out.println("Testing getAcceleration() : PASSED");
  }
  
  public void testCenterValues() {
    int centerX = NBody.CANVAS_WIDTH / 2;
    int centerY = NBody.CANVAS_HEIGHT / 2;
    
    int xPosition = b1.centerXToPixelPosition();
    int yPosition = b1.centerYToPixelPosition();
    if (xPosition < -2 || xPosition > 2 ||
        yPosition < -2 || yPosition > 2) {
      System.out.println("A body with an x and y of 0 should draw centered around (0, 0)! Your center: " +
                          "(" + xPosition + "," + yPosition + ")");
      System.out.println("Testing Center Locations: FAILED");
      return;
    }
    
    xPosition = b3.centerXToPixelPosition();
    yPosition = b4.centerYToPixelPosition();
    
    if (xPosition - centerX < -2 || xPosition - centerX > 2 ||
        yPosition - centerY < -2 || yPosition - centerY > 2) {
      System.out.println("A body with an x or y equal to the half the universe size should center at the center of the picture!");
      System.out.println("Center at: (" + centerX + "," + centerY + ") Your center: (" + xPosition + "," + yPosition + ")");
      System.out.println("Testing Center Locations: FAILED");
      return;
    }
    
    System.out.println("Testing Center Locations: PASSED");
  }
  
  public void testMove() {
    double initialX = b5.getXPosition();
    b5.move();
    if (b5.getXPosition() <= initialX) {
      System.out.println("A body with a positive x velocity should move right when move() is called!");
      System.out.println("Testing move(): FAILED");
      System.out.println("Note: You will not pass the test for updateVelocity() until you have written move().");
      return;
    }
    
    double initialY = b6. getYPosition();
    b6.move();
    if (b6.getYPosition() >= initialY) {
      System.out.println("A body with a negative y velocity should move up when move() is called!");
      System.out.println("Testing move(): FAILED");
      System.out.println("Note: You will not pass the test for updateVelocity() until you have written move().");
      return;
    }
    System.out.println("Testing move(): PASSED");
  }
  
  public void testUpdateVelocity() {
    double initialX = b1.getXPosition();
    
    
    b1.updateVelocity(b2);
    b1.move();
    
    if (b1.getXPosition() >= initialX) {
      System.out.println("A body should start to move to the left after accelerating towards a body to the left!");
      System.out.println("Yours changed its X by: " + (b1.getXPosition() - initialX) +
                         " (should be negative)");
      System.out.println("Testing updateVelocity(): FAILED");
      return;
    }
    
    initialX = b4.getXPosition();
    double initialY = b4.getYPosition();
    
    b4.updateVelocity(b3);
    b4.move();
    
    if (b4.getXPosition() <= initialX || b4.getYPosition() >= initialY) {
      System.out.println("A body should start to move up and right after accelerating towards" +
                         " a body to the up and right!");
      System.out.println("Yours changed its X by: " + (b4.getXPosition() - initialX) +
                         " (should be positive)");
      System.out.println("Yours changed its Y by: " + (b4.getYPosition() - initialY) +
                         " (should be negative)");
      System.out.println("Testing updateVelocity(): FAILED");
      return;
    }
    
    System.out.println("Testing updateVelocity(): PASSED");
  }
  
  public void testDraw() {
      Picture canvas = new Picture(500, 500);
      
      b1.draw(canvas);
      
      if (!fake_100_100.wasCalled()) {
        System.out.println("You need to call alphaBlending in draw() before it can be tested!");
        System.out.println("Testing draw(): FAILED");
        return;
      }
      
      if (fake_100_100.getLastX() != b1.centerXToPixelPosition() - 50 || 
          fake_100_100.getLastY() != b1.centerYToPixelPosition() - 50)
      {
        System.out.println("Make sure to center around centerXToPixelPosition and centerYToPixelPosition in draw()!");
        System.out.println("Coordinates passed to alphaBlending(): (" + fake_100_100.getLastX() + "," + 
                           fake_100_100.getLastY() + ") but should be (" + (b1.centerXToPixelPosition() - 50) + "," + 
                           (b1.centerYToPixelPosition() - 50) + ")");
        System.out.println("Testing draw(): FAILED");
        return;
      }
      
      b2.draw(canvas);
      if (fake_50_100.getLastX() != b2.centerXToPixelPosition() - 25 || 
          fake_50_100.getLastY() != b2.centerYToPixelPosition() - 50)
      {
        System.out.println("Make sure to center around centerXToPixelPosition and centerYToPixelPosition in draw()!");
        System.out.println("Coordinates passed to alphaBlending(): (" + fake_50_100.getLastX() + "," + 
                           fake_50_100.getLastY() + ") but should be (" + (b2.centerXToPixelPosition() - 25) + "," + 
                           (b2.centerYToPixelPosition() - 50) + ")");
        System.out.println("Testing draw(): FAILED");
        return;
      }
      
      b3.draw(canvas);
      if (fake_50_50.getLastX() != b3.centerXToPixelPosition() - 25 || 
          fake_50_50.getLastY() != b3.centerYToPixelPosition() - 25)
      {
        System.out.println("Make sure to center around centerXToPixelPosition and centerYToPixelPosition in draw()!");
        System.out.println("Coordinates passed to alphaBlending(): (" + fake_50_50.getLastX() + "," + 
                           fake_50_50.getLastY() + ") but should be (" + (b3.centerXToPixelPosition() - 25) + "," + 
                           (b3.centerYToPixelPosition() - 25) + ")");
        System.out.println("Testing draw(): FAILED");
        return;
      }
      System.out.println("Testing draw(): PASSED");
    }
    
    private class FakePicture extends Picture {
      private int lastX;
      private int lastY;
      private boolean called;
      
      public FakePicture(int width, int height) { super(width, height); }
      
      @Override
      public void alphaBlending(int x, int y, Picture p) {
        this.lastX = x;
        this.lastY = y;
        this.called = true;
      }
      
      public int getLastX() { return lastX; }
      public int getLastY() { return lastY; }
      public boolean wasCalled() { return called; }
    }
}
