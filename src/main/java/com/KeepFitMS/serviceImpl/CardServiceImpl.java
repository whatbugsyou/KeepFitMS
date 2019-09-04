package com.KeepFitMS.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KeepFitMS.dao.CardDao;
import com.KeepFitMS.entity.Card;
import com.KeepFitMS.service.CardService;
@Service
public class CardServiceImpl implements CardService {
	@Autowired
	private CardDao cd;
	@Override
	public int addCard(Card card) {
		// TODO Auto-generated method stub
		return cd.addCard(card);
	}

	@Override
	public List<Card> getCardsByType(String ctype) {
		// TODO Auto-generated method stub
		return cd.getCardsByType(ctype);
	}

	@Override
	public int getCardMoney(int cid) {
		// TODO Auto-generated method stub
		return cd.getCardMoney(cid);
	}

	@Override
	public int deposit(int money, int cid) {
		// TODO Auto-generated method stub
		return cd.deposit(money, cid);
	}

	@Override
	public int delCard(int cid) {
		// TODO Auto-generated method stub
		return cd.delCard(cid);
	}

}
