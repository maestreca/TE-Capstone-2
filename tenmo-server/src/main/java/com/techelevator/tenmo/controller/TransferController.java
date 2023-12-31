package com.techelevator.tenmo.controller;
import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.exception.DaoException;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class TransferController {

    private TransferDao transferDao;
    private AccountDao accountDao;

    public TransferController (TransferDao transferDao,AccountDao accountDao) {
        this.transferDao = transferDao;
        this.accountDao = accountDao;

        if (this.accountDao == null) {
            // Log a message or throw an exception to indicate that accountDao is null
            throw new RuntimeException("AccountDao is not properly injected into TransferController");
        }
    }

//    @RequestMapping(path = "/transfer/{transfer_id}", method = RequestMethod.GET)
//    public Transfer getTransferById(@PathVariable int transfer_id) {
//        Transfer transfer = transferDao.getTransferById(transfer_id);
//        return transfer;
//    }



    @RequestMapping(path = "/transfers/sendMoney/{accountTo}/{accountFrom}/{amount}", method = RequestMethod.PUT)
    public Transfer sendMoney(
            @Valid @RequestBody Transfer transfer,
            @PathVariable int accountTo,
            @PathVariable int accountFrom,
            @PathVariable BigDecimal amount) {
        int accountToId = accountDao.getAccountIdByUserId(accountTo);
        int accountFromId = accountDao.getAccountIdByUserId(accountFrom);

        transfer.setAccount_to(accountToId);
        transfer.setAccount_from(accountFromId);
        transfer.setAmount(amount);
        transfer.setTransfer_id(0);
        transfer.setTransfer_type_id(2);
        transfer.setTransfer_status_id(2);

        try {
            transferDao.sendMoney(transfer.getAccount_to(), transfer.getAccount_from(), transfer.getAmount());
            return transfer;
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during money transfer", e);
        }
    }

    @RequestMapping(path = "/transfers/{userId}", method = RequestMethod.GET)
    public List<Transfer> list( @PathVariable int userId){
//            @RequestParam(defaultValue = "0") int transfer_id,
//            @RequestParam(defaultValue = "0") int accountFrom,
//           @RequestParam(defaultValue = "0") int accountTo,
//            @RequestParam(defaultValue = "") String username,
//            @RequestParam(defaultValue = "0") BigDecimal amount
        // int accountsId = accountDao.getAccountIdByUserId(userId);

        return transferDao.viewTransferHistory(userId);

    }
    @RequestMapping(path = "/transfers/viewDetails/{transferId}", method = RequestMethod.GET)
    public Transfer viewTransferDetails(@PathVariable int transferId){
        return transferDao.viewTransferDetails(transferId);
    }


}
