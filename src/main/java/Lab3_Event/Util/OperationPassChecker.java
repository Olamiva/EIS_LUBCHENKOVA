package Lab3_Event.Util;

import Lab3_Event.Annotation.AddedOperation;
import Lab3_Event.Annotation.RemovedOperation;
import Lab3_Event.Util.Model.Operation;
import Lab3_Event.Util.Model.OperationCode;

import javax.enterprise.event.Observes;
import java.util.HashMap;
import java.util.Map;

public class OperationPassChecker {

    // Карта для отслеживания баланса операций по каждой станции
    private final Map<String, Integer> stationOperationBalance = new HashMap<>();

    /**
     * Обработчик события добавления операции.
     * @param operation добавленная операция
     */
    public void onOperationAdd(@Observes @AddedOperation Operation operation) {
        updateBalance(operation, 1); // Увеличиваем баланс
        checkDefects(operation.getStation().getCode()); // Проверяем наличие дефектов
    }

    /**
     * Обработчик события удаления операции.
     * @param operation удалённая операция
     */
    public void onOperationRemove(@Observes @RemovedOperation Operation operation) {
        updateBalance(operation, -1); // Уменьшаем баланс
        checkDefects(operation.getStation().getCode()); // Проверяем наличие дефектов
    }

    /**
     * Обновляет баланс операций для станции.
     * @param operation операция (прибытие, отправление или проследование)
     * @param delta изменение баланса (+1 для добавления, -1 для удаления)
     */
    private void updateBalance(Operation operation, int delta) {
        String stationCode = operation.getStation().getCode(); // Код станции
        // Определяем изменение баланса в зависимости от типа операции
        int balanceChange = (operation.getCode() == OperationCode.Arrival) ? delta :
                (operation.getCode() == OperationCode.Departure ? -delta : 0);
        // Обновляем баланс для станции
        stationOperationBalance.merge(stationCode, balanceChange, Integer::sum);
    }

    /**
     * Проверяет наличие дефектов для указанной станции.
     * @param stationCode код станции
     */
    private void checkDefects(String stationCode) {
        int balance = stationOperationBalance.getOrDefault(stationCode, 0);
        if (balance > 0) {
            System.out.println("Обнаружен дефект: Отсутствуют отправления со станции " + stationCode);
        } else if (balance < 0) {
            System.out.println("Обнаружен дефект: Отсутствуют прибытия на станцию " + stationCode);
        } else {
            System.out.println("Станция " + stationCode + " без дефектов.");
        }
    }
}
