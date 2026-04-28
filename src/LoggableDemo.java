import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

interface Loggable{

    String getComponentName();

    default void log(String message) {
        String timestamp = formatTimestamp();
        String name = getComponentName();
        System.out.println("[" + timestamp + "] " + "[" + name + "] " + message);
    }
    default void logError(String message) {
        log("ОШИБКА: " + message);
    }
    private String formatTimestamp() {
        LocalTime now = LocalTime.now();
        DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("HH:mm:ss");
        return now.format(formatter);
    }
    static String getLogLevel() {
        return "INFO";
    }
}
class DatabaseService implements Loggable {
    @Override
    public String getComponentName() {
        return "DatabaseService";
    }
    public void connect(String url) {
        log("Подключение к " + url);
        log("Подключение установлено");

    }
}
class AuthService implements Loggable {
    @Override
    public String getComponentName() {
        return "AuthService";
    }
    public void login(String username, boolean success) {
        if (success) {
            logError("Вход пользователя " + username + " - успешно");
        } else {
            logError("Вход пользователя: " + username + " — отказано");
        }
    }
}

public class LoggableDemo {
    public static void main(String[] args) {
        DatabaseService db = new DatabaseService();
        AuthService auth = new AuthService();

        System.out.println("Уровень логирования: " + Loggable.getLogLevel());
        System.out.println();

        db.connect("jdbc:postgresql://localhost/mydb");
        System.out.println();

        auth.login("admin", true);
        auth.login("hacker", false);
    }
}

