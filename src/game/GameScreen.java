package game;

import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * Functions as a wrapper for LWJGL's Display class.
 * 
 * @author Mitch
 * @author Brian Holland (bholland@mymail.mines.edu)
 */
public class GameScreen {
  private int width;
  private int height;
  private boolean fullscreen;
  private String name;
  private int vsync;
  
  public static class Builder {
    //Required parameters
    private final String name;
    
    //Optional parameters
    private int width = 800;
    private int height = 600;
    private boolean fullscreen = false;
    private int vsync = 60;
    
    public Builder( String name ) 
    { 
      this.name = name; 
    }
    public Builder height( int height )
    { 
      this.height = height; return this; 
    }
    public Builder width( int width )
    { 
      this.width = width; return this; 
    }
    public Builder fullscreen( boolean fullscreen )
    {
      this.fullscreen = fullscreen; return this;
    }
    public Builder vsynch( int vsync )
    {
      this.vsync = vsync; return this;
    }
    public GameScreen build()
    {
      return new GameScreen( this );
    }
  }
  
  private GameScreen( Builder builder ) {
    this.name = builder.name;
    this.height = builder.height;
    this.width = builder.width;
    this.fullscreen = builder.fullscreen;
    this.vsync = builder.vsync;
    init();
  }

  private void init()
  {
    try {
      Display.setTitle(name);
      Display.setDisplayMode(new DisplayMode(width, height));
      Display.setFullscreen(fullscreen);
      Display.create();
      initOpenGL();
    } catch (LWJGLException e) {
      e.printStackTrace();
      System.exit(0);
    }
  }
  
  public void initOpenGL() {
    glViewport(0, 0, height, width);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();

    gluPerspective(90f, 1, 0, 2);
  }
  
  public void toggleFullscreen()
  {
    fullscreen = !fullscreen;
    try
    {
      Display.setFullscreen(fullscreen);
    }
    catch (LWJGLException e)
    {
      e.printStackTrace();
      System.exit(0);
    }
  }
  
  /**
   * Assuming that the width or height has changed, this will destroy the 
   * current display, then make a new one based off of the new values.
   */
  public void changeScreenDimensions()
  {
    Display.destroy();
    init();
  }
  
  /**
   * Updates the display and synchronizes to the given frame rate.
   */
  public void update()
  {
    Display.update();
    Display.sync(vsync);
  }
  
  public static boolean isCloseRequested()
  {
    return Display.isCloseRequested();
  }
  
  public void cleanUp()
  {
    Display.destroy();
  }
  
  public int getWidth()
  {
    return width;
  }

  public void setWidth(int width)
  {
    this.width = width;
  }

  public int getHeight()
  {
    return height;
  }

  public void setHeight(int height)
  {
    this.height = height;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }
  
}
