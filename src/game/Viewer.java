package game;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Viewer {
  private int width;
  private int height;
  private boolean fullscreen;
  private String name;
  
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
    this.name = builder.name;
    this.height = builder.height;
    this.width = builder.width;
    this.fullscreen = builder.fullscreen;
    init();
  }

  private void init()
  {
    try 
    {
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
