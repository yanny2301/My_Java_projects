public class PatternMatching {
    static String describe(Object obj) {
        return switch (obj) {
            case null -> "null";
            case Integer i when i < 0 -> "неположительное";
            case Integer i when i > 0 -> "положительное";
            case String s when s.length() == 0 -> "пустая строка";
            case String s when s.length() > 0 -> "непустая строка";
            case Double d -> "число с плавающей точкой";
            case int[] arr -> "массив";
            default -> "иные объекты";
        };
    }
    public static void main(String[] args) {
        System.out.println(describe(null));
        System.out.println(describe(42));
        System.out.println(describe(-5));
        System.out.println(describe(""));
        System.out.println(describe("Привет"));
        System.out.println(describe(3.14));
        System.out.println(describe(new int[] {1, 2, 3}));
        System.out.println(describe(true));
    }
}
