package Arrow;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Help {

	public void render(Graphics g)
	{
		Graphics2D g2d=(Graphics2D)g;
		
		Rectangle help_rec=new Rectangle(600,40,100,50);
		
		Font help_font=new Font("arial",Font.BOLD,30);
		g.setFont(help_font);
		g.setColor(new Color(102, 0, 102));
		g.drawString("*Press Space to Fire", 100, 100);
		
		g.drawString("*Press Up-Arrow to Go UP", 100, 150);
		
		g.drawString("*Press Down-Arrow to go DOWN", 100, 200);
		
		g.drawString("*Press Left-Arrow to go Back", 100, 250);
		
		g.drawString("*Press Right-Arrow to go Front", 100, 300);
		
		//g.drawString("BACK", 605,75);
		
		g2d.draw(help_rec);
		
		
	}
}
