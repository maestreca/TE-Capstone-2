package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpHeaders;

public class AccountService {
    public String API_BASE_URL = "http://localhost:8080/accountByUserId";

    private final RestTemplate restTemplate = new RestTemplate();

    private String authToken = null;

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

//    public Account getAccountByUserId_secure(int userId){
//        Account account =null;
//        HttpHeaders headers = new HttpHeaders();
//        headers.setBearerAuth(authToken);
//        HttpEntity entite = new HttpEntity(headers);
//
//        account = restTemplate.exchange(API_BASE_URL + userId, HttpMethod.GET,
//                Account.class).getBody();
//        return account;
//    }

    public Account getAccountByUserId(int userId) {

        Account account = null;
        account = restTemplate.getForObject(API_BASE_URL + "/" + userId, Account.class);
        return account;

    }
}

