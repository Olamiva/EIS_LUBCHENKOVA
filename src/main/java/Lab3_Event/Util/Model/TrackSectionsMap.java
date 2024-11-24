package Lab3_Event.Util.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackSectionsMap {
    // Карта, где ключ — станция, а значение — список перегона (TrackSection) для этой станции
    private Map<Station, List<TrackSection>> trackSections = new HashMap<>();

    /**
     * Добавляет станцию в карту, если её там ещё нет.
     * @param station станция, которую нужно добавить
     */
    private void addStation(Station station) {
        trackSections.putIfAbsent(station, new ArrayList<>());
    }

    /**
     * Добавляет перегон (TrackSection) между двумя станциями.
     * Если станции ещё не существуют в карте, они будут добавлены автоматически.
     * @param name     название перегона
     * @param from     начальная станция
     * @param to       конечная станция
     * @param distance расстояние между станциями
     */
    public void addTrackSection(String name, Station from, Station to, double distance) {
        addStation(from); // Убедимся, что начальная станция добавлена
        addStation(to);   // Убедимся, что конечная станция добавлена
        // Добавляем новый перегон в список, связанный с начальной станцией
        trackSections.get(from).add(new TrackSection(name, from, to, distance));
    }

    /**
     * Возвращает множество всех станций, содержащихся в карте.
     * @return множество объектов Station
     */
    public Set<Station> getStations() {
        return trackSections.keySet();
    }

    /**
     * Возвращает список всех перегонов (TrackSection) в карте.
     * Собирает данные из всех списков значений карты.
     * @return список всех TrackSection
     */
    public List<TrackSection> getTrackSections() {
        List<TrackSection> sections = new ArrayList<>();
        // Проходим по всем спискам значений (списки TrackSection) и добавляем их в общий список
        for (List<TrackSection> trackSectionList : trackSections.values()) {
            sections.addAll(trackSectionList);
        }
        return sections;
    }

    /**
     * Возвращает список перегонов (TrackSection), начинающихся с указанной станции.
     * Если станция отсутствует в карте, возвращается пустой список.
     * @param station начальная станция
     * @return список перегонов, начинающихся с указанной станции
     */
    public List<TrackSection> getTrackSectionFrom(Station station) {
        // Если станция отсутствует в карте, возвращаем пустой список
        return trackSections.getOrDefault(station, Collections.emptyList());
    }
}
