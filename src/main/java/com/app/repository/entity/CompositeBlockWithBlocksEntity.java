package com.app.repository.entity;

import com.app.block.composite_block.CompositeBlock;
import com.app.block.composite_block.CompositeBlockImpl;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompositeBlockWithBlocksEntity {
    private Long id;
    private String color;
    private String material;
    private List<BlockEntity> blocks;

    public CompositeBlock toCompositeBlock() {
        return CompositeBlockImpl
                .builder()
                .color(color)
                .material(material)
                .blocks(blocks.
                        stream()
                        .map(BlockEntity::toBlock)
                        .toList())
                .build();
    }
}
