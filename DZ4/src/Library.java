public class Library {
    String name;
    int capacity;

    public Library(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public class Book{
        String title;
        String author;
        int year;

        public Book(String title, String author, int year){
            this.author = author;
            this.title = title;
            this.year = year;
        }

        public void getInfo() {
            System.out.println("Книга: " + title + " автора " + author + " (" + year + ") "
                    + "в библиотеке " + name);
        }

    }

    public static void main(String[] args) {
        Library lib = new Library("Городская библиотека", 1);
        Book book = lib.new Book("Война и мир", "Л. Н. Толстой", 1869);
        book.getInfo();
    }

}
