package com.github.ahvargas.katas.nqueen;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


class QueenTest {

    private final static Queen FIRST_COL_FIRST_ROW = Queen.of(1, 1);
    private final static Queen FIRST_COL_SECOND_ROW = Queen.of(1, 2);
    private final static Queen FIRST_COL_THIRD_ROW = Queen.of(1, 3);
    private final static Queen THIRD_COL_FIFTH_ROW = Queen.of(3, 5);
    private final static Queen FIFTH_COL_SECOND_ROW = Queen.of(5, 2);

    @ParameterizedTest(name = "Should handle safe positions for {0} vs {1} should be {2}")
    @MethodSource("validationParameters")
    void testPairs(final Queen queen, final Queen otherQueen, final boolean expected) {
        assertThat(queen.safe(otherQueen)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Should handle empty list")
    void shouldHandleEmptyList() {
        assertThat(Queen.safe(Collections.emptyList())).isTrue();
    }

    @Test
    @DisplayName("Should handle list of one element")
    void shouldHandleListOfOneElement() {
        assertThat(Queen.safe(Collections.singletonList(FIRST_COL_FIRST_ROW))).isTrue();
    }

    @ParameterizedTest(name = "Should validate list of queens: {0} should be {1}")
    @MethodSource("listParameters")
    void testLists(final List<Queen> queens, final boolean expected) {
        assertThat(Queen.safe(queens)).isEqualTo(expected);
    }

    private static Stream<Arguments> validationParameters() {
        return Stream.of(
                Arguments.of(FIRST_COL_FIRST_ROW, THIRD_COL_FIFTH_ROW, true),
                Arguments.of(FIRST_COL_FIRST_ROW, FIRST_COL_SECOND_ROW, false),
                Arguments.of(FIRST_COL_FIRST_ROW, FIRST_COL_THIRD_ROW, false),
                Arguments.of(FIRST_COL_SECOND_ROW, FIRST_COL_FIRST_ROW, false),
                Arguments.of(FIRST_COL_SECOND_ROW, FIRST_COL_SECOND_ROW, false),
                Arguments.of(FIRST_COL_SECOND_ROW, FIFTH_COL_SECOND_ROW, false),
                Arguments.of(FIRST_COL_SECOND_ROW, FIRST_COL_THIRD_ROW, false),
                Arguments.of(THIRD_COL_FIFTH_ROW, FIFTH_COL_SECOND_ROW, true)
        );
    }

    private static Stream<Arguments> listParameters() {
        return Stream.of(
                Arguments.of(List.of(FIRST_COL_FIRST_ROW, THIRD_COL_FIFTH_ROW), true),
                Arguments.of(List.of(FIRST_COL_FIRST_ROW, THIRD_COL_FIFTH_ROW, FIFTH_COL_SECOND_ROW), true),
                Arguments.of(List.of(FIRST_COL_FIRST_ROW, THIRD_COL_FIFTH_ROW, FIFTH_COL_SECOND_ROW, FIRST_COL_SECOND_ROW), false)
        );
    }


}
