package barrons_test1;

import java.util.ArrayList;
import java.util.List;

// Problem 3

public class Tournament {

	private Player[] slots;
	private List<String> waitingList = new ArrayList<String>();
	
	public Tournament(boolean list) {
		slots = new Player[100];
		for (int i = 0; i < slots.length; i++) {
			slots[i] = new Player(Integer.toHexString(i*1000+2874), i);
		}
		if (list) {
			for (int i = 0; i < 10; i++) {
				waitingList.add(Character.toString((char) (i+65)));
			}
		}
	}
	
	public Tournament() {
		this(false);
	}
	
	public Player requestSlot(String playerName) {
		for (int i = 0; i < slots.length; i++) {
			if (slots[i] == null) {
				Player p = new Player(playerName, i);
				return p;
			}
		}
		waitingList.add(playerName);
		return null;
	}
	
	public Player cancelAndReassignSlot(Player p) {
		if (waitingList.size() > 0) {
			Player newPlayer = new Player(waitingList.get(0), p.getPlayerNumber());
			slots[p.getPlayerNumber()] = newPlayer;
			return newPlayer;
		}
		slots[p.getPlayerNumber()] = null;
		return null;
	}
}
