package terrain;

import java.util.Random;

public class TerrainGenerator {
  private Random r;
  private int terrain_width;
  private int terrain_height;
  private final double e = 0.125;
  private double[][] mask;
  
  public TerrainGenerator(int width, int height) {
    r = new Random();
    
    terrain_width = width + 1;
    terrain_height = height + 1;
        
    mask = new double[terrain_width][];
    for (int i = 0; i < terrain_width; ++i) {
      mask[i] = new double[terrain_height];
      for (int j = 0; j < terrain_height; ++j) {
        mask[i][j] = -1.0;
      }
    }
  }
  
  public int[][] generateHeightMap(int max_height) {
    log(terrain_width);
    generate(0, 0, terrain_width  - 1, terrain_height - 1);
    
    int[][] height_map = new int[terrain_width-1][terrain_height-1];
    
    for (int i = 0; i < terrain_width - 1; ++i) {
      for (int j = 0; j < terrain_height - 1; ++j) {
        height_map[i][j] = (int)Math.floor((mask[i][j]/2) * max_height) + (max_height/4);
      }
    }
    
    return height_map;
  }
  
  public double[][] generateHeightMask() {
    generate(0, 0, terrain_width - 1, terrain_height - 1);

    return mask;
  }
    
  public void generate(int x0, int y0, int x1, int y1) {
    int mpx = (x1 - x0)/2;
    int mpy = (y1 - y0)/2;
    int width = x1 - x0;
    int height = y1 - y0;
       
    if (((width) > 1)  || ((height) > 1)) {     
      if (mask[x0][y0] < 0) {
        mask[x0][y0] = r.nextDouble();
      }
      
      if (mask[x1][y0] < 0) {
        mask[x1][y0] = r.nextDouble();
      }
      
      if (mask[x0][y1] < 0) { 
        mask[x0][y1] = r.nextDouble();
      }
      
      if (mask[x1][y1] < 0) {
        mask[x1][y1] = r.nextDouble();
      }
            
      double c0 = mask[x0][y0];
      double c1 = mask[x1][y0];
      double c2 = mask[x0][y1];
      double c3 = mask[x1][y1];
                 
      double mp = (c0 + c1 + c2 + c3) / 4d;
      double c6 = (r.nextBoolean() ? -e : e);
            
      mask[x0 + mpx][y0 + mpy] = mp + c6;
      mask[x0 + mpx][y0] = (c0 + c1) / 2d;
      mask[x1][y0 + mpy] = (c1 + c2) / 2d;
      mask[x0 + mpx][y1] = (c2 + c3) / 2d;
      mask[x0][y0 + mpy] = (c3 + c1) / 2d;
      
      generate(x0, y0, x0 + mpx, y0 + mpy);
      generate(x0 + mpx, y0, x1, y0 + mpy);
      generate(x0, y0 + mpy, x0 + mpx, y1);
      generate(x0 + mpx, y0 + mpy, x1, y1);
    }
  }
  
  public void log(Object o) {
    System.out.println(o);
  }
}
