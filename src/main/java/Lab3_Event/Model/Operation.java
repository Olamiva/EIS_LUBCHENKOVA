package Lab3_Event.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Operation {
    private Train train;
    private Station from;
    private Station to;
    private OperationCode operationCode;
}
