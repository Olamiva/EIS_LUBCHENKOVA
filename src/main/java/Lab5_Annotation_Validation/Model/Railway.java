package Lab5_Annotation_Validation.Model;

import Lab5_Annotation_Validation.Annotation.ValidRailway;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ValidRailway
public class Railway {
    private List<RailwayManagementArea> managementAreas; // Список районов управления
}
