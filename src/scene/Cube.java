package scene;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glNormal3f;
import static org.lwjgl.opengl.GL11.glVertex3f;

import org.lwjgl.util.vector.Vector3f;

public class Cube implements Node {
  private Vector3f V[];
  private static float D = 0.5f; 
  
  public Cube() {
    V = new Vector3f[8];
    V[0] = new Vector3f(-D, -D , -D);
    V[1] = new Vector3f(-D, D, -D);
    V[2] = new Vector3f(D, D , -D);
    V[3] = new Vector3f(D, -D , -D);
    V[4] = new Vector3f(-D, -D , D);
    V[5] = new Vector3f(-D, D , D);
    V[6] = new Vector3f(D, D , D);
    V[7] = new Vector3f(D, -D , D);
  }
  
  @Override
  public void draw() {
    glBegin(GL_QUADS);
    // Face 1:
    glNormal3f(0, 0, 1f);
    glVertex3f(V[0].x, V[0].y, V[0].z);
    glVertex3f(V[1].x, V[1].y, V[1].z);
    glVertex3f(V[2].x, V[2].y, V[2].z);
    glVertex3f(V[3].x, V[3].y, V[3].z);
    
    // Face 2:
    glNormal3f(0, 0, -1f);
    glVertex3f(V[7].x, V[7].y, V[7].z);
    glVertex3f(V[6].x, V[6].y, V[6].z);
    glVertex3f(V[5].x, V[5].y, V[5].z);
    glVertex3f(V[4].x, V[4].y, V[4].z);
    
    // Face 3:
    glNormal3f(-1f, 0, 0);
    glVertex3f(V[0].x, V[0].y, V[0].z);
    glVertex3f(V[4].x, V[4].y, V[4].z);
    glVertex3f(V[5].x, V[5].y, V[5].z);
    glVertex3f(V[1].x, V[1].y, V[1].z);

    // Face 4:
    glNormal3f(0, 1f, 0);
    glVertex3f(V[1].x, V[1].y, V[1].z);
    glVertex3f(V[5].x, V[5].y, V[5].z);
    glVertex3f(V[6].x, V[6].y, V[6].z);
    glVertex3f(V[2].x, V[2].y, V[2].z);

    // Face 5:
    glNormal3f(0, -1f, 0);
    glVertex3f(V[3].x, V[3].y, V[3].z);
    glVertex3f(V[7].x, V[7].y, V[7].z);
    glVertex3f(V[4].x, V[4].y, V[4].z);
    glVertex3f(V[0].x, V[0].y, V[0].z);
   
    // Face 6:
    glNormal3f(1f, 0, 0);
    glVertex3f(V[2].x, V[2].y, V[2].z);
    glVertex3f(V[6].x, V[6].y, V[6].z);
    glVertex3f(V[7].x, V[7].y, V[7].z);
    glVertex3f(V[3].x, V[3].y, V[3].z);

    glEnd();

  }

}
