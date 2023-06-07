public class Determinant {
    Matrix matrix;
    double determinant;

    public Determinant(Matrix matrix) throws IllegalArgumentException{
        if(!matrix.isSquareMatrix())
            throw new IllegalArgumentException("The matrix is not a square matrix");
        determinant = det(matrix);
    }

    private double det(Matrix matrix) {
        double result = 0;
        if (matrix.getColNum() == 1 && matrix.getRowNum() == 1)
            return matrix.getElement(1, 1);
        for (int i = 1; i < matrix.getRowNum()+1; i++) {
            result+= Math.pow(-1,1+i)*(matrix.getElement(1,i))*det(matrix.omit(1, i));
        }
        return result;
    }

    public double getDeterminant() {
        return determinant;
    }
}
