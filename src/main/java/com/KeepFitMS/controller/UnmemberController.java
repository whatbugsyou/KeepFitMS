package com.KeepFitMS.controller;
/*
 * 游客控制层
 * */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.KeepFitMS.entity.Unmember;
import com.KeepFitMS.service.UnmemberService;
@Controller
public class UnmemberController {
	@Autowired
	private UnmemberService ums;
	@RequestMapping("unmember")
	public ModelAndView unmember() {
		return new ModelAndView("unmember.html");
	}
	
	@RequestMapping( "addUnmember.do")
	public ModelAndView addUnmember(Unmember um) {
		ModelAndView mv=new ModelAndView("unmember.html");
		if(ums.addUnmember(um)==1) {
			mv.addObject("msg", "添加成功!");
		}else {
			mv.addObject("msg", "添加失败!");
		}
		
		return mv;
	}
}
