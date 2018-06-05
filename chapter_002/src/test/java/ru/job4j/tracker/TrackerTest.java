package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тестирование методов трекера
 */
public class TrackerTest {

    /**
     * Тестирование добавления новой заявки.
     */
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription");
        tracker.add(item);
        assertThat(tracker.getAll()[0], is(item));
    }

    /**
     * Тест для замены заявки.
     */
    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription");
        tracker.add(previous);
        Item next = new Item("test2", "testDescription2");
        next.setId(previous.getId());
        tracker.replace(previous.getId(), next);
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    /**
     * Тестирование удаления заявок (public void delete(String id))
     * и получения списка всех заявок без null элементов (public Item[] findAll()).
     */
    @Test
    public void whenDeleteAverageItemThenTwoItems() {
        Tracker tracker = new Tracker();
        Item itemOne = new Item("test1", "testDescription1");
        Item itemTwo = new Item("test2", "testDescription2");
        Item itemThree = new Item("test3", "testDescription3");
        tracker.add(itemOne);
        tracker.add(itemTwo);
        tracker.add(itemThree);
        tracker.delete(itemTwo.getId());
        Item[] expect = {itemOne, itemThree};
        assertThat(tracker.findAll(), is(expect));
    }

    /**
     * Тестирование получения списка по имени.
     */
    @Test
    public void whenThreeNamesAreEqualThenThreeItems() {
        Tracker tracker = new Tracker();
        Item itemOne = new Item("test1", "testDescription1");
        Item itemTwo = new Item("test2", "testDescription2");
        Item itemThree = new Item("test1", "testDescription3");
        Item itemFour = new Item("test2", "testDescription4");
        Item itemFive = new Item("test1", "testDescription5");
        tracker.add(itemOne);
        tracker.add(itemTwo);
        tracker.add(itemThree);
        tracker.add(itemFour);
        tracker.add(itemFive);
        Item[] result = tracker.findByName("test1");
        Item[] expect = {itemOne, itemThree, itemFive};
        assertThat(result, is(expect));
    }

    /**
     * Тестирование получения заявки по id.
     */
    @Test
    public void whenFindByIdThenItem() {
        Tracker tracker = new Tracker();
        Item itemOne = new Item("test1", "testDescription1");
        Item itemTwo = new Item("test2", "testDescription2");
        Item itemThree = new Item("test3", "testDescription3");
        tracker.add(itemOne);
        tracker.add(itemTwo);
        tracker.add(itemThree);
        Item result = tracker.findById(itemTwo.getId());
        assertThat(result, is(itemTwo));
    }
}
