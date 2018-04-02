package Arrow;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import entity_classes.EntityA;
import entity_classes.EntityB;

//import com.sun.org.apache.bcel.internal.generic.NEW;

public class Controller {

	/*private LinkedList<Bulletarrow> b = new LinkedList<Bulletarrow>();
	private LinkedList<Balloons> balloons = new LinkedList<Balloons>();
	private LinkedList<Pink_balloon> p = new LinkedList<Pink_balloon>();*/
	private LinkedList<EntityA> ea = new LinkedList<EntityA>();
	private LinkedList<EntityB> eb = new LinkedList<EntityB>();
	
	EntityA entity_A;
	EntityB entity_B;
	private Textures tex;
	Random r = new Random();
	private Game game;
	
	public Controller(Textures tex, Game game){
		this.tex = tex;
		this.game = game;
	}
	public void createBallons(int balloon_count){
		for(int i = 0; i < balloon_count; i++)
			addEntity(new Balloons(r.nextInt(640), -10, tex, this, game));
	}
	public void tick(){
		//Entity A
		for(int i = 0; i < ea.size(); i++){
			entity_A = ea.get(i);
			
			entity_A.tick();
		}
		//Entity B
		for(int i = 0; i < eb.size(); i++){
			entity_B = eb.get(i);
			
			entity_B.tick();
		}
	}
	public void render(Graphics g){
		//Entity A
		for(int i = 0; i < ea.size(); i++){
			entity_A = ea.get(i);
			
			entity_A.render(g);
		}
		//Entity B
		for(int i = 0; i < eb.size(); i++){
			entity_B = eb.get(i);
			
			entity_B.render(g);
		}
	}
	
	//Here different types of parameters so we can use same name of the method
	public void addEntity(EntityA block){
		ea.add(block);
	}
	public void removeEntity(EntityA block){
			ea.remove(block);
	}
	public void addEntity(EntityB block){
		eb.add(block);
	}
	public void removeEntity(EntityB block){
			eb.remove(block);
	}
	
	public LinkedList<EntityA> getEntityA(){
		return ea;
	}
	public LinkedList<EntityB> getEntityB(){
		return eb;
	}
}
