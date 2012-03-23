package viewer;

public class Viewer {
  private int WINDOW_WIDTH;
  private int WINDOW_HEIGHT;
  private String WINDOW_NAME;
  private int FLOAT_BYTES = 16 * 4;
  private float DEGTORAD = (float) (Math.PI / 180);
  private float RADTODEG = 1 / DEGTORAD;
  private final int WHEEL_CONST = 120;
  private boolean HasWheel;
  
  public static class Builder {
    //Required parameters
    private final String WINDOW_NAME;
    //Optional parameters
    private int width = 800;
    private int height = 600;
    
    public Builder( String window_name ) {
      WINDOW_NAME = window_name;
    }
    
    public Builder height( int height )
      { this.height = height; return this; }
    public Builder width( int width )
      { this.width = width; return this; }
    
    
    
  }
  
}
