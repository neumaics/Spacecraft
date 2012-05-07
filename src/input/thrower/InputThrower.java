package input.thrower;

import input.ActionEvent;
import input.LivingEntityInputListener;
import input.MovementEvent;
import input.RotationEvent;
import input.ActionEvent.Action;

import org.lwjgl.util.vector.Vector3f;

import entities.LivingEntity;

public abstract class InputThrower implements Runnable {

	protected LivingEntity target = null;
	private LivingEntityInputListener observer = null;
	private Thread thread = null;
	public InputThrower(LivingEntity target,LivingEntityInputListener observer) {
		this.target = target;
		this.observer = observer;
	}
	
	public void start() {
		if(thread==null||!thread.isAlive()) {
			thread = new Thread(this);
			thread.start();
		}
	}
	
	public void stop() {
		if(thread!=null) {
			thread.interrupt();
		}
	}
	
	@Override
	public abstract void run();

	protected final void throwRotatation(float pitch, float direction) {
		observer.livingEntityRotated(new RotationEvent(pitch,direction,target));
	}
	
	protected final void throwMovement(Vector3f direction) {
		observer.livingEntityMoved(new MovementEvent(direction,target));
	}
	
	protected final void throwAction(Action action) {
		observer.livingEntityActed(new ActionEvent(action,target));
	}
}
