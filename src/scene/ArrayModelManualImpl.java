package scene;

import scene.Cube.DIRECTION;
import scene.Cube.ORDER;
import scene.Cube.ORIENTATION;
import util.Vector3i;

public class ArrayModelManualImpl extends Model {

  private Cube[][][] world;

  public ArrayModelManualImpl(int l, int w, int h) {
    super(l, w, h);

    int lradius = l / 2;
    int wradius = w / 2;

    world = new Cube[this.length][][];
    for (int i = 0; i < this.length; ++i) {
      world[i] = new Cube[this.height][];
      for (int j = 0; j < this.height; ++j) {
        world[i][j] = new Cube[this.width];
        for (int k = 0; k < this.width; ++k) {
          // if (j >= height/2)
          // world[i][j][k] = new Cube(ORDER.Z, DIRECTION.EAST, ORIENTATION.UP);
          // else
          if (Math.sqrt((i) * (j)) < lradius) {
            world[i][j][k] = new Cube(ORDER.A, DIRECTION.SOUTH_EAST, ORIENTATION.UP);
          } else {
            world[i][j][k] = new Cube(ORDER.Z, DIRECTION.EAST, ORIENTATION.UP);
          }

        }
      }
    }
  }

  @Override
  public Cube getCube(Vector3i p) {
    return world[p.z][p.x][p.y];
  }

}
