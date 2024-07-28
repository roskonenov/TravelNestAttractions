package bg.softuni.travelNestAccount.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.awt.*;

@Configuration
public class RestConfig {

    @Bean("imageRestClient")
    public RestClient imageRestClient(ImgurConfig imgurConfig){
        return RestClient.builder()
                .defaultHeader("Authorization", "Client-ID " + imgurConfig.getKey())
                .build();
    }

    @Bean("myAccountRestClient")
    public RestClient myAcountRestClient(TravelNestApiConfig travelNestApiConfig){
        return RestClient
                .builder()
                .baseUrl(travelNestApiConfig.getBaseUrl())
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
