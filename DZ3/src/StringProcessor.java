/*
Реализуйте класс StringProcessor со статическими методами: countVowels(String text) — подсчёт гласных букв (русских и английских);
isPalindrome(String text) — проверка на палиндром без учёта регистра и знаков препинания (используйте сравнение символов с двух
концов строки); reverse(String text) — реверс строки без StringBuilder (с двумя указателями); findLongestWord(String sentence) —
самое длинное слово в предложении. Протестируйте на: "Привет, Java-разработчик!", "топот", "Madam", "hello",
"А роза упала на лапу Азора", "The quick brown fox jumps over the lazy dog".
 */
import java.util.List;

public class StringProcessor {
    public static int countVowels(String text) {
        List<Character> allVowels = List.of(
                'а', 'е', 'ё', 'и', 'о', 'у', 'ы', 'э', 'ю', 'я',
                'a', 'e', 'i', 'o', 'u', 'y'
        );
        text = text.toLowerCase();
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if  (allVowels.contains(ch)) {
                count++;
            }
        }
        return count;
    }

    public static boolean isPalindrom(String text) {
        text = text.toLowerCase().replaceAll("[,.!?\\s]", "");
        int left = 0;
        int right = text.length() - 1;
        while (left < right) {
            if (text.charAt(left) != text.charAt(right))  {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static String reverse(String text) {
        char[] chars = new char[text.length()];
        for (int i = 0; i < text.length(); i++) {
            chars[i] = text.charAt(text.length() - 1 - i);
        }
        return new String(chars);
    }

    public static String findLongestWord(String sentence) {
        sentence = sentence.replaceAll("[,.!?]", "");
        String[] words = sentence.split(" ");
        String longest = "";
        for (String word : words) {
            if (word.length() > longest.length()) {
                longest = word;
            }
        }
        return longest;
    }
    public static void main(String[] args) {
        String a = "Привет, Java-разработчик!";
        String b = "топот";
        String c = "Madam";
        String d = "hello";
        String e = "А роза упала на лапу Азора";
        String f = "The quick brown fox jumps over the lazy dog";

        System.out.println(countVowels(d));
        System.out.println(countVowels(b));
        System.out.println(isPalindrom(c));
        System.out.println(isPalindrom(e));
        System.out.println(reverse(e));
        System.out.println(reverse(a));
        System.out.println(findLongestWord(f));

    }
}
