public class NumberClassifier {
    public static String classify(int number) {
        if (number < 0) {
            return "отрицательное";
        } else if (0 == number) {
            return "== 0";
        } else if (number <= 9) {
            return "однозначное";
        } else if (number <= 99) {
            return "двузначное";
        } else if (number <= 999) {
            return "трёхзначное";
        } else if (number >= 1000) {
            return "большое";
        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println(classify(-5));
        System.out.println(classify(0));
        System.out.println(classify(7));
        System.out.println(classify(42));
        System.out.println(classify(100));
        System.out.println(classify(1000));
        System.out.println(classify(-999));
    }
}

