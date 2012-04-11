package game;

import org.lwjgl.Sys;

/**
 * This class all of the timing tracking and calculation.
 * 
 * It is based in part on the information found at:
 *  ninjacave.com/lwjglbasics4
 *   
 * @author Brian Holland (bholland@mymail.mines.edu)
 *
 */
public class Timer {
  private static final int MILS_TO_SECS = 1000;  
  private static int fps;
  private static long lastFPS;
  private static long lastFrame;
  
  public Timer() {
    lastFPS = getTime();
  }
  
  /**
   * Gets the time in milliseconds.
   * @return System time in milliseconds.
   */
  public long getTime() {
    return (Sys.getTime() * MILS_TO_SECS) / Sys.getTimerResolution();
  }
  

  public void updateFPS() {
    if (getTime() - lastFPS > MILS_TO_SECS) {
      System.out.println(fps);
      fps = 0;
      lastFPS += 1000;
    }
    
    fps++;
  }

  /**
   * Gets the time between frames. 
   * 
   * @return integer time in milliseconds between frames.
   */
  public int getDelta() {
    long time = getTime();
    int delta = (int)(time - lastFrame);
    lastFrame = time;
    
    return delta;
  }
  
  public int getFps() {
    return fps;
  }
  
}
