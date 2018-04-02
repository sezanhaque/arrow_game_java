package Arrow;

import java.awt.image.BufferedImage;

public class Textures {

	public BufferedImage player, bulletarrow, balloons, pink_balloon;
	private SpriteSheet ss;
	
	public Textures(Game game){
		ss = new SpriteSheet(game.getSpriteSheet());
		
		getTextures();
	}
	private void getTextures() {
		player = ss.grabImage(1, 1, 128, 128);
		bulletarrow = ss.grabImage(2, 1, 128, 128);
		balloons = ss.grabImage(3, 1, 128, 128);
	}
	
}
