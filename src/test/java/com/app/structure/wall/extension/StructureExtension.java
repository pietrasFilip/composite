package com.app.structure.wall.extension;

import com.app.structure.Structure;
import com.app.structure.wall.Wall;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import static com.app.Blocks.BLOCKS_WITH_COMPOSITE;

public class StructureExtension implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(Structure.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return Wall.builder()
                .blocks(BLOCKS_WITH_COMPOSITE)
                .build();
    }
}
