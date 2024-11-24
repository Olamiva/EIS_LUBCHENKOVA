package Lab3_Event;

import Lab3_Event.Util.OperationProcessor;
import Lab3_Event.Util.Model.Operation;
import Lab3_Event.Util.Model.OperationCode;
import Lab3_Event.Util.Model.Station;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import java.util.Date;

public class Laboratory3 {

    protected static Weld weld;
    protected static WeldContainer container;

    public static void main(String[] args) {
        weld = new Weld();
        container = weld.initialize();

        try {
            OperationProcessor operationProcessor = container.select(OperationProcessor.class).get();

            // Create sample stations
            Station stationA = new Station("Station A", "STA");
            Station stationB = new Station("Station B", "STB");

            // Create operations
            Operation arrivalA = new Operation(OperationCode.Arrival, new Date(), stationA);
            Operation departureA = new Operation(OperationCode.Departure, new Date(), stationA);
            Operation followUpB = new Operation(OperationCode.FollowUp, new Date(), stationB);

            // Process operations
            operationProcessor.addOperation(arrivalA);
            operationProcessor.addOperation(departureA);
            operationProcessor.addOperation(followUpB);

            // Remove operation
            operationProcessor.removeOperation(arrivalA);

        } finally {
            weld.shutdown();
        }
    }
}
