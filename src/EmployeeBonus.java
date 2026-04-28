abstract class Employee {
    protected String name;
    protected double baseSalary;

    public Employee(String name, double baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public String getName() {
        return name;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public abstract double calculateBonus();

    public double totalCompensation() {
        return baseSalary + calculateBonus();
    }

    @Override
    public String toString() {
        return name + " | Оклад:" + baseSalary + " | Бонус:" + calculateBonus() + " | Итого:" + totalCompensation();
    }
}
class Manager extends Employee {
    int teamSize;

    public Manager(String name, int baseSalary, int teamSize) {
        super(name, baseSalary);
        this.teamSize = teamSize;
    }

    @Override
    public double calculateBonus() {
        return baseSalary * 0.15 + teamSize * 5000;
    }
}
class Developer extends Employee {
    String language;

    public Developer(String name, int baseSalary, String language) {
        super(name, baseSalary);
        this.language = language;
    }
    @Override
    public double calculateBonus() {
        return baseSalary * 0.12;
    }
}
class Intern extends Employee {

    public Intern(String name, int baseSalary) {
        super(name, baseSalary);
    }
    @Override
    public double calculateBonus() {
        return 10000;
    }
}
    public class EmployeeBonus {
        public static void main(String[] args) {
            Employee[] team = {
                    new Manager("Ольга", 120000, 5),
                    new Developer("Андрей", 95000, "Java"),
                    new Developer("Мария", 100000, "Python"),
                    new Intern("Стажёр Петя", 30000)
            };

            System.out.println("=== Расчёт бонусов ===");
            double totalBudget = 0;
            for (Employee e : team) {
                System.out.println(e);
                totalBudget += e.totalCompensation();
            }
            System.out.printf("\nОбщий бюджет: %.0f руб.%n", totalBudget);
        }
    }

