package entities;

import org.lwjgl.util.vector.Vector3f;


public class Player extends LivingEntity {
	
	public Player() {
		super();
	}

	public Player(Vector3f position, Vector3f direction) {
		super(position,direction);
	}
}
