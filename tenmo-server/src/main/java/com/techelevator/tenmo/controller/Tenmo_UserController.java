package com.techelevator.tenmo.controller;

import javax.validation.Valid;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.Tenmo_UserDao;
import com.techelevator.tenmo.exception.DaoException;
import com.techelevator.tenmo.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.security.jwt.TokenProvider;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class Tenmo_UserController {

    private Tenmo_UserDao tenmo_userDao;

    public Tenmo_UserController (Tenmo_UserDao tenmo_userDao) {
        this.tenmo_userDao = tenmo_userDao;

    }

    @RequestMapping(path = "/tenmo_user", method = RequestMethod.GET)
    public List<Tenmo_User> list (@RequestParam (defaultValue = "") int user_id, @RequestParam (defaultValue = "") String username) {
        if (user_id > 1000) {
            return tenmo_userDao.getUserListByUserId();
        }
    }
    public Tenmo_User getUserListByUserId() {
        Tenmo_User tenmo_user = tenmo_userDao.getUserListByUserId();
        return tenmo_user;
    }



}
