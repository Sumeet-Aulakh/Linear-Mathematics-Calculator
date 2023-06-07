public class MatrixDemo {
    public static void main(String[] args) {
        Matrix m = new Matrix(3, 3, 1,0,0,0,1,0,0,0,1);
        System.out.println(m.determinant());
        System.out.println();
        m.inverse().printMatrix();
    }
}