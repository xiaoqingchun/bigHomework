package com.xiaoqingchun.service.impi;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xiaoqingchun.dao.ContentDao;
import com.xiaoqingchun.dao.TrxDao;
import com.xiaoqingchun.model.Content;
import com.xiaoqingchun.model.Trx;
import com.xiaoqingchun.model.common.BuyList;
import com.xiaoqingchun.service.TrxService;

@Component
public class TrxServiceImpi implements TrxService {

	@Autowired
	private ContentDao contentDao;
	@Autowired
	private TrxDao trxdao;

	public boolean buy(int contentId, int personId, Integer buyprice, long buytime) {
		trxdao.buy(contentId, personId, buyprice, buytime);
		return true;
	}

	public List<BuyList> getBuyList(Integer id) {
		List<BuyList> list = new ArrayList<BuyList>();
		List<Trx> list2 = trxdao.getTrxListByPersonId(id);
		for (Trx trx : list2) {
			Content content = contentDao.getContent(trx.getContentId());
			BuyList buyList = new BuyList();
			System.out.println();
			buyList.setBuyPrice(trx.getPrice());
			buyList.setBuyTime(trx.getTime().longValue());
			try {
				buyList.setImage(new String(content.getIcon(),"utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			buyList.setTitle(content.getTitle());
			buyList.setId(content.getId());
			list.add(buyList);
		}
		
		return list;
	}
}
