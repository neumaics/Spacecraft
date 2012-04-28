package input;

import entities.LivingEntity;

import org.lwjgl.util.vector.Vector3f;

public class RotationEvent {

	private Vector3f rotation;
	private LivingEntity target;
	
	public Vector3f getRotation() {
		return rotation;
	}
	public LivingEntity getTarget() {
		return target;
	}
	public RotationEvent(Vector3f rotation, LivingEntity target) {
		super();
		this.rotation = rotation;
		this.target = target;
	}
	
}
