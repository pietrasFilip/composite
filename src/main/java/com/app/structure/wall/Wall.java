package com.app.structure.wall;

import com.app.block.Block;
import com.app.block.composite_block.CompositeBlock;
import com.app.repository.provider.CompositeProvider;
import com.app.structure.Structure;
import lombok.Builder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


@Builder
public class Wall implements Structure {
    private List<Block> blocks;

    public Wall(CompositeProvider blocks) {
        this.blocks = blocks.provide();
    }

    public Wall(List<Block> blocks) {
        this.blocks = blocks;
    }

    /**
     * @param color block color
     * @return any block that are in given color
     */
    @Override
    public Optional<Block> findBlockByColor(String color) {
        return wallToStream(blocks)
                .filter(block -> block.getColor().equalsIgnoreCase(color))
                .findFirst();
    }

    /**
     * @param material block material
     * @return all blocks that are made of given material
     */
    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return wallToStream(blocks)
                .filter(block -> block.getMaterial().equalsIgnoreCase(material))
                .toList();
    }

    /**
     * @return number of blocks, that creates structure
     */
    @Override
    public int count() {
        return wallToStream(blocks).toList().size();
    }

    /**
     *
     * @param blocks list of blocks in wall
     * @return stream of all possible blocks in wall
     */
    Stream<Block> wallToStream(List<Block> blocks) {
        return blocks.stream()
                .flatMap(block -> block instanceof CompositeBlock cb ?
                        Stream.concat(Stream.of(cb), wallToStream(cb.getBlocks())) :
                        Stream.of(block)
                );
    }
}
