package Arrow;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.function.Predicate;

import entity_classes.EntityA;
import entity_classes.EntityB;

public class Balloons extends GameObject implements EntityB{

	Random r = new Random();
	private Textures tex;
	private  Game game;
	private Controller c;
	private int speed = r.nextInt(3) + 1;
	
	//public int score = 0;
	
	public Balloons(double x, double y, Textures tex,Controller c, Game game){
		super(x, y);
		this.tex = tex;
		this.c = c;
		this.game = game;
	}
	
	public void tick(){
		y += speed;
		
		//when the balloon will exit the frame then it will return in different location of the screen
		if(y > (Game.Height * Game.Scale)){
			speed = r.nextInt(3) + 1;
			y = -10;
			x = r.nextInt(640);
		}
		for(int i = 0; i < game.ea.size(); i++){
			EntityA tempEnt = game.ea.get(i);
			
			if(Physics.Collision(this, tempEnt)){
				game.score++;
				c.removeEntity(tempEnt);
				c.removeEntity(this);
				game.setBalloon_bust(game.getBalloon_bust() + 1);
				System.out.println("Score : "+game.score);
			}
		}

		//System.out.println("Score: " + score);
		
	}
	public void render(Graphics g){
		g.drawImage(tex.balloons, (int) x, (int) y, null);
	}
	/*public  BufferedImage getBalloonsSpriteSheet() { 
		return image;
	}*/
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 120, 120);
	}
}