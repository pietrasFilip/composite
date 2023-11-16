package com.app.repository.composite_block;

import com.app.repository.block.BlockRepository;
import com.app.repository.entity.CompositeBlockEntity;
import lombok.RequiredArgsConstructor;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class CompositeBlockRepositoryImpl implements CompositeBlockRepository{
    private final Jdbi jdbi;
    private final BlockRepository blockRepository;

    @Override
    public List<CompositeBlockEntity> findAll() {
        var sql = """
                SELECT
                    composite_blocks.id,
                    composite_blocks.color,
                    composite_blocks.material,
                    blocks.id AS block_id,
                    blocks.color AS block_color,
                    blocks.material AS block_material
                FROM
                    composite_blocks
                        LEFT JOIN blocks ON composite_blocks.block_id = blocks.id;
                """;
        try {
            return jdbi.withHandle(handle -> handle
                    .createQuery(sql)
                    .map((rs, ctx) ->
                        CompositeBlockEntity
                                .builder()
                                .id(rs.getLong("id"))
                                .color(rs.getString("color"))
                                .material(rs.getString("material"))
                                .block(blockRepository.findById(rs.getLong("id")).orElseThrow())
                                .build()
                    )
                            .list());

        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException(e.getMessage());
        }
    }
}
