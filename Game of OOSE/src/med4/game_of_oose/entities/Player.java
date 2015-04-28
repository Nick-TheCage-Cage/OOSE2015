package med4.game_of_oose.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import med4.game_of_oose.gamestate.ApplicationState;
import med4.game_of_oose.main.ApplicationPanel;

public class Player {
	
	//movement booleans
	private boolean right = false, left = false, jumping = false, falling = false;
	
	//bounds
	private double x, y;
	private int width, height;
	
	//Jump speed
	private double jumpSpeed = 8;
	private double currentJumpSpeed = jumpSpeed;
	
	//fall speed
	private double maxFallSpeed = 16;
	private double currentFallSpeed = 0.2;
	
	public Player(int width, int height) {
		x = ApplicationPanel.WIDTH/2;
		y = ApplicationPanel.HEIGHT/2;
		this.width = width;
		this.height = height;
	}
	
	public void tick() {
		if(right){
			ApplicationState.xOffset++;
		}
		if(left){
			ApplicationState.xOffset--;
		}
		if(jumping){
			y -= currentJumpSpeed;
			
			currentJumpSpeed -= 0.2;
			
			if(currentJumpSpeed<=0){
				currentJumpSpeed=jumpSpeed;
				jumping= false;
				falling=true;
			}
		}
		if(falling){
			y+=currentFallSpeed;
			
			if(currentFallSpeed<maxFallSpeed) {
				currentFallSpeed+=0.2;
			}
		}
		
		if(!falling){
			currentFallSpeed=0.2;
		}
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect((int)x, (int)y, width, height);
	}
	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_D) {
			right = true;
			//System.out.print("x is " + x);
		}
		if(k == KeyEvent.VK_A) left = true;
		if(k == KeyEvent.VK_SPACE) jumping = true;
	}
	
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_D) right = false;
		if(k == KeyEvent.VK_A) left = false;
	}
}
