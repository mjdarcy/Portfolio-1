package project_4;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Project_4 {
	
	interface Shape {
		float getArea();
		float getPerimeter();
	}
	
	class Rectangle implements Shape{
		float width, length;
		
		public Rectangle(int i, int j) {
			this.width = i;
			this.length = j;
		}

		@Override
		public float getArea() {
			return length*width;
			
		}
		
		@Override
		public float getPerimeter() {
			return length*2+width*2;
			
		}
	}
	
	class Triangle implements Shape {
		float a, b, c;
		
		public Triangle(int i, int j, int k) {
			this.a = i;
			this.b = j;
			this.c = k;
		}

		@Override
		public float getArea() {
			return a*b/2;
		}
		
		@Override
		public float getPerimeter() {
			return a+b+c;
		}
	}
	
	class Circle implements Shape {
		float radius;

		public Circle(float radius) {
			this.radius = radius;
		}
		
		@Override
		public float getArea() {
			return (float) ((float) Math.PI * Math.pow(radius, 2));
		}

		@Override
		public float getPerimeter() {
			return (float) ((float) 2*Math.PI*radius);
		}
	}

	public static void main(String[] args) {
		Project_4 p = new Project_4();
		p.testShapes();
	}
	
	void testShapes() {
		Shape circle = new Circle(5);
		Shape rectangle = new Rectangle(3, 6);
		Shape triangle = new Triangle(1, 2, 3);
		ArrayList<Shape> shapes = new ArrayList<Shape>();
		
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		
		shapes.add(circle);
		shapes.add(rectangle);
		shapes.add(triangle);
		
		for (Shape shape : shapes) {
		    System.out.println(shape.getClass().getSimpleName() + " area: " + df.format(shape.getArea()));
		    System.out.println(shape.getClass().getSimpleName() + " perimeter " + df.format(shape.getPerimeter())+"\n");
		}
	}
}