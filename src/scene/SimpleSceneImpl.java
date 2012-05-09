package scene;

import java.util.HashMap;
import java.util.List;

import scene.Cube.DIRECTION;
import scene.Cube.ORDER;
import scene.Cube.ORIENTATION;
import util.Rectangle;
import util.Triangle;
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
//	    createShapeMaps();
	  }
	  
	  public void generateScene() {
		  int ground = height/2;
		  for(int i = 0;i<2;i++) {
			  for(int j = 0;j<2;j++) {
				  world[i][ground+1][j] = new Cube(ORDER.A, DIRECTION.SOUTH_WEST, ORIENTATION.UP); 
			  }
		  }
		  
		  for(int i = 0;i<4;i++) {
			  for(int j = 0;j<5;j++) {
				  world[i][ground][j] = new Cube(ORDER.A, DIRECTION.SOUTH_WEST, ORIENTATION.UP); 
			  }
		  }
		  world[2][ground+1][0] = new Cube(ORDER.C,DIRECTION.NORTH,ORIENTATION.UP);
		  world[2][ground+1][1] = new Cube(ORDER.C,DIRECTION.NORTH,ORIENTATION.UP);
		  world[2][ground+1][2] = new Cube(ORDER.D,DIRECTION.NORTH_EAST,ORIENTATION.UP);
		  world[0][ground+1][2] = new Cube(ORDER.A,DIRECTION.NORTH_EAST,ORIENTATION.UP);
		  world[1][ground+1][2] = new Cube(ORDER.B,DIRECTION.NORTH_EAST,ORIENTATION.UP);
		  world[1][ground+1][3] = new Cube(ORDER.C,DIRECTION.NORTH,ORIENTATION.UP);
		  world[0][ground+1][3] = new Cube(ORDER.A,DIRECTION.NORTH,ORIENTATION.UP);
		  world[1][ground+1][4] = new Cube(ORDER.D,DIRECTION.NORTH_EAST,ORIENTATION.UP);
		  world[0][ground+1][4] = new Cube(ORDER.C,DIRECTION.EAST,ORIENTATION.UP);

		  for(int i = 0;i<3;i++) {
			  world[i][ground][5] = new Cube(ORDER.C,DIRECTION.EAST,ORIENTATION.UP);
		  }
		  
		  world[3][ground][5] = new Cube(ORDER.D,DIRECTION.NORTH_EAST,ORIENTATION.UP);
		  world[3][ground][4] = new Cube(ORDER.B,DIRECTION.NORTH_EAST,ORIENTATION.UP);
		  
		  world[4][ground][4] = new Cube(ORDER.D,DIRECTION.NORTH_EAST,ORIENTATION.UP);
		  
		  for(int i = 0;i<4;i++) {
			  world[4][ground][i]=new Cube(ORDER.C,DIRECTION.NORTH,ORIENTATION.UP);
		  }
		  
		  world[15][ground+2][16] = new Cube(ORDER.D,DIRECTION.NORTH_EAST,ORIENTATION.DOWN);
		  world[15][ground+2][15] = new Cube(ORDER.B,DIRECTION.NORTH_EAST,ORIENTATION.DOWN);
		  world[16][ground+2][15] = new Cube(ORDER.D,DIRECTION.NORTH_EAST,ORIENTATION.DOWN);
		  world[15][ground+1][15] = new Cube(ORDER.C,DIRECTION.NORTH_EAST,ORIENTATION.NEUTRAL);
	  }
	
	  public Cube getCube(Vector3i p) {
	    return world[p.x][p.y][p.z];
	  }


	  private void createShapeMaps() {
		  rectangleMap = new HashMap<Cube, List<Rectangle>>();
		  triangleMap = new HashMap<Cube, List<Triangle>>();
		  
		  //Algorithm for placing lists of rectangles with each 
		  
		  for(int i = 0;i<this.length;i++) {
			  for(int j = 0;j<this.height;j++) {
				  for(int k = 0;k<this.width;k++) {
					  
				  }
			  }
		  }
	  }
	  
	  private List<Rectangle> getRectangles(Cube c) {
		 return null;
	  }
	  
	  private List<Triangle> getTriangles(Cube c) {
		  return null;
	  }
	  
	  
	  

}
