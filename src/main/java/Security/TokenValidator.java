package Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.PublicKey;
import java.util.Optional;

@Service
// or can use @component also
public class TokenValidator {
    private RestTemplateBuilder restTemplateBuilder;
    @Autowired
    TokenValidator(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }
    public Optional<JwtObject> validateToken(String authToken){
        /**
         * This method should calls userService to call to validate token. If token is valid
         * then return the corresponding object else return empty.
         *
         */

        //Make hthp call to userService/authService
        RestTemplate restTemplate = restTemplateBuilder.build();

        return Optional.empty();
    }
}
