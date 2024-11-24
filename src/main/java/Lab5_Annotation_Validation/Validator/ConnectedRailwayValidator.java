package Lab5_Annotation_Validation.Validator;

import Lab5_Annotation_Validation.Annotation.ValidRailway;
import Lab5_Annotation_Validation.Model.Railway;
import Lab5_Annotation_Validation.Model.RailwayManagementArea;
import Lab5_Annotation_Validation.Model.Station;
import Lab5_Annotation_Validation.Model.TrackSection;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.*;

public class ConnectedRailwayValidator implements ConstraintValidator<ValidRailway, Railway> {

    @Override
    public boolean isValid(Railway railway, ConstraintValidatorContext context) {
        if (railway == null || railway.getManagementAreas() == null || railway.getManagementAreas().isEmpty()) {
            return true; // Если объект пустой, пропускаем проверку
        }

        // Собираем граф станций
        Set<Station> allStations = new HashSet<>();
        Map<Station, List<Station>> graph = new HashMap<>();

        for (RailwayManagementArea area : railway.getManagementAreas()) {
            if (area.getStations() != null) {
                allStations.addAll(area.getStations());
            }

            if (area.getTrackSections() != null) {
                for (TrackSection track : area.getTrackSections()) {
                    graph.computeIfAbsent(track.getStationFrom(), k -> new ArrayList<>()).add(track.getStationTo());
                    graph.computeIfAbsent(track.getStationTo(), k -> new ArrayList<>()).add(track.getStationFrom());
                }
            }
        }

        if (allStations.isEmpty()) {
            return true; // Если станций нет, объект считается валидным
        }

        // Проверяем связность графа (поиск в глубину/ширину)
        return isGraphConnected(graph, allStations);
    }

    private boolean isGraphConnected(Map<Station, List<Station>> graph, Set<Station> allStations) {
        if (graph.isEmpty()) return false;

        Set<Station> visited = new HashSet<>();
        Queue<Station> queue = new LinkedList<>();

        // Начинаем с любой станции
        Station startStation = allStations.iterator().next();
        queue.add(startStation);

        while (!queue.isEmpty()) {
            Station current = queue.poll();
            if (!visited.add(current)) {
                continue; // Уже посещена
            }
            List<Station> neighbors = graph.getOrDefault(current, Collections.emptyList());
            queue.addAll(neighbors);
        }

        // Если все станции были посещены, граф связен
        return visited.containsAll(allStations);
    }
}
