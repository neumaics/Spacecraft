package game;

import input.ActionEvent;
import input.LivingEntityInputListener;
import input.MovementEvent;
import input.RotationEvent;
import input.thrower.InputThrower;
import input.thrower.UserInputThrower;

import entities.Player;

//import scene.ArrayModelImpl;
//import scene.Model;

public class ClientController {
  private Timer timer;
  private Renderer viewer;
  private GameScreen display;
  private InputThrower thrower;
  
  private Player player;
//  private Model model;
  public ClientController() {
	   display = new GameScreen.Builder("Spacecraft").build();
	    
	   timer = new Timer();
	   viewer = new Renderer();
	   LivingEntityInputListener listener = new UserInputListener();
	   player = new Player();

	   thrower = new UserInputThrower(player,listener);
//	   model = new ArrayModelImpl();
	  }
  
  public void start() {
	  thrower.start();

	  while (!GameScreen.isCloseRequested()) {
      display.update();
      viewer.drawScene();
      timer.updateFPS();
    }
    
	thrower.stop();
    display.cleanUp();
  }
  
  public class UserInputListener implements LivingEntityInputListener {

	@Override
	public void livingEntityActed(ActionEvent actionEvent) {
		System.out.println("Entity Acted!");
	}

	@Override
	public void livingEntityMoved(MovementEvent movementEvent) {
		System.out.println("Entity Moved!");
	}

	@Override
	public void livingEntityRotated(RotationEvent rotationEvent) {
		System.out.println("Entity Rotated!");
	}
	  
  }
  
}
