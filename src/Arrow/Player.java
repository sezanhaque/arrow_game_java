package Arrow;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import entity_classes.EntityA;
import entity_classes.EntityB;

public class Player extends GameObject implements EntityA{
	
	private double velX = 0;
	private double velY = 0;
	
	private Textures tex;
	Game game;
	Controller controller;
	
	public Player(double x, double y, Textures tex, Game game, Controller controller){
		super(x, y);
		this.tex = tex;
		this.game = game;
		this.controller = controller;
	}
	
	public void tick() {
		// x++; //updating x axis and moving in x-axis
		x += velX;
		y += velY;
		
		// to fix the screen size of player to do movement
		if(x <= 0)
			x = 0;
		if(x >= 1180)
			x = 1180;
		if(y <= 0)
			y = 0;
		if(y >= 895)
			y = 895;
		
		for(int i =0; i < game.eb.size(); i++){
			EntityB tempEnt = game.eb.get(i);
			
			if(Physics.Collision(this, tempEnt)){
				controller.removeEntity(tempEnt);
				Game.HEALTH -= 10;
				game.setBalloon_bust(game.getBalloon_bust() + 1);
			}
			if(Game.HEALTH <= 0)
				System.exit(0);
		}
	}
	public void  render(Graphics g) {
		g.drawImage(tex.player, (int)x, (int)y, null); //we set x,y as int because "drawImage" get only int value and previously we define x,y as a double
	}
	
	public double getX(){
		return x;
	}
	public double getY() {
		return y;
	}
	public void setX(double x){
		this.x = x;
	}
	public void setY(double y){
		this.y = y;
	}
	public void setVelX(double velX){
		this.velX = velX;
	}
	public void setVelY(double velY){
		this.velY = velY;
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 128, 128);
	}
}
