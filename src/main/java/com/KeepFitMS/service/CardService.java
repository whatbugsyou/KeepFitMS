package com.KeepFitMS.service;

import java.util.Date;
import java.util.List;



import com.KeepFitMS.entity.Card;

public interface CardService {
	//用户开卡(和录入会员信息同时进行)
	int  addCard(Card card);
	//根据类型查询卡
	List<Card> getCardsByType(String ctype);
	//根据卡号查询会员卡余额
	int getCardMoney(int cid);
	//根据卡号充值
	int deposit(int money,int cid);
	//删除卡
	int delCard(int cid);
	//根据卡号查询卡
	
	Card selectCardByCid(int cid);
	
	//根据会员ID修改会员卡信息
	int updateCardByCid(Card c);
}
