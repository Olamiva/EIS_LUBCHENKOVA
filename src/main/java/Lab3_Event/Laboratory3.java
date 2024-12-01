package Lab3_Event;

import Lab3_Event.Model.*;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public class Laboratory3 {
    protected static Weld weld;
    protected static WeldContainer container;

    public static void main(String[] args) throws Exception {
        weld = new Weld();
        container = weld.initialize();

        try {
            RailRoad railRoad = container.select(RailRoad.class).get();

            // Инициализация станций
            Station stationA = new Station(1, "Станция A", "A");
            Station stationB = new Station(2, "Станция B", "B");
            Station stationC = new Station(3, "Станция C", "C");
            Station stationD = new Station(4, "Станция D", "D");
            Station stationE = new Station(5, "Станция E", "E");
            Station stationF = new Station(6, "Станция F", "F");
            Station stationG = new Station(7, "Станция G", "G");

            // Добавление станций в железную дорогу
            railRoad.addStation(stationA);
            railRoad.addStation(stationB);
            railRoad.addStation(stationC);
            railRoad.addStation(stationD);
            railRoad.addStation(stationE);
            railRoad.addStation(stationF);
            railRoad.addStation(stationG);

            // Инициализация перегонов
            railRoad.addTrackSection(stationA, stationB);
            railRoad.addTrackSection(stationA, stationC);
            railRoad.addTrackSection(stationA, stationD);
            railRoad.addTrackSection(stationB, stationC);
            railRoad.addTrackSection(stationD, stationC);
            railRoad.addTrackSection(stationC, stationE);
            railRoad.addTrackSection(stationC, stationF);
            railRoad.addTrackSection(stationC, stationG);
            railRoad.addTrackSection(stationE, stationF);

            // Инициализация поездов
            Train train1 = new Train(1, "Поезд 1", "T1");
            Train train2 = new Train(2, "Поезд 2", "T2");

            // Инициализация операций
            Operation operation1 = new Operation(train1, stationA, stationD, OperationCode.DEPARTURE);
            Operation operation2 = new Operation(train1, stationD, stationC, OperationCode.FOLLOW_UP);
            Operation operation3 = new Operation(train1, stationC, stationE, OperationCode.ARRIVAL);

            Operation operation4 = new Operation(train2, stationA, stationB, OperationCode.DEPARTURE);
            Operation operation5 = new Operation(train2, stationB, stationC, OperationCode.FOLLOW_UP);
            Operation operation6 = new Operation(train2, stationC, stationG, OperationCode.ARRIVAL);

            // Добавление операций в железную дорогу
            railRoad.addOperation(operation1);
            railRoad.addOperation(operation2);
            railRoad.addOperation(operation3);
            railRoad.addOperation(operation4);
            railRoad.addOperation(operation5);
            railRoad.addOperation(operation6);

            // Вывод состояния железной дорогиФ
            railRoad.printRailRoad();

        } finally {
            weld.shutdown();
        }
    }
}
