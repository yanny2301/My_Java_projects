import java.util.ArrayList;
import java.util.List;

abstract class Shape {
    protected String color;

    public Shape(String color) {
        this.color = color;
    }

    public abstract double area();
    public abstract double perimeter();
    public abstract void draw(); // Симуляция рисования

    // Метод сравнения по площади
    public int compareArea(Shape other) {
        return Double.compare(this.area(), other.area());
    }

    @Override
    public String toString() {
        return String.format("%s[цвет=%s, S=%.2f, P=%.2f]",
                getClass().getSimpleName(), color, area(), perimeter());
    }
}

class Circle extends Shape {
    private double radius;

    public Circle(String color, double radius) {
        super(color);
        if (radius <= 0) throw new IllegalArgumentException("Радиус должен быть > 0");
        this.radius = radius;
    }

    @Override
    public double area() { return Math.PI * radius * radius; }

    @Override
    public double perimeter() { return 2 * Math.PI * radius; }

    @Override
    public void draw() {
        System.out.println("Рисую " + color + " круг с радиусом " + radius);
    }
}

class Rectangle extends Shape {
    protected double width;
    protected double height;

    public Rectangle(String color, double width, double height) {
        super(color);
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() { return width * height; }

    @Override
    public double perimeter() { return 2 * (width + height); }

    @Override
    public void draw() {
        System.out.printf("Рисую %s прямоугольник %.1f x %.1f%n", color, width, height);
    }
}

class Square extends Rectangle {

    public Square(String color, double side) {
        super(color, side, side);
    }

    @Override
    public void draw() {
        System.out.printf("Рисую %s квадрат %.1f x %.1f%n", color, width, height);
    }


}

public class ShapeTest {
    public static void main(String[] args) {
        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Circle("красный", 5));
        shapes.add(new Rectangle("синий", 4, 6));
        shapes.add(new Square("зелёный", 5));
        shapes.add(new Circle("жёлтый", 3));

        System.out.println("=== Все фигуры ===");
        for (Shape s : shapes) {
            s.draw();
            System.out.println("  " + s);
        }

        System.out.println("\n=== Сортировка по площади ===");
        shapes.sort(Shape::compareArea);
        shapes.forEach(System.out::println);

        System.out.println("\n=== Только круги ===");
        for (Shape s : shapes) {
            if (s instanceof Circle c) {
                System.out.printf("Круг с радиусом: проверьте через toString: %s%n", c);
            }
        }

        double totalArea = shapes.stream().mapToDouble(Shape::area).sum();
        System.out.printf("%nОбщая площадь: %.2f%n", totalArea);
    }
}

