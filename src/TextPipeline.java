import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class TextPipeline {
    public static void main(String[] args) {
        // Часть A - Композиция функций
        System.out.println("=== ЧАСТЬ A: Композиция функций ===\n");

        // Создаем отдельные функции
        Function<String, String> trim = String::trim;
        Function<String, String> lower = String::toLowerCase;
        Function<String, String> removeExtraSpaces = s -> s.replaceAll("\\s+", " ");
        Function<String, String> capitalize = s -> {
            if (s == null || s.isEmpty()) return s;
            return s.substring(0, 1).toUpperCase() + s.substring(1);
        };

        // Компонуем их в конвейер с помощью andThen()
        Function<String, String> normalize = trim
                .andThen(lower)
                .andThen(removeExtraSpaces)
                .andThen(capitalize);

        // Тестируем на нескольких строках
        String[] testStrings = {
                "  hello   world   ",
                "   JAVA    PROGRAMMING    LANGUAGE   ",
                "   functional   programming   is   awesome   ",
                "   multiple    spaces    test   "
        };

        System.out.println("Результаты нормализации:");
        System.out.println("-".repeat(40));
        for (String test : testStrings) {
            String result = normalize.apply(test);
            System.out.printf("Исходная: \"%s\"%n", test);
            System.out.printf("Результат: \"%s\"%n%n", result);
        }

        // Часть B - Локальный класс WordCounter
        System.out.println("\n=== ЧАСТЬ B: Локальный класс WordCounter ===\n");

        // Объявляем локальный класс внутри метода main
        class WordCounter {
            private final String text;

            public WordCounter(String text) {
                this.text = text;
            }

            public Map<String, Integer> count() {
                Map<String, Integer> wordCount = new HashMap<>();

                // Разбиваем текст на слова (по пробелам)
                String[] words = text.split("\\s+");

                for (String word : words) {
                    if (!word.isEmpty()) {
                        wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
                    }
                }

                return wordCount;
            }

            public String mostFrequent() {
                Map<String, Integer> wordCount = count();

                if (wordCount.isEmpty()) {
                    return null;
                }

                String mostFrequentWord = null;
                int maxCount = 0;

                for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
                    if (entry.getValue() > maxCount) {
                        maxCount = entry.getValue();
                        mostFrequentWord = entry.getKey();
                    }
                }

                return mostFrequentWord;
            }
        }

        // Создаем текст для анализа
        String rawText = "  кошка   собака кошка   птица   собака кошка   рыбка   ";

        // Нормализуем текст с помощью созданного конвейера
        String normalizedText = normalize.apply(rawText);

        System.out.println("Исходный текст: \"" + rawText + "\"");
        System.out.println("Нормализованный текст: \"" + normalizedText + "\"\n");

        // Анализируем нормализованный текст с помощью WordCounter
        WordCounter counter = new WordCounter(normalizedText);
        Map<String, Integer> frequency = counter.count();
        String mostFrequent = counter.mostFrequent();

        System.out.println("Частотный словарь слов:");
        System.out.println("-".repeat(30));

        // Сортируем для красивого вывода
        frequency.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(entry -> System.out.printf("%-10s: %d%n", entry.getKey(), entry.getValue()));

        System.out.println("\nСамое частое слово: \"" + mostFrequent + "\"");

        // Дополнительный тест с несколькими предложениями
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ДОПОЛНИТЕЛЬНЫЙ ТЕСТ: Анализ текста из нескольких предложений");
        System.out.println("=".repeat(50) + "\n");

        String multiSentenceText = "  java   это   язык   программирования.   java   используется   для   создания   приложений.   java   очень   популярен!   ";

        // Нормализуем текст
        String normalizedMultiText = normalize.apply(multiSentenceText);

        // Создаем новый счетчик для нормализованного текста
        WordCounter multiCounter = new WordCounter(normalizedMultiText);
        Map<String, Integer> multiFrequency = multiCounter.count();
        String multiMostFrequent = multiCounter.mostFrequent();

        System.out.println("Исходный текст: \"" + multiSentenceText + "\"");
        System.out.println("Нормализованный текст: \"" + normalizedMultiText + "\"\n");
        System.out.println("Частотный словарь:");

        multiFrequency.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(entry -> System.out.printf("%-20s: %d%n", entry.getKey(), entry.getValue()));

        System.out.println("\nСамое частое слово: \"" + multiMostFrequent + "\"");
    }

}
