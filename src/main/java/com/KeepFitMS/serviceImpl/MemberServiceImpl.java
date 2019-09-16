package com.KeepFitMS.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.KeepFitMS.dao.CardDao;
import com.KeepFitMS.dao.MemberDao;
import com.KeepFitMS.entity.Card;
import com.KeepFitMS.entity.Member;
import com.KeepFitMS.service.MemberService;
@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDao md;
	@Autowired
	private CardDao cd;
	@Override
	public List<Member> getAllMember() {
		// TODO Auto-generated method stub
		return md.getAllMember();
	}
	@Transactional //开启事务，添加会员同时添加会员卡
	@Override
	public int addMember(Member member,Card c) {
		if(md.addMember(member)==1&&cd.addCard(c)==1) {
			return 1;
		}else {
			return -1;
		}
	}
	@Override
	public Member getMemberByMid(int mid) {
		// TODO Auto-generated method stub
		return md.getMemberByMid(mid);
	}

	
	@Override
	public List<Member> shaixuanMember(Member m) {
		// TODO Auto-generated method stub
		return md.shaixuanMember(m);
	}
	@Transactional//开启事务，删除会员前先注销卡
	@Override
	public int deleteMemberByMid(int mid) {
		if(cd.delCard(mid)==1&&md.deleteMemberByMid(mid)==1) {
			return 1;
		}else {
			return -1;
				
		}
	}
	@Override
	public int updateMemberByMid(Member m) {
		// TODO Auto-generated method stub
		return md.updateMemberByMid(m);
	}

}
