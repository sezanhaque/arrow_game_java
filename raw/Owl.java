package Arrow;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Owl extends GameObject{

	BufferedImage image;
	BufferedImage sprites;
	
	public Owl(double x, double y, Game game){
		super(x, y);
		
		//SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
		//or this
		BufferImageLoader loader = new BufferImageLoader();
		try{
			sprites = loader.loadImage("/Owl/owl.png");
		}catch (IOException  e) {
			e.printStackTrace();
		}
		SpriteSheet ss = new SpriteSheet(sprites);
		
		image = ss.grabImage(1, 1, 80, 40);
	}
	
	public void tick(){
		y -= 5;
	}
	public void render(Graphics g){
		g.drawImage(image, (int) x, (int) y, null);
	}

	public double getY() {
		return y;
	}
	public  BufferedImage getOwlSpriteSheet() { 
		return image;
	}
}
