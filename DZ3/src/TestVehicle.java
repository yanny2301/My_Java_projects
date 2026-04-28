import java.util.List;
import java.util.ArrayList;

// 1. Абстрактный класс Vehicle
abstract class Vehicle {
    private String brand;
    private String model;
    private int year;
    private double fuelLevel;

    public Vehicle(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.fuelLevel = 1.0;
    }

    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public double getFuelLevel() { return fuelLevel; }

    public void setFuelLevel(double fuelLevel) {
        if (fuelLevel >= 0.0 && fuelLevel <= 1.0) {
            this.fuelLevel = fuelLevel;
        }
    }

    public abstract double getFuelConsumption(); // л/100км
    public abstract String getType();

    public double calculateFuelNeeded(double distanceKm) {
        return (getFuelConsumption() * distanceKm) / 100.0;
    }

    public boolean canTravel(double distanceKm, double tankCapacityLiters) {
        double fuelNeeded = calculateFuelNeeded(distanceKm);
        double availableFuel = tankCapacityLiters * fuelLevel;
        return availableFuel >= fuelNeeded;
    }

    @Override
    public String toString() {
        return String.format("%s %s (%d)", brand, model, year);
    }
}

// 2. Класс Car
class Car extends Vehicle {
    private int doors;
    private boolean automatic;

    public Car(String brand, String model, int year, int doors, boolean automatic) {
        super(brand, model, year);
        this.doors = doors;
        this.automatic = automatic;
    }

    @Override
    public double getFuelConsumption() {
        return automatic ? 9.5 : 8.0;
    }

    @Override
    public String getType() {
        return "Car";
    }

    public void honk() {
        System.out.println("BEEEP!");
    }
}

// 3. Класс Truck
class Truck extends Vehicle {
    private double cargoCapacityTons;

    public Truck(String brand, String model, int year, double cargoCapacityTons) {
        super(brand, model, year);
        this.cargoCapacityTons = cargoCapacityTons;
    }

    @Override
    public double getFuelConsumption() {
        return 20 + cargoCapacityTons * 3;
    }

    @Override
    public String getType() {
        return "Truck";
    }
}

// 4. Интерфейс Electric
interface Electric {
    double getBatteryLevel();
    double getRangeKm();
    void charge(double hours);
}

// 5. Класс ElectricCar
class ElectricCar extends Car implements Electric {
    private double batteryLevel;
    private double maxRangeKm;

    public ElectricCar(String brand, String model, int year, int doors,
                       boolean automatic, double maxRangeKm) {
        super(brand, model, year, doors, automatic);
        this.batteryLevel = 1.0;
        this.maxRangeKm = maxRangeKm;
    }

    @Override
    public double getFuelConsumption() {
        return 0;
    }

    @Override
    public String getType() {
        return "Electric Car";
    }

    @Override
    public double getBatteryLevel() {
        return batteryLevel;
    }

    @Override
    public double getRangeKm() {
        return maxRangeKm * batteryLevel;
    }

    @Override
    public void charge(double hours) {
        batteryLevel = Math.min(1.0, batteryLevel + hours * 0.2);
        System.out.printf("%s %s заряжается... Уровень батареи: %.0f%%\n",
                getBrand(), getModel(), batteryLevel * 100);
    }
}

// 6. Тестовый класс
public class TestVehicle {
    public static void main(String[] args) {
        List<Vehicle> vehicles = new ArrayList<>();

        vehicles.add(new Car("Toyota", "Camry", 2022, 4, true));
        vehicles.add(new Car("Lada", "Vesta", 2023, 4, false));
        vehicles.add(new Truck("Kamaz", "5490", 2021, 15.0));
        vehicles.add(new ElectricCar("Tesla", "Model 3", 2024, 4, true, 600.0));

        System.out.println("=== Информация о транспортных средствах ===\n");

        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
            System.out.println("Тип: " + vehicle.getType());

            double fuelNeeded = vehicle.calculateFuelNeeded(500);
            if (vehicle.getFuelConsumption() > 0) {
                System.out.printf("Расход топлива на 500 км: %.2f л\n", fuelNeeded);
            } else {
                System.out.println("Расход топлива на 500 км: 0 л (электромобиль)");
            }

            if (vehicle instanceof Electric) {
                Electric ev = (Electric) vehicle;
                System.out.printf("Запас хода: %.1f км\n", ev.getRangeKm());
            }

            System.out.println();
        }

        // полиморфизм
        System.out.println("=== Демонстрация полиморфизма ===\n");

        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Car) {
                Car car = (Car) vehicle;
                System.out.print(vehicle.getBrand() + " " + vehicle.getModel() + ": ");
                car.honk();
            }

            if (vehicle instanceof Electric) {
                Electric ev = (Electric) vehicle;
                System.out.printf("%s %s: Запас хода = %.1f км\n",
                        vehicle.getBrand(), vehicle.getModel(), ev.getRangeKm());
            }
        }

        System.out.println("\n=== Демонстрация зарядки ===\n");
        Electric tesla = (Electric) vehicles.get(3);
        System.out.println("До зарядки:");
        System.out.printf("Батарея: %.0f%%, Запас хода: %.1f км\n",
                tesla.getBatteryLevel() * 100, tesla.getRangeKm());

        tesla.charge(2.5);

        System.out.println("\nПосле зарядки:");
        System.out.printf("Батарея: %.0f%%, Запас хода: %.1f км\n",
                tesla.getBatteryLevel() * 100, tesla.getRangeKm());
    }
}
