package com.github.ahvargas.katas.idealista;

import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DroneDriverLimitedDataTest {

    final DroneDriver driver = new DroneDriverLimitedData(5, 5);

    @Test
    @DisplayName("Should get neighbors")
    void shouldGetNeighbors() {
        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
            softly.assertThat(driver.getNeighbor(13, Direction.UP)).hasValue(8);
            softly.assertThat(driver.getNeighbor(13, Direction.DOWN)).hasValue(18);
            softly.assertThat(driver.getNeighbor(13, Direction.LEFT)).hasValue(12);
            softly.assertThat(driver.getNeighbor(13, Direction.RIGHT)).hasValue(14);
            softly.assertThat(driver.getNeighbor(1, Direction.UP)).isEmpty();
            softly.assertThat(driver.getNeighbor(22, Direction.DOWN)).isEmpty();
            softly.assertThat(driver.getNeighbor(1, Direction.LEFT)).isEmpty();
            softly.assertThat(driver.getNeighbor(15, Direction.RIGHT)).isEmpty();
            softly.assertThat(driver.getNeighbor(1558, Direction.RIGHT)).isEmpty();
        }
    }

    @Test
    @DisplayName("Should find by id")
    void shouldFindById() {
        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
            softly.assertThat(driver.getIdForResidentialDevelopment(0, 0)).hasValue(1);
            softly.assertThat(driver.getIdForResidentialDevelopment(0, 1)).hasValue(2);
            softly.assertThat(driver.getIdForResidentialDevelopment(0, 2)).hasValue(3);
            softly.assertThat(driver.getIdForResidentialDevelopment(0, 3)).hasValue(4);
            softly.assertThat(driver.getIdForResidentialDevelopment(0, 4)).hasValue(5);
            softly.assertThat(driver.getIdForResidentialDevelopment(1, 0)).hasValue(6);
            softly.assertThat(driver.getIdForResidentialDevelopment(1, 1)).hasValue(7);
            softly.assertThat(driver.getIdForResidentialDevelopment(1, 2)).hasValue(8);
            softly.assertThat(driver.getIdForResidentialDevelopment(1, 3)).hasValue(9);
            softly.assertThat(driver.getIdForResidentialDevelopment(1, 4)).hasValue(10);
            softly.assertThat(driver.getIdForResidentialDevelopment(2, 0)).hasValue(11);
            softly.assertThat(driver.getIdForResidentialDevelopment(2, 1)).hasValue(12);
            softly.assertThat(driver.getIdForResidentialDevelopment(2, 2)).hasValue(13);
            softly.assertThat(driver.getIdForResidentialDevelopment(2, 3)).hasValue(14);
            softly.assertThat(driver.getIdForResidentialDevelopment(2, 4)).hasValue(15);
            softly.assertThat(driver.getIdForResidentialDevelopment(3, 0)).hasValue(16);
            softly.assertThat(driver.getIdForResidentialDevelopment(3, 1)).hasValue(17);
            softly.assertThat(driver.getIdForResidentialDevelopment(3, 2)).hasValue(18);
            softly.assertThat(driver.getIdForResidentialDevelopment(3, 3)).hasValue(19);
            softly.assertThat(driver.getIdForResidentialDevelopment(3, 4)).hasValue(20);
            softly.assertThat(driver.getIdForResidentialDevelopment(4, 0)).hasValue(21);
            softly.assertThat(driver.getIdForResidentialDevelopment(4, 1)).hasValue(22);
            softly.assertThat(driver.getIdForResidentialDevelopment(4, 2)).hasValue(23);
            softly.assertThat(driver.getIdForResidentialDevelopment(4, 3)).hasValue(24);
            softly.assertThat(driver.getIdForResidentialDevelopment(4, 4)).hasValue(25);
        }
    }
}
