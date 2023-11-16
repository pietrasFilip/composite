package com.app.structure.wall;

import com.app.block.Block;
import com.app.structure.Structure;
import com.app.structure.wall.extension.StructureExtension;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.app.Blocks.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(StructureExtension.class)
@RequiredArgsConstructor
public class FindBlocksByMaterialTest {
    private final Structure structure;

    public static Stream<Arguments> argSource() {
        return Stream.of(
                Arguments.of("wood", List.of(
                        BLOCK_3, COMPOSITE_BLOCK, BLOCK_3
                )),
                Arguments.of("concrete", List.of(
                        BLOCK_1, BLOCK_2, BLOCK_1, BLOCK_2, BLOCK_4
                ))
        );
    }

    public static Stream<Arguments> argSourceDoubleNested() {
        return Stream.of(
                Arguments.of("wood", List.of(
                        DOUBLE_NESTED_COMPOSITE, COMPOSITE_BLOCK, BLOCK_3, BLOCK_3, COMPOSITE_BLOCK, BLOCK_3, BLOCK_3
                )),
                Arguments.of("concrete", List.of(
                        BLOCK_1, BLOCK_2, BLOCK_1, BLOCK_2, BLOCK_4, BLOCK_1, BLOCK_2, BLOCK_1, BLOCK_2, BLOCK_4
                ))
        );
    }

    @ParameterizedTest
    @MethodSource("argSource")
    @DisplayName("When material is wood and concrete")
    void test1(String material, List<Block> expectedBlocks) {
        var blocks = structure.findBlocksByMaterial(material);

        assertThat(blocks)
                .containsExactlyInAnyOrderElementsOf(expectedBlocks);
    }

    @Test
    @DisplayName("When there is no block with such material")
    void test2() {
        assertThat(structure.findBlocksByMaterial("material"))
                .isEqualTo(List.of());
    }

    @ParameterizedTest
    @MethodSource("argSourceDoubleNested")
    @DisplayName("When material is wood, concrete and block is double nested")
    void test3(String material, List<Block> expectedBlocks) {
        var blocks = Wall
                .builder()
                .blocks(LIST_DOUBLE_NESTED)
                .build()
                .findBlocksByMaterial(material);

        assertThat(blocks)
                .containsExactlyInAnyOrderElementsOf(expectedBlocks);
    }
}
