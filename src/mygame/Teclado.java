package mygame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Teclado extends KeyAdapter{
	
	private Player play;
	private Platform[] platforms;
	
	
	public Teclado(Player p, Platform[] pf) {
		play = p;
		platforms = pf;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		//System.out.println("Hello");
		char c = e.getKeyChar();
		boolean flag = false;
		int min = 1000;
		int[] ret;
		
		
		if(c == 'd' || c == 'D') {
			for(int i = 0; i < platforms.length; i++) {
				ret = play.checkCollision(platforms[i], 1);
				if(ret[0] == 1) {
					if(ret[1] < min) min = ret[1];
				}
			}
			play.walkForward(min);
			
		}
		if(c == 'w' || c == 'W') {
			for(int i = 0; i < platforms.length; i++) {
				ret = play.checkCollision(platforms[i], 4);
				if(ret[0] == 4) {
					if(ret[1] < min) min = ret[1];
				}
			}
			play.jump(min);
			
		}
		if(c == 'a' || c == 'A') {
			for(int i = 0; i < platforms.length; i++) {
				ret = play.checkCollision(platforms[i], 3);
				if(ret[0] == 3) {
					if(ret[1] < min) min = ret[1];
				}
			}
			play.walkBackward(min);
			
		}
		if(c == 's' || c == 'S') {
			for(int i = 0; i < platforms.length; i++) {
				ret = play.checkCollision(platforms[i], 2);
				if(ret[0] == 2) {
					if(ret[1] < min) min = ret[1];
				}
			}
			play.down(min);
		}
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		//System.out.println("chau");
		char c = e.getKeyChar();
		//System.out.println("oi bye"+c);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		//System.out.println("oi bye");
		char c = e.getKeyChar();
		//System.out.println("oi bye"+c);
		
	}
}
