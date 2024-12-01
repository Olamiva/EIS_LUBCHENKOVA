package Lab3_Event.Event;

import Lab3_Event.Model.Operation;
import Lab3_Event.Model.Station;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OperationsAndStationsEvent {
    private List<Operation> operations;
    private List<Station> stations;
}
