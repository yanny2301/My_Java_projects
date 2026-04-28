import java.util.*;
import java.util.stream.Collectors;

public class OrderAnalytics {
    // Определяем record Order внутри класса для удобства
    record Order(String customer, String product, double price, int quantity, String category) {
        double total() {
            return price * quantity;
        }
    }

    public static void main(String[] args) {
        // Создаем список заказов (10+)
        List<Order> orders = createOrders();

        System.out.println("===== Анализ заказов с помощью Stream API =====\n");

        // 1. Найдите все заказы дороже 5000 руб. (по total()) и выведите их
        System.out.println("1. Заказы дороже 5000 руб.:");
        orders.stream()
                .filter(order -> order.total() > 5000)
                .forEach(System.out::println);
        System.out.println();

        // 2. Получите список уникальных имён клиентов (List<String>), отсортированный по алфавиту
        System.out.println("2. Уникальные имена клиентов (отсортированы по алфавиту):");
        List<String> uniqueCustomers = orders.stream()
                .map(Order::customer)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        uniqueCustomers.forEach(System.out::println);
        System.out.println();

        // 3. Вычислите общую выручку (сумма всех total())
        double totalRevenue = orders.stream()
                .mapToDouble(Order::total)
                .sum();
        System.out.printf("3. Общая выручка: %.2f руб.\n\n", totalRevenue);

        // 4. Найдите самый дорогой заказ (по total())
        Optional<Order> mostExpensiveOrder = orders.stream()
                .max(Comparator.comparingDouble(Order::total));
        mostExpensiveOrder.ifPresent(order ->
                System.out.printf("4. Самый дорогой заказ: %s (%.2f руб.)\n\n", order, order.total()));

        // 5. Подсчитайте количество заказов в каждой категории (Map<String, Long>)
        System.out.println("5. Количество заказов по категориям:");
        Map<String, Long> ordersByCategory = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::category,
                        Collectors.counting()));
        ordersByCategory.forEach((category, count) ->
                System.out.printf("   %s: %d\n", category, count));
        System.out.println();

        // 6. Вычислите среднюю стоимость заказа по каждому клиенту (Map<String, Double>)
        System.out.println("6. Средняя стоимость заказа по клиентам:");
        Map<String, Double> avgOrderValueByCustomer = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::customer,
                        Collectors.averagingDouble(Order::total)));
        avgOrderValueByCustomer.forEach((customer, avg) ->
                System.out.printf("   %s: %.2f руб.\n", customer, avg));
        System.out.println();

        // 7. Разделите заказы на две группы: дорогие (total > 3000) и дешёвые (Map<Boolean, List<Order>>)
        System.out.println("7. Разделение заказов на дорогие и дешёвые:");
        Map<Boolean, List<Order>> partitionedOrders = orders.stream()
                .collect(Collectors.partitioningBy(
                        order -> order.total() > 3000));
        System.out.println("   Дорогие заказы (>3000 руб.):");
        partitionedOrders.get(true).forEach(order ->
                System.out.printf("      %s (%.2f руб.)\n", order.product(), order.total()));
        System.out.println("   Дешёвые заказы (≤3000 руб.):");
        partitionedOrders.get(false).forEach(order ->
                System.out.printf("      %s (%.2f руб.)\n", order.product(), order.total()));
        System.out.println();

        // 8. Получите список названий товаров дороже 1000 руб., без дубликатов, в верхнем регистре
        System.out.println("8. Уникальные товары дороже 1000 руб. (в верхнем регистре):");
        List<String> expensiveProducts = orders.stream()
                .filter(order -> order.price() > 1000)
                .map(Order::product)
                .map(String::toUpperCase)
                .distinct()
                .collect(Collectors.toList());
        expensiveProducts.forEach(System.out::println);
    }

    // Метод для создания списка заказов
    private static List<Order> createOrders() {
        return Arrays.asList(
                new Order("Иванов", "Ноутбук", 45000.0, 1, "Электроника"),
                new Order("Петров", "Книга", 800.0, 3, "Книги"),
                new Order("Сидорова", "Смартфон", 25000.0, 1, "Электроника"),
                new Order("Иванов", "Мышь", 1500.0, 2, "Компьютерные аксессуары"),
                new Order("Козлов", "Наушники", 3500.0, 1, "Аудио"),
                new Order("Петров", "Клавиатура", 2800.0, 1, "Компьютерные аксессуары"),
                new Order("Сидорова", "Планшет", 18000.0, 1, "Электроника"),
                new Order("Иванов", "Флешка", 1200.0, 3, "Компьютерные аксессуары"),
                new Order("Козлов", "Колонки", 4200.0, 1, "Аудио"),
                new Order("Петров", "Монитор", 15000.0, 1, "Электроника"),
                new Order("Сидорова", "Чехол", 800.0, 2, "Аксессуары"),
                new Order("Иванов", "Внешний диск", 5500.0, 1, "Компьютерные аксессуары")
        );
    }
}
