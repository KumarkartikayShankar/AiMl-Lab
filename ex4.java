class Geometry {
    public static double calculateCircleArea(double radius) {
        return Math.PI * radius * radius;
    }

    public static double calculateRectangleArea(double length, double width) {
        return length * width;
    }

    public double calculateTriangleArea(double base, double height) {
        return 0.5 * base * height;
    }
}

class ex4 {
    public static void main(String[] args) {
        double circleRadius = 5.0;
        double rectangleLength = 10.0;
        double rectangleWidth = 8.0;
        double triangleBase = 6.0;
        double triangleHeight = 4.0;

        double circleArea = Geometry.calculateCircleArea(circleRadius);
        double rectangleArea = Geometry.calculateRectangleArea(rectangleLength, rectangleWidth);

        System.out.println("Circle Area: " + circleArea);
        System.out.println("Rectangle Area: " + rectangleArea);

        Geometry geometryInstance = new Geometry();
        double triangleArea = geometryInstance.calculateTriangleArea(triangleBase, triangleHeight);
        System.out.println("Triangle Area: " + triangleArea);
    }
}