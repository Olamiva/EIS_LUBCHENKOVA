package Lab3_Event.Util;

import Lab3_Event.Annotation.EditedOperations;
import Lab3_Event.Event.OperationsAndStationsEvent;
import Lab3_Event.Model.Operation;
import Lab3_Event.Model.Station;

import javax.enterprise.event.Observes;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StationsValidator {

    public void validateStations(@Observes @EditedOperations OperationsAndStationsEvent event) {
        List<Operation> operations = event.getOperations();
        List<Station> stations = event.getStations();

        // Собираем все станции, которые участвовали в операциях
        Set<Station> involvedStations = new HashSet<>();
        for (Operation operation : operations) {
            involvedStations.add(operation.getFrom());
            involvedStations.add(operation.getTo());
        }

        // Сравниваем с полным списком станций
        System.out.println("Список станций без операций:");
        for (Station station : stations) {
            if (!involvedStations.contains(station)) {
                System.out.println("- " + station.getName());
            }
        }
    }
}
