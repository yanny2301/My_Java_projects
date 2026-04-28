import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

// Аннотации
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface NotEmpty {
    String message() default "Поле не может быть пустым";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Range {
    int min();
    int max();
    String message() default "Значение вне допустимого диапазона";
}

// Класс формы
class RegistrationForm {
    @NotEmpty(message = "Имя обязательно")
    private String name;

    @NotEmpty
    private String email;

    @Range(min = 18, max = 120, message = "Возраст должен быть от 18 до 120")
    private int age;

    public RegistrationForm(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }
}

// Валидатор
class Validator {
    public static List<String> validate(Object obj) {
        List<String> errors = new ArrayList<>();
        Class<?> clazz = obj.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);

            try {
                if (field.isAnnotationPresent(NotEmpty.class)) {
                    NotEmpty notEmpty = field.getAnnotation(NotEmpty.class);
                    Object value = field.get(obj);

                    if (value == null || ((String) value).trim().isEmpty()) {
                        errors.add(notEmpty.message());
                    }
                }

                if (field.isAnnotationPresent(Range.class)) {
                    Range range = field.getAnnotation(Range.class);
                    int value = field.getInt(obj);

                    if (value < range.min() || value > range.max()) {
                        errors.add(range.message());
                    }
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return errors;
    }
}

// Главный класс
public class ValidationFramework {
    public static void main(String[] args) {
        RegistrationForm valid = new RegistrationForm("Иван", "ivan@mail.ru", 25);
        RegistrationForm invalid = new RegistrationForm("", null, 15);

        System.out.println("=== Валидация корректной формы ===");
        List<String> errors1 = Validator.validate(valid);
        System.out.println(errors1.isEmpty() ? "Все поля валидны!" : errors1);

        System.out.println("\n=== Валидация некорректной формы ===");
        List<String> errors2 = Validator.validate(invalid);
        errors2.forEach(e -> System.out.println("  - " + e));
    }
}
