package com.app;

import com.app.block.Block;
import com.app.block.BlockImpl;
import com.app.block.composite_block.CompositeBlockImpl;

import java.util.List;

public interface Blocks {
    Block BLOCK_1 = BlockImpl
            .builder()
            .color("red")
            .material("concrete")
            .build();

    Block BLOCK_2 = BlockImpl
            .builder()
            .color("blue")
            .material("concrete")
            .build();

    Block BLOCK_3 = BlockImpl
            .builder()
            .color("red")
            .material("wood")
            .build();

    Block BLOCK_4 = BlockImpl
            .builder()
            .color("white")
            .material("concrete")
            .build();

    List<Block> BLOCKS = List.of(BLOCK_1, BLOCK_2, BLOCK_3);

    Block COMPOSITE_BLOCK = CompositeBlockImpl
            .builder()
            .color("white")
            .material("wood")
            .blocks(BLOCKS)
            .build();

    List<Block> BLOCKS_WITH_COMPOSITE = List.of(
            COMPOSITE_BLOCK, BLOCK_1, BLOCK_2, BLOCK_3, BLOCK_4
    );

    Block DOUBLE_NESTED_COMPOSITE = CompositeBlockImpl
            .builder()
            .color("white")
            .material("wood")
            .blocks(BLOCKS_WITH_COMPOSITE)
            .build();

    List<Block> LIST_DOUBLE_NESTED = List.of(DOUBLE_NESTED_COMPOSITE, COMPOSITE_BLOCK, BLOCK_1, BLOCK_2, BLOCK_3,
            BLOCK_4);
}
