package com.app.web.routing;

import com.app.repository.provider.CompositeProvider;
import com.app.structure.Structure;
import com.app.web.dto.ResponseDto;
import com.app.web.transformer.JsonTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static spark.Spark.get;
import static spark.Spark.path;


@Component
@RequiredArgsConstructor
public class WallRouting {
    private final Structure wall;
    private final CompositeProvider compositeProvider;
    private final JsonTransformer jsonTransformer;

    public void routes() {
        path("/all/blocks", () -> {
            // http://localhost:8080/all/blocks
            get(
                    "",
                    (request, response) -> {
                        response.header("Content-Type", "application/json;charset=utf-8");

                        return new ResponseDto<>(compositeProvider.provide());
                    },
                    jsonTransformer
            );

            path("/color", () -> {
                // http://localhost:8080/all/blocks/color/:color
                get(
                        "/:color",
                        (request, response) -> {
                            var color = request.params("color");
                            response.header("Content-Type", "application/json;charset=utf-8");

                            return new ResponseDto<>(wall.findBlockByColor(color).orElseThrow());
                        },
                        jsonTransformer
                );
            });

            path("/material", () -> {
                // http://localhost:8080/all/blocks/material/:material
                get(
                        "/:material",
                        (request, response) -> {
                            var material = request.params("material");
                            response.header("Content-Type", "application/json;charset=utf-8");

                            return new ResponseDto<>(wall.findBlocksByMaterial(material));
                        },
                        jsonTransformer
                );
            });

            path("/count", () -> {
                // http://localhost:8080/all/blocks/count
                get(
                        "",
                        (request, response) -> {
                            response.header("Content-Type", "application/json;charset=utf-8");

                            return new ResponseDto<>(wall.count());
                        },
                        jsonTransformer
                );
            });
        });
    }
}
