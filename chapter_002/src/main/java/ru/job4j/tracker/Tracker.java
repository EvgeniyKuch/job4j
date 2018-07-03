package ru.job4j.tracker;

import java.util.*;

/**
 * Обёртка над массивом заявок.
 * @version $Id$
 * @since 0.1
 */
public class Tracker {
    /**
     * Массив для хранение заявок.
     */
//    private final Item[] items = new Item[100];
    private final List<Item> items = new ArrayList<>();

    /**
     * Указатель ячейки для новой заявки.
     */
//    private int position = 0;

    /**
     * Метод реализаущий добавление заявки в хранилище
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
//        this.items[this.position++] = item;
        this.items.add(item);
        return item;
    }

    /**
     * Редактирование заявок. Метод заменяет ячейку.
     * @param id id заменяемой заявки.
     * @param item заменяющая заявка.
     */
    public void replace(String id, Item item) {
//        for (int i = 0; i != this.position; i++) {
//            if (this.items[i].getId().equals(id)) {
//                this.items[i] = item;
//                this.items[i].setId(id);
//                break;
//            }
//        }
//        Iterator<Item> itemIterator = this.items.iterator();
//        while (itemIterator.hasNext()) {
//            if (itemIterator.next().getId().equals(item.getId())) {
//
//            }
//        }
        item.setId(id);
        this.items.replaceAll(itemCurrent -> itemCurrent.getId().equals(id) ? item : itemCurrent);
    }

    /**
     * Удаление заявок.
     * @param id id удаляемой заявки.
     */
    public void delete(String id) {
//        for (int i = 0; i != this.position; i++) {
//            if (this.items[i].getId().equals(id)) {
//                System.arraycopy(this.items, i + 1, this.items, i, this.items.length - i - 1);
//                this.position--;
//                break;
//            }
//        }
        this.items.removeIf(item -> item.getId().equals(id));
    }

    /**
     * Получение списка всех заявок.
     * @return копия массива this.items без null элементов.
     */
    public Item[] findAll() {
//        return Arrays.copyOf(this.items, this.position);
        return this.items.toArray(new Item[this.items.size()]);
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
//        Item[] result = this.findAll();
//        int j = 0;
//        for (int i = 0; i != result.length; i++) {
//            if (this.items[i].getName().equals(key)) {
//                result[j++] = this.items[i];
//            }
//        }
//        return Arrays.copyOf(result, j);

//        List<Item> result = new ArrayList<>();
//        for (Item item : this.items) {
//            if (item.getName().equals(key)) {
//                result.add(item);
//            }
//        }
//        return result.toArray(new Item[result.size()]);

       return this.items.stream().filter(item -> item.getName().equals(key)).toArray(Item[]::new);
    }

    /**
     * Получение заявки по id.
     * Метод проверяет в цикле все элементы массива this.items,
     * сравнивая id с аргументом String id.
     * @param id искомый id.
     * @return найденный Item. Если Item не найден - возвращает null.
     */
    public Item findById(String id) {
//        Item result = null;
//        for (Item current: this.findAll()) {
//            if (current.getId().equals(id)) {
//                result = current;
//                break;
//            }
//        }
//        return result;
            return items.stream().filter(item -> item.getId().equals(id)).toArray(Item[]::new)[0];
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