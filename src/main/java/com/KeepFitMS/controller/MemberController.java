package com.KeepFitMS.controller;


import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.KeepFitMS.entity.Card;
import com.KeepFitMS.entity.Member;
import com.KeepFitMS.service.CardService;
import com.KeepFitMS.service.MemberService;

/*
 * 会员控制层
 * */
@Controller
public class MemberController {
	@Autowired
	private MemberService ms;
	@Autowired
	private CardService cs;
	//进入主页
	@RequestMapping("index")
	public ModelAndView index() {
		return new ModelAndView("test.html");
	}
	//进入添加会员界面
	@RequestMapping("addMember")
	public ModelAndView add() {
		
		return new ModelAndView("addMember.html");
	}
	//进入会员记录登记界面
	@RequestMapping("m_record")
	public ModelAndView m_record() {
		
		return new ModelAndView("m_record.html");
	}
	
	//会员信息录入
	@RequestMapping("addMember.do")
	public ModelAndView addMember(MultipartFile image,Member m,String ctype,String sdate) throws IOException, ParseException {
		//新创建一个随机的文件名
		String ext = image.getOriginalFilename();
		String fileName = UUID.randomUUID().toString().replace("-", "")+ext.substring(ext.indexOf("."));
		//将文件路径作为member的icon属性,存到服务器的Image文件中
		String icon="image/"+fileName;
		System.out.println(m.getCoach_id());
		m.setIcon(icon);
		
		
		image.transferTo(new File("D:/image/"+fileName));
		//会员卡绑定
		Card c=new Card();
		c.setCmoney(0);
		c.setCpoints(0);
		c.setMid(m.getMid());
		c.setCtype(ctype);
		//开始时间 结束时间
		Date date=new SimpleDateFormat("yyyy-MM-dd").parse(sdate);
		c.setSdate(date);
		Calendar cl=Calendar.getInstance();
		cl.setTime(date);
		if(ctype.equals("年卡")) {
			cl.add(Calendar.YEAR, 1);//年卡，开始时间加一年
			c.setCprice(8888);
		}
		else if(ctype.equals("季卡")) {
			cl.add(Calendar.MONTH, 3);//季节卡，开始时间加三个月
			c.setCprice(4999);
		}
		else if(ctype.equals("月卡")) {
			cl.add(Calendar.MONTH, 1);//月卡，开始时间加一个月
			c.setCprice(2999);
		}
		Date edate=cl.getTime();//获取结束时间Date类型
		c.setEdate(edate);
		
		//添加会员同时绑定会员卡
		ModelAndView mv=new ModelAndView("addMember.html");
		if(ms.addMember(m, c)==1) {
			mv.addObject("msg", "ok");
		}else {
			mv.addObject("msg", "no");
		}
		

		return mv;
		
	}
	//会员注册检测
	@ResponseBody
	@RequestMapping("checkMember.do")
	public String checkMember(int mid) {
		Member m=ms.getMemberByMid(mid);
		if(m==null) {//判断用户是否存在，不存在返回ok
			return "ok";
		}else {
			return "exist";
		}
		
	}
	//会员登记检测
	@ResponseBody
	@RequestMapping("checkMember2.do")
	public String checkMember2(int mid) {
		Member m=ms.getMemberByMid(mid);
		if(m==null) {//用户不存在，返回no exsit
			return "no exsit";
		}else {
			//获取会员卡的到期时间与当前时间比较
			System.out.println(m);
			if(m.getCard().getEdate().getTime()<new Date().getTime()) {//已过期，返回过期
			return "guoqi";
			}
			else {
			return "ok";
			}
		}
	}
	
	
}
