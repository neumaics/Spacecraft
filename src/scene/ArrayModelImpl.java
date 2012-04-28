package scene;

import scene.Cube.DIRECTION;
import scene.Cube.ORDER;
import scene.Cube.ORIENTATION;

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
      world[i] = new Cube[this.length][];
      for (int j = 0; j < this.length; ++j) {
        world[i][j] = new Cube[this.height];
        for (int k = 0; k < this.height; ++k) {
          world[i][j][k] = new Cube(ORDER.Z, DIRECTION.EAST, ORIENTATION.UP);
        }
      }
    }
  }
  
  public Cube getCube(Vector3i p) {
    return world[p.x][p.y][p.z];
  }
}
