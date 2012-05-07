package game;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import entities.Player;
import scene.*;
import util.Vector3i;
import static org.lwjgl.opengl.GL11.*;

public class Renderer {
  private Scene scene;
  private Vector3f V[];
  private static float D = 1f;
  private Matrix4f modelviewmatrix;
  private FloatBuffer MvMBuffer = ByteBuffer.allocateDirect(64).order(ByteOrder.nativeOrder()).asFloatBuffer();
  private Matrix4f inversemodelviewmatrix;
  
  
  /* Lighting variables and buffer */
  private Light light;
  private ByteBuffer temporary = ByteBuffer.allocateDirect(16);
  private float[] lightposition; 
  
  public Renderer(){
    initialize();
  }
  
  private void initialize() {
    scene = new Scene();
    scene.attach(new LegacyCube());
    
    inversemodelviewmatrix = new Matrix4f();
    modelviewmatrix = new Matrix4f();
    V = new Vector3f[8];
    V[0] = new Vector3f(0, 0, 0);
    V[1] = new Vector3f(0, D, 0);
    V[2] = new Vector3f(D, D, 0);
    V[3] = new Vector3f(D, 0, 0);
    V[4] = new Vector3f(0, 0, D);
    V[5] = new Vector3f(0, D, D);
    V[6] = new Vector3f(D, D, D);
    V[7] = new Vector3f(D, 0, D);
    
    glEnable(GL_DEPTH_TEST);
    glShadeModel(GL_SMOOTH);
    glEnable(GL_NORMALIZE);
    glEnable(GL_COLOR_MATERIAL);
    glColorMaterial(GL_FRONT_AND_BACK,GL_AMBIENT_AND_DIFFUSE);
  }
  
  public void drawScene() {
//    scene.draw();
  }
  
  public void setLight(Model m) {
    light = m.getLightSource();
  }
  
