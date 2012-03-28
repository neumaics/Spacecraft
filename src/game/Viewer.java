package game;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Viewer {
  private static int width;
  private static int height;
  private static boolean fullscreen;
  private static String name;
  
  public static class Builder {
    //Required parameters
    private final String name;
    
    //Optional parameters
    private int width = 800;
    private int height = 600;
    private boolean fullscreen = false;
    
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
    public Viewer build()
    {
      return new Viewer( this );
    }
  }
  
  private Viewer( Builder builder ) {
    Viewer.name = builder.name;
    Viewer.height = builder.height;
    Viewer.width = builder.width;
    Viewer.fullscreen = builder.fullscreen;
    init();
  }

  private static void init()
  {
    try 
    {
      Display.setTitle(name);
      Display.setDisplayMode(new DisplayMode(width, height));
      Display.setFullscreen(fullscreen);
      Display.create();
    } 
    catch (LWJGLException e) 
    {
      e.printStackTrace();
      System.exit(0);
    }
  }
  
  public static void toggleFullscreen()
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
  public static void changeScreenDimensions()
  {
    Display.destroy();
    init();
  }
  
  public static void update()
  {
    Display.update();
  }
  
  public static boolean isCloseRequested()
  {
    return Display.isCloseRequested();
  }
  
  public static void cleanUp()
  {
    Display.destroy();
  }
  
  public int getWidth()
  {
    return width;
  }

  public void setWidth(int width)
  {
    Viewer.width = width;
  }

  public int getHeight()
  {
    return height;
  }

  public void setHeight(int height)
  {
    Viewer.height = height;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    Viewer.name = name;
  }
  
}
