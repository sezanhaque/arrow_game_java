package Arrow;

import java.awt.Graphics;
import java.awt.Rectangle;

import entity_classes.EntityA;

public class Pink_balloon extends GameObject implements EntityA{
	
	private Textures tex;
	
	public Pink_balloon(double x, double y, Textures tex){
		super(x, y);
		
		this.tex = tex;;
	}
	
	public void tick(){
		y += 5;
	}
	public void render(Graphics g){
		g.drawImage(tex.pink_balloon, (int)x, (int)y, null);
	}

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
