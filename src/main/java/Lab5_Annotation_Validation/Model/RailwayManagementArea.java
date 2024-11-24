package Lab5_Annotation_Validation.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RailwayManagementArea {
    private List<Station> stations; // Список станций в районе
    private List<TrackSection> trackSections; // Список перегонов в районе
}
