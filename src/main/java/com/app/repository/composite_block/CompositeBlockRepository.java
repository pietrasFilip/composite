package com.app.repository.composite_block;

import com.app.repository.entity.CompositeBlockEntity;

import java.util.List;

public interface CompositeBlockRepository {
    List<CompositeBlockEntity> findAll();
}
