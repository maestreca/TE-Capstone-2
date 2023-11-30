package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Tenmo_User;

import java.util.List;

public interface Tenmo_UserDao {

    //    Tenmo_User getTenmo_UserById(int user_id);
    List<Tenmo_User> getUserListByUserId();
}
