import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Система управления библиотекой
 * Демонстрирует: enum, record, sealed классы, интерфейсы, Optional, Stream API
 */
public class LibrarySystem {
    public static void main(String[] args) {
        System.out.println("=== СИСТЕМА УПРАВЛЕНИЯ БИБЛИОТЕКОЙ ===\n");

        // Создаем библиотеку
        Library library = new Library();

        // Добавляем книги (создаем их через записи Book)
        System.out.println("Добавляем книги в библиотеку...\n");

        // Физические книги
        Book book1 = new Book("Война и мир", "Лев Толстой", 1869, Genre.HISTORY, 1500.50);
        library.add(new PhysicalBook(book1, "A-12"));

        Book book2 = new Book("Преступление и наказание", "Федор Достоевский", 1866, Genre.FICTION, 1200.00);
        library.add(new PhysicalBook(book2, "B-05"));

        Book book3 = new Book("Краткая история времени", "Стивен Хокинг", 1988, Genre.SCIENCE, 2000.00);
        library.add(new PhysicalBook(book3, "C-08"));

        // Электронные книги
        Book book4 = new Book("Изучаем Java", "Кэти Сьерра", 2018, Genre.PROGRAMMING, 3500.00);
        library.add(new EBook(book4, "PDF", 25.5));

        Book book5 = new Book("Java. Полное руководство", "Герберт Шилдт", 2021, Genre.PROGRAMMING, 4200.00);
        library.add(new EBook(book5, "EPUB", 18.2));

        Book book6 = new Book("Мона Лиза", "Вальтер Исааксон", 2017, Genre.ART, 2800.00);
        library.add(new EBook(book6, "PDF", 35.0));

        Book book7 = new Book("История Древнего Рима", "Теодор Моммзен", 1854, Genre.HISTORY, 1800.00);
        library.add(new PhysicalBook(book7, "D-15"));

        Book book8 = new Book("Анна Каренина", "Лев Толстой", 1877, Genre.FICTION, 1300.00);
        library.add(new PhysicalBook(book8, "A-12"));

        Book book9 = new Book("Физика будущего", "Митио Каку", 2011, Genre.SCIENCE, 2900.00);
        library.add(new EBook(book9, "PDF", 22.3));

        // 1. Демонстрация работы с enum Genre
        System.out.println("\n=== РАБОТА С ENUM GENRE ===");
        System.out.println("Жанр 'Наука' на русском: " + Genre.SCIENCE.getRussianName());
        System.out.println("Поиск жанра по русскому названию 'История': " + Genre.fromString("История"));
        System.out.println();

        // 2. Вывод всего каталога с использованием паттерн-матчинга
        System.out.println("=== КАТАЛОГ БИБЛИОТЕКИ ===");
        library.printCatalog();
        System.out.println();

        // 3. Группировка по жанрам
        System.out.println("=== КНИГИ ПО ЖАНРАМ ===");
        Map<Genre, List<LibraryItem>> grouped = library.groupByGenre();
        grouped.forEach((genre, items) -> {
            System.out.println(genre.getRussianName() + ": " + items.size() + " книг(а)");
        });
        System.out.println();

        // 4. Общая стоимость библиотеки
        System.out.println("=== СТОИМОСТЬ ===");
        System.out.printf("Общая стоимость библиотеки: %.2f руб.\n", library.totalValue());
        System.out.println();

        // 5. Самая дорогая книга
        System.out.println("=== САМАЯ ДОРОГАЯ КНИГА ===");
        library.mostExpensive().ifPresentOrElse(
                item -> System.out.println("Самая дорогая: " + item.getInfo()),
                () -> System.out.println("Библиотека пуста")
        );
        System.out.println();

        // 6. Авторы по жанру
        System.out.println("=== АВТОРЫ ЖАНРА ПРОГРАММИРОВАНИЕ ===");
        List<String> programmingAuthors = library.authorsByGenre(Genre.PROGRAMMING);
        programmingAuthors.forEach(author -> System.out.println("- " + author));
        System.out.println();

        // 7. Демонстрация Searchable интерфейса
        System.out.println("=== ПОИСК КНИГ ПО ЗАПРОСУ ===");
        List<LibraryItem> allItems = library.getAllItems();
        List<LibraryItem> searchResult = Searchable.search(allItems, "толстой");
        System.out.println("Найдено книг по запросу 'толстой': " + searchResult.size());
        searchResult.forEach(item -> System.out.println("  " + item.getInfo()));

        System.out.println("\n=== ПОИСК ПО ЗАПРОСУ 'JAVA' ===");
        List<LibraryItem> javaSearch = Searchable.search(allItems, "java");
        System.out.println("Найдено книг по запросу 'java': " + javaSearch.size());
        javaSearch.forEach(item -> System.out.println("  " + item.getInfo()));
    }
}

