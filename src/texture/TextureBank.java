package texture;

import game.Material;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class TextureBank {
	private Map<Material, Texture> textures = new HashMap<Material, Texture>();

	private static TextureBank textureBank = new TextureBank();

	private TextureBank() {

	}

	public static TextureBank Instance() {
		return textureBank;
	}

	public boolean loadTexture(String path, Material material) {
		Texture texture;

		try {
			texture = TextureLoader.getTexture( "PNG", new FileInputStream(path));
			textures.put(material,texture);
			return  true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Texture getTexture(Material material) {
		return textures.get(material);
	}
}

