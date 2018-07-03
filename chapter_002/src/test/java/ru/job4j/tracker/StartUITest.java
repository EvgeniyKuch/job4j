package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Date;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StartUITest {
    // поле содержит дефолтный вывод в консоль.
    private final PrintStream stdout = System.out;
    // буфер для результата.
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    private String menu() {
        return new StringJoiner(System.lineSeparator())
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
    }

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    /**
     * Тест добавления новой заявки.
     */
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(Arrays.asList("0", "test name", "desc", "6"));
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().get(0).getName(), is("test name"));
    }

    /**
     * Тест для замены заявки.
     */
    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("1", "1"));
        Input input = new StubInput(Arrays.asList("2", item.getId(), "test name", "desc", "6"));
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test name"));
    }

    /**
     * Тест для удаления заявки.
     */
    @Test
    public void whenDeleteThenTrackerDoesNotHaveItem() {
        Tracker tracker = new Tracker();
        Item itemOne = tracker.add(new Item("1", "1"));
        Item itemTwo = tracker.add(new Item("2", "2"));
        Input input = new StubInput(Arrays.asList("3", itemOne.getId(), "6"));
        new StartUI(input, tracker).init();
        Item expect = null;
        assertThat(tracker.findById(itemOne.getId()), is(expect));
    }

    /**
     * Тест для вывода списка всех заявок.
     */
    @Test
    public void whenShowAllItems() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "test desc"));
        Input input = new StubInput(Arrays.asList("1", "6"));
        new StartUI(input, tracker).init();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringJoiner(System.lineSeparator())
                                .add(this.menu())
                                .add("")
                                .add("------------ Список заявок начало --------------")
                                .add("")
                                .add("Имя: test name")
                                .add("Описание: test desc")
                                .add("Создана: " + new Date(item.getCreated()))
                                .add("ID: " + item.getId())
                                .add("------------ Список заявок конец --------------")
                                .add(this.menu())
                                .add(System.lineSeparator())
                                .toString()
                )
        );
    }

    /**
     * Тест для поиска заявки по ID.
     */
    @Test
    public void whenFindById() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "test desc"));
        Input input = new StubInput(Arrays.asList("4", item.getId(), "6"));
        new StartUI(input, tracker).init();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringJoiner(System.lineSeparator())
                                .add(this.menu())
                                .add("")
                                .add("------------ Поиск заявки по id --------------")
                                .add("------------- Найденная заявка ---------------")
                                .add("")
                                .add("Имя: test name")
                                .add("Описание: test desc")
                                .add("Создана: " + new Date(item.getCreated()))
                                .add("ID: " + item.getId())
                                .add("------------- Найденная заявка ---------------")
                                .add(this.menu())
                                .add(System.lineSeparator())
                                .toString()
                )
        );
    }

    /**
     * Тест для поиска заявок по имени.
     */
    @Test
    public void whenFindByName() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "test desc"));
        Input input = new StubInput(Arrays.asList("5", "test name", "6"));
        new StartUI(input, tracker).init();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringJoiner(System.lineSeparator())
                                .add(this.menu())
                                .add("")
                                .add("------------ Поиск заявки по имени --------------")
                                .add("---------- Найденные заявки начало --------------")
                                .add("")
                                .add("Имя: test name")
                                .add("Описание: test desc")
                                .add("Создана: " + new Date(item.getCreated()))
                                .add("ID: " + item.getId())
                                .add("---------- Найденные заявки конец --------------")
                                .add(this.menu())
                                .add(System.lineSeparator())
                                .toString()
                )
        );
    }
}
