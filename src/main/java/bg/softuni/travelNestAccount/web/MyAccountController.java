package bg.softuni.travelNestAccount.web;

import bg.softuni.travelNestAccount.config.TravelNestApiConfig;
import bg.softuni.travelNestAccount.dto.PropertyDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestClient;

import java.util.List;

@Controller
public class MyAccountController {

    private final RestClient restClient;
    private final TravelNestApiConfig travelNestApiConfig;

    public MyAccountController(@Qualifier("myAccountRestClient") RestClient restClient, TravelNestApiConfig travelNestApiConfig) {
        this.restClient = restClient;
        this.travelNestApiConfig = travelNestApiConfig;
    }

    @GetMapping("/my-account")
    public String showMyAccount(){
        List<PropertyDTO> favoriteHousings =
                restClient.get()
                        .uri("/get-user-favorites")
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .body(new ParameterizedTypeReference<>() {});

        return "my_account";
    }
}
