package input;

public interface LivingEntityInputListener {

	public void livingEntityRotated(RotationEvent rotationEvent);
	public void livingEntityMoved(MovementEvent movementEvent);
	public void livingEntityActed(ActionEvent actionEvent);
}