  public void render(Model m, Player p) {
    setModelViewMatrix();
    
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    Vector3i position;
    drawLight(m.getLightSource());

    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();

    Vector3f playerPosition = p.getPosition();

    // 2 rotations
    // x axis
    glRotatef(-p.getPitch(),1f,0f,0f);
    // y axis
    glRotatef(p.getDirection(),0f,1f,0f);
    
    
    glScalef(2f/m.length, 2f/m.height, 2f/m.width);
    glTranslatef(-playerPosition.x, -playerPosition.y-1.75f, -playerPosition.z);
    
    
//    drawLight(m.getLightSource());
    
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
  //  glScalef(.1f,.1f,.1f);
    glColor3f(0,1f,0);
    switch(cube.o) {
    case A:
      
      glBegin(GL_QUADS);

      /*
        V[0] = new Vector3f(0, 0, 0);
    	V[1] = new Vector3f(0, D, 0);
    	V[2] = new Vector3f(D, D, 0);
    	V[3] = new Vector3f(D, 0, 0);
    	V[4] = new Vector3f(0, 0, D);
    	V[5] = new Vector3f(0, D, D);
    	V[6] = new Vector3f(D, D, D);
    	V[7] = new Vector3f(D, 0, D);
       */
      // West Face
      glNormal3f(0, 0, -1f);
      glVertex3f(V[0].x, V[0].y, V[0].z);
      glVertex3f(V[1].x, V[1].y, V[1].z);
      glVertex3f(V[2].x, V[2].y, V[2].z);
      glVertex3f(V[3].x, V[3].y, V[3].z);
     
      // East Face
      glNormal3f(0, 0, 1f);
      glVertex3f(V[7].x, V[7].y, V[7].z);
      glVertex3f(V[6].x, V[6].y, V[6].z);
      glVertex3f(V[5].x, V[5].y, V[5].z);
      glVertex3f(V[4].x, V[4].y, V[4].z);
      
      // North Face
      glNormal3f(1f, 0, 0);
      glVertex3f(V[2].x, V[2].y, V[2].z);
      glVertex3f(V[6].x, V[6].y, V[6].z);
      glVertex3f(V[7].x, V[7].y, V[7].z);
      glVertex3f(V[3].x, V[3].y, V[3].z);

      // South Face
      glNormal3f(-1f, 0, 0);
      glVertex3f(V[0].x, V[0].y, V[0].z);
      glVertex3f(V[4].x, V[4].y, V[4].z);
      glVertex3f(V[5].x, V[5].y, V[5].z);
      glVertex3f(V[1].x, V[1].y, V[1].z);

      // Top Face
      glNormal3f(0, 1f, 0);
      glVertex3f(V[1].x, V[1].y, V[1].z);
      glVertex3f(V[5].x, V[5].y, V[5].z);
      glVertex3f(V[6].x, V[6].y, V[6].z);
      glVertex3f(V[2].x, V[2].y, V[2].z);
     
      // Bottom Face
      glNormal3f(0, -1f, 0);
      glVertex3f(V[3].x, V[3].y, V[3].z);
      glVertex3f(V[7].x, V[7].y, V[7].z);
      glVertex3f(V[4].x, V[4].y, V[4].z);
      glVertex3f(V[0].x, V[0].y, V[0].z);
     
      glEnd();
      break;
    case B:   
//    	switch(cube.v) {
//    	case DOWN:
//    		float[] array = 	{
//    							1.0f,  0.0f, 0.0f, 1.0f,
//    							0.0f, -1.0f, 0.0f, 1.0f,
//    							0.0f,  0.0f, 1.0f, 1.0f,
//    							1.0f,  1.0f, 1.0f, 1.0f
//    							};
//    		ByteBuffer temp = ByteBuffer.allocateDirect(64);
//    		
//    		glMultMatrix((FloatBuffer) temp.asFloatBuffer().put(array).flip());
//    	}

    	switch(cube.d) {
    		case NORTH_WEST:
    			glTranslatef(D/2f,0,D/2f);
    			glRotatef(90,0,1f,0);
    			glTranslatef(-D/2f,0,-D/2f);
    			break;
    		case SOUTH_WEST:
    			glTranslatef(D/2f,0,D/2f);
    			glRotatef(180,0,1f,0);
    			glTranslatef(-D/2f,0,-D/2f);
    			break;
    		case SOUTH_EAST:
    			glTranslatef(D/2f,0,D/2f);
    			glRotatef(270,0,1f,0);
    			glTranslatef(-D/2f,0,-D/2f);
    	}
    		
    		
    		//Draws a Carved Cube with Up Orientation and North East 
    		glBegin(GL_QUADS);
    		      // Bottom Face
    			  glNormal3f(0, -1f, 0);
    		      glVertex3f(V[3].x, V[3].y, V[3].z);
    		      glVertex3f(V[7].x, V[7].y, V[7].z);
    		      glVertex3f(V[4].x, V[4].y, V[4].z);
    		      glVertex3f(V[0].x, V[0].y, V[0].z);
    		      
    		      // South Face
    		      glNormal3f(-1f, 0, 0);
    		      glVertex3f(V[0].x, V[0].y, V[0].z);
    		      glVertex3f(V[4].x, V[4].y, V[4].z);
    		      glVertex3f(V[5].x, V[5].y, V[5].z);
    		      glVertex3f(V[1].x, V[1].y, V[1].z);

    		      // West Face
    		      glNormal3f(0, 0, -1f);
    		      glVertex3f(V[0].x, V[0].y, V[0].z);
    		      glVertex3f(V[1].x, V[1].y, V[1].z);
    		      glVertex3f(V[2].x, V[2].y, V[2].z);
    		      glVertex3f(V[3].x, V[3].y, V[3].z);
  
    		    glEnd();
    			glBegin(GL_TRIANGLES);
    			
    			  // North Triangle
    			  glNormal3f(1f,0,0);
    		      glVertex3f(V[2].x, V[2].y, V[2].z);
    		      glVertex3f(V[7].x, V[7].y, V[7].z);
    		      glVertex3f(V[3].x, V[3].y, V[3].z);
    			  
    		      // East Triangle
    		      glNormal3f(0, 0, 1f);
    		      glVertex3f(V[7].x, V[7].y, V[7].z);
    		      glVertex3f(V[5].x, V[5].y, V[5].z);
    		      glVertex3f(V[4].x, V[4].y, V[4].z);
    			
    			  // Top Triangle
    		      glNormal3f(0, 1f, 0);
    		      glVertex3f(V[1].x, V[1].y, V[1].z);
    		      glVertex3f(V[5].x, V[5].y, V[5].z);
    		      glVertex3f(V[2].x, V[2].y, V[2].z);

    		      // Top North East Triangle
    		      glNormal3f(1f,1f,1f);
    		      glVertex3f(V[2].x, V[2].y, V[2].z);
    		      glVertex3f(V[5].x, V[5].y, V[5].z);
    		      glVertex3f(V[7].x, V[7].y, V[7].z);

    		    glEnd();
      break;
    case C:
    	switch(cube.v) {
    	case DOWN:
    		//TODO: Add Multiplication Matrix for Down
    	case UP:
        	switch(cube.d) {
        	case EAST:
    			glTranslatef(D/2,0,D/2);
        		glRotatef(270, 0, 1, 0);
    			glTranslatef(-D/2,0,-D/2);
        		break;
        	case WEST:
    			glTranslatef(D/2,0,D/2);
        		glRotatef(90,0,1,0);
    			glTranslatef(-D/2,0,-D/2);
        	case SOUTH:
    			glTranslatef(D/2,0,D/2);
        		glRotatef(180,0,1,0);
    			glTranslatef(-D/2,0,-D/2);
        	}
    	
        	//Draw Cube with North orientation
        	glBegin(GL_QUADS);

            // South Face
            glNormal3f(-1f, 0, 0);
            glVertex3f(V[0].x, V[0].y, V[0].z);
            glVertex3f(V[4].x, V[4].y, V[4].z);
            glVertex3f(V[5].x, V[5].y, V[5].z);
            glVertex3f(V[1].x, V[1].y, V[1].z);
           
            // Bottom Face
            glNormal3f(0, -1f, 0);
            glVertex3f(V[3].x, V[3].y, V[3].z);
            glVertex3f(V[7].x, V[7].y, V[7].z);
            glVertex3f(V[4].x, V[4].y, V[4].z);
            glVertex3f(V[0].x, V[0].y, V[0].z);

            //North Up Face
            glNormal3f(1f, 1f, 0);
            glVertex3f(V[3].x, V[3].y, V[3].z);
            glVertex3f(V[7].x, V[7].y, V[7].z);
            glVertex3f(V[5].x, V[5].y, V[5].z);
            glVertex3f(V[1].x, V[1].y, V[1].z);
       
        	glEnd();
        	glBegin(GL_TRIANGLES);
            // East TRIANGLE
            glNormal3f(0, 0, 1f);
            glVertex3f(V[7].x, V[7].y, V[7].z);
            glVertex3f(V[5].x, V[5].y, V[5].z);
            glVertex3f(V[4].x, V[4].y, V[4].z);
              	
		    // West Triangle
		    glNormal3f(0, 0, -1f);
		    glVertex3f(V[0].x, V[0].y, V[0].z);
		    glVertex3f(V[1].x, V[1].y, V[1].z);
		    glVertex3f(V[3].x, V[3].y, V[3].z);
        	
        	glEnd();
    	}
    	
      break;
    case D:        
    	switch(cube.d) {
		case NORTH_WEST:
			glTranslatef(D/2f,0,D/2f);
			glRotatef(90,0,1f,0);
			glTranslatef(-D/2f,0,-D/2f);
			break;
		case SOUTH_WEST:
			glTranslatef(D/2f,0,D/2f);
			glRotatef(180,0,1f,0);
			glTranslatef(-D/2f,0,-D/2f);
			break;
		case SOUTH_EAST:
			glTranslatef(D/2f,0,D/2f);
			glRotatef(270,0,1f,0);
			glTranslatef(-D/2f,0,-D/2f);
	}

    glBegin(GL_TRIANGLES);
    // Bottom Triangle
    
    // West Triangle
    glNormal3f(0, 0, -1f);
    glVertex3f(V[0].x, V[0].y, V[0].z);
    glVertex3f(V[1].x, V[1].y, V[1].z);
    glVertex3f(V[3].x, V[3].y, V[3].z);
    
    // South Triangle
    glNormal3f(-1f, 0, 0);
    glVertex3f(V[0].x, V[0].y, V[0].z);
    glVertex3f(V[4].x, V[4].y, V[4].z);
    glVertex3f(V[1].x, V[1].y, V[1].z);
    
    // Bottom Face
    glNormal3f(0, -1f, 0);
    glVertex3f(V[3].x, V[3].y, V[3].z);
    glVertex3f(V[4].x, V[4].y, V[4].z);
    glVertex3f(V[0].x, V[0].y, V[0].z);

    // Top North East Triangle
    glNormal3f(1f, 1f, 1f);
    glVertex3f(V[3].x, V[3].y, V[3].z);
    glVertex3f(V[1].x, V[1].y, V[1].z);
    glVertex3f(V[4].x, V[4].y, V[4].z);
        
    /*
		V[0] = new Vector3f(0, 0, 0);
		V[1] = new Vector3f(0, D, 0);
		V[2] = new Vector3f(D, D, 0);
    	V[3] = new Vector3f(D, 0, 0);
    	V[4] = new Vector3f(0, 0, D);
    	V[5] = new Vector3f(0, D, D);
    	V[6] = new Vector3f(D, D, D);
    	V[7] = new Vector3f(D, 0, D);
     */

    glEnd();
    	
   	break;
    case Z:
      //do nothing. It's a void cube.
      break;
    }

    glPopMatrix();
  }
  
