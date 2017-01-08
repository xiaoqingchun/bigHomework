package com.xiaoqingchun.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.xiaoqingchun.model.Person;

public interface PersonDao  {
	@Select("select * from person where userName=#{username} and password=#{password}")
	Person login(@Param(value="username")String username, @Param(value="password")String password);
	
	@Select("select * from person where userName=#{username}")
	Person getPersonByUserName(@Param(value="username")String username);
	
	@Select("select * from person where id =#{id}")
	Person getPersonById(@Param(value="id")int id);
}