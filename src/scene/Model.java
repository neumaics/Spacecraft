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

  public abstract Cube getCube(Vector3i p);
}
