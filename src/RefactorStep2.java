import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class RefactorStep2 {
    public static void main(String[] args) {
        List<String> cities = Arrays.asList("Москва", "Берлин", "Токио", "Нью-Йорк", "Париж");

        // 1. Сортировка по длине (лямбда)
        // НЕЛЬЗЯ заменить на ссылку на метод
        cities.sort((a, b) -> Integer.compare(a.length(), b.length()));

        // 2. Вывод каждого элемента
        // МОЖНО заменить
        cities.forEach(System.out::println);

        // 3. Преобразование в верхний регистр
        // МОЖНО заменить
        Function<String, String> toUpper = String::toUpperCase;

        // 4. Проверка длины > 5 (лямбда)
        // НЕЛЬЗЯ заменить на ссылку на метод
        Predicate<String> isLong = s -> s.length() > 5;

        // 5. Формирование строки с восклицательным знаком (лямбда)
        // НЕЛЬЗЯ заменить на ссылку на метод
        Function<String, String> exclaim = s -> s + "!";

        // 6. Создание нового списка
        // МОЖНО заменить
        Supplier<List<String>> listFactory = ArrayList::new;

        // Использование
        List<String> result = listFactory.get();
        for (String city : cities) {
            if (isLong.test(city)) {
                result.add(toUpper.apply(city));
            }
        }
        System.out.println("Длинные города: " + result);
    }
}
