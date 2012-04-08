package game;

import scene.*;
public class ClientController {

  private static Scene scene;
  
  public ClientController() {
    initialize(); 
  }
  
  private void initialize() {
    GameScreen viewer = new GameScreen.Builder("Spacecraft").build();
    scene = new Scene();
    scene.attach(new Cube());
    viewer.getHeight();
  }
  
  public void start() {
    while (!GameScreen.isCloseRequested()) {
      GameScreen.update();
      scene.draw();
    }
    
    GameScreen.cleanUp();
  }
}
