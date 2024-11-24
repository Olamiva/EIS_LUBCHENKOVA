package Lab3_Event.Util.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Operation {
    private OperationCode code; // Код операции
    private Date date; // Дата проведения операции
    private Station station; // Станция операции

}
