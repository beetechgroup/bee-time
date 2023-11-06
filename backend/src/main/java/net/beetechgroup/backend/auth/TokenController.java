package net.beetechgroup.backend.auth;

import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/token")
@RestController()
public class TokenController {

    @PostMapping
    public ResponseEntity<String> token (@RequestBody User user) {
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        RestTemplate rt = new RestTemplate();
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("client_id", user.clientId);
        formData.add("username", user.username);
        formData.add("password", user.password);
        formData.add("grant_type", user.grantType);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity(formData, headers);

        ResponseEntity<String> result = rt.postForEntity("http://localhost:8180/realms/master/protocol/openid-connect/token", entity, String.class);
        return result;
    }

    public record User(String password, String clientId, String grantType, String username) {

    }
}
