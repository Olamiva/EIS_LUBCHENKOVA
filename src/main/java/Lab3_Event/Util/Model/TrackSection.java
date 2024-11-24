package Lab3_Event.Util.Model;

import Lab3_Event.Util.Model.Station;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrackSection {
    private String name; // Наименование перегона.
    private Station stationFrom; // Начальная станция.
    private Station stationTo; // Конечная станция.
    private double distance; // Расстояние между станциями.
}
