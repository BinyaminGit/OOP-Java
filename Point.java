
	
	import java.util.Scanner;
public class Point {
	
	private int _x;
	private int _y;
	
	public Point(int x, int y){
		_x = x;
		_y = y;		
	}
	public int getX(){
		return _x;
	}
	public int getY(){
		return _y;
	}
	public void setX(int a){
		_x = a;
	}
	public void setY(int b){
		_y = b;
	}
	public String toString(){
		return "(<" + _x + ">,<" + _y + ">)";
	}
	
}
