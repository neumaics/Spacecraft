package game.engine;

import org.lwjgl.util.vector.Vector3f;

import entities.LivingEntity;
import scene.Model;

public class GameEngine {

	public void entityMove(LivingEntity entity,Vector3f movement, Model model) {
		Vector3f newLocation = new Vector3f();
		newLocation = Vector3f.add(entity.getPosition(),movement,newLocation);
		entity.setPosition(newLocation);
		System.out.println("X: "+newLocation.x+" Z: "+newLocation.z+" D: "+entity.getDirection());
	}
	
}
