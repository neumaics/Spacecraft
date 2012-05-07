package scene;

import org.lwjgl.util.Color;
import org.lwjgl.util.vector.Vector4f;

public class Light implements Drawable{
  
  private Color color;
  private float[] position;
  public final float[] light_ambient = { .2f, .2f, .2f, 1.0f };
  public final float[] light_diffuse = { 1f, 1f, 1f, 1.0f };
  public final float[] light_specular = { 0, 0, 0, 1.0f };
  
  public final int X = 0;
  public final int Y = 1;
  public final int Z = 2;
  public final int W = 3;
  
  private float time = 0;
  
  public Light(Color color, float[] position) {
    super();
    this.color = color;
    this.position = position;
    
  }
  
  public Light(int r, int g, int b, int a, float x, float y, float z) {
    super();
    color = new Color(r, g, b);
    position = new float[4];
    position[X] = x;
    position[Y] = y;
    position[Z] = z;
    position[W] = 0;
  }
  
  public Light() {
    color = new Color(1, 1, 1);
    position = new float[4];
    position[X] = position[Y] = position[Z] = position[W] = 0;
  }
  public void draw() {
	 time=(float)(time )%360;
	 
	 position[X] =(float)Math.sin(Math.toRadians(time));
	 position[Y] =(float)Math.cos(Math.toRadians(time));
	 
//	 System.out.println(time)
  }

public Color getColor() {
	return color;
}

public void setColor(Color color) {
	this.color = color;
}

public float[] getPosition() {
	return position;
}

public void setPosition(float[] position) {
	this.position = position;
}

public float getTime() {
	return time;
}

public void setTime(float time) {
	this.time = time;
}
  
  
}
