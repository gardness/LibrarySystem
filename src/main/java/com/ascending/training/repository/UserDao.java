/*
 * Copyright (C) 2019 Liwei Wang, daveywang@live.com - All Rights Reserved
 */

package com.ascending.training.repository;

import com.ascending.training.model.User;

public interface UserDao {
    boolean save(User user);
    User getUserByEmail(String email);
    User getUserByCredentials(String email, String password);
}