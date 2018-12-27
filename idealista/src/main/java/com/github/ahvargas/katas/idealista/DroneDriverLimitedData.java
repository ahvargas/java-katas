package com.github.ahvargas.katas.idealista;

import lombok.val;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DroneDriverLimitedData implements DroneDriver {

    private final int rows;
    private final int cols;

    private final List<ResidentialDevelopment> residentialDevelopments;

    public DroneDriverLimitedData(final int rows, final int cols) {
        this.rows = rows;
        this.cols = cols;
        this.residentialDevelopments = generateSampleData();
    }

    private List<ResidentialDevelopment> generateRow(int row) {
        return IntStream.range(0, cols)
                .mapToObj(col -> ResidentialDevelopment.of(row, col, 1 + (row * rows) + col))
                .collect(Collectors.toList());
    }

    private List<ResidentialDevelopment> generateSampleData() {
        return IntStream.range(0, rows)
                .mapToObj(this::generateRow)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Integer> getIdForResidentialDevelopment(int x, int y) {
        return residentialDevelopments.stream()
                .filter(r -> r.getX() == x && r.getY() == y)
                .map(ResidentialDevelopment::getId)
                .findFirst();
    }

    @Override
    public Optional<Integer> getNeighbor(int id, Direction direction) {
        return residentialDevelopments.stream()
                .filter(r -> r.getId() == id)
                .findFirst()
                .flatMap(r -> getNeighbor(r, direction));
    }

    Optional<Integer> getNeighbor(ResidentialDevelopment development, Direction direction) {
        switch (direction) {
            case UP:
                return getNeighborUp(development);
            case DOWN:
                return getNeighborDown(development);
            case LEFT:
                return getNeighborLeft(development);
            case RIGHT:
                return getNeighborRight(development);
            default:
                return Optional.empty();
        }
    }

    Optional<Integer> getNeighborUp(ResidentialDevelopment development) {
        if (development.getX() == 0) {
            return Optional.empty();
        }
        val index = residentialDevelopments.indexOf(development) - rows;
        return Optional.of(residentialDevelopments.get(index).getId());
    }

    Optional<Integer> getNeighborDown(ResidentialDevelopment development) {
        if (development.getX() == cols - 1) {
            return Optional.empty();
        }
        val index = residentialDevelopments.indexOf(development) + rows;
        return Optional.of(residentialDevelopments.get(index).getId());
    }

    Optional<Integer> getNeighborLeft(ResidentialDevelopment development) {
        if (development.getY() == 0) {
            return Optional.empty();
        }
        val index = residentialDevelopments.indexOf(development) - 1;
        return Optional.of(residentialDevelopments.get(index).getId());
    }

    Optional<Integer> getNeighborRight(ResidentialDevelopment development) {
        if (development.getY() == rows - 1) {
            return Optional.empty();
        }
        val index = residentialDevelopments.indexOf(development) + 1;
        return Optional.of(residentialDevelopments.get(index).getId());
    }

}
