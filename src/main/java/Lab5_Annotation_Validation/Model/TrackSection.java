package Lab5_Annotation_Validation.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrackSection {
    private Station stationFrom; // Станция отправления
    private Station stationTo; // Станция прибытия
}
