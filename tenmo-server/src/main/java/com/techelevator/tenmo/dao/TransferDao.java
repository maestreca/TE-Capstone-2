package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDao {

    Transfer getTransferById (int transfer_id);
    void sendMoney(int accountTo, int accountFrom, BigDecimal amount);

    List<Transfer> viewTransferHistory();


}
