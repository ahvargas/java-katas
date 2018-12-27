package com.github.ahvargas.katas.idealista;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.github.ahvargas.katas.idealista.Direction.*;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DisplayName("DronePath spec")
class DronePathTest {

    @Test
    @DisplayName("should return no elements from range 0")
    void testRange0() {
        assertThat(DronePath.fromRange(0)).isEmpty();
    }

    @Test
    @DisplayName("should return basePath elements from range 1")
    void testRange1() {
        val expected = Set.of(
                DronePath.builder().direction(UP).build(),
                DronePath.builder().direction(DOWN).build(),
                DronePath.builder().direction(LEFT).build(),
                DronePath.builder().direction(RIGHT).build(),
                DronePath.builder().direction(UP).direction(LEFT).build(),
                DronePath.builder().direction(DOWN).direction(LEFT).build(),
                DronePath.builder().direction(UP).direction(RIGHT).build(),
                DronePath.builder().direction(DOWN).direction(RIGHT).build()
        );
        assertThat(DronePath.fromRange(1)).containsAll(expected);
    }

    //TODO: Is better to test generatePath.....

    @Test
    @DisplayName("should return basePath elements from range 2")
    void testRange2() {
        val result = DronePath.fromRange(2);
        assertThat(result).hasSize(16);
        assertThat(result).contains(DronePath.builder().direction(UP).direction(UP).build());
    }

}
