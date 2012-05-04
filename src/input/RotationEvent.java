package input;

import entities.LivingEntity;

import org.lwjgl.util.vector.Vector3f;

public class RotationEvent {

	private LivingEntity target;
	private float pitch;
	private float direction;
	
	public LivingEntity getTarget() {
		return target;
	}
	public RotationEvent(float pitch,float direction, LivingEntity target) {
		super();
		this.target = target;
		this.pitch = pitch;
		this.direction = direction;
	}
	public float getPitch() {
		return pitch;
	}
	public float getDirection() {
		return direction;
	}

}
