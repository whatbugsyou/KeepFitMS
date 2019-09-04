package com.KeepFitMS.controller;

import java.util.List;

import javax.xml.ws.RespectBinding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KeepFitMS.dao.MemberDao;
import com.KeepFitMS.entity.Member;
import com.KeepFitMS.service.MemberService;

/*
 * 会员管理控制层
 * */
@Controller
public class MemberController {
	@Autowired
	private MemberService ms;
	
	//测试用
	@ResponseBody
	@RequestMapping("Membertest")
	public String test() {
		List<Member> lm=ms.getAllMember();
		System.out.println(lm);
		return "test";	
	}
}
