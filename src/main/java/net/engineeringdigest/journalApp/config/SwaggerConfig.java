package net.engineeringdigest.journalApp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI myCustomConfig() {
        return new OpenAPI().info(
                        new Info().title("Journal App Api Doc")
                                .description("APi documentation for journal app.")
                )
                .servers(Arrays.asList(
                        new Server().url("localhost:8080").description("Local"), new Server().url("localhost:8081").description("Live")
                ))
                .tags(
                        Arrays.asList(
                                new Tag().name("Public APIs"),
                                new Tag().name("User APIs"),
                                new Tag().name("Journal APIs"),
                                new Tag().name("Admin APIs")
                        )
                );
    }
}
