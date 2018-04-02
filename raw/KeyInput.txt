package Arrow;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{

	Game game;
	
	public KeyInput(Game game){
		this.game = game;
	}
	
	public void keyPressed(KeyEvent e){ 
		game.keyPressed(e); //when a key pressed then this will call keyPresed() which is in Game class
	}
	
	public void keyReleased(KeyEvent e) {
		game.keyReleased(e); 
		game.keyPressed(e); //when a key released then this will call keyPresed() which is in Game class
	}
}