  public void drawLight(Light light) {
 //   light.draw();
    
 //   glLight(GL_LIGHT0, GL_POSITION, (FloatBuffer)temporary.asFloatBuffer().put(light.getPosition()).flip());
  }
  
  public void setModelViewMatrix() {
    glGetFloat(GL_MODELVIEW_MATRIX, MvMBuffer);
    
    inversemodelviewmatrix.m00 = modelviewmatrix.m00 = MvMBuffer.get(0);
    inversemodelviewmatrix.m01 = modelviewmatrix.m01 = MvMBuffer.get(1);
    inversemodelviewmatrix.m02 = modelviewmatrix.m02 = MvMBuffer.get(2);
    inversemodelviewmatrix.m03 = modelviewmatrix.m03 = MvMBuffer.get(3);
    
    inversemodelviewmatrix.m10 = modelviewmatrix.m10 = MvMBuffer.get(4);
    inversemodelviewmatrix.m11 = modelviewmatrix.m11 = MvMBuffer.get(5);
    inversemodelviewmatrix.m12 = modelviewmatrix.m12 = MvMBuffer.get(6);
    inversemodelviewmatrix.m13 = modelviewmatrix.m13 = MvMBuffer.get(7);
    
    inversemodelviewmatrix.m20 = modelviewmatrix.m20 = MvMBuffer.get(8);
    inversemodelviewmatrix.m21 = modelviewmatrix.m21 = MvMBuffer.get(9);
    inversemodelviewmatrix.m22 = modelviewmatrix.m22 = MvMBuffer.get(10);
    inversemodelviewmatrix.m23 = modelviewmatrix.m23 = MvMBuffer.get(11);
    
    inversemodelviewmatrix.m30 = modelviewmatrix.m30 = MvMBuffer.get(12);
    inversemodelviewmatrix.m31 = modelviewmatrix.m31 = MvMBuffer.get(13);
    inversemodelviewmatrix.m32 = modelviewmatrix.m32 = MvMBuffer.get(14);
    inversemodelviewmatrix.m33 = modelviewmatrix.m33 = MvMBuffer.get(15);
    
    inversemodelviewmatrix.invert();
  }
  
