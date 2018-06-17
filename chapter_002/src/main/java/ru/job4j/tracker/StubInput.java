package ru.job4j.tracker;

public class StubInput implements Input {
    private final String[] answers;
    private int position = 0;

    public StubInput(String[] answers) {
        this.answers = answers;
    }

    @Override
    public String ask(String question) {
        return this.answers[position++];
    }

    @Override
    public int ask(String question, int[] range) {
        return Integer.valueOf(this.ask(question));
    }
}
