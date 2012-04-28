package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import entities.Player;

import scene.ArrayModelImpl;
import scene.Model;

public class ClientController {
  private Timer timer;
  private Viewer viewer;
  private GameScreen display;
  private Player player;
  private Model model;
  public ClientController() {
	   display = new GameScreen.Builder("Spacecraft").build();
	    
	   timer = new Timer();
	   viewer = new Viewer();
	   player = new Player();
	   model = new ArrayModelImpl();
	  }
  
  public void start() {
    while (!GameScreen.isCloseRequested()) {
      display.update();
      viewer.drawScene();
      timer.updateFPS();
    }
    
    display.cleanUp();
  }
  
  public class MovementListener implements KeyListener {

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
  }
  
  public class ActionListener implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	  
  }
  
  public class RotationListener implements MouseMotionListener {

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		
	}
	  
  }
  
}
