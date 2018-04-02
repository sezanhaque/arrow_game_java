package Arrow;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener{

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		int mouse_x = e.getX();
		int mouse_y = e.getY();
		
		// Play Button
		if(mouse_x >= 400 && mouse_x <= 500){
			if(mouse_y >= 150 && mouse_y <= 200){
				//Pressed Play Button
				Game.s = Game.State.Game;
			}
		}
		// Quit Button
				if(mouse_x >= 400 && mouse_x <= 500){
					if(mouse_y >= 250 && mouse_y <= 300){
						System.exit(0);
					}
					
				}
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
