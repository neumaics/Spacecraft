package scene;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class should be the collection of the objects visible in the world at any given time. It 
 * includes:
 * 
 * Things the player can see
 * Things near the player that might be seen soon
 * 
 * But does NOT include
 * 
 * Things the player won't see for a while (something on the other side of a planet)
 * 
 * Basically, if the player can see it right now (or soon, if the player turns around, for example,
 * it should be somewhere in the Scene
 * 
 * @author Mitch
 *
 */
public class Scene {
  private Collection<Node> scene_objects;
  public Scene()
  {
    scene_objects = new ArrayList<Node>();
  }
  
  public void attach( Node node )
  {
    scene_objects.add( node );
  }
  
  public void draw(){
    for (Node n : scene_objects) {
      n.draw();
    }
  }
}
