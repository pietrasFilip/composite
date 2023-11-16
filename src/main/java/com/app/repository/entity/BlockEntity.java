package com.app.repository.entity;

import com.app.block.Block;
import com.app.block.BlockImpl;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlockEntity {
    private Long id;
    private String color;
    private String material;

    public Block toBlock() {
        return BlockImpl
                .builder()
                .color(color)
                .material(material)
                .build();
    }
}
