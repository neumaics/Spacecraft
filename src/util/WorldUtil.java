package util;

import java.util.List;
import java.util.Map;

import scene.Cube;
import scene.Cube.ORDER;

public class WorldUtil {

	public static void  populateShapeMaps(	Cube[][][] model,
											Map<Cube,List<Rectangle>> rectangles,
											Map<Cube,List<Triangle>> triangles ){

		
		
		for(int i = 0;i<model.length;i++) {
			for(int j = 0;j<model[i].length;j++) {
				for(int k = 0;k<model[i][j].length;k++) {
					Cube cube = model[i][j][k];
					if(cube.o==ORDER.Z)continue;
				}
			}
		}
		
	}
	
}
