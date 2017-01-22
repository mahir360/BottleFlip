import java.awt.*;
public class Player{
	static Player player1, player2;
	int size;
	double x,y;
	double velocity;
	double shift;
	Color color;
	String name;
	boolean lost = false;
	Player(int x, int y, String name){
		this.x = x;
		this.y = y;
		velocity = 0;
		shift = 0;
		this.name = name;
		size = 4;
	}
	public void update(){
		velocity += 0.055;
		y += velocity;
		if(shift > 10.8) shift = 10.8;
		if(shift < -10.8) shift = -10.8;
		x += shift;
	}
	public static void hit(){
		if(Math.sqrt(Math.pow(player1.y - player2.y,2) + Math.pow(player1.x - player2.x,2) ) < player1.size + player2.size){
			if(player1.size < player2.size) 		player1.velocity += 6;
			else if(player1.size > player2.size)	player2.velocity += 6;
		}
	}
	public static void collide(){
		for(int i = 0; i < Block.all.size(); i++){
			Block b = Block.all.get(i);
			boolean collided = false;
			if(player1.y <= b.y && player1.velocity > 1 && player1.x <= b.x+51 && player1.x >= b.x && (int)Math.abs((player1.y - b.y)) <= 5){
				player1.velocity = -2-Block.speed*1.2;
				collided = true;
				Block.speed += 0.005;
				if(player1.color.equals(b.color)){
					if(player1.size < 26) player1.size += 2;
				}
				else player1.size = 4;
				player1.color = b.color;
				Block.fallen.add(b);
			}
			if(player2.y <= b.y && player2.velocity > 1 && player2.x <= b.x+51 && player2.x >= b.x && (int)Math.abs((player2.y - b.y)) <= 5){
				player2.velocity = -2-Block.speed*1.2;
				collided = true;
				Block.speed += 0.005;
				if(player2.color.equals(b.color)){
					if(player2.size < 26) player2.size += 2;
				}
				else player2.size = 4;
				player2.color = b.color;
				Block.fallen.add(b);
			}
		}
	}
	public static void lost(){
		if(player1.y > 1000) player1.lost = true;
		else if(player2.y > 1000) player2.lost = true;
	}
}
