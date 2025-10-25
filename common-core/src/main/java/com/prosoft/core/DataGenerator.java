package com.prosoft.core;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DataGenerator {

    public static List<String> generateList(int size) {
        return IntStream.range(0, size)
                .mapToObj(i -> "element-" + i)
                .collect(Collectors.toList());
    }

    public static Map<String, String> generateMap(int size) {
        return IntStream.range(0, size)
                .boxed()
                .collect(Collectors.toMap(
                        i -> "key-" + i,
                        i -> "value-" + i
                ));
    }

    public static List<String> getSearchTargets(List<String> data, int count) {
        Random random = new Random(42); // фиксированный seed для воспроизводимости
        List<String> targets = new ArrayList<>();

        // Добавляем существующие элементы
        for (int i = 0; i < count / 2; i++) {
            targets.add(data.get(random.nextInt(data.size())));
        }

        // Добавляем несуществующие элементы
        for (int i = 0; i < count / 2; i++) {
            targets.add("non-existent-" + random.nextInt());
        }

        Collections.shuffle(targets, random);
        return targets;
    }
}

