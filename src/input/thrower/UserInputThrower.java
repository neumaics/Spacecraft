package input.thrower;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import input.LivingEntityInputListener;
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
	
	@Override
	public void run() {

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
		}
		
		//Click
		if(Mouse.isButtonDown(0)) {
//			throwAction()
		}
		
	}

}
