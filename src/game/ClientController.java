package game;

import org.lwjgl.input.Mouse;

import game.engine.GameEngine;
import input.ActionEvent;
import input.LivingEntityInputListener;
import input.MovementEvent;
import input.RotationEvent;
import input.thrower.InputThrower;
import input.thrower.UserInputThrower;

import entities.Player;

import scene.ArrayModelImpl;
import scene.Model;
import scene.SimpleSceneImpl;

public class ClientController {
  private Timer timer;
  private Renderer renderer;
  private GameScreen display;
  private InputThrower thrower;
  private GameEngine engine;
  
  private Player player;
  private Model model;
  public ClientController() {
	   display = new GameScreen.Builder("Spacecraft").build();
	    
	   timer = new Timer();
	   renderer = new Renderer();
	   LivingEntityInputListener listener = new UserInputListener();
	   player = new Player();

	   thrower = new UserInputThrower(player,listener);
	//   model = new ArrayModelImpl(20,20,20);
	   model = new SimpleSceneImpl(); 
	   engine = new GameEngine();
	  }
  
  public void start() {
      Mouse.setCursorPosition(display.getWidth()/2,display.getHeight()/2);
	  thrower.start();

	  while (!GameScreen.isCloseRequested()) {
      display.update();
      renderer.render(model, player);
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
		engine.entityMove(movementEvent.getTarget(),movementEvent.getDirection(), model);
	}

	@Override
	public void livingEntityRotated(RotationEvent rotationEvent) {
		float newDirection = rotationEvent.getTarget().getDirection()+rotationEvent.getDirection();
		rotationEvent.getTarget().setDirection(newDirection%360);
		
		float newPitch = rotationEvent.getTarget().getPitch()+rotationEvent.getPitch();
		rotationEvent.getTarget().setPitch(Math.max(Math.min(newPitch,89),-89));
	}
	  
  }
  
}
