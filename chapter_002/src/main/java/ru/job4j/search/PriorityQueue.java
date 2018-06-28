package ru.job4j.search;

import java.util.LinkedList;

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позицию определять по полю приоритет.
     * Для вставик использовать add(int index, E value)
     * @param task задача
     */
    public void put(Task task) {
        int startSize = this.tasks.size();
        for (int i = 0; i != this.tasks.size(); i++) {
            if (task.getPriority() <= this.tasks.get(i).getPriority()) {
                this.tasks.add(i, task);
                break;
            }
        }
        if (startSize == this.tasks.size()) {
            this.tasks.add(task);
        }
    }

    public Task take() {
        return this.tasks.poll();
    }
}