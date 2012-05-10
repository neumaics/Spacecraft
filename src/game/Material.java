package game;

public enum Material {
	
	GRASS(1),DIRT(2);
	
	private int data = 0;
	private int id = 0;
	
	public int getData() {
		return data;
	}
	
	public int getId() {
		return id;
	}
	
	private Material(int id) {
		this(id,0);
	}
	private Material(int id, int data) {
		this.id = id;
		this.data = data;
	}
}
