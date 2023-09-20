# Polygon Class
A Java class that defines a polygon and provides operations for manipulation and analysis.

## Overview
The Polygon class represents a polygon with vertices and offers methods for calculating perimeter, area, finding vertices, and determining a bounding rectangle.

## Methods
addVertex(double x, double y): Adds a vertex to the polygon.
highestVertex(): Returns the highest point in the polygon.
toString(): Generates a string representation of the polygon.
calcPerimeter(): Calculates the perimeter of the polygon.
calcArea(): Calculates the area of the polygon using the Heron formula.
isBigger(Polygon other): Compares areas to determine if this polygon is larger.
findVertex(Point p): Finds the index of a given vertex in the polygon.
getNextVertex(Point p): Finds the next vertex after a given point.
getBoundingBox(): Determines the bounding rectangle of the polygon.
## Usage
1. Copy the Polygon class code into your Java project.
2. Create a Polygon object.
3. Use the provided methods for polygon operations.
```bash
public static void main(String[] args) {
    // Example usage of the Polygon class.
    // ...
}
```
