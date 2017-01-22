import java.awt.*;
import java.util.*;

public class Block{

	static double speed = 1.8;
	static ArrayList<Block> all = new ArrayList<Block>();
	static ArrayList<Block> fallen = new ArrayList<Block>();
	double x, y;
	Color color;

	Block(int x, int y, Color color){
		this.color = color;
		this.x = x;
		this.y = y;
		all.add(this);
	}

	public static void checkOut(){
		for(int i = 0; i < Block.all.size(); i++){
			if(Block.all.get(i).color == Color.black){
				Block.all.remove(i);
				i--;
			}
		}
	}

	public static void remove(int y){
		int removed = 0;
		for(int i = 0; i < Block.all.size(); i++){
			Block a = Block.all.get(i);
			for(int j = 0; j < Block.all.size(); j++){
				Block b = Block.all.get(j);
				if(a.y == y && b.y == y){
					if(a != b && (a.x < b.x+50 && a.x > b.x) || (a.x+50 < b.x+50 && a.x+50 > b.x)){
						Block.all.remove(j);
						removed++;
					}
				}
			}
		}
		for(int i = 0; i < removed; i++) generate(y);
	}

	public static void generate(int y){
		int x = (int)(Math.random()*1150);
		int red = (int)(Math.random()*2);
		int blue = (int)(Math.random()*2);
		int green = (int)(Math.random()*2);
		if(red == 0 && green == 0 && blue == 0){
			red = 1; green = 1; blue = 1;
		}
		new Block(x,y,new Color(red*255,green*255,blue*255));
		remove(y);
	}

	public static void update(){
		boolean removed = false;
		for(int i = 0; i < all.size(); i++){
			Block b = all.get(i);
			if(fallen.contains(b)) b.y = b.y * 1.007;
			b.y += speed;
			if(b.y > 1210){
				removed = true;
				if(fallen.contains(b)) removed = false;
				all.remove(i);
				i--;
			}
		}
		if(removed) {
			for(int j = 0; j < Math.random()*2+1; j++){
				generate(-10);
			}
		}
	}
}
