	package game;

public class GameController {
	private static ClientController client;
	
  public static void main(String[] args) {
    initializeClient();
    client.start();
  }
  
  public static void initializeClient(){
    client = new ClientController();
  }
}
