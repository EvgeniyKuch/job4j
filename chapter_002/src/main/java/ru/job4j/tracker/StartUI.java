package ru.job4j.tracker;

import java.util.List;

public class StartUI {
    /**
     * Получение данных от пользователя.
     */
    private Input input;

    /**
     * Хранилище заявок.
     */
    private final ITracker tracker;

    /**
     * Диапазон ключей.
     */
    private List<Integer> range;

    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, ITracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();
        this.range = menu.fillRange();
        boolean exit = false;
        do {
            menu.show();
            exit = menu.select(this.input.ask("Введите пункт меню : ", this.range));
        } while (!exit);
    }

    /**
     * Запуск программы.
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(
                new ValidateInput(
                        new ConsoleInput()
                ),
                new Tracker()
        ).init();
    }
}
