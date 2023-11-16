package com.app.structure.wall;

import com.app.block.Block;
import com.app.block.composite_block.CompositeBlock;
import com.app.structure.Structure;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;


@Builder
@AllArgsConstructor
public class Wall implements Structure {
    private List<Block> blocks;

    /**
     *
     * @param color block color
     * @return any block that are in given color
     */
    @Override
    public Optional<Block> findBlockByColor(String color) {
        return findBlockByPredicate(block -> block.getColor().equals(color))
                .parallelStream()
                .findAny();
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
