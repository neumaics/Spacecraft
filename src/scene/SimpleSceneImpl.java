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
	          if (j >= height/2 || j < height/2 - 1 )
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
		  for(int i = 0;i<2;i++) {
			  for(int j = 0;j<2;j++) {
				  world[i][ground][j] = new Cube(ORDER.A, DIRECTION.SOUTH_WEST, ORIENTATION.UP); 
			  }
		  }
		  world[2][ground][0] = new Cube(ORDER.C,DIRECTION.NORTH,ORIENTATION.UP);
		  world[2][ground][1] = new Cube(ORDER.C,DIRECTION.NORTH,ORIENTATION.UP);
		  world[2][ground][2] = new Cube(ORDER.D,DIRECTION.NORTH_EAST,ORIENTATION.UP);
		  world[0][ground][2] = new Cube(ORDER.A,DIRECTION.NORTH_EAST,ORIENTATION.UP);
		  world[1][ground][2] = new Cube(ORDER.B,DIRECTION.NORTH_EAST,ORIENTATION.UP);
		  world[1][ground][3] = new Cube(ORDER.C,DIRECTION.NORTH,ORIENTATION.UP);
		  world[0][ground][3] = new Cube(ORDER.A,DIRECTION.NORTH,ORIENTATION.UP);
		  world[1][ground][4] = new Cube(ORDER.D,DIRECTION.NORTH_EAST,ORIENTATION.UP);
		  world[0][ground][4] = new Cube(ORDER.C,DIRECTION.EAST,ORIENTATION.UP);

		  world[15][ground][16] = new Cube(ORDER.D,DIRECTION.NORTH_EAST,ORIENTATION.UP);
		  world[15][ground][15] = new Cube(ORDER.B,DIRECTION.NORTH_EAST,ORIENTATION.UP);
		  world[16][ground][15] = new Cube(ORDER.D,DIRECTION.NORTH_EAST,ORIENTATION.UP);
	  
	  }
	
	  public Cube getCube(Vector3i p) {
	    return world[p.x][p.y][p.z];
	  }

}
