public class Fibonacci {
    public static int fibIterative(int n){
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        int a = 0;
        int b = 1;
        int i = 1;
        while (i <  n) {
            int current = a + b;
            a = b;
            b = current;
            i += 1;
        }
        return b;
    }
    public static int fibFor(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        int a = 0;
        int b = 1;
        for (int i = 1; i < n; i++) {
            int current = a + b;
            a = b;
            b = current;
        }
        return b;
    }
    public static void main(String[] args) {
        System.out.println("цикл while");
        for (int j = 0; j < 16; j++) {
            System.out.println(fibIterative(j));
        }
        System.out.println("цикл for");
        for (int k = 0; k < 16; k++) {
            System.out.println(fibFor(k));
        }
        int n = 0;
        while (fibFor(n) < 1000) {
            n++;
        }
        System.out.println("первое число Фибоначчи, превышающее 1000: " + fibFor(n));
    }
}
