package com.github.ahvargas.katas.idealista;

import lombok.AllArgsConstructor;
import lombok.val;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@AllArgsConstructor
public class DroneRangeCalculator {
    private final DroneDriver driver;

    public List<Integer> getResidentialDevelopments(final int x, final int y, final int range) {
        val paths = DronePath.fromRange(range);
        return driver.getIdForResidentialDevelopment(x, y)
                .map(target -> getResidentialDevelopmentsIdFromPath(target, paths))
                .orElse(Collections.emptyList());
    }

    private Optional<Integer> getResidentialDevelopmentIdFromDronePath(final int currentPosition, DronePath path) {
        val directions = path.getDirections();
        if (directions.isEmpty()) {
            return Optional.empty();
        }
        val head = directions.get(0);
        val nextPosition = driver.getNeighbor(currentPosition, head);
        if (path.getDirections().size() == 1) {
            return nextPosition;
        }
        val tail = directions.subList(1, directions.size());
        val tailPath = DronePath.builder().directions(tail).build();
        return nextPosition.flatMap(id -> getResidentialDevelopmentIdFromDronePath(id, tailPath));
    }

    private List<Integer> getResidentialDevelopmentsIdFromPath(final int target, Set<DronePath> paths) {
        return paths.stream()
                .map(path -> getResidentialDevelopmentIdFromDronePath(target, path))
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
    }

}
