package Lab3_Event.Util.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Station {
    private String name; // Наименование станции.
    private String code; // Код станции.
}
