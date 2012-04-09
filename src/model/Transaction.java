package model;


/**
 * A Transaction is one atomic operation on a world. It is a collection of smaller operations
 * on the world. A Transaction must be completed once it has started, otherwise it will be rolled
 * back to avoid leaving the world in an invalid state.
 * 
 * @author Mitch
 *
 */
public interface Transaction {
  
  public void add( Operation operation );
  
}
