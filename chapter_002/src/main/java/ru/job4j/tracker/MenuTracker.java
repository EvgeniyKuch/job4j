package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MenuTracker {
    private Input input;
    private Tracker tracker;
    private List<UserAction> actions = new ArrayList<>();

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void fillActions() {
        this.actions.add(this.new AddItem(0));
        this.actions.add(new ShowAllItem(1)); //внешний класс, расположенный в одном файле
        this.actions.add(new MenuTracker.ReplaceItem(2)); //статический внутренний класс
        this.actions.add(this.new DeleteItem(3));
        this.actions.add(this.new FindItemById(4));
        this.actions.add(this.new FindItemByName(5));
    }

    public List<Integer> fillRange() {
        List<Integer> range = new ArrayList<>();
        for (UserAction action : this.actions) {
            range.add(action.key());
        }
        return range;
    }

    public void show() {
        System.out.println("");
        System.out.println("Меню.");
        this.actions.forEach(userAction -> System.out.println(userAction.info()));
        String str = String.format("%s. %s", this.actions.size(), "Выйти из программы");
        System.out.println(str);
        System.out.println("");
    }

    public boolean select(int key) {
        boolean exit = false;
        if (key != this.actions.size()) {
            this.actions.get(key).execute(this.input, this.tracker);
        } else {
            exit = true;
        }
        return exit;
    }

    /**
     * Класс реализует добавление новой заявки в хранилище.
     */
    public class AddItem extends BaseAction {
        protected AddItem(int key) {
            super(key, "Добавить новую заявку");
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
    }

    /**
     * Класс реализует замену заявки в хранилище.
     */
    public static class ReplaceItem extends BaseAction {
        protected ReplaceItem(int key) {
            super(key, "Редактировать заявку");
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
    }

    /**
     * Класс реализует удаление заявки из хранилища.
     */
    public class DeleteItem extends BaseAction {
        protected DeleteItem(int key) {
            super(key, "Удалить заявку");
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
    }

    /**
     * Класс реализует поиск заявки по ID.
     */
    public class FindItemById extends BaseAction {
        protected FindItemById(int key) {
            super(key, "Поиск заявки по id");
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
    }

    /**
     * Класс показывает все заявки с указанным именем.
     */
    public class FindItemByName extends BaseAction {
        protected FindItemByName(int key) {
            super(key, "Поиск заявок по имени");
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Поиск заявки по имени --------------");
            String name = input.ask("Введите имя искомой заявки: ");
            List<Item> listItem = tracker.findByName(name);
            if (listItem.size() != 0) {
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
    }
}

class ShowAllItem extends BaseAction {
    protected ShowAllItem(int key) {
        super(key, "Показать все заявки");
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Список заявок начало --------------");
        List<Item> allItem = tracker.findAll();
        if (allItem.size() == 0) {
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
}
