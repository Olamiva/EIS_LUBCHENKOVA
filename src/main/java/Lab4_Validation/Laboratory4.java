package Lab4_Validation;

import Lab4_Validation.Model.CodeValidatedRailway;
import Lab4_Validation.Model.XmlValidatedRailway;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import java.util.Set;

public class Laboratory4 {

    public static void main(String[] args) {
        // Создание фабрики валидаторов
        ValidatorFactory factory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory();
        Validator validator = factory.getValidator();

        // Создание и проверка объекта XmlValidatedRailway
        XmlValidatedRailway xmlRailway = new XmlValidatedRailway();
        xmlRailway.setName("Транссибирская магистраль");
        xmlRailway.setCountry("Россия");
        xmlRailway.setStationCount(50);
        xmlRailway.setLengthKm(9200);
        xmlRailway.setTrackType("электрифицированный");

        System.out.println("Валидация XmlValidatedRailway:");
        validateAndPrint(validator, xmlRailway);

        // Создание и проверка объекта CodeValidatedRailway
        CodeValidatedRailway railway = new CodeValidatedRailway();
        railway.setName("Железная дорога");
        railway.setCountry("Россия");
        railway.setStationCount(200);
        railway.setLengthKm(0);
        railway.setTrackType("неэлектрифицированный");

        System.out.println("\nВалидация CodeValidatedRailway:");
        validateAndPrint(validator, railway);
    }

    // Метод для валидации и вывода результата валидации в консоль
    private static <T> void validateAndPrint(Validator validator, T object) {
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        if (violations.isEmpty()) {
            System.out.println("Валидация прошла успешно!");
        } else {
            for (ConstraintViolation<T> violation : violations) {
                System.out.println(violation.getPropertyPath() + " " + violation.getMessage());
            }
        }
    }
}
