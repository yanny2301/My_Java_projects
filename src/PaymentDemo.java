sealed interface PaymentMethod permits CreditCard, BankTransfer, CryptoWallet {
    String process(double amount);
    double fee(double amount);
}

record CreditCard(String cardNumber, String holder) implements PaymentMethod {
    public String process(double amount) {
        return "Оплата картой " + "*" + cardNumber.substring(cardNumber.length() - 4)
                + ": " + amount + " руб.";
    }
    public double fee(double amount) {
        return amount * 0.02;
    }
}
record BankTransfer(String  bankName, String iban) implements PaymentMethod {
    public String process(double amount) {
        return "Перевод через " + bankName + ": " + amount + " руб.";
    }
    public double fee(double amount) {
        return 50;
    }
}
record CryptoWallet(String address, String currency) implements PaymentMethod {
    public String process(double amount) {
        return "Криптоплатёж (" + currency + "): " + amount + " руб.";
    }
    public double fee(double amount) {
        return amount * 0.015;
    }
}
class PaymentProcessor{
    static String describe(PaymentMethod pm) {
        return switch (pm) {
            case CreditCard cc -> "Кредитная карта с номером " + cc.cardNumber() + ", владелец: " + cc.holder();
            case BankTransfer bt -> "Название банка " + bt.bankName() + ", ID банка: " + bt.iban();
            case CryptoWallet cw -> "Адресс кошелька " + cw.address() + ", валюта: " + cw.currency();
        };
    }
}
public class PaymentDemo {
    public static void main(String[] args) {
        PaymentMethod[] payments = {
                new CreditCard("4111222233334444", "Иван Петров"),
                new BankTransfer("Сбербанк", "RU1234567890"),
                new CryptoWallet("0xABC123", "BTC")
        };

        double amount = 10000;
        for (PaymentMethod pm : payments) {
            System.out.println(pm.process(amount));
            System.out.printf("  Комиссия: %.2f руб.%n", pm.fee(amount));
            System.out.println(PaymentProcessor.describe(pm));
            System.out.println();
        }
    }
}
