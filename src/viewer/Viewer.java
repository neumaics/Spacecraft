package viewer;

import static org.lwjgl.opengl.GL11.GL_BACK;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.glCullFace;
import static org.lwjgl.opengl.GL11.glEnable;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Viewer {
  private int width;
  private int height;
  private String name;
  
  public static class Builder {
    //Required parameters
    private final String name;
    
    //Optional parameters
    private int width = 800;
    private int height = 600;
    
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
    public Viewer build()
    {
      return new Viewer( this );
    }
  }
  
  private Viewer( Builder builder ) {
    this.name = builder.name;
    this.height = builder.height;
    this.width = builder.width;
    init();
  }

  private void init()
  {
    try {
      Display.setDisplayMode(new DisplayMode(width, height));
      Display.create();
    } catch (LWJGLException e) {
      e.printStackTrace();
      System.exit(0);
    }
    
    //TODO(mitch) Should these be changeable from outside the Viewer?
    glEnable(GL_CULL_FACE);
    glCullFace(GL_BACK);
    glEnable(GL_DEPTH_TEST);
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
