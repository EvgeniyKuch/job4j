package ru.job4j.tracker;

import java.util.Date;

public class MenuTracker {
    private Input input;
    private Tracker tracker;
    private UserAction[] actions = new UserAction[6];

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void fillActions() {
        this.actions[0] = this.new AddItem();
        this.actions[1] = new ShowAllItem(); //внешний класс, расположенный в одном файле
        this.actions[2] = new MenuTracker.ReplaceItem(); //статический внутренний класс
        this.actions[3] = this.new DeleteItem();
        this.actions[4] = this.new FindItemById();
        this.actions[5] = this.new FindItemByName();
    }

    public void show() {
        System.out.println("");
        System.out.println("Меню.");
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
        String str = String.format("%s. %s", this.actions.length, "Выйти из программы");
        System.out.println(str);
        System.out.println("");
    }

    public boolean select(int key) {
        boolean exit = false;
        if (key >= 0 && key < this.actions.length && this.actions[key] != null) {
            this.actions[key].execute(this.input, this.tracker);
        } else if (key == this.actions.length) {
            exit = true;
        }
        return exit;
    }

    /**
     * Класс реализует добавление новой заявки в хранилище.
     */
    public class AddItem implements UserAction {
        @Override
        public int key() {
            return 0;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Добавление новой заявки --------------");
            String name = input.ask("Введите имя заявки: ");
            String desc = input.ask("Введите описание заявки: ");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println("------------ Новая заявка с getId : " + item.getId() + "-----------");
        }

        @Override
        public String info() {
            return String.format("%s. %s", key(), "Добавить новую заявку");
        }
    }

    /**
     * Класс реализует замену заявки в хранилище.
     */
    public static class ReplaceItem implements UserAction {
        @Override
        public int key() {
            return 2;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Замена заявки --------------");
            String id = input.ask("Введите id заменяемой заявки: ");
            if (tracker.findById(id) != null) {
                String name = input.ask("Введите новое имя заявки: ");
                String desc = input.ask("Введите новое описание заявки: ");
                Item item = new Item(name, desc);
                tracker.replace(id, item);
            } else {
                System.out.println("Заявка не найдена");
            }
            System.out.println("-------- Завершение операции замены ---------");
        }

        @Override
        public String info() {
            return String.format("%s. %s", key(), "Редактировать заявку");
        }
    }

    /**
     * Класс реализует удаление заявки из хранилища.
     */
    public class DeleteItem implements UserAction {
        @Override
        public int key() {
            return 3;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("-------------- Удаление заявки --------------");
            String id = input.ask("Введите id удаляемой заявки:");
            if (tracker.findById(id) != null) {
                tracker.delete(id);
            } else {
                System.out.println("Заявка не найдена");
            }
            System.out.println("------- Завершение операции удаления --------");
        }

        @Override
        public String info() {
            return String.format("%s. %s", key(), "Удалить заявку");
        }
    }

    /**
     * Класс реализует поиск заявки по ID.
     */
    public class FindItemById implements UserAction {
        @Override
        public int key() {
            return 4;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Поиск заявки по id --------------");
            String id = input.ask("Введите id искомой заявки: ");
            Item item = tracker.findById(id);
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

        @Override
        public String info() {
            return String.format("%s. %s", key(), "Поиск заявки по id");
        }
    }

    /**
     * Класс показывает все заявки с указанным именем.
     */
    public class FindItemByName implements UserAction {
        @Override
        public int key() {
            return 5;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Поиск заявки по имени --------------");
            String name = input.ask("Введите имя искомой заявки: ");
            Item[] listItem = tracker.findByName(name);
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

        @Override
        public String info() {
            return String.format("%s. %s", key(), "Поиск заявок по имени");
        }
    }
}

class ShowAllItem implements UserAction {
    @Override
    public int key() {
        return 1;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Список заявок начало --------------");
        Item[] allItem = tracker.findAll();
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

    @Override
    public String info() {
        return String.format("%s. %s", key(), "Показать все заявки");
    }
}
