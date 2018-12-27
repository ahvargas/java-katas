package com.github.ahvargas.katas.idealista;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;
import lombok.val;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.github.ahvargas.katas.idealista.Direction.*;

@Value
@Builder(toBuilder = true)
class DronePath {
    @Singular
    List<Direction> directions;

    static Set<DronePath> fromRange(int range) {
        if (range == 0) {
            return Collections.emptySet();
        }
        val all = Stream.of(
                generatePath(range, UP, LEFT),
                generatePath(range, UP, RIGHT),
                generatePath(range, DOWN, LEFT),
                generatePath(range, DOWN, RIGHT),

                generatePath(range, LEFT, UP, false),
                generatePath(range, LEFT, DOWN, false),
                generatePath(range, RIGHT, UP, false),
                generatePath(range, RIGHT, DOWN, false)
        );

        return all
                .reduce(Stream::concat)
                .orElseGet(Stream::empty)
                .collect(Collectors.toSet());

    }

    static IntStream generateRange(final int range, final boolean closed) {
        return closed ? IntStream.rangeClosed(0, range) : IntStream.range(0, range);
    }

    static Stream<DronePath> generatePath(final int range, final Direction firstMove, final Direction secondMove) {
        return generatePath(range, firstMove, secondMove, true);
    }


    static Stream<DronePath> generatePath(final int range, final Direction firstMove, final Direction secondMove, final boolean closed) {
        return generateRange(range, closed)
                .mapToObj(i -> DronePath.builder().directions(firstMove.times(range)).directions(secondMove.times(i)).build());
    }
}



