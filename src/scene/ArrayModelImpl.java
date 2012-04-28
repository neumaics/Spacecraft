package scene;

import util.Vector3i;

/**
 * 
 * A model composed of a 3D grid of cubes.
 * @author Brian Holland
 *
 */
public class ArrayModelImpl extends Model {
  private Cube[][][] world;
  private final int length;
  private final int width;
  private final int height;
  
  public ArrayModelImpl() {
   this.length = this.width = this.height = 20; 
  }
  
  public ArrayModelImpl(int length, int width, int height) {
    this.length = length;
    this.width = width;
    this.height = height;
    
    world = new Cube[this.width][][];
    
    for (int i = 0; i < this.width; ++i) {
      for (int j = 0; j < this.length; ++j) {
        for (int k = 0; k < this.height; ++k) {
          
        }
      }
    }
  }
  
  public Cube getCube(Vector3i p) {
    return world[p.x][p.y][p.z];
  }
}
