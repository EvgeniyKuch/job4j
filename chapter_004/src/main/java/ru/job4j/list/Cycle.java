package ru.job4j.list;

public class Cycle {

    /**
     * Метод определяет цикличность связанного списка с помощью
     * алгоритма черепахи и зайца. Заяц бежит по списку в 2 раза
     * быстрее черепахи. Если они попадают в цикл, то заяц
     * догонит черпаху и они займут одну позицию, т.к. на каждой
     * итерации расстояние между ними увеличивается на единицу и
     * перепрыгнуть он её не сможет.
     * @param first первый узел списка.
     * @return true - список имеет замыкания, false - замыканий нет.
     */
    boolean hasCycle(Node first) {
        boolean result = false;
        Node turtle = first != null ? first.next : null;
        Node hare = turtle != null ? turtle.next : null;
        while (hare != null && !result) {
            turtle = turtle.next;
            hare = hare.next != null ? hare.next.next : null;
            result = hare == turtle;
        }
        return result;
    }

    class Node<T> {
        T value;
        Node<T> next;

        Node(T value) {
            this.value = value;
        }
    }
}
