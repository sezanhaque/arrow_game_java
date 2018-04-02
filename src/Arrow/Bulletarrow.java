package Arrow;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import entity_classes.EntityA;

public class Bulletarrow extends GameObject implements EntityA {

	/*BufferedImage image;
	BufferedImage sprites;-**/
	
	private Textures tex;
	private Game game;
	
	
	public Bulletarrow(double x, double y,Textures tex, Game game){
		super(x, y);
		this.game = game;
		this.tex = tex;
		//SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
		//or this
		/*BufferImageLoader loader = new BufferImageLoader();
		try{
			sprites = loader.loadImage("/sample1024.png");
		}catch (IOException  e) {
			e.printStackTrace();
		}
		SpriteSheet ss = new SpriteSheet(sprites);
		
		image = ss.grabImage(6, 2, 92, 80);*/
	}
	
	public void tick(){
		y -= 5;
		
		/*if(Physics.Collision(this, game.eb)){
			System.out.println("Collision detected");
		}*/
	}
	public void render(Graphics g){
		g.drawImage(tex.bulletarrow, (int) x, (int) y, null);
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}

	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 16, 96);
	}
}
