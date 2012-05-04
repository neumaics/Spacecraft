package game;

import java.util.Random;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import entities.Player;
import scene.*;
import scene.Cube.DIRECTION;
import scene.Cube.ORDER;
import scene.Cube.ORIENTATION;
import util.Vector3i;
import static org.lwjgl.opengl.GL11.*;

public class Renderer {
  private Scene scene;
  private Vector3f V[];
  private static float D = 1f;
  
  public Renderer(){
    initialize();
  }
  
  private void initialize() {
    scene = new Scene();
    scene.attach(new LegacyCube());
    
    V = new Vector3f[8];
    V[0] = new Vector3f(0, 0, 0);
    V[1] = new Vector3f(0, D, 0);
    V[2] = new Vector3f(D, D, 0);
    V[3] = new Vector3f(D, 0, 0);
    V[4] = new Vector3f(0, 0, D);
    V[5] = new Vector3f(0, D, D);
    V[6] = new Vector3f(D, D, D);
    V[7] = new Vector3f(D, 0, D);
  }
  
  public void drawScene() {
//    scene.draw();
  }
  
  public void render(Model m, Player p){
	glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);
	Vector3i position;
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();
    
    Vector3f playerPosition = p.getPosition();
    
    //2 rotations
    // x axis
    glRotatef(-p.getPitch(),1f,0f,0f);
    // y axis
    glRotatef(p.getDirection(),0f,1f,0f);
    
  
    glScalef(2f/m.length, 2f/m.height, 2f/m.width);
    glTranslatef(-playerPosition.x, -playerPosition.y-.5f, -playerPosition.z);
    
    for (int i = 0; i < m.length; i++) {
      for (int j = 0; j < m.height; j++) {
        for (int k = 0; k < m.width; k++) {
          position = new Vector3i(j, k, i);
          drawCube(m.getCube(position), position);
        }
      }
    }
    
    
//    GL11.glTranslatef(-1, -1, -1);
  }
  
  public void drawCube(Cube cube, Vector3i position) {
    glPushMatrix();
    glTranslatef(position.x, position.y, position.z);
//    glScalef(.1f,.1f,.1f);
    glBegin(GL_QUADS);
      
    
    switch(cube.o) {
    case A:
      
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
      break;
    case B:        
      break;
    case C:
      break;
    case D:        
      break;
    case Z:
      //do nothing. It's a void cube.
      break;
    }

    glEnd();
    glPopMatrix();
  }
  
//  public void rotateCube(Cube)
}
