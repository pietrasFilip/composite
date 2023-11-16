package com.app.repository.provider;

import com.app.block.Block;

import java.util.List;

public interface CompositeProvider {
    List<Block> provide();
}
