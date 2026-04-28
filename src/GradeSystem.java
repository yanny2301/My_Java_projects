import java.util.*;

public class GradeSystem {
    enum Grade {
        A("Отлично", 4.0),
        B("Хорошо", 3.0),
        C("Удовлетворительно", 2.0),
        D("Неудовлетворительно", 1.0),
        F("Провал", 0.0);

        private final String description;
        private final double gpaValue;

        Grade(String description, double gpaValue) {
            this.description = description;
            this.gpaValue = gpaValue;

        }
        public String getDescription(){
            return description;
        }
        public double getGpaValue() {
            return gpaValue;
        }

        public boolean isPassing(){
            if (this == Grade.D || this == Grade.F) {
                return false;
            }
            else {
                return true;
            }
        }
        public static Grade fromScore(int score) {
            if (score >= 90) {
                return Grade.A;
            }
            else if (score >= 80) {
                return Grade.B;
            }
            else if (score >= 70) {
                return Grade.C;
            }
            else if (score >= 60) {
                return Grade.D;
            }
            else {
                return Grade.F;
            }
        }

    }
    public record Student(String name, int id) {
        public Student {
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("Имя не может быть пустым");
            }
            if (id <= 0) {
                throw new IllegalArgumentException("ID не может быть отрицательным");
            }
        }
    }
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Jack", 1),
                new Student("Ann", 2),
                new Student("Alice", 3),
                new Student("Bob", 4),
                new Student("Kimi", 5),
                new Student("Chanel", 6)
        );

        int[] scores = {95, 82, 67, 45, 78, 91};

        // Группируем студентов по оценкам
        EnumMap<Grade, List<Student>> gradeMap = new EnumMap<>(Grade.class);
        for (Grade grade : Grade.values()) {
            gradeMap.put(grade, new ArrayList<>());
        }

        // Оцениваем и группируем
        double totalGPA = 0;
        for (int i = 0; i < students.size(); i++) {
            Grade grade = Grade.fromScore(scores[i]);
            gradeMap.get(grade).add(students.get(i));
            totalGPA += grade.getGpaValue();
            System.out.printf("%s получил %d баллов -> %s%n",
                    students.get(i).name(), scores[i], grade);
        }

        // Проходные оценки
        EnumSet<Grade> passingGrades = EnumSet.range(Grade.A, Grade.C);

        // Вывод результатов
        System.out.println("\nРезультаты");
        for (Grade grade : Grade.values()) {
            List<Student> list = gradeMap.get(grade);
            System.out.printf("%s (%s): %d студентов - %s%n",
                    grade, grade.getDescription(), list.size(),
                    list.stream().map(Student::name).toList());
        }

        System.out.printf("%nПроходные оценки: %s%n", passingGrades);
        System.out.printf("Средний GPA: %.2f%n", totalGPA / students.size());
    }
}
