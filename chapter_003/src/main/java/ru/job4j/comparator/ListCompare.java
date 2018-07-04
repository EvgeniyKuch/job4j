package ru.job4j.comparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        List<Character> leftStr = left.chars()
                .collect(ArrayList::new, (t, i) -> t.add((char) i), ArrayList::addAll);
        List<Character> rightStr = right.chars()
                .mapToObj(i -> (char) i)
                .collect(Collectors.toList());
        int length = leftStr.size() < rightStr.size() ? leftStr.size() : rightStr.size();
        int result = 0;
        for (int i = 0; i < length && result == 0; i++) {
            result = leftStr.get(i) - rightStr.get(i);
        }
        result = result == 0 ? leftStr.size() - rightStr.size() : result;
        return result;
    }
}