/**
 * ENUM Жанры книг с русскими названиями
 */
enum Genre {
    FICTION("Художественная литература"),
    SCIENCE("Наука"),
    HISTORY("История"),
    PROGRAMMING("Программирование"),
    ART("Искусство");

    private final String russianName;

    Genre(String russianName) {
        this.russianName = russianName;
    }

    public String getRussianName() {
        return russianName;
    }

    /**
     * Находит жанр по русскому названию
     */
    public static Genre fromString(String name) {
        for (Genre genre : Genre.values()) {
            if (genre.getRussianName().equalsIgnoreCase(name)) {
                return genre;
            }
        }
        throw new IllegalArgumentException("Жанр не найден: " + name);
    }
}

/**
 * RECORD Книга - основная информация о книге
 * Компактный конструктор с валидацией
 */
record Book(String title, String author, int year, Genre genre, double price) {

    // Компактный конструктор (без параметров, только валидация)
    Book {
        // Проверяем, что название не пустое
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Название книги не может быть пустым");
        }

        // Проверяем, что автор не пустой
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Автор книги не может быть пустым");
        }

        // Проверяем год (первая печатная книга - 1450 год)
        int currentYear = Year.now().getValue();
        if (year < 1450 || year > currentYear) {
            throw new IllegalArgumentException("Год должен быть между 1450 и " + currentYear);
        }

        // Проверяем цену
        if (price < 0) {
            throw new IllegalArgumentException("Цена не может быть отрицательной");
        }
    }
}

/**
 * Запечатанный интерфейс для элементов библиотеки
 * Разрешает только PhysicalBook и EBook
 */
sealed interface LibraryItem extends Searchable permits PhysicalBook, EBook {
    String getInfo();
}

/**
 * Физическая книга (реализация LibraryItem)
 */
final class PhysicalBook implements LibraryItem {
    private final Book book;
    private final String shelf;

    public PhysicalBook(Book book, String shelf) {
        this.book = book;
        this.shelf = shelf;
    }

    public Book book() {
        return book;
    }

    public String shelf() {
        return shelf;
    }

    @Override
    public String getInfo() {
        return String.format("[ФИЗИЧЕСКАЯ] %s - %s (%d г.) | Жанр: %s | Полка: %s | Цена: %.2f руб.",
                book.title(), book.author(), book.year(),
                book.genre().getRussianName(), shelf, book.price());
    }

    @Override
    public boolean matches(String query) {
        if (query == null || query.trim().isEmpty()) {
            return false;
        }
        String lowerQuery = query.toLowerCase();
        return book.title().toLowerCase().contains(lowerQuery) ||
                book.author().toLowerCase().contains(lowerQuery);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhysicalBook that = (PhysicalBook) o;
        return Objects.equals(book, that.book) &&
                Objects.equals(shelf, that.shelf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book, shelf);
    }

    @Override
    public String toString() {
        return String.format("PhysicalBook[book=%s, shelf=%s]", book, shelf);
    }
}

/**
 * Электронная книга (реализация LibraryItem)
 */
final class EBook implements LibraryItem {
    private final Book book;
    private final String format;
    private final double sizeMB;

    public EBook(Book book, String format, double sizeMB) {
        this.book = book;
        this.format = format;
        this.sizeMB = sizeMB;
    }

    public Book book() {
        return book;
    }

    public String format() {
        return format;
    }

    public double sizeMB() {
        return sizeMB;
    }

    @Override
    public String getInfo() {
        return String.format("[ЭЛЕКТРОННАЯ] %s - %s (%d г.) | Жанр: %s | Формат: %s | Размер: %.1f MB | Цена: %.2f руб.",
                book.title(), book.author(), book.year(),
                book.genre().getRussianName(), format, sizeMB, book.price());
    }

