package ru.worldmac.wmbot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.worldmac.wmbot.feign.JRGroupClient;
import ru.worldmac.wmbot.feign.JRPostsClient;

@Configuration
public class FeignClientConfig {

    @Bean
    public JRPostsClient jRPostsClient() {
        final String jrUrl = "https://javarush.ru/api/1.0/rest/posts";
        var jrPostsClient = FeignClientConfigBuilder.feignBuildJson(JRPostsClient.class, jrUrl);
        return jrPostsClient;
    }

    @Bean
    public JRGroupClient jrGroupClient() {
        final String jrUrl = "https://javarush.ru/api/1.0/rest/groups";
        var jrGroupClient = FeignClientConfigBuilder.feignBuildJson(JRGroupClient.class, jrUrl);
        return jrGroupClient;
    }
}
