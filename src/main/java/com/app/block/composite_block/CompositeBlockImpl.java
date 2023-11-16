package com.app.block.composite_block;

import com.app.block.Block;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Builder
@RequiredArgsConstructor
public class CompositeBlockImpl implements CompositeBlock {
    private final String color;
    private final String material;
    private final List<Block> blocks;
    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getMaterial() {
        return material;
    }

    @Override
    public List<Block> getBlocks() {
        return blocks;
    }

    @Override
    public String toString() {
        return "CompositeBlockImpl(color=%s, material=%s)"
                .formatted(color, material);
    }
}
