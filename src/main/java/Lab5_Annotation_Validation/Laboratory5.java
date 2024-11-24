package Lab5_Annotation_Validation;

import Lab5_Annotation_Validation.Model.Railway;
import Lab5_Annotation_Validation.Model.RailwayManagementArea;
import Lab5_Annotation_Validation.Model.TrackSection;
import Lab5_Annotation_Validation.Model.Station;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import java.util.Arrays;
import java.util.Collections;

public class Laboratory5 {
    public static void main(String[] args) {
        // Станции
        Station stationA = new Station("Station A");
        Station stationB = new Station("Station B");
        Station stationC = new Station("Station C");
        Station stationD = new Station("Station D");

        // Перегоны
        TrackSection trackSection1 = new TrackSection(stationA, stationB);
        TrackSection trackSection2 = new TrackSection(stationB, stationC);
        TrackSection trackSection3 = new TrackSection(stationC, stationD);

        // Районы управления
        RailwayManagementArea area1 = new RailwayManagementArea(
                Arrays.asList(stationA, stationB, stationC),
                Arrays.asList(trackSection1, trackSection2)
        );

        RailwayManagementArea area2 = new RailwayManagementArea(
                Arrays.asList(stationC, stationD),
                Collections.singletonList(trackSection3)
        );

        // Валидная железная дорога
        Railway validRailway = new Railway(Arrays.asList(area1, area2));

        // Невалидная железная дорога
        RailwayManagementArea disconnectedArea = new RailwayManagementArea(
                Collections.singletonList(new Station("Station E")),
                Collections.emptyList()
        );
        Railway invalidRailway = new Railway(Arrays.asList(area1, disconnectedArea));

        // Валидация
        ValidatorFactory factory = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory();
        Validator validator = factory.getValidator();

        System.out.println("Валидная железная дорога:");
        validator.validate(validRailway).forEach(violation -> System.out.println(violation.getMessage()));

        System.out.println("\nНевалидная железная дорога:");
        validator.validate(invalidRailway).forEach(violation -> System.out.println(violation.getMessage()));
    }
}

