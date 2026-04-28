public class GradeChecker {
    static String getGradeClassic(int score) {
        switch (score / 10) {
            case 10:
            case 9:
                return "Отлично (A)";
            case 8:
                return "Хорошо (B)";
            case 7:
                return "Удовлетворительно (C)";
            case 6:
                return "Слабо (D)";
            default:
                return "Неудовлетворительно (F)";
        }
    }
    static String getGradeArrow(int score) {
        return switch (score / 10) {
            case 10, 9 -> "Отлично (A)";
            case 8 -> "Хорошо (B)";
            case 7 -> "Удовлетворительно (C)";
            case 6 -> "Слабо (D)";
            default -> "Неудовлетворительно (F)";
        };
    }
    public static void main(String[] args) {
        System.out.println(getGradeClassic(95));
        System.out.println(getGradeClassic(85));
        System.out.println(getGradeClassic(73));
        System.out.println(getGradeClassic(62));
        System.out.println(getGradeArrow(42));
        System.out.println(getGradeArrow(100));
        System.out.println(getGradeArrow(0));
    }
}
