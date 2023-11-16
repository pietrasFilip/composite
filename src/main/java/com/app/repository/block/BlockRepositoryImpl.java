package com.app.repository.block;

import com.app.repository.entity.BlockEntity;
import lombok.RequiredArgsConstructor;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BlockRepositoryImpl implements BlockRepository{
    private final Jdbi jdbi;

    @Override
    public List<BlockEntity> findAll() {
        try {
            return jdbi.withHandle(handle -> handle
                    .createQuery("SELECT * from blocks")
                    .mapToBean(BlockEntity.class)
                    .list()
            );
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    @Override
    public Optional<BlockEntity> findById(Long id) {
        var sql = "SELECT * from blocks where id = %s".formatted(id);
        return jdbi.withHandle(handle -> handle
                .createQuery(sql)
                .mapToBean(BlockEntity.class)
                .findFirst());
    }
}
