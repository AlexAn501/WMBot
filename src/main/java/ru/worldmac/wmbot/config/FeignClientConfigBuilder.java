package ru.worldmac.wmbot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import feign.Feign;
import feign.Logger;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;

public class FeignClientConfigBuilder {
    //    @Value("javarush.api.path")
    private final static String JR_URL = "https://javarush.ru/api/1.0/rest";

    public static <T> T feignBuildJson(Class<T> tClass) {
        T rushClient = Feign.builder()
                .client(new OkHttpClient())
                .encoder(initJacksonEncoder())
                .decoder(initJacksonDecoder())
                .logger(new Slf4jLogger(tClass))
                .logLevel(Logger.Level.FULL)
                .target(tClass, JR_URL);
        return rushClient;
    }

    private static Encoder initJacksonEncoder() {
        return new JacksonEncoder(createObjectMapper());
    }

    private static Decoder initJacksonDecoder() {
        return new JacksonDecoder(createObjectMapper());
    }

    private static ObjectMapper createObjectMapper() {
        return new ObjectMapper().registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .findAndRegisterModules();
    }
}