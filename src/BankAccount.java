public class BankAccount {
    // приватные поля
    private String owner;
    private double balance;
    private String accountNumber;
    // приватное статическое поле
    public static int totalAccounts = 0;
    public static String bankName = "Java Bank";
    // статический блок инициализации
    static {
        System.out.println("Банковская система инициализирована");
    }
    // блок инициализации экземпляра
    {
        ++totalAccounts;
        System.out.println("Создание счёта #" + totalAccounts);
    }
    // конструктор
    public BankAccount(String owner, double initialBalance) {
        this.owner = owner;
        this.balance = initialBalance;
        this.accountNumber = "ACC-" + totalAccounts;
    }
    // метод
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Ошибка: сумма должна быть положительной");
            return;
        }
        balance += amount;
    }
    // метод
    public void withdraw(double amount) {
        if (balance < amount) {
            System.out.println("Ошибка: недостаточно средств");
            return;
        }
        balance -= amount;
    }
    // статический метод
    public static int getTotalAccounts() {
        return totalAccounts;
    }
    // предопределенный метод
    @Override // аннотация, показывает что мы переопределяем метод родительского класса
    public String toString() {
        return "[" + accountNumber + "] " + owner + ": " + String.format("%.2f", balance) + " руб.";
    }

    public static void main(String[] args) {
        BankAccount a1 = new BankAccount("Алиса", 1000);
        BankAccount a2 = new BankAccount("Борис", 500);

        System.out.println(a1);
        System.out.println(a2);

        a1.deposit(500);
        System.out.println("После пополнения: " + a1);

        a1.withdraw(200);
        System.out.println("После снятия: " + a1);

        a1.withdraw(5000);

        a2.deposit(-100);

        System.out.println("Всего счетов: " + BankAccount.getTotalAccounts());
    }
}
