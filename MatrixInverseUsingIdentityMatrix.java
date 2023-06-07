public class MatrixInverseUsingIdentityMatrix {
    Matrix identity;

    public MatrixInverseUsingIdentityMatrix(Matrix matrix) throws IllegalArgumentException{
        if(!matrix.isSquareMatrix()) throw new IllegalArgumentException("Not a square matrix");
        if(matrix.determinant() == 0.0) throw new IllegalArgumentException("Singular Matrix");

        int[] identityEntries = new int[matrix.getColNum()*matrix.getRowNum()];
        for (int i = 0; i < matrix.getRowNum()*matrix.getColNum(); i++) {
            identityEntries[i] = 0;
        }
        identity = new Matrix(matrix.getRowNum(), matrix.getColNum(), identityEntries);

        for (int i = 1; i < identity.getRowNum()+1; i++) {
            for (int j = 1; j < identity.getColNum()+1; j++) {
                if (i==j) 
                    identity.setElement(i, j, 1);
            }
        }
        identity.printMatrix();


    }   
}
