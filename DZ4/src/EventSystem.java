import java.util.ArrayList;
import java.util.List;

public class EventSystem {
    // вложенный интерфейс, неявно static
    public interface EventHandler<T> {
        void handle(T event);
    }
    public interface EventFilter<T> {
        boolean accept(T event);
    }
    public static class Event {
        String type;
        String data;
        long timestamp;
        public Event(String type, String data) {
            this.type = type;
            this.data = data;
            this.timestamp = System.currentTimeMillis();
        }
    }
    private List<EventHandler<Event>> handlers = new ArrayList<>();
    private EventFilter<Event> filter;

    // установить фильтр
    public void setFilter(EventFilter<Event> filter) {
        this.filter = filter;
    }
    // добавить обработчик в список
    public void addHandler(EventHandler<Event> handler) {
        handlers.add(handler);
    }
    // запустить событие
    public void fire(Event event) {
        if (filter != null && !filter.accept(event)) {
            System.out.println("Событие отфильтровано: " + event.type);
            return;
        }

        for (EventHandler<Event> handler : handlers) {
            handler.handle(event);  // каждый обработчик получает событие
        }
    }

    public static void main(String[] args) {
        // 1. Создаем систему событий
        EventSystem eventSystem = new EventSystem();

        // 2. Устанавливаем фильтр: пропускает только ERROR
        eventSystem.setFilter(new EventSystem.EventFilter<EventSystem.Event>() {
            @Override
            public boolean accept(EventSystem.Event event) {
                return "ERROR".equals(event.type);
            }
        });

        // 3. Добавляем обработчик: выводит информацию о событии
        eventSystem.addHandler(new EventSystem.EventHandler<EventSystem.Event>() {
            @Override
            public void handle(EventSystem.Event event) {
                System.out.println("Обработано событие: " + event.type +
                        " | Данные: " + event.data +
                        " | Время: " + event.timestamp);
            }
        });

        // 4. Запускаем 4 события
        System.out.println("=== Запуск событий ===");

        eventSystem.fire(new EventSystem.Event("INFO", "Информационное сообщение"));
        eventSystem.fire(new EventSystem.Event("ERROR", "Ошибка подключения к БД"));
        eventSystem.fire(new EventSystem.Event("DEBUG", "Отладочная информация"));
        eventSystem.fire(new EventSystem.Event("ERROR", "Критическая ошибка памяти"));

    }

}
