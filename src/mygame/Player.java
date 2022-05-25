package mygame;

import java.awt.*;

import javax.swing.*;

public class Player {
	private int life;
	private int posX, posY;
	private int dX, dY;
	private Image[] player, runing, jumping, runingBack, falling;
	private int width, height;
	private int dXd, dYd;
	
	public int getdX() {
		return dX;
	}

	public void setdX(int dX) {
		this.dX = dX;
	}

	public int getdY() {
		return dY;
	}

	public void setdY(int dY) {
		this.dY = dY;
	}

	public int getPosX() {
		posX = posX + dX;
		dX = 0;
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		posY = posY + dY;
		dY = 0;
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public Image getPlayer(int frameNumber) {
		
		if(dX == 0 && dY == 0) {
			return player[frameNumber%6];
		} else if(dX > 0){
			return runing[frameNumber%8];
		} else if(dX < 0) {
			return runingBack[frameNumber%8];
		} else if(dY > 0) {
			return falling[frameNumber%3];
		} else if(dY < 0) {
			return jumping[frameNumber%3];
		} else {
			return player[frameNumber%6];
		}
		
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public Player() {
		
		runing = new Image[8];
		player = new Image[6];
		runingBack = new Image[8];
		jumping = new Image[3];
		falling = new Image[3];
		
		ImageIcon player_img;// = new ImageIcon("resources\\Player//Warrior_Idle_1.png");
		
		for(int i = 0; i < 8; i++) {
			player_img = new ImageIcon("resources\\Player//Warrior_Run_"+(i+1)+".png");
			runing[i] = player_img.getImage();
		}
		
		for(int i = 0; i < 6; i++) {
			player_img = new ImageIcon("resources\\Player//Warrior_Idle_"+(i+1)+".png");
			player[i] = player_img.getImage();
		}
		
		for(int i = 0; i < 8; i++) {
			player_img = new ImageIcon("resources\\Player//Warrior_RunBack_"+(i+1)+".png");
			runingBack[i] = player_img.getImage();
		}
		
		for(int i = 0; i < 3; i++) {
			player_img = new ImageIcon("resources\\Player//Warrior_Jump_"+(i+1)+".png");
			jumping[i] = player_img.getImage();
		}
		
		for(int i = 0; i < 3; i++) {
			player_img = new ImageIcon("resources\\Player//Warrior_Fall_"+(i+1)+".png");
			falling[i] = player_img.getImage();
		}
	    
	    width = player[0].getWidth(null);
	    height = player[0].getHeight(null);
		life = 10;
		posX = 100;
		posY = 535;
		dX = 0;
		dY = 0;
		dXd = 10;
		dYd = 10;
	}
	
	public int[] checkCollision(Platform p, int sent) {
		
		int[] ret;
		ret = new int[2];
		ret[0] = 0;
		ret[1] = 0;
		int posX = this.posX;
		int posY = this.posY;
		
		if(sent == 1) {
			ret[0] = 0;
			ret[1] = dXd;
			for(; posX < this.posX + dXd; posX++) {
				if((posX >= p.getX1() && posX <= p.getX2()) && (posY >= p.getY1() && posY <= p.getY2())) {
					//ret = true;
					System.out.println("entrei 1 " + p.getX2() +" "+ posX + " " + p.getY2() +" "+ posY);
					if(p.getX2() == posX)           {  ret[1] = (posX - this.posX) - 1; ret[0] = 3;}//3;             //quadrado a esquerda
					if(p.getY2() == posY)           {  ret[1] = (posX - this.posX) - 1; ret[0] = 4;}//4;             //quadrado acima
					
					return ret;
				}
				if((posX + width >= p.getX1() && posX + width <= p.getX2()) && (posY >= p.getY1() && posY <= p.getY2())) {
					//ret = true;
					System.out.println("entrei 2 " + p.getX1() +" "+ (posX + width) + " " + p.getY2() +" "+ posY);
					if(p.getX1() == posX + width)   {  ret[1] = (posX - this.posX) - 1; ret[0] = 1;}//1;             //quadrado a direita
					if(p.getY2() == posY)           {  ret[1] = (posX - this.posX) - 1; ret[0] = 4;}//4;             //quadrado acima
					return ret;
				}
				if((posX >= p.getX1() && posX <= p.getX2()) && (posY + height  >= p.getY1() && posY + height <= p.getY2())) {
					//ret = true;
					System.out.println("entrei 3 " + p.getX2() +" "+ posX + " " + p.getY1() +" "+ (posY + height));
					if(p.getX2() == posX)           {  ret[1] = (posX - this.posX) - 1; ret[0] = 3;}//3;             //quadrado a esquerda
					if(p.getY1() == posY + height)  {  ret[1] = (posX - this.posX) - 1; ret[0] = 2;}//2;             //quadrado abaixo
					return ret;
				}
				if((posX + width >= p.getX1() && posX + width <= p.getX2()) && (posY + height >= p.getY1() && posY + height <= p.getY2())) {
					//ret = true;
					System.out.println("entrei 4 " + p.getX1() +" "+ (posX + width) + " " + p.getY1() +" "+ (posY + height));
					if(p.getX1() == posX + width)   {  ret[1] = (posX - this.posX) - 1; ret[0] = 1;}//1;             //quadrado a direita
					if(p.getY1() == posY + height)  {  ret[1] = (posX - this.posX) - 1; ret[0] = 2;}//2;             //quadrado abaixo
					return ret;
				}
			}
		}else if(sent == 2) {
			ret[0] = 0;
			ret[1] = dYd;
			for(; posY < this.posY + dYd; posY++) {
				if((posX >= p.getX1() && posX <= p.getX2()) && (posY >= p.getY1() && posY <= p.getY2())) {
					//ret = true;
					System.out.println("entrei 1 " + p.getX2() +" "+ posX + " " + p.getY2() +" "+ posY);
					if(p.getX2() == posX)           {  ret[1] = (posY - this.posY) - 1; ret[0] = 3;}//3;             //quadrado a esquerda
					if(p.getY2() == posY)           {  ret[1] = (posY - this.posY) - 1; ret[0] = 4;}//4;             //quadrado acima
					
					return ret;
				}
				if((posX + width >= p.getX1() && posX + width <= p.getX2()) && (posY >= p.getY1() && posY <= p.getY2())) {
					//ret = true;
					System.out.println("entrei 2 " + p.getX1() +" "+ (posX + width) + " " + p.getY2() +" "+ posY);
					if(p.getX1() == posX + width)   {  ret[1] = (posY - this.posY) - 1; ret[0] = 1;}//1;             //quadrado a direita
					if(p.getY2() == posY)           {  ret[1] = (posY - this.posY) - 1; ret[0] = 4;}//4;             //quadrado acima
					return ret;
				}
				if((posX >= p.getX1() && posX <= p.getX2()) && (posY + height  >= p.getY1() && posY + height <= p.getY2())) {
					//ret = true;
					System.out.println("entrei 3 " + p.getX2() +" "+ posX + " " + p.getY1() +" "+ (posY + height));
					if(p.getX2() == posX)           {  ret[1] = (posY - this.posY) - 1; ret[0] = 3;}//3;             //quadrado a esquerda
					if(p.getY1() == posY + height)  {  ret[1] = (posY - this.posY) - 1; ret[0] = 2;}//2;             //quadrado abaixo
					return ret;
				}
				if((posX + width >= p.getX1() && posX + width <= p.getX2()) && (posY + height >= p.getY1() && posY + height <= p.getY2())) {
					//ret = true;
					System.out.println("entrei 4 " + p.getX1() +" "+ (posX + width) + " " + p.getY1() +" "+ (posY + height));
					if(p.getX1() == posX + width)   {  ret[1] = (posY - this.posY) - 1; ret[0] = 1;}//1;             //quadrado a direita
					if(p.getY1() == posY + height)  {  ret[1] = (posY - this.posY) - 1; ret[0] = 2;}//2;             //quadrado abaixo
					return ret;
				}
			}
		}else if(sent == 3) {
			ret[0] = 0;
			ret[1] = dXd;
			for(; posX > this.posX - dXd; posX--) {
				if((posX >= p.getX1() && posX <= p.getX2()) && (posY >= p.getY1() && posY <= p.getY2())) {
					//ret = true;
					System.out.println("entrei 1 " + p.getX2() +" "+ posX + " " + p.getY2() +" "+ posY);
					if(p.getX2() == posX)          {  ret[1] = (this.posX - posX) - 1; ret[0] = 3;}//3;             //quadrado a esquerda
					if(p.getY2() == posY)          {  ret[1] = (this.posX - posX) - 1; ret[0] = 4;}//4;             //quadrado acima
					
					return ret;
				}
				if((posX + width >= p.getX1() && posX + width <= p.getX2()) && (posY >= p.getY1() && posY <= p.getY2())) {
					//ret = true;
					System.out.println("entrei 2 " + p.getX1() +" "+ (posX + width) + " " + p.getY2() +" "+ posY);
					if(p.getX1() == posX + width)  {  ret[1] = (this.posX - posX) - 1; ret[0] = 1;}//1;             //quadrado a direita
					if(p.getY2() == posY)          {  ret[1] = (this.posX - posX) - 1; ret[0] = 4;}//4;             //quadrado acima
					return ret;
				}
				if((posX >= p.getX1() && posX <= p.getX2()) && (posY + height  >= p.getY1() && posY + height <= p.getY2())) {
					//ret = true;
					System.out.println("entrei 3 " + p.getX2() +" "+ posX + " " + p.getY1() +" "+ (posY + height));
					if(p.getX2() == posX)          {  ret[1] = (this.posX - posX) - 1; ret[0] = 3;}//3;             //quadrado a esquerda
					if(p.getY1() == posY + height) {  ret[1] = (this.posX - posX) - 1; ret[0] = 2;}//2;             //quadrado abaixo
					return ret;
				}
				if((posX + width >= p.getX1() && posX + width <= p.getX2()) && (posY + height >= p.getY1() && posY + height <= p.getY2())) {
					//ret = true;
					System.out.println("entrei 4 " + p.getX1() +" "+ (posX + width) + " " + p.getY1() +" "+ (posY + height));
					if(p.getX1() == posX + width)  {  ret[1] = (this.posX - posX) - 1; ret[0] = 1;}//1;             //quadrado a direita
					if(p.getY1() == posY + height) {  ret[1] = (this.posX - posX) - 1; ret[0] = 2;}//2;             //quadrado abaixo
					return ret;
				}
			}
		}else if(sent == 4) {
			ret[0] = 0;
			ret[1] = dYd;
			for(; posY > this.posY - dYd; posY--) {
				if((posX >= p.getX1() && posX <= p.getX2()) && (posY >= p.getY1() && posY <= p.getY2())) {
					//ret = true;
					System.out.println("entrei 1 " + p.getX2() +" "+ posX + " " + p.getY2() +" "+ posY);
					if(p.getX2() == posX)          {  ret[1] = (this.posY - posY) - 1; ret[0] = 3;}//3;             //quadrado a esquerda
					if(p.getY2() == posY)          {  ret[1] = (this.posY - posY) - 1; ret[0] = 4;}//4;             //quadrado acima
					
					return ret;
				}
				if((posX + width >= p.getX1() && posX + width <= p.getX2()) && (posY >= p.getY1() && posY <= p.getY2())) {
					//ret = true;
					System.out.println("entrei 2 " + p.getX1() +" "+ (posX + width) + " " + p.getY2() +" "+ posY);
					if(p.getX1() == posX + width)  {  ret[1] = (this.posY - posY) - 1; ret[0] = 1;}//1;             //quadrado a direita
					if(p.getY2() == posY)          {  ret[1] = (this.posY - posY) - 1; ret[0] = 4;}//4;             //quadrado acima
					return ret;
				}
				if((posX >= p.getX1() && posX <= p.getX2()) && (posY + height  >= p.getY1() && posY + height <= p.getY2())) {
					//ret = true;
					System.out.println("entrei 3 " + p.getX2() +" "+ posX + " " + p.getY1() +" "+ (posY + height));
					if(p.getX2() == posX)          {  ret[1] = (this.posY - posY) - 1; ret[0] = 3;}//3;             //quadrado a esquerda
					if(p.getY1() == posY + height) {  ret[1] = (this.posY - posY) - 1; ret[0] = 2;}//2;             //quadrado abaixo
					return ret;
				}
				if((posX + width >= p.getX1() && posX + width <= p.getX2()) && (posY + height >= p.getY1() && posY + height <= p.getY2())) {
					//ret = true;
					System.out.println("entrei 4 " + p.getX1() +" "+ (posX + width) + " " + p.getY1() +" "+ (posY + height));
					if(p.getX1() == posX + width)  {  ret[1] = (this.posY - posY) - 1; ret[0] = 1;}//1;             //quadrado a direita
					if(p.getY1() == posY + height) {  ret[1] = (this.posY - posY) - 1; ret[0] = 2;}//2;             //quadrado abaixo
					return ret;
				}
			}
		}
		
		//System.out.println(""+ret);
		
		return ret;
	}

	public void walkForward(int k) {
		System.out.println(""+k);
		if(k > dXd) {
			dX = dXd;
		}else {dX = k;}
	}
	
	public void walkBackward(int k) {
		System.out.println(""+k);
		if(k > dXd) {
			dX = -dXd;
		}else {dX = -k;}
	}
	
	public void jump(int k) {
		System.out.println(""+k);
		if(k > dYd) {
			dY = -dYd;
		}else {dY = -k;}
	}
	
	public void down(int k) {
		System.out.println(""+k);
		if(k > dYd) {
			dY = dYd;
		}else {dY = k;}
	}

}