  public void transformLightPosition() {
    
  }
//  public void rotateCube(Cube)
}

/*
 *    		case NORTH_EAST:
    			glBegin(GL_QUADS);
    		      // Bottom Face
    			  glNormal3f(0, -1f, 0);
    		      glVertex3f(V[3].x, V[3].y, V[3].z);
    		      glVertex3f(V[7].x, V[7].y, V[7].z);
    		      glVertex3f(V[4].x, V[4].y, V[4].z);
    		      glVertex3f(V[0].x, V[0].y, V[0].z);
    		      
    		      // South Face
    		      glNormal3f(-1f, 0, 0);
    		      glVertex3f(V[0].x, V[0].y, V[0].z);
    		      glVertex3f(V[4].x, V[4].y, V[4].z);
    		      glVertex3f(V[5].x, V[5].y, V[5].z);
    		      glVertex3f(V[1].x, V[1].y, V[1].z);

    		      // West Face
    		      glNormal3f(0, 0, -1f);
    		      glVertex3f(V[0].x, V[0].y, V[0].z);
    		      glVertex3f(V[1].x, V[1].y, V[1].z);
    		      glVertex3f(V[2].x, V[2].y, V[2].z);
    		      glVertex3f(V[3].x, V[3].y, V[3].z);
  
    		    glEnd();
    			glBegin(GL_TRIANGLES);
    			
    			  // North Triangle
    			  glNormal3f(1f,0,0);
    		      glVertex3f(V[2].x, V[2].y, V[2].z);
    		      glVertex3f(V[7].x, V[7].y, V[7].z);
    		      glVertex3f(V[3].x, V[3].y, V[3].z);
    			  
    		      // East Triangle
    		      glNormal3f(0, 0, 1f);
    		      glVertex3f(V[7].x, V[7].y, V[7].z);
    		      glVertex3f(V[5].x, V[5].y, V[5].z);
    		      glVertex3f(V[4].x, V[4].y, V[4].z);
    			
    			  // Top Triangle
    		      glNormal3f(0, 1f, 0);
    		      glVertex3f(V[1].x, V[1].y, V[1].z);
    		      glVertex3f(V[5].x, V[5].y, V[5].z);
    		      glVertex3f(V[2].x, V[2].y, V[2].z);

    		      // Top North East Triangle
    		      glNormal3f(1f,1f,1f);
    		      glVertex3f(V[2].x, V[2].y, V[2].z);
    		      glVertex3f(V[5].x, V[5].y, V[5].z);
    		      glVertex3f(V[7].x, V[7].y, V[7].z);

    		    glEnd();
    			break;
    		case NORTH_WEST:
    			glBegin(GL_QUADS);
	  		      // Bottom Face
	  			  glNormal3f(0, -1f, 0);
	  		      glVertex3f(V[3].x, V[3].y, V[3].z);
	  		      glVertex3f(V[7].x, V[7].y, V[7].z);
	  		      glVertex3f(V[4].x, V[4].y, V[4].z);
	  		      glVertex3f(V[0].x, V[0].y, V[0].z);
	  		      
	  		      // South Face
	  		      glNormal3f(-1f, 0, 0);
	  		      glVertex3f(V[0].x, V[0].y, V[0].z);
	  		      glVertex3f(V[4].x, V[4].y, V[4].z);
	  		      glVertex3f(V[5].x, V[5].y, V[5].z);
	  		      glVertex3f(V[1].x, V[1].y, V[1].z);
	
	  		      // East Face
	  		      glNormal3f(0, 0, 1f);
	  		      glVertex3f(V[7].x, V[7].y, V[7].z);
	  		      glVertex3f(V[6].x, V[6].y, V[6].z);
	  		      glVertex3f(V[5].x, V[5].y, V[5].z);
	  		      glVertex3f(V[4].x, V[4].y, V[4].z);

	  		    glEnd();
	  		    glBegin(GL_TRIANGLES);
  			
  				//North Triangle
		  	      glNormal3f(1f, 0, 0);
		  	      glVertex3f(V[6].x, V[6].y, V[6].z);
		  	      glVertex3f(V[7].x, V[7].y, V[7].z);
		  	      glVertex3f(V[3].x, V[3].y, V[3].z);
  			
  				//Top Triangle
		  	      glNormal3f(0, 1f, 0);
		  	      glVertex3f(V[1].x, V[1].y, V[1].z);
		  	      glVertex3f(V[5].x, V[5].y, V[5].z);
		  	      glVertex3f(V[6].x, V[6].y, V[6].z);
  			
  				// West Triangle
		  	      glNormal3f(0, 0, -1f);
		  	      glVertex3f(V[0].x, V[0].y, V[0].z);
		  	      glVertex3f(V[1].x, V[1].y, V[1].z);
		  	      glVertex3f(V[3].x, V[3].y, V[3].z);
  			
  				//Top North West Triangle
		  	      glNormal3f(1f,1f,-1f);
		  	      glVertex3f(V[3].x, V[3].y, V[3].z);
		  	      glVertex3f(V[6].x, V[6].y, V[6].z);
		  	      glVertex3f(V[1].x, V[1].y, V[1].z);
		  	  			  	      
  				
    			V[0] = new Vector3f(0, 0, 0);
    			V[1] = new Vector3f(0, D, 0);
    			V[2] = new Vector3f(D, D, 0);
			    V[3] = new Vector3f(D, 0, 0);
			    V[4] = new Vector3f(0, 0, D);
			    V[5] = new Vector3f(0, D, D);
			    V[6] = new Vector3f(D, D, D);
			    V[7] = new Vector3f(D, 0, D);
    			

  		      glEnd();
  				break;
    		case SOUTH_EAST:
    			glBegin(GL_QUADS);
	  		      // Bottom Face
	  			  glNormal3f(0, -1f, 0);
	  		      glVertex3f(V[3].x, V[3].y, V[3].z);
	  		      glVertex3f(V[7].x, V[7].y, V[7].z);
	  		      glVertex3f(V[4].x, V[4].y, V[4].z);
	  		      glVertex3f(V[0].x, V[0].y, V[0].z);
	  		      
	  		      // North Face
	  		      glNormal3f(1f, 0, 0);
	  		      glVertex3f(V[2].x, V[2].y, V[2].z);
	  		      glVertex3f(V[6].x, V[6].y, V[6].z);
	  		      glVertex3f(V[7].x, V[7].y, V[7].z);
	  		      glVertex3f(V[3].x, V[3].y, V[3].z);
	  		      
	  		      // West Face
	  		      glNormal3f(0, 0, -1f);
	  		      glVertex3f(V[0].x, V[0].y, V[0].z);
	  		      glVertex3f(V[1].x, V[1].y, V[1].z);
	  		      glVertex3f(V[2].x, V[2].y, V[2].z);
	  		      glVertex3f(V[3].x, V[3].y, V[3].z);
	  		      
	  		    glEnd();
	  		    glBegin(GL_TRIANGLES);
			
	  		      // Top Triangle
		  	      glNormal3f(0, 1f, 0);
		  	      glVertex3f(V[1].x, V[1].y, V[1].z);
		  	      glVertex3f(V[6].x, V[6].y, V[6].z);
		  	      glVertex3f(V[2].x, V[2].y, V[2].z);
	  		    
	  		      // East Triangle
		  	      glNormal3f(0, 0, 1f);
		  	      glVertex3f(V[7].x, V[7].y, V[7].z);
		  	      glVertex3f(V[6].x, V[6].y, V[6].z);
		  	      glVertex3f(V[4].x, V[4].y, V[4].z);
	  		    
	  		      // South Triangle
		  	      glNormal3f(-1f, 0, 0);
		  	      glVertex3f(V[0].x, V[0].y, V[0].z);
		  	      glVertex3f(V[4].x, V[4].y, V[4].z);
		  	      glVertex3f(V[1].x, V[1].y, V[1].z);
	  		    
	  		      // Top South East Triangle
		  	      glNormal3f(-1f,1f,1f);
		  	      glVertex3f(V[4].x, V[4].y, V[4].z);
		  	      glVertex3f(V[6].x, V[6].y, V[6].z);
		  	      glVertex3f(V[1].x, V[1].y, V[1].z);
		  	      
				
  				V[0] = new Vector3f(0, 0, 0);
  				V[1] = new Vector3f(0, D, 0);
  				V[2] = new Vector3f(D, D, 0);
			    V[3] = new Vector3f(D, 0, 0);
			    V[4] = new Vector3f(0, 0, D);
			    V[5] = new Vector3f(0, D, D);
			    V[6] = new Vector3f(D, D, D);
			    V[7] = new Vector3f(D, 0, D);
  			

		        glEnd();
				break;
    		case SOUTH_WEST:
    			
    			break;
    		default:
    			break;
    		}
 
 * 
 * 
*/
