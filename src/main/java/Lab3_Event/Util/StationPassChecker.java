package Lab3_Event.Util;

import Lab3_Event.Annotation.AddedOperation;
import Lab3_Event.Annotation.RemovedOperation;
import Lab3_Event.Util.Model.Operation;
import Lab3_Event.Util.Model.Station;
import Lab3_Event.Util.Model.TrackSectionsMap;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

public class StationPassChecker {

    // Инъекция карты перегона (TrackSectionsMap), которая содержит список всех станций
    @Inject
    private TrackSectionsMap trackSectionsMap;

    // Множество станций, для которых есть операции
    private final Set<String> stationsWithOperations = new HashSet<>();

    /**
     * Обработчик события добавления операции.
     * Добавляет код станции операции в множество.
     * @param operation добавленная операция
     */
    public void onOperationAdd(@Observes @AddedOperation Operation operation) {
        stationsWithOperations.add(operation.getStation().getCode());
        checkAllStations(); // Проверяем, покрыты ли операции все станции
    }

    /**
     * Обработчик события удаления операции.
     * Удаляет код станции операции из множества.
     * @param operation удалённая операция
     */
    public void onOperationRemove(@Observes @RemovedOperation Operation operation) {
        stationsWithOperations.remove(operation.getStation().getCode());
        checkAllStations(); // Проверяем, покрыты ли операции все станции
    }

    /**
     * Проверяет, есть ли операции для всех станций из TrackSectionsMap.
     * Если станции нет в списке с операциями, выводит сообщение о дефекте.
     */
    private void checkAllStations() {
        for (Station station : trackSectionsMap.getStations()) {
            if (!stationsWithOperations.contains(station.getCode())) {
                System.out.println("Обнаружен дефект: Для станции " + station.getCode() + " отсутствуют операции.");
            } else {
                System.out.println("Станция " + station.getCode() + " без дефектов.");
            }
        }
    }
}
