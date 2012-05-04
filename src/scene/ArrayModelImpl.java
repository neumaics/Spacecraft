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
  
  public ArrayModelImpl() {
   super(20, 20, 20);
//   this.length = this.width = this.height = 20; 
   
  }
  
  public ArrayModelImpl(int length, int width, int height) {
    super(length, width, height);
//    this.length = length;
//    this.width = width;
//    this.height = height;
    
    world = new Cube[this.length][][];
    for (int i = 0; i < this.length; ++i) {
      world[i] = new Cube[this.height][];
      for (int j = 0; j < this.height; ++j) {
        world[i][j] = new Cube[this.width];
        for (int k = 0; k < this.width; ++k) {
          if (j >= height/2)
            world[i][j][k] = new Cube(ORDER.Z, DIRECTION.EAST, ORIENTATION.UP);
          else
            world[i][j][k] = new Cube(ORDER.A, DIRECTION.EAST, ORIENTATION.UP);
        }
      }
    }
  }
  
  public Cube getCube(Vector3i p) {
    return world[p.x][p.y][p.z];
  }
}
