package com.github.ahvargas.katas.idealista;

import java.util.Optional;

public interface DroneDriver {
    Optional<Integer> getIdForResidentialDevelopment(int x, int y);

    Optional<Integer> getNeighbor(int id, Direction direction);

}
