package com.xiaoqingchun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.xiaoqingchun.model.Trx;
import com.xiaoqingchun.model.common.Product;

public interface TrxDao{
	@Select("select * from trx where personId = #{personId} ")
	public List<Trx> getTrxListByPersonId(@Param(value= "personId")Integer personId);
	
	@Select("select * from trx where contentId = #{contentId} ")
	public List<Trx> getTrxListByContentId(@Param(value= "contentId")Integer contentId);
	
	
	@Select("select * from trx where personId = #{personId} and personId=#{personId)")
	public Product getProduct(@Param(value = "contentId")int contentId);
	
	@Insert("insert trx(contentId, personId, price,time)values(#{contentId},#{personId},#{price},#{time})")
	public int buy(@Param(value="contentId")int contentId, @Param(value="personId")int personId, @Param(value="price")int buyprice, @Param(value="time")long time);
}