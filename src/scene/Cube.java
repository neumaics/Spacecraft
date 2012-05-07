package scene;

public class Cube {
  public enum ORDER {A, B, C, D, Z}
  public enum DIRECTION {
    NORTH, EAST, SOUTH, WEST,
    NORTH_EAST, SOUTH_EAST, SOUTH_WEST, NORTH_WEST; 
  }
  
  public enum ORIENTATION { UP, DOWN, NEUTRAL; }
  
  public ORDER o;
  public DIRECTION d;
  public ORIENTATION v;
  
  public Cube(ORDER o, DIRECTION d, ORIENTATION v) {
    this.o = o;
    this.d = d;
    this.v = v;
  }
}
