package com.github.ahvargas.katas.nqueen;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BoardTest {

    @ParameterizedTest(name = "Should validate that board: {0} should have {1} solutions")
    @MethodSource("solvedBoard")
    void testLists(final Board board, final int resultSize) {
        List<Board> solutions = board.bt();
        assertThat(solutions.size()).isEqualTo(resultSize);

    }

    private static Stream<Arguments> solvedBoard() {
        return Stream.of(
                Arguments.of(new Board(1), 1),
                Arguments.of(new Board(2), 0),
                Arguments.of(new Board(3), 0),
                Arguments.of(new Board(4), 2),
                Arguments.of(new Board(5), 10)
        );
    }

    @Test
    @DisplayName("Solution for the 8 queen ")
    void shouldHandleListOfOneElement() {
        final List<Board> solutions = new Board(8).bt();
        assertThat(solutions.size()).isEqualTo(92);
        solutions.stream().map(BoardPrinter::of).forEach(BoardPrinter::print);
    }
}
