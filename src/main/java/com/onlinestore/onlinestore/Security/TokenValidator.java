package com.onlinestore.onlinestore.Security;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;


@Component
public class TokenValidator {
    private final RestTemplateBuilder restTemplateBuilder;
    TokenValidator(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public Optional<JwtObject> validateToken(String authToken){
        /*
         * This method should calls userService to call to validate token. If token is valid
         * then return the corresponding object else return empty.
         *
         */

        //Make http call to userService/authService
        RestTemplate restTemplate = restTemplateBuilder.build();
        //

        return Optional.empty();
    }
}

//@Configuration
//@Getter
//@Setter
//@RequiredArgsConstructor
//public class TokenValidator {
//
//    @Bean
//    public TokenValidator demo(){
//        return new TokenValidator();
//    }
//
//    public Optional<JwtObject> validateToken(String AuthToken){
//        return Optional.empty();
//    }
//}