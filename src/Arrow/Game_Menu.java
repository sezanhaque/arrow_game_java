package Arrow;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Game_Menu {
	public Rectangle playButton = new Rectangle(400, 150, 100, 50);
	//public Rectangle helpButton = new Rectangle(400, 250, 100, 50);
	public Rectangle quitButton = new Rectangle(400, 250, 100, 50);
	
	public void  render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt0 = new Font("Halo3", Font.ITALIC, 60);
		g.setFont(fnt0);
		g.setColor(Color.ORANGE);
		g.drawString("Arrow Game", 250, 100);
		
		Font fnt1 = new Font("Halo3", Font.ITALIC, 35);
		g.setFont(fnt1);
		g.drawString("Play",playButton.x+3, playButton.y+30);
		g2d.draw(playButton);
		
		g.drawString("Quit",playButton.x+3, playButton.y+130);
		g2d.draw(quitButton);
		
		/*g.drawString("Quit",playButton.x+4, playButton.y+230);
		g2d.draw(quitButton);*/
	}
}
