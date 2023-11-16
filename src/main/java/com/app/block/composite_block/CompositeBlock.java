package com.app.block.composite_block;

import com.app.block.Block;

import java.util.List;

public interface CompositeBlock extends Block {
    List<Block> getBlocks();
}
