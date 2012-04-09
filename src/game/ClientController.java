package game;

public class ClientController {

  private static Viewer viewer;
  
  public ClientController() {
    initialize(); 
  }
  
  private static void initialize() {
    GameScreen disp = new GameScreen.Builder("Spacecraft").build();
    disp.getHeight();
    viewer = new Viewer();
    
    
   
  }
  
  public void start() {
    while (!GameScreen.isCloseRequested()) {
      GameScreen.update();
      viewer.drawScene();
    }
    
    GameScreen.cleanUp();
  }
}
