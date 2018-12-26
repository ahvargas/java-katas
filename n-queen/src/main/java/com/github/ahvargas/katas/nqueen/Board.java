package com.github.ahvargas.katas.nqueen;


import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Value
@Builder(toBuilder = true)
class Board {

    private final int boardSize;
    @Singular
    private final List<Queen> queens;


    private Board(final int boardSize, final List<Queen> queens) {
        this.boardSize = boardSize;
        this.queens = queens;
    }

    Board(final int boardSize) {
        this(boardSize, Collections.emptyList());
    }

    List<Board> bt() {
        if (reject()) {
            return Collections.emptyList();
        }
        if (accept()) {
            return Collections.singletonList(this);
        }
        return generate()
                .parallelStream()
                .map(Board::bt)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private boolean reject() {
        return !Queen.safe(queens);
    }

    private boolean accept() {
        return queens.size() == boardSize;
    }

    IntStream intStream() {
        return intStream(0);
    }

    private IntStream intStream(final int first) {
        return IntStream.range(first, boardSize);
    }

    private List<Board> generate() {
        return intStream()
                .mapToObj(this::generate)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<Board> generate(final int col) {
        return intStream(queens.size())
                .mapToObj(row -> this.addQueen(col, row))
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
    }

    private Optional<Board> addQueen(final int col, final int row) {
        if (queens.contains(Queen.of(col, row))) {
            return Optional.empty();
        }
        return Optional.of(this.toBuilder().queen(Queen.of(col, row)).build());
    }

}
