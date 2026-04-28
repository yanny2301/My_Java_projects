public class EmployeeFixed {
        private String name;
        private int age;
        private double salary;              // какой модификатор?
        private String password;
        // конструктор для инициализации объектов - присвоение переменной начального значения
        public EmployeeFixed(String name, int age, double salary, String password) {
            this.name = name;
            this.age = age;
            this.salary = salary;
            this.password = password;
        }
        //геттеры - метод для получения значения приватного поля
        public String getName(){
            return name;
        }

        public int getAge(){
            return age;
        }

        public double getSalary() {
            return salary;
        }

        private String getRole() {
            return "Employee";
        }
        // метод
        public void promote(double raise) {
            this.salary += raise;
        }

        public void printSummary() {
            System.out.println(name + ", " + age + " лет, " + salary + " руб.");
        }
        // метод
        private boolean validatePassword(String input) {
            return password.equals(input);
        }
        public boolean authenticate(String input) {
            return validatePassword(input);
        }
        public static void main(String[] args) {
            EmployeeFixed emp = new EmployeeFixed("Иван", 30, 80000, "secret");

            System.out.println(emp.name);            // Строка A
            System.out.println(emp.age);             // Строка B
            System.out.println(emp.salary);          // Строка C
            System.out.println(emp.password);        // Строка D
            System.out.println(emp.getRole());       // Строка E
            emp.promote(5000);                       // Строка F
            emp.printSummary();                      // Строка G
            emp.validatePassword("secret");          // Строка H
        }
    }
