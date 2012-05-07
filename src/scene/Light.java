package scene;

import org.lwjgl.util.Color;
import org.lwjgl.util.vector.Vector4f;

public class Light implements Drawable{
  
  private Color color;
  private Vector4f position;
  private float time = 0;
  
  public Light(Color color, Vector4f position) {
    super();
    this.color = color;
    this.position = position;
    
  }
  
  public Light(int r, int g, int b, int a, float x, float y, float z) {
    super();
    color = new Color(r, g, b);
    position = new Vector4f(x, y, z,0);
  }
  
  public Light() {
    color = new Color(1, 1, 1);
    position = new Vector4f(0, 0, 0,0);
  }
  public void draw() {
	 time=(float)(time+.005)%360;
	 
	 position.x=(float)Math.sin(Math.toRadians(time));
	 position.y=(float)Math.sin(Math.toRadians(time));
  }

public Color getColor() {
	return color;
}

public void setColor(Color color) {
	this.color = color;
}

public Vector4f getPosition() {
	return position;
}

public void setPosition(Vector4f position) {
	this.position = position;
}

public float getTime() {
	return time;
}

public void setTime(float time) {
	this.time = time;
}
  
  
}
