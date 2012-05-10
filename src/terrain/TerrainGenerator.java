package terrain;

import java.util.Random;

public class TerrainGenerator {
  private Random r;
  private int terrain_width;
  private int terrain_height;
  private final double e = 0.25;
  private double[][] mask;
  private double[][] smoothed;
  
  public TerrainGenerator(int width, int height) {
    r = new Random();
    
    terrain_width = width + 1;
    terrain_height = height + 1;
        
    mask = new double[terrain_width+2][];
    for (int i = 0; i < terrain_width+2; ++i) {
      mask[i] = new double[terrain_height+2];
      for (int j = 0; j < terrain_height+2; ++j) {
        mask[i][j] = -1.0;
      }
    }
    
    smoothed = new double[width][];
    for (int i = 0; i < width; ++i) {
      smoothed[i] = new double[height];
      for (int j = 0; j < height; ++j) {
        smoothed[i][j] = -1.0;
      }
    }
  }
  
  public int[][] generateHeightMap(int max_height) {
    generate(0, 0, terrain_width  - 1, terrain_height - 1);
    
    smooth();
    
    int[][] height_map = new int[terrain_width-1][terrain_height-1];
    
    for (int i = 0; i < terrain_width - 1; ++i) {
      for (int j = 0; j < terrain_height - 1; ++j) {
        height_map[i][j] = (int)Math.floor((smoothed[i][j]) * max_height) + (max_height/4);
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
  
  private void smooth() {
    double x;

    // ConvolveOp p;

    for (int i = 0; i < terrain_width-1; ++i) {
      for (int j = 0; j < terrain_height-1; ++j) {
        x = getValue(i - 1, j + 1) * 1 + getValue(i + 0, j + 1) * 1 + getValue(i + 1, j + 1) * 1
            + getValue(i - 1, j + 0) * 1 + getValue(i + 0, j + 0) * 1 + getValue(i + 1, j + 0) * 1
            + getValue(i - 1, j - 1) * 1 + getValue(i + 0, j - 1) * 1 + getValue(i + 1, j - 1) * 1;

        x = x / 9d;

        smoothed[i][j] = x;
      }
    }

    for (int i = 1; i < terrain_width - 2; ++i) {
      for (int j = 1; j < terrain_width - 2; ++j) {
        mask[i][j] = smoothed[i][j];
      }
    }  
  }
  
  public double getValue(int i, int j) {
    if (i < 0){
      i = 0;
    }
    
    if (i > mask.length - 1) {
      i = mask.length - 1;
    }
    
    if (j < 0) {
      j = 0;
    }
    
    if (j > mask[0].length - 1) {
      j = mask[0].length - 1;
    }
    
    
    return mask[i][j];
  }
  
  public void log(Object o) {
    System.out.println(o);
  }
}