    @Override
    public boolean matches(String query) {
        if (query == null || query.trim().isEmpty()) {
            return false;
        }
        String lowerQuery = query.toLowerCase();
        return book.title().toLowerCase().contains(lowerQuery) ||
                book.author().toLowerCase().contains(lowerQuery) ||
                format.toLowerCase().contains(lowerQuery);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EBook eBook = (EBook) o;
        return Double.compare(eBook.sizeMB, sizeMB) == 0 &&
                Objects.equals(book, eBook.book) &&
                Objects.equals(format, eBook.format);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book, format, sizeMB);
    }

    @Override
    public String toString() {
        return String.format("EBook[book=%s, format=%s, sizeMB=%.1f]", book, format, sizeMB);
    }
}

/**
 * Интерфейс для поиска с default и static методами
 */
interface Searchable {
    // Default метод для проверки соответствия строке
    default boolean matches(String query) {
        if (query == null || query.trim().isEmpty()) {
            return false;
        }
        return false; // Базовая реализация
    }

    // Static метод для поиска по списку
    static <T extends Searchable> List<T> search(List<T> items, String query) {
        if (query == null || query.trim().isEmpty()) {
            return new ArrayList<>();
        }
        return items.stream()
                .filter(item -> item.matches(query))
                .collect(Collectors.toList());
    }
}

/**
 * Класс Библиотека - основная логика системы
 */
class Library {

    // Хранилище элементов библиотеки
    private final List<LibraryItem> items = new ArrayList<>();

    /**
     * Добавить элемент в библиотеку
     */
    public void add(LibraryItem item) {
        if (item != null) {
            items.add(item);
            System.out.println("Добавлено: " + item.getInfo());
        }
    }

    /**
     * Вывести каталог с использованием паттерн-матчинг switch
     */
    public void printCatalog() {
        if (items.isEmpty()) {
            System.out.println("Библиотека пуста");
            return;
        }

        for (int i = 0; i < items.size(); i++) {
            LibraryItem item = items.get(i);
            System.out.print((i + 1) + ". ");

            // Используем паттерн-матчинг switch (Java 17+)
            switch (item) {
                case PhysicalBook physicalBook ->
                        System.out.println(physicalBook.getInfo());
                case EBook eBook ->
                        System.out.println(eBook.getInfo());
                default ->
                        System.out.println("Неизвестный тип");
            }
        }
    }

    /**
     * Группировка книг по жанрам с использованием EnumMap
     */
    public Map<Genre, List<LibraryItem>> groupByGenre() {
        // Создаем EnumMap с начальными пустыми списками для каждого жанра
        Map<Genre, List<LibraryItem>> grouped = new EnumMap<>(Genre.class);

        // Инициализируем пустыми списками
        for (Genre genre : Genre.values()) {
            grouped.put(genre, new ArrayList<>());
        }

        // Группируем книги по жанрам
        for (LibraryItem item : items) {
            Book book = getBookFromItem(item);
            if (book != null) {
                grouped.get(book.genre()).add(item);
            }
        }

        return grouped;
    }

    /**
     * Общая стоимость всех книг (используем reduce)
     */
    public double totalValue() {
        return items.stream()
                .map(this::getBookFromItem)
                .filter(Objects::nonNull)
                .map(Book::price)
                .reduce(0.0, Double::sum);
    }

    /**
     * Самая дорогая книга (Optional)
     */
    public Optional<LibraryItem> mostExpensive() {
        return items.stream()
                .max((item1, item2) -> {
                    double price1 = getBookFromItem(item1).price();
                    double price2 = getBookFromItem(item2).price();
                    return Double.compare(price1, price2);
                });
    }

    /**
     * Уникальные авторы по жанру, отсортированные по алфавиту
     */
    public List<String> authorsByGenre(Genre genre) {
        return items.stream()
                .map(this::getBookFromItem)
                .filter(book -> book != null && book.genre() == genre)
                .map(Book::author)
                .distinct() // Уникальные
                .sorted() // Сортировка по алфавиту
                .collect(Collectors.toList());
    }

    /**
     * Получить все элементы (для поиска)
     */
    public List<LibraryItem> getAllItems() {
        return new ArrayList<>(items);
    }

    /**
     * Вспомогательный метод для получения Book из LibraryItem
     */
    private Book getBookFromItem(LibraryItem item) {
        return switch (item) {
            case PhysicalBook physicalBook -> physicalBook.book();
            case EBook eBook -> eBook.book();
            default -> null;
        };
    }
}
