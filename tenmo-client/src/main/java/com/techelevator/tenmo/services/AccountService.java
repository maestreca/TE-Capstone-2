package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.web.client.RestTemplate;

import com.techelevator.util.BasicLogger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.UserCredentials;
// Caro added imports from Authentication Service on Sun 12/3


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

    public Account getAccountByUserId_secure(int userId){ // Caro added Sun 12/3
        Account account =null;
        org.springframework.http.HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        HttpEntity entity = new HttpEntity(headers);

        account = restTemplate.exchange(API_BASE_URL + "/" + userId, HttpMethod.GET,
                entity, Account.class).getBody();
        return account;
    }
//    public Account getAccountByUserId(int userId) {
//
//        Account account = null;
//        account = restTemplate.getForObject(API_BASE_URL + "/" + userId, Account.class);
//        return account;
//
//    }
}

