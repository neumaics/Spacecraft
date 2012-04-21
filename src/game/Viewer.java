package game;

import scene.*;
public class Viewer {
  private Scene scene;
  
  public Viewer(){
    initialize();
  }
  
  private void initialize() {
    scene = new Scene();
    scene.attach(new Cube());
  }
  
  public void drawScene() {
    scene.draw();
  }
}
