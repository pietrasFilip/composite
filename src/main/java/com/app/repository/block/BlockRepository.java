package com.app.repository.block;

import com.app.repository.entity.BlockEntity;

import java.util.List;
import java.util.Optional;

public interface BlockRepository {
    List<BlockEntity> findAll();
    Optional<BlockEntity> findById(Long id);
}
