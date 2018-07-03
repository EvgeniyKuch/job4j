package ru.job4j.tracker;

import java.util.List;

public class StubInput implements Input {
    private final List<String> answers;
    private int position = 0;

    public StubInput(List<String> answers) {
        this.answers = answers;
    }

    @Override
    public String ask(String question) {
        return this.answers.get(position++);
    }

    @Override
    public int ask(String question, List<Integer> range) {
        return Integer.valueOf(this.ask(question));
    }
}
