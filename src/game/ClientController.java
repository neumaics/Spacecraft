package game;

public class ClientController {
  private static Timer timer;
  private static Viewer viewer;
  
  public ClientController() {
    initialize(); 
  }
  
  private static void initialize() {
    GameScreen disp = new GameScreen.Builder("Spacecraft").build();
    disp.getHeight();
    
    timer = new Timer();
    viewer = new Viewer();
    
  }
  
  public void start() {
    while (!GameScreen.isCloseRequested()) {
      GameScreen.update();
      viewer.drawScene();
      timer.updateFPS();
    }
    
    GameScreen.cleanUp();
  }
}
