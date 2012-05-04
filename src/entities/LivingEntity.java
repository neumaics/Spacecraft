package entities;

import org.lwjgl.util.vector.Vector3f;

public class LivingEntity {
	protected Vector3f position = null;
	protected float pitch;
	protected float direction;
	
	public LivingEntity() {
		position = new Vector3f(10f,10f,10f);
		pitch = 0f;
		direction = 0f;
	}

	public LivingEntity(Vector3f position, Vector3f direction) {
		super();
		this.position = position;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public float getPitch() {
		return pitch;
	}

	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

	public float getDirection() {
		return direction;
	}

	public void setDirection(float direction) {
		this.direction = direction;
	}
	
}
