import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class BluestRed implements KeyListener{

	static JFrame frame;

	public static void init(){
		frame = new JFrame();
		frame.setSize(1200,1000);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(new BluestRed());
		for(int i = 0; i <= 1200; i+= 50){
			for(int j = 0; j < Math.random()*2; j++) Block.generate(i);	
		}
		Player.player1 = new Player(50,50,"alex");
		Player.player2 = new Player(350,50,"ekim");
		Player.player1.color = Color.red;
		Player.player2.color = Color.blue;
	}

	public static void update(){
		Player.player1.update();
		Player.player2.update();
		Player.collide();
		Block.update();
		frame.add(new Canvas());
		frame.setVisible(true);
		try{
			Thread.sleep(10);
		}catch(Exception e){

		}
	}

	public static class Canvas extends JPanel{
		public void paintComponent(Graphics g2){
			Graphics2D g = (Graphics2D) g2;
				g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
			g.setColor(Color.black);
			g.fillRect(0,0,frame.getWidth(),frame.getHeight());
			for(int i = 0; i < Block.all.size(); i++){
				Block b = Block.all.get(i);
				g.setColor(b.color);
				g.fillRect((int)b.x+4,(int)b.y+4,50,8);
			}
			g.setColor(Player.player1.color);
			g.fillOval((int)(Player.player1.x-5),(int)(Player.player1.y-5),10,10);
			g.setColor(Player.player2.color);
			g.fillOval((int)(Player.player2.x-5),(int)(Player.player2.y-5),10,10);
		}
	}
		
	public static void main(String[] args){
		init();
		while(true){
			update();
			//if(Player.lost()) break;
		}
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_S){
			Player.player1.shift += -2.6;
		}
		else if(e.getKeyCode() == KeyEvent.VK_D){
			Player.player1.shift += 2.6;
		}
		else if(e.getKeyCode() == KeyEvent.VK_K){
			Player.player2.shift += -2.6;
		}
		else if(e.getKeyCode() == KeyEvent.VK_L){
			Player.player2.shift += 2.6;
		}
	}
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_S){
			Player.player1.shift = 0;
		}
		else if(e.getKeyCode() == KeyEvent.VK_D){
			Player.player1.shift = 0;
		}
		else if(e.getKeyCode() == KeyEvent.VK_K){
			Player.player2.shift = 0;
		}
		else if(e.getKeyCode() == KeyEvent.VK_L){
			Player.player2.shift = 0;
		}
	}
	public void keyTyped(KeyEvent arg0) {
	}
}
