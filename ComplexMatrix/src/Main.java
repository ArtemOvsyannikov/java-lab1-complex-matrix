import java.util.Random;

public class Main {
    public static void testMatrix() {
        Random random = new Random();
        Matrix m1 = new Matrix(2, 2);
        Matrix m2 = new Matrix(2, 2);

        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 2; col++) {
                m1.setElement(row, col, new ComplexNumber(random.nextInt(-5, 5), random.nextInt(-5, 5)));
                m2.setElement(row, col, new ComplexNumber(random.nextInt(-5, 5), random.nextInt(-5, 5)));
            }
        }

        System.out.println("Matrix 1:\n" + m1 + "\n");
        System.out.println("Matrix 2:\n" + m2 + "\n");

        System.out.println("Addition:\n" + m1.add(m2) + "\n");
        System.out.println("Subtraction:\n" + m1.subtract(m2) + "\n");
        System.out.println("Multiplication:\n" + m1.multiply(m2) + "\n");

        System.out.println("Transpose of Matrix 1:\n" + m1.transpose() + "\n");
        System.out.println("Transpose of Matrix 2:\n" + m2.transpose() + "\n");

        System.out.println("Determinant of Matrix 1: " + m1.determinant() + "\n");
        System.out.println("Determinant of Matrix 2: " + m2.determinant() + "\n");
    }

    public static void main(String[] args) {
        testMatrix();
    }
}
