package demo;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import viewer.Viewer;


public class LWJGLDemo {
  // Assorted constants
  public static final int WINDOW_WIDTH = 800;
  public static final int WINDOW_HEIGHT = 600;
  public static final String WINDOW_NAME = "Spacecraft";
  public static final int FLOAT_BYTES = 16 * 4;
  public static final float DEGTORAD = (float) (Math.PI / 180);
  public static final float RADTODEG = 1 / DEGTORAD;
  public static final int WHEEL_CONST = 120;
  public static boolean HasWheel;

  // Rotation Matrixes and corresponding buffers.
  public static Matrix4f R;
  public static Matrix4f R0;
  public static FloatBuffer RBuff = ByteBuffer.allocateDirect(FLOAT_BYTES)
  .order(ByteOrder.nativeOrder()).asFloatBuffer();
  public static FloatBuffer R0Buff = ByteBuffer.allocateDirect(FLOAT_BYTES)
  .order(ByteOrder.nativeOrder()).asFloatBuffer();
  
  // Rotation variables.
  public static int X = 0;
  public static int Y = 0;
  public static int lastX;
  public static int lastY;
  public static double sX;
  public static double sY;
  private static float angle;
  private static Vector3f axis;
  private static boolean stateChanged = false;

  private static Vector3f p;
  private static Vector3f q;
  
  
  // View Configuration
  public static float d = 10f;
  public static float s = 2f;
  private static ByteBuffer temp = ByteBuffer.allocateDirect(FLOAT_BYTES);
  private static float fov = 20;
  public static Cube cube;  
  
  public static void main(String[] args) {
    Viewer viewer = new Viewer.Builder( WINDOW_NAME ).width( WINDOW_WIDTH ).height( WINDOW_HEIGHT ).build();
    initVariables();
    initProjection();
    initLightsource();
 
    
    cube = new Cube();
    
    start();
  }
  
  public static void initVariables() {
    HasWheel = Mouse.hasWheel();
    axis = new Vector3f();
    p = new Vector3f();
    q = new Vector3f();
    X = 0;
    Y = 0;
    R = new Matrix4f();
    R0 = new Matrix4f();

    R.setIdentity();
    R0.setIdentity();
  }
  
  public static void initProjection() {
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    
    if (fov < 0)
      fov = 0;
    if (fov > 180)
      fov = 180;
    gluPerspective(fov, 1, (d - 1), (d + 3));
  }
  
  public static void initLightsource() {

    float light_ambient[] = { .2f, .2f, .2f, 1.0f };
    float light_diffuse[] = { 1f, 1f, 1f, 1.0f };
    float light_specular[] = { 0, 0, 0, 1.0f };
    float light_position[] = { 2.0f, 2.0f, 2.0f, 1 };

    temp.order(ByteOrder.nativeOrder());

    glLight(GL_LIGHT0, GL_AMBIENT,
        (FloatBuffer) temp.asFloatBuffer().put(light_ambient).flip());
    glLightModel(GL_LIGHT_MODEL_AMBIENT, (FloatBuffer) temp.asFloatBuffer()
        .put(light_ambient).flip());
    glLight(GL_LIGHT0, GL_DIFFUSE,
        (FloatBuffer) temp.asFloatBuffer().put(light_diffuse).flip());
    glLight(GL_LIGHT0, GL_SPECULAR,
        (FloatBuffer) temp.asFloatBuffer().put(light_specular).flip());
    glLight(GL_LIGHT0, GL_POSITION,
        (FloatBuffer) temp.asFloatBuffer().put(light_position).flip());

    glLightf(GL_LIGHT0, GL_CONSTANT_ATTENUATION, 1.0f);
    glLightf(GL_LIGHT0, GL_LINEAR_ATTENUATION, 0.0f);
    glLightf(GL_LIGHT0, GL_QUADRATIC_ATTENUATION, 0.0f);

    glEnable(GL_LIGHTING);
    glEnable(GL_LIGHT0);
  }
  
