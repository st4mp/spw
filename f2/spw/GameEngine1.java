package f2.spw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import javafx.animation.Interpolator;

public class GameEngine1 implements KeyListener, GameReporter{
	GamePanel gp;
		
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<Support> support = new ArrayList<Support>();
	private SpaceShip v;	
	
	private Timer timer;
	private long score = 0;
	private int state = 1;
	private double difficulty = 0.1;
	
	public GameEngine1(GamePanel gp, SpaceShip v) {
		this.gp = gp;
		this.v = v;		
		
		gp.sprites.add(v);
		
		timer = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				process();
			}
		});
		timer.setRepeats(true);
		
	}
	
	public void start(){
		timer.start();
	}
	
	private void generateEnemy(){
		Enemy e = new Enemy((int)(Math.random()*390), 30);
		gp.sprites.add(e);
		enemies.add(e);
	}

	Support s;
	public void generateSup(){
			s = new Support((int)(Math.random()*390), (int)(Math.random()*200)+200);
			gp.sprites.add(s);
			support.add(s);
	}

	private void process(){
		if(Math.random() < difficulty){
			generateEnemy();
		}
		
		Iterator<Enemy> e_iter = enemies.iterator();
		Iterator<Support> s_iter = support.iterator();

		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();
			
			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
				score += 100;
			}

			if( getScore() > 1000 * state ){
				
				while(s_iter.hasNext()){
					Support s = s_iter.next();
					s.proceeds();
					
					if(!s.isAlives()){
						s_iter.remove();
						gp.sprites.remove(s);
					}
				}
				generateSup();
				difficulty += 0.001;
				state++;
			}
		}
		
		gp.updateGameUI(this);
		
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er, es;
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr)){
				die();
				return;
			}
		}

		for(Support s : support){
			es = s.getRectangle();
			if(es.intersects(vr)){
				gp.sprites.remove(s);
				score += 250;
			}
		}
	}
	
	public void die(){
		timer.stop();
	}
	
	void controlVehicle(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			v.moveX(-1);
			break;
		case KeyEvent.VK_D:
			v.moveX(1);
			break;
		case KeyEvent.VK_W:
			v.moveY(-1);
			break;
		case KeyEvent.VK_S:
			v.moveY(1);
			break;
		}
	}

	public long getScore(){
		return score;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		controlVehicle(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}
}
