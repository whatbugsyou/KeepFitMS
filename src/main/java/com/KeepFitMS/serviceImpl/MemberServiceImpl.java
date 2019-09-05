package com.KeepFitMS.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KeepFitMS.dao.MemberDao;
import com.KeepFitMS.entity.Member;
import com.KeepFitMS.service.MemberService;
@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDao md;
	@Override
	public List<Member> getAllMember() {
		// TODO Auto-generated method stub
		return md.getAllMember();
	}
	@Override
	public int addMember(Member member) {
		// TODO Auto-generated method stub
		return md.addMember(member);
	}
	@Override
	public Member getMemberByMid(int mid) {
		// TODO Auto-generated method stub
		return md.getMemberByMid(mid);
	}

}
