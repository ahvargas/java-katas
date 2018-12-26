package com.github.ahvargas.katas.nqueen;

import lombok.Value;
import lombok.val;

import java.util.Collections;
import java.util.List;

@Value(staticConstructor = "of")
class Queen {
    private final int col;
    private final int row;

    private boolean safeCol(final Queen other) {
        return col != other.col;
    }

    private boolean safeRow(final Queen other) {
        return row != other.row;
    }

    private boolean safeDiagonal(final Queen other) {
        int xDiff = Math.abs(other.col - col);
        int yDiff = Math.abs(other.row - row);
        return xDiff != yDiff;
    }

    boolean safe(final Queen other) {
        return safeCol(other) && safeDiagonal(other) && safeRow(other);
    }

    static boolean safe(final List<Queen> board) {
        if (board.size() <= 1) {
            return true;
        }
        val first = board.get(0);
        val others = Collections.unmodifiableList(board.subList(1, board.size()));
        return others.stream().allMatch(first::safe) && safe(others);
    }

}
