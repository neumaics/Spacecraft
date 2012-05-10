package scene;

import static org.lwjgl.opengl.GL11.GL_AMBIENT;
import static org.lwjgl.opengl.GL11.GL_CONSTANT_ATTENUATION;
import static org.lwjgl.opengl.GL11.GL_DIFFUSE;
import static org.lwjgl.opengl.GL11.GL_LIGHT0;
import static org.lwjgl.opengl.GL11.GL_LIGHTING;
import static org.lwjgl.opengl.GL11.GL_LINEAR_ATTENUATION;
import static org.lwjgl.opengl.GL11.GL_POSITION;
import static org.lwjgl.opengl.GL11.GL_QUADRATIC_ATTENUATION;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLight;
import static org.lwjgl.opengl.GL11.glLightf;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import org.lwjgl.util.Color;
import org.lwjgl.util.vector.Vector4f;

import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Light implements Drawable {

  private Color color;
  private float[] position;
  public final float[] light_ambient = { .4f, .4f, .4f, 1.0f };
  public final float[] light_diffuse = { 1f, 1f, 1f, 1.0f };
  public final float[] light_specular = { 0, 0, 0, 1.0f };

  public final int X = 0;
  public final int Y = 1;
  public final int Z = 2;
  public final int W = 3;

  private float time = 0;

  private ByteBuffer temporary = ByteBuffer.allocateDirect(16);

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
    
    initialize();
  }

  public Light() {
    color = new Color(1, 1, 1);
    position = new float[4];
    position[X] = position[Y] = position[Z] = position[W] = 1;
    
    initialize();
  }

  public void draw() {
    time = (float) (time) % 360;

    position[X] = (float) Math.sin(Math.toRadians(time));
    position[Y] = (float) Math.cos(Math.toRadians(time));

    // System.out.println(time)
  }
  
  public void initialize() {
    temporary.order(ByteOrder.nativeOrder());
    glLight(GL_LIGHT0, GL_AMBIENT, (FloatBuffer)temporary.asFloatBuffer().put(this.light_ambient).flip());
    glLight(GL_LIGHT0, GL_DIFFUSE, (FloatBuffer)temporary.asFloatBuffer().put(this.light_diffuse).flip());
    glLight(GL_LIGHT0, GL_POSITION, (FloatBuffer)temporary.asFloatBuffer().put(this.position).flip());
    
    glEnable(GL_LIGHT0);
    glLightf(GL_LIGHT0, GL_CONSTANT_ATTENUATION, 1.0f);
    glLightf(GL_LIGHT0, GL_LINEAR_ATTENUATION, 0.1f);
    glLightf(GL_LIGHT0, GL_QUADRATIC_ATTENUATION, 0.0f);
    
    glEnable(GL_LIGHTING);
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
