package com.github.ahvargas.katas.idealista;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum Direction {
    UP, DOWN, LEFT, RIGHT;

    List<Direction> times(final int times) {
        return IntStream.rangeClosed(1, times)
                .mapToObj(unused -> this)
                .collect(Collectors.toList());
    }

}
