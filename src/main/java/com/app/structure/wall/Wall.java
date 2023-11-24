package com.app.structure.wall;

import com.app.block.Block;
import com.app.block.composite_block.CompositeBlock;
import com.app.repository.provider.CompositeProvider;
import com.app.structure.Structure;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;


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
     *
     * @param color block color
     * @return any block that are in given color
     */
    @Override
    public Optional<Block> findBlockByColor(String color) {
        return findBlockInList(blocks, color);
    }

    private Optional<Block> findBlockInList(List<Block> blocks, String color) {
        for (Block block : blocks) {
            if (block.getColor().equalsIgnoreCase(color)) {
                return Optional.of(block);
            }
            if (block instanceof CompositeBlock cb) {
                Optional<Block> found = findBlockInList(cb.getBlocks(), color);
                if (found.isPresent()) {
                    return found;
                }
            }
        }
        return Optional.empty();
    }

    /**
     *
     * @param material block material
     * @return all blocks that are made of given material
     */
    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return findBlockByPredicate(block -> block.getMaterial().equals(material));
    }

    /**
     *
     * @return number of blocks, that creates structure
     */
    @Override
    public int count() {
        return findBlockByPredicate(Objects::nonNull).size();
    }

    /**
     *
     * @param predicate predicate condition
     * @return - list of blocks, that met given condition
     */
    private List<Block> findBlockByPredicate(Predicate<Block> predicate) {
        var result = new ArrayList<Block>();

        for (var block : blocks) {
            checkComposite(block, predicate, result);
        }
        return result;
    }

    /**
     *
     * @param block element from blocks list. Can be Block or CompositeBlock.
     * @param predicate predicate condition.
     * @param result list with final result.
     */
    private void checkComposite(Block block, Predicate<Block> predicate, List<Block> result) {
        if (predicate.test(block)) {
            result.add(block);
        }

        if (block instanceof CompositeBlock compositeBlock) {
            for (var composite : compositeBlock.getBlocks()) {
                checkComposite(composite, predicate, result);
            }
        }
    }
}
