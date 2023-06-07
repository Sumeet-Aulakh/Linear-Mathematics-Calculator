public class MatrixInverseUsingArgument {
    private Matrix inverse;
    private double det;

    public MatrixInverseUsingArgument(Matrix matrix) throws IllegalArgumentException{
        det = matrix.determinant();
        if(det == 0.0) throw new IllegalArgumentException("Singular Matrix");
        Matrix argument = matrix.argument();
        Matrix inverse = argument.transpose();
        for (int i = 1; i < inverse.getRowNum()+1; i++) {
            for (int j = 1; j < inverse.getColNum()+1; j++) {
                inverse.setElement(i, j, inverse.getElement(i, j)/det);
            }
        }
        this.inverse = inverse;
    }

    public Matrix getInverse() {
        return inverse;
    }
}
