class BankAccount {
    private final String accountNumber;
    private double balance;
    private final String owner;
    private int failedAttempts;
    private boolean blocked;
    private String pin;

    public BankAccount(String accountNumber, double balance, String owner, String pin) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.owner = owner;
        this.failedAttempts = 0;
        this.blocked = false;
        this.pin = pin;
    }
    public String getAccountNumber() {return accountNumber;}
    public double getBalance() {return balance;}
    public String getOwner() {return owner;}
    public int getFailedAttempts() {return failedAttempts;}
    public boolean isBlocked() {return blocked;}

    public void withdraw(String enteredPin, double amount) {
        if (blocked) {
            System.out.println("Счёт заблокирован");
            return;
        }
        if (!enteredPin.equals(pin)) {
            failedAttempts++;
            System.out.println("Неверный PIN, попыток: " + failedAttempts);
            if (failedAttempts >= 3) {
                blocked = true;
                System.out.println("Счёт заблокирован из-за 3 неудачных попыток");
            }
            return;
        }

        failedAttempts = 0;
        if (amount <= 0) {
            System.out.println("Сумма должна быть положительной");
        } else if (amount > balance) {
            System.out.println("Недостаточно средств");
        } else {
            balance -= amount;
            System.out.println("Снято" + amount + "Остаток" + balance);
        }
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }
    public boolean validatePin(String pin) {
        return this.pin.equals(pin);
    }
    public String getMaskedBalance() {
        if (balance > 100_000) {
            return "> 100_000";
        }
        return String.valueOf(balance);
    }
    @Override
    public String toString() {
        String blockedMark = blocked ? "[ЗАБЛОКИРОВАН]" : "";
        return "BankAccount{" + "accountNumber=" + accountNumber + '\'' + ", balance=" + getMaskedBalance() + ", owner='" + owner + '\'' + ", failedAttempts=" +
        blockedMark + '}';
    }
    public static void main(String[] args) {
        BankAccount acc = new BankAccount("12345", 150000, "Иван", "1111");
        acc.withdraw("0000", 100);  // Неверный PIN
        acc.withdraw("0000", 100);  // Неверный PIN
        acc.withdraw("0000", 100);  // 3-я ошибка — блокировка
        acc.withdraw("1111", 10);   // Счёт заблокирован
        System.out.println(acc);    // [ЗАБЛОКИРОВАН]
    }
}

