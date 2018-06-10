package ru.job4j.pseudo;

import java.util.StringJoiner;

/**
 * Треугольник.
 * @author <a href="mailto:evgeniy.kuchumov@gmail.com.com">Кучумов Евгений</a>
 * @version $Id$
 * @since 0.1
 */
public class Triangle implements Shape {
    @Override
    public String draw() {
        StringJoiner pic = new StringJoiner(
                System.lineSeparator(), "",
                System.lineSeparator());
        pic
                .add("    +    ")
                .add("   +++   ")
                .add("  +++++  ")
                .add(" +++++++ ")
                .add("+++++++++");
        return pic.toString();
    }
}
