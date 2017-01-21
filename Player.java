import java.awt.*;
public class Player{
	static Player player1, player2;
	double x,y;
	double velocity;
	double shift;
	Color color;
	String name;
	Player(int x, int y, String name){
		this.x = x;
		this.y = y;
		velocity = 0;
		shift = 0;
		this.name = name;
	}
	public void update(){
		velocity += 0.05;
		y += velocity;
		if(shift > 2.6) shift = 2.6;
		if(shift < -2.6) shift = -2.6;
		x += shift;
	}
	public static void collide(){
		for(int i = 0; i < Block.all.size(); i++){
			Block b = Block.all.get(i);
			boolean collided = false;
			if(player1.y <= b.y && player1.velocity > 1 && player1.x <= b.x+51 && player1.x >= b.x && (int)Math.abs((player1.y - b.y)) <= 5){
				player1.velocity = -2-Block.speed;
				collided = true;
				Block.speed += 0.05;
				player1.color = b.color;
				b.color = Color.black;
			}
			if(player2.y <= b.y && player2.velocity > 1 && player2.x <= b.x+51 && player2.x >= b.x && (int)Math.abs((player2.y - b.y)) <= 5){
				player2.velocity = -2-Block.speed;
				collided = true;
				Block.speed += 0.05;
				player2.color = b.color;
				b.color = Color.black;
			}
		}
		Block.checkOut();
	}
	public static boolean lost(){
		if(player1.y > 1000 || player2.y > 1000) return true;
		return false;
	}
}
