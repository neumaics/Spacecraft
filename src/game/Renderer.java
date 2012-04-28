package game;

import scene.*;
public class Renderer {
  private Scene scene;
  
  public Renderer(){
    initialize();
  }
  
  private void initialize() {
    scene = new Scene();
    scene.attach(new LegacyCube());
  }
  
  public void drawScene() {
    scene.draw();
  }
}
