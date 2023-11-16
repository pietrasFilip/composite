package com.app.repository.provider;

import com.app.block.Block;
import com.app.repository.block.BlockRepository;
import com.app.repository.composite_block.CompositeBlockRepository;
import com.app.repository.entity.BlockEntity;
import com.app.repository.entity.CompositeBlockEntity;
import com.app.repository.entity.CompositeBlockWithBlocksEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toMap;

@Repository
@RequiredArgsConstructor
public class CompositeProviderImpl implements CompositeProvider{
    private final BlockRepository blockRepository;
    private final CompositeBlockRepository compositeBlockRepository;

    @Override
    public List<Block> provide() {
        var blocks = new ArrayList<>(blockRepository
                .findAll()
                .stream()
                .map(BlockEntity::toBlock)
                .toList()
        );
        var composite = new ArrayList<>(compositeBlockRepository
                .findAll()
                .stream()
                .map(CompositeBlockEntity::withBlocks)
                .collect(toMap(CompositeBlockWithBlocksEntity::getId, val -> val,
                        (b1, b2) -> {
                            b1.getBlocks().addAll(b2.getBlocks());
                            return b1;
                        }))
                .values()
                .stream()
                .map(CompositeBlockWithBlocksEntity::toCompositeBlock)
                .toList()
        );
        blocks.addAll(composite);
        return blocks;
    }
}
