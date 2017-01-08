package com.xiaoqingchun.service;

import com.xiaoqingchun.model.common.User;

public interface PersonService {

	boolean login(String userName, String password);

	User getUser(String userName);
}
