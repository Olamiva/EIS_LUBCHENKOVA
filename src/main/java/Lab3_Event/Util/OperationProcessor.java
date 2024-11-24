package Lab3_Event.Util;

import Lab3_Event.Annotation.AddedOperation;
import Lab3_Event.Annotation.RemovedOperation;
import Lab3_Event.Util.Model.Operation;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class OperationProcessor {

    // Список всех операций
    private final List<Operation> operations = new ArrayList<>();

    // CDI-событие для уведомления о добавлении операции
    @Inject
    @AddedOperation
    private Event<Operation> operationAddedEvent;

    // CDI-событие для уведомления об удалении операции
    @Inject
    @RemovedOperation
    private Event<Operation> operationRemovedEvent;

    /**
     * Добавляет операцию в список и уведомляет слушателей.
     * @param operation добавляемая операция
     */
    public void addOperation(Operation operation) {
        operations.add(operation); // Добавляем операцию в список
        operationAddedEvent.fire(operation); // Уведомляем о добавлении
        System.out.println("Операция добавлена: " + operation);
    }

    /**
     * Удаляет операцию из списка и уведомляет слушателей.
     * Если операция не найдена, выводит сообщение.
     * @param operation удаляемая операция
     */
    public void removeOperation(Operation operation) {
        if (operations.remove(operation)) { // Удаляем операцию, если она существует
            operationRemovedEvent.fire(operation); // Уведомляем об удалении
            System.out.println("Операция удалена: " + operation);
        } else {
            System.out.println("Операция не найдена: " + operation);
        }
    }

    /**
     * Возвращает список всех операций.
     * @return список операций
     */
    public List<Operation> getOperations() {
        return new ArrayList<>(operations); // Возвращаем копию списка операций
    }
}
