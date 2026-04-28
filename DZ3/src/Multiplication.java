public class Multiplication {
    public static void tableMultiplication(int[] a, int[] b){
        int row = a.length;
        int col = b.length;
        for (int i = 0; i < row; i++) {
            System.out.printf("%4d", a[i]);
            for (int j = 0; j < col; j++) {
                System.out.printf("%4d", a[i] * b[j]);
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] b = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        tableMultiplication(a, b);
    }
}
/*
        int first = a.length;
        int second = b.length;
        for (int i = 0; i < first; i++) {
            for (int j = 0; j < second; j++) {
                System.out.println(a[i] + "*" + b[j] + "=" + (a[i] * b[j]));
            }
            System.out.println("");
        }
 */