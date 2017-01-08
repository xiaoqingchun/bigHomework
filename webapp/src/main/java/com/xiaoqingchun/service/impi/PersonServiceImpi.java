package com.xiaoqingchun.service.impi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xiaoqingchun.dao.PersonDao;
import com.xiaoqingchun.model.Person;
import com.xiaoqingchun.model.common.User;
import com.xiaoqingchun.service.PersonService;

@Component
public class PersonServiceImpi implements PersonService{

	@Autowired
	private PersonDao persondao;
	
	public boolean  login(String username, String password) {	
		Person person = persondao.login(username, password);
		if(person!=null){
			return true;
		}
		return false;
	}

	public User getUser(String userName) {
		Person person = persondao.getPersonByUserName(userName);
		User user = new User();
		user.setId(person.getId());
		user.setUsername(person.getUsername());
		user.setUsertype(person.getUsertype());
		return user;
	}
}
