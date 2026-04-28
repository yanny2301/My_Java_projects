public class LoopControl {
    public static void main(String[] args) {
        // Блок 1: Найти первое простое число больше 100
        System.out.println("--- Первое простое > 100 ---");
        int n = 101;
        outer:
        while (true) {
            if (n % 2 == 0 && n != 2) {
                n++;
                continue;
            }
            for (int d = 3; d * d <= n; d += 2) {
                if (n % d == 0) {
                    n++;
                    continue outer; // Перейти к следующей итерации while
                }
            }
            break; // n — простое
        }
        System.out.println("Первое простое > 100: " + n);

        // Блок 2: Распечатать только нечётные числа, пропуская кратные 3
        System.out.println("\n--- Нечётные, не кратные 3 (от 1 до 20) ---");
        for (int i = 1; i <= 20; i++) {
            if (i % 2 == 0) continue; // Пропустить чётные
            if (i % 3 == 0) continue; // Пропустить кратные 3
            System.out.print(i + " ");
        }
        System.out.println();

        // Блок 3: Поиск в матрице — найти и сразу выйти из обоих циклов
        System.out.println("\n--- Поиск в матрице ---");
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int target = 5;
        int foundRow = -1, foundCol = -1;

        search:
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == target) {
                    foundRow = row;
                    foundCol = col;
                    break search; // Выйти из ОБОИХ циклов
                }
            }
        }

        if (foundRow != -1) {
            System.out.printf("Число %d найдено на позиции [%d][%d]%n", target, foundRow, foundCol);
        }
    }
}

