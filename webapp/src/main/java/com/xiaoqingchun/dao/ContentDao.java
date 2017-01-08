package com.xiaoqingchun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xiaoqingchun.model.Content;

public interface ContentDao{
	@Delete("delete from content where id = #{id}")
	int delete(int id);	
	
	@Select("select * from content")
	List<Content> getAllContent();
	
	@Select("select * from content where id = #{id}")
	Content getContent(@Param(value="id")Integer contentId);
	
	@Update("update content set price=#{price},title=#{title},icon=#{icon},abstract=#{abstract_},text=#{text} where id = #{id}")
	void updateContent(@Param(value = "price")Integer price, @Param(value = "title")String title, @Param(value = "icon")byte[] icon, @Param(value = "abstract_")String abstract_, @Param(value = "text") byte[] text, @Param(value = "id")Integer id);
	
	//并发访问的时候会出错
	@Select("select max(id) from content")
	int getLastId();
	
	@Insert("insert into content(price, title, icon, abstract, text) values(#{price},#{title},#{icon},#{abstract_},#{text})")	
	@Options(useGeneratedKeys=true, keyProperty="id")
	Integer  insertContent(@Param(value = "price")Integer price, @Param(value = "title")String title, @Param(value = "icon")byte[] icon, @Param(value = "abstract_")String abstract_, @Param(value = "text") byte[] text);

}
