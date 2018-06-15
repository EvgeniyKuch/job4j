package ru.job4j.tracker;

public class ValidateInput extends ConsoleInput {
    @Override
    public int ask(String question, int[] range) {
        boolean invalid = true;
        int key = -1;
        do {
            try {
                key = super.ask(question, range);
                invalid = false;
            } catch (MenuOutException ex) {
                System.out.println("Необходимо выбрать значение из диапазона меню");
            } catch (NumberFormatException nfe) {
                System.out.println("Необходимо ввести корректное значение");
            }
        } while (invalid);
        return key;
    }
}
