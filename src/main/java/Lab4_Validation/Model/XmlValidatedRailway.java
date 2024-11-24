package Lab4_Validation.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class XmlValidatedRailway {
    private String name;
    private String country;
    private int stationCount;
    private int lengthKm;
    private String trackType;
}
