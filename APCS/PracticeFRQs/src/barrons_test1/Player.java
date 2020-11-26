package barrons_test1;

// Problem 3

public class Player {

	private String name;
	private int playerNumber;
	
	public Player(String name, int playerNumber) {
		this.name = name;
		this.playerNumber = playerNumber;
	}
	
	public String getPlayerName() {
		return name;
	}
	
	public int getPlayerNumber() {
		return playerNumber;
	}
}
