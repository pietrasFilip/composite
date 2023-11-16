package com.app.structure.wall;

import com.app.block.Block;
import com.app.structure.Structure;
import com.app.structure.wall.extension.StructureExtension;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

@ExtendWith(StructureExtension.class)
@RequiredArgsConstructor
class FindBlockByColorTest {
    private final Structure structure;

    @TestFactory
    @DisplayName("When color is red")
    Stream<DynamicNode> test1() {
        var color = "red";
        var optionalBlock = structure.findBlockByColor(color);

        return Stream.of(optionalBlock)
                        .map(n -> dynamicContainer("Container", Stream.of(
                                dynamicTest("Is instance of optional",
                                        () -> assertThat(n).isInstanceOf(Optional.class)),
                                dynamicTest("Is red color",
                                        () -> assertThat(n.orElseThrow())
                                                .extracting(Block::getColor)
                                                .isEqualTo(color))
                        )));
    }

    @TestFactory
    @DisplayName("When color is blue")
    Stream<DynamicNode> test2() {
        var color = "blue";
        var optionalBlock = structure.findBlockByColor(color);

        return Stream.of(optionalBlock)
                .map(n -> dynamicContainer("Container", Stream.of(
                        dynamicTest("Is instance of optional",
                                () -> assertThat(n).isInstanceOf(Optional.class)),
                        dynamicTest("Is red color",
                                () -> assertThat(n.orElseThrow())
                                        .extracting(Block::getColor)
                                        .isEqualTo(color))
                )));
    }

    @TestFactory
    @DisplayName("When color is white")
    Stream<DynamicNode> test3() {
        var color = "white";
        var optionalBlock = structure.findBlockByColor(color);

        return Stream.of(optionalBlock)
                .map(n -> dynamicContainer("Container", Stream.of(
                        dynamicTest("Is instance of optional",
                                () -> assertThat(n).isInstanceOf(Optional.class)),
                        dynamicTest("Is red color",
                                () -> assertThat(n.orElseThrow())
                                        .extracting(Block::getColor)
                                        .isEqualTo(color))
                )));
    }
}
