package game;

public class ClientController {
  private Timer timer;
  private Viewer viewer;
  private GameScreen display;
  
  public ClientController() {
    initialize(); 
  }
  
  private void initialize() {
    display = new GameScreen.Builder("Spacecraft").build();
    
    timer = new Timer();
    viewer = new Viewer();
    
  }
  
  public void start() {
    while (!GameScreen.isCloseRequested()) {
      display.update();
      viewer.drawScene();
      timer.updateFPS();
    }
    
    display.cleanUp();
  }
}
