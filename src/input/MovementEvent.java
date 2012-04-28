package input;

import entities.LivingEntity;

import org.lwjgl.util.vector.Vector3f;

public class MovementEvent {

	private Vector3f direction;
	private LivingEntity target;
	
	public Vector3f getDirection() {
		return direction;
	}
	public LivingEntity getTarget() {
		return target;
	}
	public MovementEvent(Vector3f direction, LivingEntity target) {
		super();
		this.direction = direction;
		this.target = target;
	}
	
}
