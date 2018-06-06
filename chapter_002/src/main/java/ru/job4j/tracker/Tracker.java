package ru.job4j.tracker;

import java.util.Arrays;
import java.util.UUID;

/**
 * Обёртка над массивом заявок.
 * @version $Id$
 * @since 0.1
 */
public class Tracker {
    /**
     * Массив для хранение заявок.
     */
    private final Item[] items = new Item[100];

    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;

    /**
     * Метод реализаущий добавление заявки в хранилище
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
        return item;
    }

    /**
     * Редактирование заявок. Метод заменяет ячейку.
     * @param id id заменяемой заявки.
     * @param item заменяющая заявка.
     */
    public void replace(String id, Item item) {
        for (int i = 0; i != this.position; i++) {
            if (this.items[i].getId().equals(id)) {
                this.items[i] = item;
                break;
            }
        }
    }

    /**
     * Удаление заявок.
     * @param id id удаляемой заявки.
     */
    public void delete(String id) {
        for (int i = 0; i != this.position; i++) {
            if (this.items[i].getId().equals(id)) {
                System.arraycopy(this.items, i + 1, this.items, i, this.items.length - i - 1);
                this.position--;
                break;
            }
        }
    }

    /**
     * Получение списка всех заявок.
     * @return копия массива this.items без null элементов.
     */
    public Item[] findAll() {
        return Arrays.copyOf(this.items, this.position);
    }

    /**
     * Получение списка по имени.
     * Проверяет в цикле все элементы массива this.items, сравнивая name
     * с аргументом метода String key. Элементы, у которых совпадает name,
     * копирует в результирующий массив и возвращает его.
     * @param key искомое имя.
     * @return результирующий массив.
     */
    public Item[] findByName(String key) {
        Item[] result = this.findAll();
        int j = 0;
        for (int i = 0; i != result.length; i++) {
            if (this.items[i].getName().equals(key)) {
                result[j++] = this.items[i];
            }
        }
        return Arrays.copyOf(result, j);
    }

    /**
     * Получение заявки по id.
     * Метод проверяет в цикле все элементы массива this.items,
     * сравнивая id с аргументом String id.
     * @param id искомый id.
     * @return найденный Item. Если Item не найден - возвращает null.
     */
    public Item findById(String id) {
        Item result = null;
        for (Item current: this.findAll()) {
            if (current.getId().equals(id)) {
                result = current;
                break;
            }
        }
        return result;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ.
     */
    private String generateId() {
        return UUID.randomUUID().toString();
    }
}