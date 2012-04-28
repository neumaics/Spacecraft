package input.thrower;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import input.LivingEntityInputListener;
import input.ActionEvent.Action;
import entities.LivingEntity;

public class UserInputThrower extends InputThrower {

	public UserInputThrower(LivingEntity target,
			LivingEntityInputListener observer) {
		super(target, observer);
	}

	private int middleX;
	private int middleY;
	private int userX;
	private int userY;
	private Vector3f rotation = new Vector3f(0f,0f,0f);
	private Vector3f movement = new Vector3f(0f,0f,0f);
	private boolean moving = false;
	boolean done = false;
	
	@Override
	public void stop() {
		done = true;
		super.stop();
	}
	@Override
	public void run() {
		while(!done) {
			//Rotation
			middleX = Display.getWidth()/2;
			middleY = Display.getHeight()/2;
	
			Mouse.poll();
	
			userX = Mouse.getX();
			userY = Mouse.getY();
			if(userX!=middleX || userY!=middleY) {
				rotation.setX((float)(userX-middleX));
				rotation.setY((float)(userY-middleY));
				throwRotatation(rotation);
				Mouse.setCursorPosition(middleX,middleY);
			}
			
			//Click
			if(Mouse.isButtonDown(0)) {
				throwAction(Action.ACTIVATE);
			}
			
			if(Mouse.isButtonDown(1)) {
				throwAction(Action.SELECT);
			}
			
			//Keyboard
			Keyboard.poll();
			
			if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
				moving = true;
				movement.setX(movement.getX()-01f);
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
				moving = true;
				movement.setX(movement.getX()+01f);
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
				moving = true;
				movement.setZ(movement.getZ()-01f);
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
				moving = true;
				movement.setZ(movement.getZ()+01f);
			}
			
			if(moving) {
				throwMovement(movement);
				movement.set(0f,0f,0f);
				moving = false;
			}
		}
	}
}
