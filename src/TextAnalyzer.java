public class TextAnalyzer {
    String text;

    public TextAnalyzer(String text) {
        this.text = text;
    }
    // чтобы был не хэш код, а текст
    @Override
    public String toString() {
        return text;
    }

    public int wordCount() {
        return text.split(" ").length;
    }
    public String longestWord() {
        String[] parts = text.split(" ");
        int maxLength = 0;
        String word = "";
        for (int i = 0; i < parts.length; i++) {
            int currentLength = parts[i].length();
            if (currentLength > maxLength) {
                maxLength = currentLength;
                word = parts[i];
            }
        }
        return word;
    }
    public String reverseWords() {
        String[] parts = text.split(" ");
        String result = "";
        for (int i = parts.length - 1; i >= 0; i--) {
            if (i == parts.length - 1) {
                result = result + parts[i];
            }
            else {
                result = result + " " + parts[i];
            }
        }
        return result;
    }

    public int countOccurrences(String target) {
        String lowerText = text.toLowerCase();
        String lowerTarget = target.toLowerCase();
        int countSubstring = 0;
        String[] parts = lowerText.split(" ");

        for (int i = 0; i < parts.length; i++) {
            String word = parts[i];
            int index = 0;
            while ((index = word.indexOf(lowerTarget, index)) != -1 ) {
                countSubstring++;
                index += lowerTarget.length();
            }
        }
        return countSubstring;
    }
    public boolean isPalindrome() {
        String formatText = text.toLowerCase().replaceAll("[^a-zA-Zа-яА-ЯёЁ]", "");
        int left = 0;
        int right = formatText.length() - 1;
        while (left < right) {
            if (formatText.charAt(left) != formatText.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        TextAnalyzer ta = new TextAnalyzer("Java Programming is Fun and Java is Powerful");

        System.out.println("Текст: " + ta);
        System.out.println("Слов: " + ta.wordCount());
        System.out.println("Самое длинное слово: " + ta.longestWord());
        System.out.println("Слова наоборот: " + ta.reverseWords());
        System.out.println("'Java' встречается: " + ta.countOccurrences("java") + " раз(а)");
        System.out.println("'is' встречается: " + ta.countOccurrences("is") + " раз(а)");
        System.out.println("Палиндром: " + ta.isPalindrome());

        System.out.println();

        TextAnalyzer palindrome = new TextAnalyzer("А роза упала на лапу Азора");
        System.out.println("Текст: " + palindrome);
        System.out.println("Палиндром: " + palindrome.isPalindrome());
    }
}

