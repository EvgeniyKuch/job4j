package ru.job4j.tracker;

public class ValidateInput implements Input {
    private Input input;

    public ValidateInput(Input input) {
        this.input = input;
    }

    @Override
    public String ask(String question) {
        return this.input.ask(question);
    }

    @Override
    public int ask(String question, int[] range) {
        boolean invalid = true;
        int key = -1;
        do {
            try {
                key = input.ask(question, range);
                boolean exist = false;
                for (int value : range) {
                    if (key == value || key == range.length) {
                        exist = true;
                        break;
                    }
                }
                if (!exist) {
                    throw new MenuOutException("Необходимо выбрать значение из диапазона меню");
                }
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
