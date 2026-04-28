import java.util.*;

// Абстрактный класс Animal
abstract class Animal {
    protected String name;
    protected int age;
    protected double weight;
    protected int energyLevel; // 0-100

    public Animal(String name, int age, double weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.energyLevel = 50; // начальный уровень энергии
    }

    public void eat(int calories) {
        energyLevel += calories / 10;
        if (energyLevel > 100) energyLevel = 100;
        weight += calories / 100.0;
        System.out.println(name + " поел(а). Энергия: " + energyLevel + ", Вес: " + weight);
    }

    public void sleep(int hours) {
        energyLevel += hours * 5;
        if (energyLevel > 100) energyLevel = 100;
        System.out.println(name + " поспал(а) " + hours + " часов. Энергия: " + energyLevel);
    }

    public abstract void makeSound();

    public String getName() {
        return name;
    }

    public int getEnergyLevel() {
        return energyLevel;
    }

    public double getWeight() {
        return weight;
    }
}

// Подкласс Predator
abstract class Predator extends Animal {
    public Predator(String name, int age, double weight) {
        super(name, age, weight);
    }

    public void hunt() {
        energyLevel += 30;
        if (energyLevel > 100) energyLevel = 100;
        weight -= 0.5;
        System.out.println(name + " охотился. Энергия: " + energyLevel + ", Вес: " + weight);
    }
}

// Подкласс Herbivore
abstract class Herbivore extends Animal {
    public Herbivore(String name, int age, double weight) {
        super(name, age, weight);
    }

    public void graze() {
        energyLevel += 15;
        if (energyLevel > 100) energyLevel = 100;
        System.out.println(name + " пасся. Энергия: " + energyLevel);
    }
}

// Lion класс
class Lion extends Predator implements Trainable {
    private ArrayList<String> commands = new ArrayList<>();

    public Lion(String name, int age, double weight) {
        super(name, age, weight);
    }

    @Override
    public void makeSound() {
        System.out.println(name + ": Р-р-р!");
    }

    public void roar() {
        System.out.println(name + " громко рычит: Р-Р-Р-Р-Р!!!");
        energyLevel -= 10;
        if (energyLevel < 0) energyLevel = 0;
    }

    @Override
    public void train(String command) {
        commands.add(command);
        System.out.println(name + " выучил команду: " + command);
    }

    @Override
    public void listCommands() {
        if (commands.isEmpty()) {
            System.out.println(name + " не знает ни одной команды.");
        } else {
            System.out.println(name + " знает команды: " + commands);
        }
    }
}

// Elephant класс
class Elephant extends Herbivore {
    public Elephant(String name, int age, double weight) {
        super(name, age, weight);
    }

    @Override
    public void makeSound() {
        System.out.println(name + ": Тууут!");
    }

    public void trumpet() {
        System.out.println(name + " трубит: ТУУУУУУУТ!");
        energyLevel -= 5;
        if (energyLevel < 0) energyLevel = 0;
    }
}

// Интерфейс Trainable
interface Trainable {
    void train(String command);
    void listCommands();
}

// Класс Zoo
class Zoo {
    private ArrayList<Animal> animals = new ArrayList<>();

    public void addAnimal(Animal a) {
        animals.add(a);
        System.out.println(a.getName() + " добавлен(а) в зоопарк!");
    }

    public void feedAll() {
        System.out.println("\n--- Кормление всех животных ---");
        for (Animal a : animals) {
            a.eat(30);
        }
    }

    public void makeNoise() {
        System.out.println("\n--- Все животные издают звуки ---");
        for (Animal a : animals) {
            a.makeSound();
        }
    }

    public void getHungryAnimals() {
        System.out.println("\n--- Голодные животные (энергия < 30) ---");
        boolean found = false;
        for (Animal a : animals) {
            if (a.getEnergyLevel() < 30) {
                System.out.println(a.getName() + " - энергия: " + a.getEnergyLevel());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Нет голодных животных!");
        }
    }

    public Optional<Animal> findAnimal(String name) {
        for (Animal a : animals) {
            if (a.getName().equalsIgnoreCase(name)) {
                return Optional.of(a);
            }
        }
        return Optional.empty();
    }

    public void showAllAnimals() {
        System.out.println("\n--- Все животные в зоопарке ---");
        for (Animal a : animals) {
            System.out.println(a.getName() + " (" + a.getClass().getSimpleName() +
                    ") - Энергия: " + a.getEnergyLevel() + ", Вес: " + a.getWeight());
        }
    }
}

// Главный класс для демонстрации
public class ZooManagement {
    public static void main(String[] args) {
        System.out.println("=== Добро пожаловать в систему управления зоопарком ===\n");

        // Создаем зоопарк
        Zoo zoo = new Zoo();

        // Создаем животных
        Lion simba = new Lion("Симба", 5, 180.5);
        Lion leo = new Lion("Лео", 3, 150.0);
        Elephant dumbo = new Elephant("Дамбо", 10, 2500.0);
        Elephant ellie = new Elephant("Элли", 7, 2100.0);

        // Добавляем животных в зоопарк
        zoo.addAnimal(simba);
        zoo.addAnimal(leo);
        zoo.addAnimal(dumbo);
        zoo.addAnimal(ellie);

        // Показываем всех животных
        zoo.showAllAnimals();

        // Дрессировка львов
        System.out.println("\n--- Дрессировка ---");
        simba.train("Сидеть");
        simba.train("Лежать");
        simba.train("Голос");
        leo.train("Апорт");

        simba.listCommands();
        leo.listCommands();

        // Животные издают звуки
        zoo.makeNoise();

        // Дополнительные действия животных
        System.out.println("\n--- Специальные действия ---");
        simba.roar();
        leo.hunt();
        dumbo.graze();
        ellie.trumpet();

        // Проверяем голодных животных
        zoo.getHungryAnimals();

        // Кормим всех
        zoo.feedAll();

        // Снова проверяем голодных
        zoo.getHungryAnimals();

        // Животные спят
        System.out.println("\n--- Животные отдыхают ---");
        simba.sleep(3);
        dumbo.sleep(2);

        // Поиск животного
        System.out.println("\n--- Поиск животного ---");
        Optional<Animal> found = zoo.findAnimal("Симба");
        if (found.isPresent()) {
            System.out.println("Найден(а): " + found.get().getName());
            found.get().makeSound();
        }

        Optional<Animal> notFound = zoo.findAnimal("Рекс");
        if (notFound.isEmpty()) {
            System.out.println("Животное с именем 'Рекс' не найдено");
        }

        // Финальное состояние зоопарка
        zoo.showAllAnimals();

        System.out.println("\n=== Работа зоопарка завершена ===");
    }
}
