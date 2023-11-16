package com.app.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompositeBlockEntity {
    private Long id;
    private String color;
    private String material;
    private BlockEntity block;

    public CompositeBlockWithBlocksEntity withBlocks() {
        return CompositeBlockWithBlocksEntity
                .builder()
                .color(color)
                .material(material)
                .blocks(new ArrayList<>(List.of(block)))
                .build();
    }
}