  public static void start(){
    while (!Display.isCloseRequested()) {
      render();
      Display.update();
    }
    
    Display.destroy();
  }
  
  private static void render() {
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    glPushMatrix();
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();

    glShadeModel(GL_FLAT);

   
    // Rotate the mesh.
    R.store(RBuff);
    R0.store(R0Buff);

    R0Buff.rewind();
    RBuff.rewind();


    glTranslatef(0, 0, -(d + 1));

    pollMouse();
    pollScrollWheel();

    glMultMatrix(RBuff);
    glMultMatrix(R0Buff);
    glScalef(s, s, s);
    
    //processSceneWithShadows();

    cube.draw();
    
    RBuff.rewind();
    R0Buff.rewind();

    glFlush();

    glPopMatrix();
  }

  public static void pollMouse(){
    //if (Mouse.isInsideWindow()) {

    {
      boolean leftButtonDown = Mouse.isButtonDown(0);
      boolean rightButtonDown = Mouse.isButtonDown(1);

      Matrix4f Rp = new Matrix4f();
      Matrix4f temp = new Matrix4f();
      temp.setIdentity();
      Rp.setIdentity();

      while (Mouse.next()) {
        if (leftButtonDown || rightButtonDown) {
          int dX = Mouse.getEventDX();
          int dY = Mouse.getEventDY();
          X = Mouse.getEventX();
          Y = Mouse.getEventY();
          //System.out.println("X: " + X + " Y: " + Y);
          if (leftButtonDown) {
            if ((dX != 0) || (dY != 0)) {
              rotateModel(lastX, lastY, X, Y);
              stateChanged = true;
            }
          } else if (rightButtonDown){
            stateChanged = true;
          }
          
        }

        lastX = X;
        lastY = Y;
      }

      if (stateChanged) {
        Rp.rotate(angle, axis);

        Matrix4f.mul(Rp, R, R);
        R0.setIdentity();
        stateChanged = false;
      }
    }
  }
  
  public static void pollScrollWheel() {
    // Check change in scroll wheel state.

    if (Mouse.isInsideWindow()) {
      int scroll = Mouse.getDWheel();
      if ((scroll != 0) && (HasWheel)) {
        fov += scroll / WHEEL_CONST;

        initProjection();
        glMatrixMode(GL_MODELVIEW);
      }
    }
  }
  
  private static void rotateModel(int i0, int j0, int i, int j) {
    sX = ((2.0 * i0) / (WINDOW_WIDTH - 1.0)) - 1.0;
    sY = 1.0 - ((2.0 * j0) / (WINDOW_HEIGHT - 1.0));

    double b = (1 - (sX * sX) - (sY * sY));
    double n = Math.sqrt((sX * sX) + (sY * sY));

    if (b >= 0) {
      p.x = (float) sX;
      p.y = (float) sY;
      p.z = (float) Math.sqrt(b);
    } else {
      p.x = (float) (sX / n);
      p.y = (float) (sY / n);
      p.z = 0;
    }

    sX = ((2.0 * i) / (WINDOW_WIDTH - 1.0)) - 1.0;
    sY = 1.0 - ((2.0 * j) / (WINDOW_HEIGHT - 1.0));

    b = (float) (1.0 - (sX * sX) - (sY * sY));
    n = (float) Math.sqrt((sX * sX) + (sY * sY));

    if (b >= 0) {
      q.x = (float) sX;
      q.y = (float) sY;
      q.z = (float) Math.sqrt(b);
    } else {
      q.x = (float) (sX / n);
      q.y = (float) (sY / n);
      q.z = 0;
    }

    Vector3f.cross(p, q, axis);

    try{
    axis.normalise();
    } catch (Exception e) {
      
    }
    axis.x = -axis.x;
    axis.z = -axis.z;

    p.normalise();
    q.normalise();

    angle = (float) (Math.acos(Vector3f.dot(p, q)));

    Matrix4f MvM = new Matrix4f();

    MvM.rotate(angle * RADTODEG, axis);
    R0.setIdentity();
    Matrix4f.mul(MvM, R0, R0);
  }
}
