package scene;

import java.util.List;
import java.util.Map;

import util.Rectangle;
import util.Triangle;
import util.Vector3i;

/**
 * This abstract class is an expression of the world, a subsection of which will 
 * be drawn as the scene.
 * 
 * @author Brian Holland
 *
 */

public abstract class Model {
  public final int length;
  public final int width;
  public final int height;
  protected Light lightSource;
  
  protected Map<Cube,List<Rectangle>> rectangleMap;
  protected Map<Cube,List<Triangle>> triangleMap;
  
  public Model(int l, int w, int h) {
    this.length = l;
    this.width = w;
    this.height = h;
    lightSource = new Light(1, 1, 1, 1, 0, 1, 0);
  }

  public Map<Cube, List<Rectangle>> getRectangleMap() {
	return rectangleMap;
  }



public void setRectangleMap(Map<Cube, List<Rectangle>> rectangleMap) {
	this.rectangleMap = rectangleMap;
}



public Map<Cube, List<Triangle>> getTriangleMap() {
	return triangleMap;
}



public void setTriangleMap(Map<Cube, List<Triangle>> triangleMap) {
	this.triangleMap = triangleMap;
}



public abstract Cube getCube(Vector3i p);

public Light getLightSource() {
	return lightSource;
}

public void setLightSource(Light lightSource) {
	this.lightSource = lightSource;
}

}
