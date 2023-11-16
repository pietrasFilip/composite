package com.app.web.config;

import com.app.repository.provider.CompositeProvider;
import com.app.structure.Structure;
import com.app.structure.wall.Wall;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import org.jdbi.v3.core.Jdbi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan("com.app")
@PropertySource("classpath:application.properties")
@RequiredArgsConstructor
public class AppConfig {
    private final Environment environment;

    @Bean
    public Gson gson() {
        return new GsonBuilder().serializeNulls().setPrettyPrinting().enableComplexMapKeySerialization().create();
    }

    @Bean
    public Jdbi jdbi() {
        var url = environment.getRequiredProperty("db.url");
        var username = environment.getRequiredProperty("db.username");
        var password = environment.getRequiredProperty("db.password");
        return Jdbi.create(url, username, password);
    }

    @Bean
    public Structure wall(CompositeProvider compositeProvider) {
        return new Wall(compositeProvider);
    }
}
