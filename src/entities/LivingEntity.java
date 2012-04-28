package entities;

import org.lwjgl.util.vector.Vector3f;

public class LivingEntity {
	protected Vector3f position = null;
	protected Vector3f direction = null;
	
	public LivingEntity() {
		position = new Vector3f(0f,0f,0f);
		direction = new Vector3f(0f,0f,0f);
	}

	public LivingEntity(Vector3f position, Vector3f direction) {
		super();
		this.position = position;
		this.direction = direction;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public Vector3f getDirection() {
		return direction;
	}

	public void setDirection(Vector3f direction) {
		this.direction = direction;
	}

}
