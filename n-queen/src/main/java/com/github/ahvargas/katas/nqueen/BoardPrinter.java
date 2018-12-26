package com.github.ahvargas.katas.nqueen;

import lombok.Value;

import java.util.stream.Collectors;

@Value(staticConstructor = "of")
public class BoardPrinter {

    public static final String PREFIX = "\n****************\n";
    private final Board board;

    private String printSpace(final int row, final int col) {
        return board.getQueens().contains(Queen.of(col, row)) ? "Q|" : " |";
    }

    private String printRow(final int row) {
        return board.intStream()
                .mapToObj(col -> printSpace(row, col))
                .collect(Collectors.joining());
    }

    public String toString() {
        return PREFIX
                + board.intStream()
                .mapToObj(this::printRow)
                .collect(Collectors.joining("\n"))
                + PREFIX;
    }

    void print() {
        System.out.println(this);
    }
}
