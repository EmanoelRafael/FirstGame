package mygame;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class Fase extends JPanel implements ActionListener{
	private Image[] fundo;
	private Player player;
	private Timer time;
	private int frameNumber;
	private Platform[] platforms;
	
	public Fase() {
		setFocusable(true);
		setDoubleBuffered(true);
		
		frameNumber = 0;
		
		fundo = new Image[12];
		
		ImageIcon ref;
		
		for(int i = 0; i < 12; i++) {
			ref = new ImageIcon("resources\\background//Layer_"+(11-i)+".png");
			fundo[i] = ref.getImage();
		}
		
		player = new Player();
		
		platforms = new Platform[3];
		platforms[0] = new Platform(0, 535+player.getHeight(), 928, 643);
		platforms[1] = new Platform(100, 100, 200, 200);
		platforms[2] = new Platform(500, 100, 600, 200);
		
		
		addKeyListener(new Teclado(player, platforms));
		time = new Timer(40, this);
		time.start();
	}
	
	public void paint(Graphics g) {
		Graphics2D grap = (Graphics2D) g;
		frameNumber++;
		for(int i = 0; i < 12; i++) {
			grap.drawImage(fundo[i], 0, -150, null);
		}
		//grap.drawImage(player.getPlayer(frameNumber), player.getPosX(), player.getPosY(), this);
		/*for(int i = 8; i < 12; i++) {
			grap.drawImage(fundo[i], 0, -150, null);
		}*/
		grap.drawImage(player.getPlayer(frameNumber), player.getPosX(), player.getPosY(), this);
		
		grap.setColor(Color.pink);
		grap.drawRect(0, 535+player.getHeight(), 928, 643 - (535+player.getHeight()));
		
		grap.drawRect(100, 100, 100, 100);
		grap.drawRect(500, 100, 100, 100);
		grap.drawRect(player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight());
		
		g.dispose();
	}
	
	
	
	
	
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
	
}
