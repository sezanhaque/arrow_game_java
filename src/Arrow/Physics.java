package Arrow;
import java.util.LinkedList;

import entity_classes.EntityA;
import entity_classes.EntityB;

public class Physics {

	public static boolean Collision(EntityA entity_A, EntityB entity_B){
		
			if(entity_A.getBounds().intersects(entity_B.getBounds())){
				return true;
			}
		return false;
	}
public static boolean Collision(EntityB entity_B, EntityA entity_A){
		
			if(entity_B.getBounds().intersects(entity_A.getBounds())){
				return true;
			}
		return false;
	}
}
