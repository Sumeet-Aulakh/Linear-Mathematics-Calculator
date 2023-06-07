public class Matrix {
    private int rowNum;
    private int colNum;
    private double[] entries;
    private boolean isSquareMatrix;

    private double[][] matrix;
    private boolean integerEntries = false;

    public Matrix(int rowNum, int colNum, int... entries) throws IllegalArgumentException {
        this.rowNum = rowNum;
        this.colNum = colNum;
        integerEntries = true;

        if (entries.length != rowNum*colNum) throw new IllegalArgumentException("Number of entries are incorrect");

        double[] doubleEntries = new double[rowNum*colNum];
        int count = 0;
        for (int i = 0; i < rowNum*colNum; i++) {
                doubleEntries[i] = Double.valueOf(entries[count++]);
        }
        this.entries = doubleEntries; 

        isSquareMatrix = checkSquareMatrix(doubleEntries);
        matrix = new double[rowNum][colNum];
        int counter = 0;
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                matrix[i][j] = doubleEntries[counter++];
            }
        }
        
    }

    public Matrix(int rowNum, int colNum, double... entries) throws IllegalArgumentException {
        this.rowNum = rowNum;
        this.colNum = colNum;
        this.entries = entries;
        if (entries.length != rowNum*colNum) throw new IllegalArgumentException("Number of entries are incorrect");
        isSquareMatrix = checkSquareMatrix(entries);
        matrix = new double[rowNum][colNum];
        int count = 0;
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                matrix[i][j] = entries[count++];
            }
        }

    }

    private boolean checkSquareMatrix(double... entries) {
        return (Math.sqrt(entries.length) - Math.floor(Math.sqrt(entries.length)) == 0);
    }

    public double getElement(int i, int j) throws IllegalArgumentException{
        i--;j--;
        if (i > rowNum || i < 0 || j > colNum || j < 0)
            throw new IllegalArgumentException("Invalid index");
        return matrix[i][j];
    }

    public double[] getRow(int index) {
        index--;
        if (index > rowNum || index < 0)
            throw new IllegalArgumentException("Invalid index");
        double[] result = new double[matrix[0].length];
        for (int i = 0; i < (matrix[0].length); i++) {
            result[i] = matrix[index][i];
        }
        return result;
    }

    public double[] getCol(int index) throws IllegalArgumentException{
        index--;
        if(index > colNum || index < 0)
            throw new IllegalArgumentException("Invlid index");
        double[] result = new double[matrix.length];
        for (int j = 0; j < (matrix.length); j++) {
            result[j] = matrix[j][index];
        }
        return result;
    }

    public void printMatrix() {
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (integerEntries)
                    System.out.print((int)matrix[i][j]+" ");
                else
                    System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }    
    }

    public void printRow(int row) throws IllegalArgumentException {
        if (row < 1 || row > rowNum+1)
            throw new IllegalArgumentException("Invalid Row Index");
        for (double d : getRow(row)) {
            if (integerEntries)
                System.out.print((int)d+" ");
            else
                System.out.print(d+" ");
        }
        System.out.println();
    }

    public void printCol(int col) throws IllegalArgumentException {
        if (col < 1 || col > colNum+1)
            throw new IllegalArgumentException("Invalid Column Index");
        for (double d : getCol(col)) {
            if (integerEntries)
                System.out.println((int)d);
            else
                System.out.println(d);
        }
    }
    public double setElement(int i, int j, double elem) throws IllegalArgumentException{
        i--;j--;
        if (i > rowNum || i < 0 || j > colNum || j < 0)
            throw new IllegalArgumentException("Invalid index");
        double result = matrix[i][j];
        matrix[i][j] = elem;

        return result;
    }

    public double determinant() throws IllegalStateException {
        if (!isSquareMatrix)
            throw new IllegalStateException("The matrix is not a square matrix");
        Determinant d = new Determinant(this);
        return d.getDeterminant();
        
    }

    public int getColNum() {
        return colNum;
    }

    public double[][] getMatrix() {
        return matrix;
    }
    public int getRowNum() {
        return rowNum;
    }
    public boolean isSquareMatrix() {
        return this.isSquareMatrix;
    }

    public Matrix omit(int i, int j) throws IllegalArgumentException {
        i--;j--;
        if (i < 0 || j < 0 || i > rowNum || j > colNum)
            throw new IllegalArgumentException("Invalid row selection and/or column selection");
        Matrix newMatrix;
        if (integerEntries) {
            int[] newEntries = new int[(rowNum-1)*(colNum-1)];
            int number = 0;
            for (int count = 0; count < matrix.length; count++) {
                for (int count2 = 0; count2 < matrix[0].length; count2++) {
                    if (count != i && count2 != j)
                        newEntries[number++] = (int)matrix[count][count2];
                }
            }
            newMatrix = new Matrix(rowNum-1, colNum-1, newEntries);
        }
        else {
            double[] newEntries = new double[(rowNum-1)*(colNum-1)];
            int number = 0;
            for (int count = 0; count < matrix.length; count++) {
                for (int count2 = 0; count2 < matrix[0].length; count2++) {
                    if (count != i && count2 != j)
                        newEntries[number++] = matrix[count][count2];
                }
            }
            newMatrix = new Matrix(rowNum-1, colNum-1, newEntries);
        }
        return newMatrix;
    }

    public double minor(int i, int j) throws IllegalArgumentException {
        if(!isSquareMatrix) throw new IllegalArgumentException("Not a square matrix");
        Matrix m = omit(i, j);
        return new Determinant(m).getDeterminant();
    }

    public double cofactor(int i, int j) throws IllegalArgumentException {
        return Math.pow(-1, i+j)*minor(i, j);
    }

    public Matrix transpose() {
        double[] newEntries = new double[rowNum*colNum];
        int count = 0;
        for (int i = 1; i < colNum+1; i++) {
            double[] column = getCol(i);
            for (double d : column)
                newEntries[count++] = d;
        }
        return new Matrix(colNum, rowNum, newEntries);
    }

    public Matrix argument() throws IllegalArgumentException{
        if(!isSquareMatrix) throw new IllegalArgumentException("Not a square matrix");
        double[] newEntries = new double[rowNum*colNum];
        int count = 0; 
        for (int i = 1; i < rowNum+1; i++) {
            for (int j = 1; j < colNum+1; j++) {
                newEntries[count++] = cofactor(i, j);
            }
        }
        return new Matrix(rowNum, colNum, newEntries);
    }

    public Matrix inverse() {
        MatrixInverseUsingArgument m = new MatrixInverseUsingArgument(this);
        return m.getInverse();
    }

}
