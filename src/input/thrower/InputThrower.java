package input.thrower;

import input.ActionEvent;
import input.LivingEntityInputListener;
import input.MovementEvent;
import input.RotationEvent;
import input.ActionEvent.Action;

import org.lwjgl.util.vector.Vector3f;

import entities.LivingEntity;

public abstract class InputThrower implements Runnable {

	private LivingEntity target = null;
	private LivingEntityInputListener observer = null;
	
	public InputThrower(LivingEntity target,LivingEntityInputListener observer) {
		this.target = target;
		this.observer = observer;
	}
	
	@Override
	public abstract void run();

	protected final void throwRotatation(Vector3f rotation) {
		observer.livingEntityRotated(new RotationEvent(rotation,target));
	}
	
	protected final void throwMovement(Vector3f direction) {
		observer.livingEntityMoved(new MovementEvent(direction,target));
	}
	
	protected final void throwActionEvent(Action action) {
		observer.livingEntityActed(new ActionEvent(action,target));
	}
}
