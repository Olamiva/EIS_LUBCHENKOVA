package Lab3_Event.Util;

import Lab3_Event.Annotation.EditedOperations;
import Lab3_Event.Event.OperationsAndStationsEvent;
import Lab3_Event.Model.Operation;

import javax.enterprise.event.Observes;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperationsValidator {

    public void validateOperations(@Observes @EditedOperations OperationsAndStationsEvent event) {
        List<Operation> operations = event.getOperations();

        // Карта для отслеживания баланса операций (прибытие/отправление) по поездам
        Map<String, Integer> operationBalance = new HashMap<>();

        // Обработка операций
        for (Operation operation : operations) {
            String trainName = operation.getTrain().getName();

            switch (operation.getOperationCode()) {
                case ARRIVAL:
                    operationBalance.put(trainName, operationBalance.getOrDefault(trainName, 0) + 1);
                    break;

                case DEPARTURE:
                    operationBalance.put(trainName, operationBalance.getOrDefault(trainName, 0) - 1);
                    break;

                case FOLLOW_UP:
                    System.out.println("Операция FOLLOW_UP игнорируется для проверки баланса.");
                    break;

                default:
                    System.out.println("Неизвестный код операции: " + operation.getOperationCode());
                    break;
            }
        }

        // Проверка баланса операций для каждого поезда
        for (Map.Entry<String, Integer> entry : operationBalance.entrySet()) {
            String trainName = entry.getKey();
            int balance = entry.getValue();

            if (balance != 0) {
                System.out.println("Ошибка! Некорректный список операций для поезда " + trainName +
                        ": баланс операций (прибытие/отправление) = " + balance);
            }
        }

        // Если все корректно
        if (operationBalance.values().stream().allMatch(balance -> balance == 0)) {
            System.out.println("Все операции корректны.");
        }
    }
}
