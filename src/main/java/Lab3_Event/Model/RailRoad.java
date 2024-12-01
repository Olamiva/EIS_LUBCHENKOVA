package Lab3_Event.Model;

import Lab3_Event.Annotation.EditedOperations;
import Lab3_Event.Event.OperationsAndStationsEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RailRoad {
    // Список станций
    private List<Station> stations = new ArrayList<>();
    // Список перегонов
    private List<TrackSection> trackSections = new ArrayList<>();
    // Местоположение поездов
    private Map<Train, Station> trainLocations = new HashMap<>();
    // Операции для каждого поезда
    private Map<Train, List<Operation>> trainOperations = new HashMap<>();

    @Inject
    @EditedOperations
    private Event<OperationsAndStationsEvent> operationsEvent;

    // Добавить станцию
    public void addStation(Station station) {
        if (!stations.contains(station)) {
            stations.add(station);
        }
    }

    // Добавить перегон
    public void addTrackSection(Station from, Station to) {
        TrackSection trackSection = new TrackSection(from, to);
        trackSections.add(trackSection);
    }

    // Найти перегон
    public TrackSection findTrackSection(Station from, Station to) {
        return trackSections.stream()
                .filter(track -> (track.getFrom().equals(from) && track.getTo().equals(to)) ||
                        (track.getFrom().equals(to) && track.getTo().equals(from)))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Перегон между станциями "
                        + from.getName() + " и "
                        + to.getName() + " не найден."));
    }

    // Добавить операцию
    public void addOperation(Operation operation) {
        Train train = operation.getTrain();
        Station from = operation.getFrom();
        Station to = operation.getTo();

        // Проверка перегона
        findTrackSection(from, to);

        // Установить начальное местоположение
        if (!trainLocations.containsKey(train)) {
            trainLocations.put(train, from);
            System.out.println("Поезд " + train.getName() + " добавлен на станцию: " + from.getName());
        }

        // Добавить операцию
        trainOperations.computeIfAbsent(train, k -> new ArrayList<>()).add(operation);

        // Выполнить операцию
        executeLastOperation(train);

        // Отправить событие
        List<Operation> operations = trainOperations.get(train);
        OperationsAndStationsEvent event = new OperationsAndStationsEvent(operations, new ArrayList<>(stations));
        operationsEvent.fire(event);
    }

    // Удалить операцию
    public void removeOperation(Operation operation) {
        Train train = operation.getTrain();

        if (!trainOperations.containsKey(train)) {
            System.out.println("Для поезда " + train.getName() + " нет операций для удаления.");
            return;
        }

        List<Operation> operations = trainOperations.get(train);

        if (!operations.remove(operation)) {
            System.out.println("Операция не найдена для удаления: " + operation.getOperationCode());
            return;
        }

        System.out.println("Операция удалена для поезда " + train.getName() + ": " + operation.getOperationCode());

        // Если список операций для поезда пуст, удаляем запись о поезде
        if (operations.isEmpty()) {
            trainOperations.remove(train);
            trainLocations.remove(train);
            System.out.println("Все операции для поезда " + train.getName() + " удалены.");
        }

        // Отправить событие
        OperationsAndStationsEvent event = new OperationsAndStationsEvent(operations, new ArrayList<>(stations));
        operationsEvent.fire(event);
    }


    // Выполнить последнюю операцию
    public void executeLastOperation(Train train) {
        if (!trainOperations.containsKey(train)) {
            System.out.println("Для поезда не найдено операций: " + train.getName());
            return;
        }

        List<Operation> operations = trainOperations.get(train);

        if (operations.isEmpty()) {
            System.out.println("Нет операций для поезда " + train.getName());
            return;
        }

        Operation lastOperation = operations.get(operations.size() - 1);
        moveTrain(lastOperation);
        System.out.println("Поезд " + train.getName() +
                " перемещен со станции " + lastOperation.getFrom().getName() +
                " на станцию " + lastOperation.getTo().getName() +
                ". Операция: " + lastOperation.getOperationCode());
    }

    // Переместить поезд
    private void moveTrain(Operation operation) {
        Train train = operation.getTrain();
        Station from = operation.getFrom();
        Station to = operation.getTo();

        if (!trainLocations.get(train).equals(from)) {
            throw new IllegalStateException("Поезд не находится в ожидаемом месте.");
        }

        findTrackSection(from, to);

        System.out.println("Перемещаем поезд " + train.getName() +
                " со станции " + from.getName() +
                " на станцию " + to.getName());

        trainLocations.put(train, to);
    }

    // Получить текущее местоположение
    public Station getTrainLocation(Train train) {
        return trainLocations.get(train);
    }

    // Печать состояния
    public void printRailRoad() {
        System.out.println();
        System.out.println("ЖЕЛЕЗНАЯ ДОРОГА");
        System.out.println("Станции:");
        for (Station station : stations) {
            System.out.println("- " + station);
        }
        System.out.println("\nПерегоны:");
        for (TrackSection track : trackSections) {
            System.out.println("- Из: " + track.getFrom().getName() +
                    " В: " + track.getTo().getName());
        }
        System.out.println("\nМестоположения поездов:");
        for (Map.Entry<Train, Station> entry : trainLocations.entrySet()) {
            System.out.println("- Поезд: " + entry.getKey().getName() +
                    " Местоположение: " + entry.getValue().getName());
        }
        System.out.println("\nОперации поездов:");
        for (Map.Entry<Train, List<Operation>> entry : trainOperations.entrySet()) {
            System.out.println("- Поезд: " + entry.getKey().getName());
            for (Operation operation : entry.getValue()) {
                System.out.println("  - Из: " + operation.getFrom().getName() +
                        " В: " + operation.getTo().getName() +
                        " Операция: " + operation.getOperationCode());
            }
        }
    }
}
