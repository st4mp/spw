package f2.spw;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

public class Support extends Sprite{
	public static final int S_TO_FADE = 600;
	public static final int S_TO_SUP = 600;
	
	private int step = 10;
	private boolean alive = true;
	
	public Support(int x, int y) {
		super(x, y, 10, 10);
	}

	@Override
	public void draw(Graphics2D g) {
		if(y < S_TO_FADE)
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		else{
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
					(float)(S_TO_SUP - y)/(S_TO_SUP - S_TO_SUP)));
		}
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
	}

	public void proceeds(){
		y += step;
		if(y > S_TO_SUP){
			alive = false;
		}
	}
	
	public boolean isAlives(){
		return alive;
	}
}
