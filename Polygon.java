/*
 * Polygon.java
 * 
 * This class represents a vertices array in the Polygon and the number of vertices in that Polygon
 * @author Binyamin 
 */
import java.util.Scanner;

public class Polygon {

	private Point [] _vertices;
	private int _noOfVertices;
	private final int MAX_HEADS = 10;
	public final int DEFAULT_VAL = 0;
	public final int JUST_A_SEGMENT = 3;
	public final int SPLIT= 2;
	public final int SINGLE_POINT = 1;
	public final int MINUS_ONE = -1;
	
/*
 * Constructs a new Polygon object. If number of vertices bigger than 10 the Polygon will stop
 * adding vertices
 * @param vertices represent Polygon
 * @param noOfvertices number of vertices in the Polygon
 */
	public Polygon(){
		
		_vertices = new Point[MAX_HEADS];
		_noOfVertices = DEFAULT_VAL;
		
	}

/*
 * Returns true if the vertex is add properly to Polygon; false otherwise.
 * @returns true if the vertex is added; false otherwise
 * 
 */
	public boolean addVertex(double x, double y){
		
		if(_noOfVertices < MAX_HEADS){
			_vertices[_noOfVertices] = new Point(x, y);
			_noOfVertices++;
			return true;
		}
		return false;
	}
	
/*
 * Returns the highest vertex in the Polygon
 * @returns the highest point in the Polygon, , if the Polygon has no vertices returns null
 */
	public Point highestVertex(){
		
		if(_noOfVertices > DEFAULT_VAL) {
			Point highestPoint = new Point(0,0);
		
			for(int i =0 ; i < _noOfVertices-1 ; i++) {
				if(_vertices[i].getX() >= _vertices[i+1].getX()) 
					highestPoint = 	_vertices[i];	
				else
					highestPoint =_vertices[i+1];			
			}
				return highestPoint;
		}
			return null;
	}
	
/*
 * Returns a string representation of this Polygon.
 * @Overrides toString in class java.lang.Object
 * @returns representation of this Polygon in the following format, for example,
 * "The polygon has 5 vertices:
 * ((2.0,1.0),(5.0,0.0),(7.0,5.0),(4.0,6.0),(1.0,4.0))"
 */
	public String toString(){									//how to pull down last comma?!
		
		if(_noOfVertices == DEFAULT_VAL)
			return "The polygon has 0 vertices.";
		else {			
				String res = "";
				//String comma = ",";
				String verticesTitle = "The polygon has " + _noOfVertices +  " vertices: \n(";
		
				for(int i =0 ; i < _noOfVertices ; i++) {
					res =  res +"(" + _vertices[i].getX() + "," + _vertices[i].getY() + "),"; 
				
				
				}
					return verticesTitle + res + ")";
			}
	}	
	
/*
 * returns the perimeter of the following Polygon
 * @returns the Polygon perimeter, if number of vertices is 2 returns segment length,
 * if the number of vertices smaller than 2 returns 0
 */
	public double calcPerimeter (){
		
		
		double endStartDistance = DEFAULT_VAL;
		
		if(_noOfVertices == DEFAULT_VAL || _noOfVertices == SINGLE_POINT)				//number of vertices smaller than 2
			return DEFAULT_VAL;
		
		else if(_noOfVertices == SPLIT)									//number of vertices is 2
			return Math.sqrt((Math.pow(_vertices[1].getY() - _vertices[0].getY(), SPLIT)) 
					+  (Math.pow(_vertices[1].getX() - _vertices[0].getX() , SPLIT)));
			//return _vertices[2].getX();
		else {														// number of vertices bigger than 2					
			double res = DEFAULT_VAL;
			for(int i = 0 ; i < _noOfVertices-1 ; i++) {
				res = res + (Math.sqrt((Math.pow(_vertices[i+1].getY() - _vertices[i].getY(), SPLIT)) 
						+  (Math.pow(_vertices[i+1].getX() - _vertices[i].getX() , SPLIT))));
				endStartDistance = (Math.sqrt((Math.pow(_vertices[_noOfVertices-1].getY() - _vertices[0].getY(), SPLIT)) 
						+  (Math.pow(_vertices[_noOfVertices-1].getX() - _vertices[0].getX() , SPLIT))));		
			}
			
			return res + endStartDistance;
		}
	}

/*
 * calculate the Polygon area, we use here in heron formula to calculate this
 * @returns the Polygon area, if number of vertices smaller than 3 returns 0
 */
	public double calcArea(){
		
		
		
		double heronFormula = DEFAULT_VAL ;
		
		if(_noOfVertices < JUST_A_SEGMENT)
			return DEFAULT_VAL;
		
		for(int i = 0 ; i < _noOfVertices-1 ; i++) {
			double segmentA = (Math.sqrt((Math.pow(_vertices[i].getY() - _vertices[0].getY(), SPLIT)) 
					+  (Math.pow(_vertices[i].getX() - _vertices[0].getX() , SPLIT))));
			double segmentB = (Math.sqrt((Math.pow(_vertices[i+1].getY() - _vertices[i].getY(), SPLIT)) 
							+  (Math.pow(_vertices[i+1].getX() - _vertices[i].getX() , SPLIT))));
			double segmentC = (Math.sqrt((Math.pow(_vertices[i+1].getY() - _vertices[0].getY(), SPLIT)) 
							+  (Math.pow(_vertices[i+1].getX() - _vertices[0].getX() , SPLIT))));
			
			double halfTraianglePerimeter = (segmentA + segmentB + segmentC)/SPLIT ;
			//System.out.println(halfTraianglePerimeter);
			heronFormula = heronFormula + Math.sqrt(halfTraianglePerimeter*(halfTraianglePerimeter - segmentA)*(halfTraianglePerimeter - segmentB)* (halfTraianglePerimeter - segmentC));
			//System.out.println(heronFormula);
		}
		
		return heronFormula;
	}

/*
 * uses the last Polygon area calculating and returns the bigger Polygon from both
 * @returns true if first Polygon bigger, false otherwise
 */
	public boolean isBigger(Polygon other){
		
		if (this.calcArea() > other.calcArea())
			return true;
		return false;
	}

/*
 * returns index number of the Polygon if p equal one of its vertices
 * @param Point p random point
 * @returns index number of Polygon if equal to point p, otherwise -1
 */
	public int findVertex(Point p){
		
		for(int i =0 ; i < _noOfVertices ; i++){
			if(_vertices[i].getX() == p.getX() && _vertices[i].getY() == p.getY())
				return i;
		}
		return MINUS_ONE;
	}

/*
 * returns the next vertex to point p, if p and that random point equals
 * @param point p random Point
 * @returns point object that following p, if p is last vertex in the Polygon or number of vertices in that Polygon equal 1 returns first vertex
 * otherwise returns null
 */
	public Point getNextVertex(Point p){
		

		if((_vertices[_noOfVertices-1].getX() == p.getX() && _vertices[_noOfVertices-1].getY() == p.getY()) || _noOfVertices == SINGLE_POINT )

			return new Point (_vertices[0].getX(), _vertices[0].getY());
		
		
		for(int i =0 ; i < _noOfVertices-1 ; i++){
			if(p.getX() ==_vertices[i].getX()  && p.getY() ==_vertices[i].getY())
					return new Point(_vertices[i+1].getX(), _vertices[i+1].getY());
		}	
		
		return null;	
	}
	
/*
 * returns the blocking rectangle of the Polygon
 * @returns vertices of the blocking rectangle of the Polygon, if number of vertices smaller than 3 returns null
 */
	public Polygon getBoundingBox(){
		
		if(_noOfVertices < JUST_A_SEGMENT)
			return null;
		
		double a = 0, b = 0, c = 0, d = 0;
		
		for(int i = 0 ; i < _noOfVertices-1 ; i++) {
			//System.out.println(a + "\t" + b +"\t" + c + "\t" + d);

			if(_vertices[i].getX() <= _vertices[i+1].getX()) {					//check smallest x
				a = _vertices[i].getX();
			}else {
			    a = _vertices[i+1].getX();
			}
			
			
			if(_vertices[i].getX() >= _vertices[i+1].getX()) {					////check biggest x
				b = _vertices[i].getX();				
			}else {
			    b = _vertices[i+1].getX();				
			}
			
			
			if(_vertices[i].getY() <= _vertices[i+1].getY()) {					//check smallest y
				c = _vertices[i].getY();
			}else {
				c = _vertices[i+1].getY();
			}	
			
					
			if(_vertices[i].getY() >= _vertices[i+1].getY()) {					//check biggest y
				d = _vertices[i].getY();
			}else {
			    d = _vertices[i+1].getY();
			}
			//System.out.println(a + "\t" + b +"\t" + c + "\t" + d);

		}
		
		Polygon rectanglePolygon = new Polygon();
		rectanglePolygon.addVertex(a, c);
		rectanglePolygon.addVertex(b, c);
		rectanglePolygon.addVertex(b, d);
		rectanglePolygon.addVertex(a, d);
		
		return rectanglePolygon;
       
		
	}	
}
