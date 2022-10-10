package com.example.RestTemplate;

import com.example.RestTemplate.model.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Alex Zarubin
 * created on 10.10.2022
 */
 public class MyRestTemplate {

    private static final String URL = "http://94.198.50.185:7081/api/users";

    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> entity = new HttpEntity<>(headers);
        System.out.println("==========  Method GET  ===========");
        ResponseEntity<User[]> response = restTemplate.exchange(URL, HttpMethod.GET, entity, User[].class);
        System.out.println(response);
        String cookie = response.getHeaders().getFirst("Set-Cookie");
        headers.set("Cookie", cookie);

        System.out.println("==========  Method POST  ===========");
        User user = new User(3L, "James", "Brown", (byte) 9);
        HttpEntity<User> entity1 = new HttpEntity<>(user, headers);
        ResponseEntity<String> response1 = restTemplate.exchange(URL, HttpMethod.POST, entity1, String.class);
        System.out.println(response1.getBody());

        System.out.println("==========  Method PUT  ===========");
        user.setName("Thomas");
        user.setLastName("Shelby");
        HttpEntity<User> entity2 = new HttpEntity<>(user, headers);
        ResponseEntity<String> response2 = restTemplate.exchange(URL, HttpMethod.PUT, entity2, String.class);
        System.out.println(response2.getBody());

        System.out.println("==========  Method DELETE  ===========");
        ResponseEntity<String> response3 = restTemplate.exchange(URL + "/3", HttpMethod.DELETE, entity2, String.class);
        System.out.println(response3.getBody());

        System.out.println("==========  Method GET  ===========");
        ResponseEntity<User[]> response4 = restTemplate.exchange(URL, HttpMethod.GET, entity, User[].class);
        System.out.println(response4.getBody());

    }
}

