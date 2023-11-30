package com.techelevator.tenmo.services;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
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

import java.math.BigDecimal;

public class TransferService {

    public String API_BASE_URL = "http://localhost:8080/transfer";

    private final RestTemplate restTemplate = new RestTemplate();
    private AuthenticatedUser currentUser;
    public Transfer getTransferById(int transfer_id) {
        Transfer transfer = null;
        transfer = restTemplate.getForObject(API_BASE_URL + "/" + transfer_id, Transfer.class);

        return transfer;
    }
    public boolean sendMoney(Transfer transfer){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Transfer> entity = new HttpEntity<>(transfer, headers);

        boolean success = false;
        try {
            restTemplate.put(API_BASE_URL + "/sendMoney/" + transfer.getAccount_to() + "/"+ transfer.getAccount_from() + "/" + transfer.getAmount(), entity);
            success = true;
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return success;
    }
}
