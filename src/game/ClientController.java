package game;

public class ClientController {

  public ClientController() {
    initialize(); 
  }
  
  private void initialize() {
    GameScreen viewer = new GameScreen.Builder("Spacecraft").build();
    viewer.getHeight();
  }
  
  public void start() {
    while (!GameScreen.isCloseRequested()) {
      GameScreen.update();
    }
    
    GameScreen.cleanUp();
  }
}
