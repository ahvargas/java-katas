package com.github.ahvargas.katas.idealista;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DisplayName("Drone range with DroneDriverLimitedData")
class DroneRangeCalculatorIT {

    final DroneDriver driver = new DroneDriverLimitedData(5, 5);
    final DroneRangeCalculator drone = new DroneRangeCalculator(driver);

    @Test
    @DisplayName("Should handle range 1 for 13")
    void shouldHandleRangeOneFor13() {
        val result = drone.getResidentialDevelopments(2, 2, 1);
        assertThat(result).containsOnly(8, 12, 14, 18, 7, 9, 17, 19);
    }

    @Test
    @DisplayName("Should handle range 1 for 1")
    void shouldHandleRangeOneFor1() {
        val result = drone.getResidentialDevelopments(0, 0, 1);
        assertThat(result).containsOnly(2, 6, 7);
    }

    @Test
    @DisplayName("Should handle range 1 for 19")
    void shouldHandleRangeOneFor25() {
        val result = drone.getResidentialDevelopments(4, 4, 1);
        assertThat(result).containsOnly(19, 20, 24);

    }

    @Test
    @DisplayName("Should handle range 1 for empty squares")
    void shouldHandleEmptySquares() {
        val result = drone.getResidentialDevelopments(20, 20, 1);
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Should handle range 2 for a corner")
    void shouldHandleRange2ForABorder() {
        val result = drone.getResidentialDevelopments(0, 0, 2);
        assertThat(result).containsOnly(3, 8, 11, 12, 13);
    }

    @Test
    @DisplayName("Should handle range 2 for the center")
    void shouldHandleRange2ForCenter() {
        val result = drone.getResidentialDevelopments(2, 2, 2);
        assertThat(result).containsOnly(1, 2, 3, 4, 5, 6, 10, 11, 15, 16, 20, 21, 22, 23, 24, 25);
    }

}
