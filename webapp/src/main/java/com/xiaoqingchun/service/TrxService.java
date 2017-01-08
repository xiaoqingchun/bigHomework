package com.xiaoqingchun.service;

import java.util.List;

import com.xiaoqingchun.model.common.BuyList;

public interface TrxService {

	public boolean buy(int contentId, int personId, Integer buyprice, long buytime);

	List<BuyList> getBuyList(Integer userId);
}
