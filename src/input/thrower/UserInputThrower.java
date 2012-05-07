package input.thrower;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Cursor;
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
		boolean paused = false;
		Cursor emptyCursor;
		try {
			emptyCursor = new Cursor(1, 1, 0, 0, 1, BufferUtils.createIntBuffer(1), null);
			Mouse.setNativeCursor(emptyCursor);
		} catch (LWJGLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(!done) {

			Keyboard.poll();

			//Rotation
			middleX = Display.getWidth()/2;
			middleY = Display.getHeight()/2;

			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
				paused = true;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_TAB)) {
				paused = false;
				Mouse.setCursorPosition(middleX, middleY);
			}
			
			if(!paused) {
				//Keyboard
	
				if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
					moving = true;
					movement.setX(movement.getX()-0.1f);
				}
				if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
					moving = true;
					movement.setX(movement.getX()+0.1f);
				}
				if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
					moving = true;
					movement.setZ(movement.getZ()+0.1f);
				}
				if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
					moving = true;
					movement.setZ(movement.getZ()-0.1f);
				}
				
				if(moving) {
					
					float x = -movement.getX();
					float z = movement.getZ();
					double d = Math.toRadians(target.getDirection());
					
					movement.setX(x*(float)Math.cos(d)-z*(float)Math.sin(d));
					movement.setZ(x*(float)Math.sin(d)+z*(float)Math.cos(d));
					throwMovement(movement);
					movement.set(0f,0f,0f);
					moving = false;
				}
		
				Mouse.poll();
		
				userX = Mouse.getX();
				userY = Mouse.getY();
				if(userX!=middleX || userY!=middleY) {
					throwRotatation((userY-middleY)*0.5f,(userX-middleX)*0.5f);
					Mouse.setCursorPosition(middleX,middleY);
				}
				
				//Click
				if(Mouse.isButtonDown(0)) {
					throwAction(Action.ACTIVATE);
				}
				
				if(Mouse.isButtonDown(1)) {
					throwAction(Action.SELECT);
				}
			}
			try {
				Thread.sleep(20);
			} catch(Exception e) {
				
			}
		}
	}
}
