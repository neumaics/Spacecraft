package scene;

import scene.Cube.DIRECTION;
import scene.Cube.ORDER;
import scene.Cube.ORIENTATION;
import util.Vector3i;

public class SimpleSceneImpl extends Model {
	private Cube[][][] world;
	
	public SimpleSceneImpl() {
		this(20,20,20);
	}
	private SimpleSceneImpl(int l, int w, int h) {
		super(l, w, h);
	    world = new Cube[this.length][][];
	    for (int i = 0; i < this.length; ++i) {
	      world[i] = new Cube[this.height][];
	      for (int j = 0; j < this.height; ++j) {
	        world[i][j] = new Cube[this.width];
	        for (int k = 0; k < this.width; ++k) {
	          if (j >= height/2)
	            world[i][j][k] = new Cube(ORDER.Z, DIRECTION.EAST, ORIENTATION.UP);
	          else 
	            world[i][j][k] = new Cube(ORDER.A, DIRECTION.SOUTH_WEST, ORIENTATION.UP);
	        }
	      }
	    }
	    
	    generateScene();
	  }
	  
	  public void generateScene() {
		  int ground = height/2;
		  world[0][ground][0] = new Cube(ORDER.A, DIRECTION.SOUTH_WEST, ORIENTATION.UP); 
	  }
	
	  public Cube getCube(Vector3i p) {
	    return world[p.x][p.y][p.z];
	  }

}
