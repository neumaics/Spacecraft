package scene;

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
  
  public Model(int l, int w, int h) {
    this.length = l;
    this.width = w;
    this.height = h;
  }

  public abstract Cube getCube(Vector3i p);

public Light getLightSource() {
	return lightSource;
}

public void setLightSource(Light lightSource) {
	this.lightSource = lightSource;
}

}
