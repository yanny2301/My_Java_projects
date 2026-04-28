public class MatrixOperations {
    public static void print(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.printf("%4d", matrix[i][j]); // внутренний цикл - вывод одного числа
            }
            System.out.println(); //внешний цикл - вывод всей матрицы
        }
    }

    static int[][] transpose(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] transposedMatrix = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposedMatrix[j][i] = matrix[i][j];
            }
        }
        return transposedMatrix;
    }

    static int[][] multiply(int[][] a, int[][] b){
        int rowsOne = a.length;
        int colsOne = a[0].length;

        int rowsTwo = b.length;
        int colsTwo = b[0].length;

        if (colsOne == rowsTwo) {
            int[][] multiplyMatrix = new int[rowsOne][colsTwo];
            for (int i = 0; i < rowsOne; i++) {
                for (int j = 0; j < colsTwo; j++) {
                    for (int k = 0; k < colsOne; k++) {
                        multiplyMatrix[i][j] += a[i][k] * b[k][j];
                    }
                }
            }
            return multiplyMatrix;
        }
        else {
            return null;
        }
    }

    static int diagonalSum(int[][] matrix) {
        int rows = matrix.length;
        int sum = 0;

        for (int i = 0; i < rows; i++) {
            sum += matrix[i][i];

        }
        return sum;
    }

    public static void main(String[] args) {
        int[][] a = {
                {1, 2, 3},
                {4, 5, 6}
        };

        int[][] b = {
                {7,  8},
                {9,  10},
                {11, 12}
        };

        System.out.println("Матрица A (2x3):");
        print(a);

        System.out.println("\nТранспонированная A (3x2):");
        print(transpose(a));

        System.out.println("\nМатрица B (3x2):");
        print(b);

        int[][] c = multiply(a, b);
        System.out.println("\nA * B (2x2):");
        print(c);

        System.out.println("\nСумма диагонали A*B: " + diagonalSum(c));
    }
}

