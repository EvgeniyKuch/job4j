package ru.job4j.tracker;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Обёртка над списком заявок.
 * @version $Id$
 * @since 0.1
 */
public class Tracker {
    /**
     * Список для хранение заявок.
     */
    private final List<Item> items = new ArrayList<>();

    /**
     * Метод реализаущий добавление заявки в хранилище
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    /**
     * Редактирование заявок. Метод заменяет ячейку.
     * @param id id заменяемой заявки.
     * @param item заменяющая заявка.
     */
    public void replace(String id, Item item) {
        item.setId(id);
        this.items
                .replaceAll(itemCurrent -> itemCurrent.getId().equals(id) ? item : itemCurrent);
    }

    /**
     * Удаление заявок.
     * @param id id удаляемой заявки.
     */
    public void delete(String id) {
        this.items.removeIf(item -> item.getId().equals(id));
    }

    /**
     * Получение списка всех заявок.
     * @return копия массива this.items без null элементов.
     */
    public List<Item> findAll() {
        return this.items;
    }

    /**
     * Получение списка по имени.
     * Проверяет в цикле все элементы массива this.items, сравнивая name
     * с аргументом метода String key. Элементы, у которых совпадает name,
     * копирует в результирующий массив и возвращает его.
     * @param key искомое имя.
     * @return результирующий массив.
     */
    public List<Item> findByName(String key) {
        return this.items.stream()
                .filter(item -> item.getName().equals(key))
                .collect(Collectors.toList());
    }

    /**
     * Получение заявки по id.
     * Метод проверяет в цикле все элементы массива this.items,
     * сравнивая id с аргументом String id.
     * @param id искомый id.
     * @return найденный Item. Если Item не найден - возвращает null.
     */
    public Item findById(String id) {
        Item result;
        List<Item> resultList = items.stream()
                .filter(item -> item.getId().equals(id))
                .collect(Collectors.toList());
        if (resultList.size() != 0) {
            result = resultList.get(0);
        } else {
            result = null;
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