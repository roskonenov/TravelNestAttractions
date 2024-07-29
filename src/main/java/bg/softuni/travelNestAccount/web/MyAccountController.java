package bg.softuni.travelNestAccount.web;

import bg.softuni.travelNestAccount.config.TravelNestApiConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestClient;

@Controller
public class MyAccountController {

    private final RestClient restClient;
    private final TravelNestApiConfig travelNestApiConfig;

    public MyAccountController(@Qualifier("myAccountRestClient") RestClient restClient, TravelNestApiConfig travelNestApiConfig) {
        this.restClient = restClient;
        this.travelNestApiConfig = travelNestApiConfig;
    }

    @GetMapping("/redirect")
    public String redirect(){
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        return "my_account";
    }
}
