package Arrow;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BufferCapabilities;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;

import entity_classes.EntityA;
import entity_classes.EntityB;

public class Game extends Canvas implements Runnable {
	
	public static final long serialversionUID = 1L;
	public static final int Width = 640;
	public static final int Height = 480; //Width/12*9;
	public static final int Scale = 2;
	//private static final String state = null;
	//private static final String Manager = null;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferedImage image= new BufferedImage(Width, Height, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private BufferedImage background = null; //background of the game
	private BufferedImage game_menu_background = null;
	
	private boolean is_shooting = false;
	
	private int balloon_count = 5;
	private int balloon_bust = 0;

	private Player p;
	private Controller c;
	private Textures tex;
	private Game_Menu menu;
	
	public LinkedList<EntityA> ea;
	public LinkedList<EntityB> eb;
	
	public static int HEALTH = 200;
	
	public int score =0;
	
	public static enum State{
		Menu,Game,Help,Resume,GameOver
	};
	public static State s=State.Menu;
	
	public void init(){
		requestFocusInWindow(); //this will focus on screen so we don't need to click on window to play
		
		BufferImageLoader loader = new BufferImageLoader();
		try{
			spriteSheet = loader.loadImage("/final_Sprite_Sheet.png");
			background = loader.loadImage("/Background/way-in-green-meadow_23-2147517607-2.jpg"); // 1300 * 1125
			game_menu_background = loader.loadImage("/Background/IMG_301358_1.jpg");
		}catch (IOException  e) {
			e.printStackTrace();
		}
		tex = new Textures(this);
		
		c = new Controller(tex, this);
		p = new Player(620, 910, tex, this, c);
		menu = new Game_Menu();
		
		ea = c.getEntityA();
		eb = c.getEntityB();

		this.addKeyListener(new KeyInput(this));
		this.addMouseListener(new MouseInput());
		c.createBallons(balloon_count);
	}
	
	//to start thread
	private synchronized void start(){
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	//to stop thread
	private synchronized void stop(){
		if(!running)
			return;
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	public void run() {
		init();
		long lastTime = System.nanoTime();
		final double ammountOfTicks = 60.0;
		double ns = 1000000000 / ammountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		while(running){
			//game loop
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			if(delta >= 0){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				//System.out.println(updates + " Ticks, Fps " + frames);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}
	private void tick(){
		if(s == State.Game){
			p.tick();
			c.tick();	
		}
		if(balloon_bust >= balloon_count){
			balloon_count += 2;
			balloon_bust = 0;
			c.createBallons(balloon_count);
		}
	}
	private void render(){
	
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		//now from this section we can draw anything
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		g.drawImage(game_menu_background, 0, 0, null);
		
		if(s == State.Game){
			g.drawImage(background, 0, 0, null); //in game background
			p.render(g);
			c.render(g);
			
			//Health bar customization
			g.setColor(Color.gray);
			g.fillRect(5, 5, 200, 50);
			
			g.setColor(Color.green);
			g.fillRect(5, 5, HEALTH, 50);
			
			g.setColor(Color.white);
			g.drawRect(5, 5, 200, 50);
			
		}else if(s==State.Menu)
		{	
			menu.render(g);
		}
		//upto this section
		g.dispose();
		bs.show();
	}
	
		public void keyPressed(KeyEvent e){
			//this will get keyPressed from "KeyInput" class
			int key = e.getKeyCode();
			
			if(s == State.Game){
				if(key == KeyEvent.VK_RIGHT){
					p.setVelX(5);
				}else if(key == KeyEvent.VK_LEFT){
					p.setVelX(-5);
				}else if(key == KeyEvent.VK_SPACE && !is_shooting){
					is_shooting = true;
					c.addEntity(new Bulletarrow(p.getX(), p.getY(), tex, this));
				}
			}
		}
		
		public void keyReleased(KeyEvent e) {
			//this will get keyReleased from "KeyInput" class
			int key = e.getKeyCode();
			
			if(key == KeyEvent.VK_RIGHT){
				p.setVelX(0);
			}else if(key == KeyEvent.VK_LEFT){
				p.setVelX(0);
			}else if(key == KeyEvent.VK_SPACE){
				is_shooting = false;
			}
		}
		//};
	//}
	public static void main(String str[]){
	
		Game game=new Game();
		
		game.setPreferredSize(new Dimension(Width*Scale, Height*Scale));
		game.setMaximumSize(new Dimension(Width*Scale, Height*Scale));
		game.setMinimumSize(new Dimension(Width*Scale, Height*Scale));
		
		JFrame frame = new JFrame("Arrow Game");
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start(); //calling start method to start thread
	}
	public  BufferedImage getSpriteSheet() { //to use getSpriteSheet from player class
		return spriteSheet;
	}
	public int getBalloon_count() {
		return balloon_count;
	}

	public void setBalloon_count(int balloon_count) {
		this.balloon_count = balloon_count;
	}

	public int getBalloon_bust() {
		return balloon_bust;
	}

	public void setBalloon_bust(int balloon_bust) {
		this.balloon_bust = balloon_bust;
	}
}
