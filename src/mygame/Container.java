package mygame;

import java.awt.*;

import javax.swing.JFrame;

public class Container extends JFrame {

    public Container(){
    	add(new Fase());
        setTitle("Meu joguinho");
        setSize(928,643);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setResizable(false);
        setVisible(true);
    }
    
    public Component add(Component c) {
 
    	return super.add(c);
    	
    }

    public static void main(String[] args) throws Exception {
        new Container();
    }
}
