 //A
 record Temperature(double value, Unit unit) {
    enum Unit {CELSIUS, FAHRENHEIT, KELVIN};
    //компактный конструктор
    public Temperature {
        // конвертация
        double kelvinValue = switch (unit) {
            case CELSIUS -> value + 273.15;
            case FAHRENHEIT -> (value - 32) * 5 / 9 + 273.15;
            case KELVIN -> value;
        };
        if (kelvinValue < 0) {
            throw new IllegalArgumentException("Температура не может быть ниже абсолютного нуля");
        }
    }
    //метод конвертации между единицами
    public Temperature convertTo(Unit targetUnit) {
        if (this.unit == targetUnit) {
            return this;
        }
        // конвертация сначала в кельвины
        double kelvin = switch (this.unit) {
            case CELSIUS -> value + 273.15;
            case FAHRENHEIT -> (value - 32) * 5 / 9 + 273.15;
            case KELVIN -> value;
        };
        double convertedValue = switch (targetUnit) {
            case CELSIUS -> kelvin - 273.15;
            case FAHRENHEIT -> (kelvin - 273.15) * 9 / 5 + 32;
            case KELVIN -> kelvin;
        };
        return new Temperature(convertedValue, targetUnit);
    }
    @Override
    public String toString() {
        String formattedValue;
        if (value == Math.floor(value)) {
            formattedValue = String.format("%.0f", value);
        } else {
            formattedValue = String.format("%.2f", value);
        }
        String unitSymbol = switch (unit) {
            case CELSIUS ->  "°C";
            case FAHRENHEIT ->  "°F";
            case KELVIN ->  "K";
        };
        return formattedValue + " " + unitSymbol;
    }
}
//B
enum MathOperation {
    ADD {
        @Override
        public double apply(double a, double b) {
            return a + b;
        }
    },
    SUBTRACT {
        @Override
        public double apply(double a, double b) {
            return a - b;
        }
    },
    MULTIPLY {
        @Override
        public double apply(double a, double b) {
            return  a * b;
        }
    },
    DIVIDE {
        @Override
        public double apply(double a, double b) {
            if (b == 0) {
                throw new ArithmeticException("Деление на 0 запрещено");
            }
            return a / b;
        }
    };
    public abstract double apply(double a, double b);

}
 public class RecordEnumDemo {
     public static void main(String[] args) {
         // Часть A: демонстрация Temperature
         System.out.println("Temperature");
         Temperature body = new Temperature(36.6, Temperature.Unit.CELSIUS);
         System.out.println("Исходная температура: " + body);
         System.out.println("В Фаренгейтах: " + body.convertTo(Temperature.Unit.FAHRENHEIT));
         System.out.println("В Кельвинах: " + body.convertTo(Temperature.Unit.KELVIN));

         // Проверка валидации (раскомментируй для проверки)
         // Temperature invalid = new Temperature(-10, Temperature.Unit.KELVIN); // Ошибка!

         System.out.println();

         // Часть B: демонстрация MathOperation
         System.out.println("MathOperation");
         double a = 10, b = 3;
         for (MathOperation op : MathOperation.values()) {
             try {
                 System.out.printf("%s(%.2f, %.2f) = %.2f%n",
                         op.name(), a, b, op.apply(a, b));
             } catch (ArithmeticException e) {
                 System.out.printf("%s(%.2f, %.2f) = %s%n",
                         op.name(), a, b, e.getMessage());
             }
         }

         // Дополнительный тест с делением на ноль
         System.out.println("\nПроверка деления на ноль");
         try {
             MathOperation.DIVIDE.apply(10, 0);
         } catch (ArithmeticException e) {
             System.out.println("Ошибка: " + e.getMessage());
         }
     }
 }

