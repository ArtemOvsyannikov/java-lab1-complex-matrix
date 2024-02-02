public class Matrix {
    private ComplexNumber[][] elements;
    private int rows, columns;

    public Matrix(int rows, int columns, ComplexNumber x) {
        this.rows = rows; this.columns = columns;
        elements = new ComplexNumber[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                elements[row][column] = x;
            }
        }
    }

    public Matrix(int rows, int columns) {
        this(rows, columns, new ComplexNumber());
    }

    public Matrix(Matrix rhs) {
        this.rows = rhs.rows; this.columns = rhs.columns;
        this.elements = new ComplexNumber[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                elements[row][column] = new ComplexNumber(rhs.elements[row][column]);
            }
        }
    }

    public ComplexNumber getElement(int row, int col) { return elements[row][col]; }
    public void setElement(int row, int col, ComplexNumber value) { elements[row][col] = value; }

    public int getRows() { return rows; }
    public int getColumns() { return columns; }

    private void checkDimensions(Matrix rhs) {
        if (this.rows != rhs.rows || this.columns != rhs.columns) {
            throw new IllegalArgumentException("Matrices dimensions are not the same to complete the operation");
        }
    }

    public Matrix add(Matrix other) {
        checkDimensions(other);
        Matrix result = new Matrix(rows, columns);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                result.elements[row][col] = elements[row][col].add(other.elements[row][col]);
            }
        }
        return result;
    }

    public Matrix subtract(Matrix other) {
        checkDimensions(other);
        Matrix result = new Matrix(rows, columns);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                result.elements[row][col] = elements[row][col].subtract(other.elements[row][col]);
            }
        }
        return result;
    }

    public Matrix multiply(Matrix rhs) {
        if (columns != rhs.rows) {
            throw new IllegalArgumentException("The number of columns in the first matrix must be equal to the number of " +
                    "rows in the second matrix to complete the multiplication operation");
        }

        Matrix result = new Matrix(rows, rhs.columns);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < rhs.columns; col++) {
                for (int k = 0; k < columns; k++) {
                    result.elements[row][col] = result.elements[row][col].add(elements[row][k].multiply(rhs.elements[k][col]));
                }
            }
        }
        return result;
    }

    public Matrix transpose() {
        Matrix result = new Matrix(columns, rows);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                result.elements[col][row] = elements[row][col];
            }
        }
        return result;
    }

    public Matrix getMinor(int rows, int columns) {
        Matrix minor = new Matrix(rows - 1, columns - 1);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < rows; col++) {
                if (row != rows && col != columns) {
                    int minorRow = row < rows ? row : row - 1;
                    int minorCol = col < columns ? col : col - 1;
                    minor.elements[minorRow][minorCol] = elements[row][col];
                }
            }
        }
        return minor;
    }

    public ComplexNumber determinant() {
        if (rows != columns) {
            throw new IllegalArgumentException("Matrix must be square to calculate determinant");
        }
        if (rows == 1) {
            return elements[0][0];
        } else if (rows == 2) {
            return elements[0][0].multiply(elements[1][1]).subtract(elements[0][1].multiply(elements[1][0]));
        } else {
            ComplexNumber det = new ComplexNumber();
            for (int c = 0; c < columns; c++) {
                Matrix minor = getMinor(0, c);
                ComplexNumber factor = elements[0][c].multiply(minor.determinant());
                det = c % 2 == 0 ? det.add(factor) : det.subtract(factor);
            }
            return det;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                sb.append(String.format("%-12s", elements[row][col]));
            }
            if (row != rows - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
