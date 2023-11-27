package com.app;

import com.app.block.Block;
import com.app.block.BlockImpl;
import com.app.block.composite_block.CompositeBlockImpl;
import com.app.structure.wall.Wall;
import com.app.web.config.AppConfig;
import com.app.web.routing.ErrorRouting;
import com.app.web.routing.WallRouting;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.initExceptionHandler;
import static spark.Spark.port;

public class Main {
    public static void main(String[] args) {
        //------------------------------------------------------------------------------------------
        // WEB
        //------------------------------------------------------------------------------------------
        /*initExceptionHandler(e -> System.out.println(e.getMessage()));
        port(8080);

        var context = new AnnotationConfigApplicationContext(AppConfig.class);

        var errorRouting = context.getBean("errorRouting", ErrorRouting.class);
        errorRouting.routes();

        var wallRouting = context.getBean("wallRouting", WallRouting.class);
        wallRouting.routes();*/

        //------------------------------------------------------------------------------------------
        // CODE TO EXECUTE WITHOUT USING DOCKER
        //------------------------------------------------------------------------------------------
        var block1 = BlockImpl
                .builder()
                .color("red")
                .material("concrete")
                .build();
        var block2 = BlockImpl
                .builder()
                .color("blue")
                .material("concrete")
                .build();
        var block3 = BlockImpl
                .builder()
                .color("red")
                .material("wood")
                .build();
        var block4 = BlockImpl
                .builder()
                .color("white")
                .material("concrete")
                .build();

        var blocks = new ArrayList<Block>();

        blocks.add(block1);
        blocks.add(block2);
        blocks.add(block3);

        var blocks2 = new ArrayList<Block>();

        blocks2.add(block1);
        blocks2.add(block2);
        blocks2.add(block3);
        blocks2.add(block4);

        var compositeBlock = CompositeBlockImpl
                .builder()
                .color("white")
                .material("wood")
                .blocks(blocks2)
                .build();

        var compositeBlock3 = CompositeBlockImpl
                .builder()
                .color("black")
                .material("wood")
                .blocks(blocks)
                .build();

        var listWithComposite = new ArrayList<Block>();
        listWithComposite.add(compositeBlock);
        listWithComposite.add(block1);
        listWithComposite.add(compositeBlock3);

        var compositeBlock2 = CompositeBlockImpl
                .builder()
                .color("green")
                .material("wood")
                .blocks(listWithComposite)
                .build();

        var blocksWithComposite = new ArrayList<>(blocks);
        blocksWithComposite.add(compositeBlock);
        blocksWithComposite.add(compositeBlock2);

        System.out.println("----------(BLOCKS)----------");
        System.out.println(blocksWithComposite);

        var wall = Wall
                .builder()
                .blocks(blocksWithComposite)
                .build();

        System.out.println("----------(WALL)----------");
        System.out.println("----------(1)----------");
        System.out.println(wall.findBlockByColor("black"));
        System.out.println("----------(2)----------");
        System.out.println(wall.findBlocksByMaterial("wood"));
        System.out.println("----------(3)----------");
        System.out.println(wall.count());
    }
}