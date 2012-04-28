package util;

/**
 * Holds a triple of integers. Obviously.
 * @author Brian Holland
 *
 */
public class Vector3i {
  public int x;
  public int y;
  public int z;
  
  public Vector3i() {
    x = y = z = 0;
  }
  
  public Vector3i(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  @Override
  public boolean equals(Object o) {
    if (o == null) {
      return false;
    } else if (o.getClass() != this.getClass()) {
      return false;
    }
    
    Vector3i v = (Vector3i)o;
    
    if ((v.x == this.x) && (v.y == this.y) && (v.z == this.z)) {
      return true;
    } else {
      return false;
    }
  }
  
  @Override
  public String toString() {
    return "x: " + this.x + " y: " + this.y + " z: " + this.z;
  }
  
  public Vector3i clone() {
    return new Vector3i(this.x, this.y, this.z);
  }
}
