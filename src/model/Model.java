package model;

import scene.Scene;

/**
 * The Model is the representation of the world/environment. It contains the whole of the world as,
 * effectively, the master copy of the environment. Areas that players can change are subsets of the 
 * Model which are called Scenes. The Model must have a way to resolve differences between
 * its representation of the world and the cumulative effects of possibly many players operating on
 * overlapping scenes.
 * 
 * The focus of a good Model should be fast indexing and high density - a world file should
 * be as small and fast as possible. One major duty of the Model is to return, on demand, a Scene
 * of a given size based on a set of coordinates in the world. It is this Scene that will be
 * given to the player's client for rendering.
 * 
 * The Model itself should not be concerned with permissions or business logic. It is only a 
 * representation of the environment itself. In the interest of quick indexing and modification,
 * the Model enforces very few constraints of the world, most notably the constraint that two
 * environment objects (cubes or other primitives) cannot occupy the same space.
 * 
 * The Model has no conception of time.
 */
public interface Model {
  
  public Model model();
  
  /**
   * Given a position in the world, return the Scene composing the "size" number of units
   * visible/possibly visible environment objects.
   * @param x - x position
   * @param y - y position
   * @param z - z position
   * @param size - the 'radius' of the Scene area. 'Radius' is in quotes because the Scene may or 
   * may not have a spherical shape.
   * @return
   */
  public Scene getScene( double x, double y, double z, int size );
  
  /**
   * Resolves a Transaction with the Model. The end result should be a properly resolved Model, with
   * the transaction's changes implemented in the Model. 
   * @param transaction
   */
  public void postTransaction( Transaction transaction );
  

}
