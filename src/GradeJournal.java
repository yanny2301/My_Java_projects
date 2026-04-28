public class GradeJournal {
    static String [] names = new String[] {"Алиса", "Борис", "Вера", "Глеб"};
    static int[][] jagged = {
            {5, 4, 5, 5, 3},
            {3, 3, 4},
            {5, 5, 5, 5, 5, 4},
            {4, 3, 4, 5}
    };

    public static double average(int[] grades) {
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return (double) sum / grades.length;
    }

    public static int max(int[] grades){
        int max = grades[0];
        for (int grade : grades) {
            if (grade > max) {
                max = grade;
            }
        }
        return max;
    }

    public static int min(int[] grades){
        int min = grades[0];
        for (int grade : grades) {
            if (grade < min) {
                min = grade;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        System.out.println("=== Журнал оценок ===");

        double bestAverage = -1;
        String bestStudent = "";


        for (int i = 0; i < names.length; i++) {
            double avg = average(jagged[i]);
            System.out.println(names[i] +
                    " | Оценок:" + jagged[i].length +
                    " | Средний: " + String.format("%.2f", avg) +
                    " | Мин: " + min(jagged[i]) +
                    " | Макс: " + max(jagged[i]));
            if (avg > bestAverage) {
                bestAverage = avg;
                bestStudent = names[i];
            }

        }
        System.out.println("Лучший студент: " + bestStudent +
                " (средний балл:" + String.format("%.2f", bestAverage) + ")");
    }
}
