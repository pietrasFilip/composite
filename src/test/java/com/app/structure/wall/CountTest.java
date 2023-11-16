package com.app.structure.wall;

import com.app.structure.Structure;
import com.app.structure.wall.extension.StructureExtension;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;

import static com.app.Blocks.LIST_DOUBLE_NESTED;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(StructureExtension.class)
@RequiredArgsConstructor
class CountTest {
    private final Structure structure;

    @Test
    @DisplayName("When there is exactly 8 elements in Structure")
    void test1() {
        var expectedCount = 8;

        assertThat(structure.count())
                .isEqualTo(expectedCount);
    }

    @Test
    @DisplayName("When there are no Block in Structure")
    void test2() {
        var expectedCount = 0;
        var wall = Wall
                .builder()
                .blocks(new ArrayList<>())
                .build();

        assertThat(wall.count())
                .isEqualTo(expectedCount);
    }

    @Test
    @DisplayName("When there is double nested structure")
    void test3() {
        var expectedCount = 17;
        var wall = Wall
                .builder()
                .blocks(LIST_DOUBLE_NESTED)
                .build();

        assertThat(wall.count())
                .isEqualTo(expectedCount);
    }
}
