package game;

import scene.*;
public class Viewer {
  private static Scene scene;
  
  public Viewer(){
    initialize();
  }
  
  private static void initialize() {
    scene = new Scene();
    scene.attach(new Cube());
  }
  
  public void drawScene() {
    scene.draw();
  }
}
