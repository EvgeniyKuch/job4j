package ru.job4j.tracker;

import java.util.Date;
import java.util.StringJoiner;

public class StartUI {
    /**
     * Константа меню для добавления новой заявки.
     */
    private static final String ADD = "0";

    /**
     * Константа меню для вывода списка заявок.
     */
    private static final String SHOWALL = "1";

    /**
     * Константа меню для редактирования заявки.
     */
    private static final String EDIT = "2";

    /**
     * Константа меню для удаления заявки заявки.
     */
    private static final String DELETE = "3";

    /**
     * Константа меню для поиска заявки по ID.
     */
    private static final String FINDID = "4";

    /**
     * Константа меню для поиска заявок по имени.
     */
    private static final String FINDNAME = "5";

    /**
     * Константа для выхода из цикла.
     */
    private static final String EXIT = "6";

    /**
     * Получение данных от пользователя.
     */
    private Input input;

    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню : ");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (SHOWALL.equals(answer)) {
                this.showAllItem();
            } else if (DELETE.equals(answer)) {
                this.deleteItem();
            } else if (EDIT.equals(answer)) {
                this.replaceItem();
            } else if (FINDID.equals(answer)) {
                this.findItemById();
            } else if (FINDNAME.equals(answer)) {
                this.findItemByName();
            } else if (EXIT.equals(answer)) {
                exit = true;
            }
        }
    }

    /**
     * Метод реализует добавленяи новый заявки в хранилище.
     */
    private void createItem() {
        System.out.println("------------ Добавление новой заявки --------------");
        String name = this.input.ask("Введите имя заявки: ");
        String desc = this.input.ask("Введите описание заявки: ");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        System.out.println("------------ Новая заявка с getId : " + item.getId() + "-----------");
    }

    /**
     * Метод показывает все заявки.
     */
    private void showAllItem() {
        System.out.println("------------ Список заявок начало --------------");
        Item[] allItem = this.tracker.findAll();
        if (allItem.length == 0) {
            System.out.println("                 Список пуст                    ");
        }
        StringBuilder list = new StringBuilder();
        for (Item item: allItem) {
            list
                    .append(System.lineSeparator())
                    .append("Имя: ").append(item.getName()).append(System.lineSeparator())
                    .append("Описание: ").append(item.getDesc()).append(System.lineSeparator())
                    .append("Создана: ").append(new Date(item.getCreated())).append(System.lineSeparator())
                    .append("ID: ").append(item.getId()).append(System.lineSeparator());
        }
        System.out.print(list.toString());
        System.out.println("------------ Список заявок конец --------------");
    }

    /**
     * Метод реализует удаление заявки из хранилища.
     */
    private void deleteItem() {
        System.out.println("-------------- Удаление заявки --------------");
        String id = this.input.ask("Введите id удаляемой заявки:");
        this.tracker.delete(id);
        System.out.println("------- Завершение операции удаления --------");
    }

    /**
     * Метод реализует замену заявки в хранилище.
     */
    private void replaceItem() {
        System.out.println("------------ Замена заявки --------------");
        String id = this.input.ask("Введите id заменяемой заявки: ");
        String name = this.input.ask("Введите новое имя заявки: ");
        String desc = this.input.ask("Введите новое описание заявки: ");
        Item item = new Item(name, desc);
        this.tracker.replace(id, item);
        System.out.println("-------- Завершение операции замены ---------");
    }

    /**
     * Метод реализует поиск заявки по ID.
     */
    private void findItemById() {
        System.out.println("------------ Поиск заявки по id --------------");
        String id = this.input.ask("Введите id искомой заявки: ");
        Item item = this.tracker.findById(id);
        if (item != null) {
            System.out.println("------------- Найденная заявка ---------------");
            StringBuilder str = new StringBuilder();
            str
                    .append(System.lineSeparator())
                    .append("Имя: ").append(item.getName()).append(System.lineSeparator())
                    .append("Описание: ").append(item.getDesc()).append(System.lineSeparator())
                    .append("Создана: ").append(new Date(item.getCreated())).append(System.lineSeparator())
                    .append("ID: ").append(item.getId()).append(System.lineSeparator());
            System.out.print(str.toString());
            System.out.println("------------- Найденная заявка ---------------");
        } else {
            System.out.println("------------ Заявки не найдено ---------------");
        }
    }

    /**
     * Метод показывает все заявки.
     */
    private void findItemByName() {
        System.out.println("------------ Поиск заявки по имени --------------");
        String name = this.input.ask("Введите имя искомой заявки: ");
        Item[] listItem = this.tracker.findByName(name);
        if (listItem.length != 0) {
            System.out.println("---------- Найденные заявки начало --------------");
            StringBuilder list = new StringBuilder();
            for (Item item : listItem) {
                list
                        .append(System.lineSeparator())
                        .append("Имя: ").append(item.getName()).append(System.lineSeparator())
                        .append("Описание: ").append(item.getDesc()).append(System.lineSeparator())
                        .append("Создана: ").append(new Date(item.getCreated())).append(System.lineSeparator())
                        .append("ID: ").append(item.getId()).append(System.lineSeparator());
            }
            System.out.print(list.toString());
            System.out.println("---------- Найденные заявки конец --------------");
        } else {
            System.out.println("------------ Заявок не найдено ---------------");
        }
    }

    private void showMenu() {
        StringJoiner menu = new StringJoiner(System.lineSeparator(), "", System.lineSeparator());
        menu
                .add("")
                .add("Меню.")
                .add("0. Добавить новую заявку")
                .add("1. Показать все заявки")
                .add("2. Редактировать заявку")
                .add("3. Удалить заявку")
                .add("4. Поиск заявки по id")
                .add("5. Поиск заявок по имени")
                .add("6. Выйти из программы")
                .toString();
        System.out.println(menu);
    }

    /**
     * Запускт программы.
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}
