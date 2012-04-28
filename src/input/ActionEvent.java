package input;

import entities.LivingEntity;

public class ActionEvent {

	public enum Action {SELECT,USE,ACTIVATE };
	private Action action;
	private LivingEntity target;
	
	public Action getAction() {
		return action;
	}
	public LivingEntity getTarget() {
		return target;
	}
	public ActionEvent(Action action, LivingEntity target) {
		super();
		this.action = action;
		this.target = target;
	}
	
}
