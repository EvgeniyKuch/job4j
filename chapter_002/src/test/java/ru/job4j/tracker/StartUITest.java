package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StartUITest {

    /**
     * Тест добавления новой заявки.
     */
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("test name"));
    }

    /**
     * Тест для замены заявки.
     */
    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("1", "1"));
        Input input = new StubInput(new String[]{"2", item.getId(), "test name", "desc", "6"});
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
        Input input = new StubInput(new String[]{"3", itemOne.getId(), "6"});
        new StartUI(input, tracker).init();
        Item expect = null;
        assertThat(tracker.findById(itemOne.getId()), is(expect));
    }
}
