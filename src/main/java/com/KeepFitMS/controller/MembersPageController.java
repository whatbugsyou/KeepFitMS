package com.KeepFitMS.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.KeepFitMS.entity.Card;
import com.KeepFitMS.entity.Member;
import com.KeepFitMS.service.CardService;
import com.KeepFitMS.service.MemberService;

/*
 * 会员管理界面控制层
 * 
 * */
@Controller
public class MembersPageController {
		@Autowired
		private CardService cs;
		@Autowired
		private MemberService ms;
		//初始的会员集合
		private List<Member> lm;
		
		//获取总页面数，第一页会员

		@RequestMapping("getAllMember.do")
		public ModelAndView test() {
			ModelAndView mv=new ModelAndView("members.html");
			lm=ms.getAllMember();//获取所有会员
			return pageBean(mv, 1);//返回到第一页
		
		}
		//分页展示会员
		@RequestMapping("getMemberByPage.do")
		
		public ModelAndView getMemberByPage(int page){
			ModelAndView mv=new ModelAndView("members.html");	
			System.out.println();
			return pageBean(mv, page);
			
		}
	
		//根据条件筛选
		@RequestMapping("shaixuanMember.do")
		public ModelAndView shaixuanMember(String cid,String mid,String mname,String mtelephone,String msex) {
			ModelAndView mv=new ModelAndView("members.html");
			Member m=new Member();
			//封装筛选的bean对象
			if(mid=="") {
				m.setMid(-1);
			}else {
				m.setMid(Integer.parseInt(mid));
			}
			Card c=new Card();
			if(cid=="") {
				c.setCid(-1);
				m.setCard(c);
			}else {
				c.setCid(Integer.parseInt(cid));
				m.setCard(c);
			}
			m.setMsex(msex);
			m.setMtelephone(mtelephone);
			m.setMname(mname);
			
			if(ms.shaixuanMember(m).size()>0) {
				lm=ms.shaixuanMember(m);//筛选后更新页面数据
				return pageBean(mv, 1);//返回第一页的数据
			}else {
				mv.addObject("msg", "未查询到数据!");//未查询到数据，不更新数据返回提示信息
				return pageBean(mv, 1);//返回第一页的数据
			}
			
		
			
		}
		//删除会员，同时删除卡
		@RequestMapping("deleteMemberByMid.do")

		public ModelAndView deleteMemberByMid(int mid) {
			ModelAndView m=new ModelAndView("members.html");
			if(ms.deleteMemberByMid(mid)==1) {
				m.addObject("msg", "删除成功!");
			}else {
				m.addObject("msg","删除失败!");
				
			}
			lm=ms.getAllMember();//重新更新一次数据
			return pageBean(m, 1);//返回到第一页
			
		}
		 @RequestMapping("updateCardBycid.do")
		 //  修改卡的积分/有效期 可选择(动态sql)
		 public ModelAndView updateCardBycid(int cid,String cpoint,String edate) throws ParseException {
		
			 Card c=new Card();
			 c.setCid(cid);
			if(cpoint!="") {
				c.setCpoints(Integer.parseInt(cpoint));
			}else {
				c.setCpoints(-1);//不修改积分，将point改为-1
			}

			if(edate !="") {
			c.setEdate(new SimpleDateFormat("yyyy-MM-dd").parse(edate));
			}
			
			ModelAndView mv=new ModelAndView("members.html");
			if(cs.updateCardByCid(c)==1) {
				mv.addObject("msg", "修改成功!");
			}else {
				mv.addObject("msg", "修改失败!");
			}
			lm=ms.getAllMember();//重新更新一次数据
			return pageBean(mv, 1);//返回到第一页
		 }
		 
		 //修改会员信息
			@RequestMapping("updateMemberBymid.do")
			public ModelAndView updateMemberBymid(Member m) {
			
				
				ModelAndView mv=new ModelAndView("members.html");
				if(ms.updateMemberByMid(m)==1) {
					mv.addObject("msg", "修改成功!");
				}else {
					mv.addObject("msg", "修改失败!");
				}
				lm=ms.getAllMember();//重新更新一次数据
				return pageBean(mv, 1);//返回到第一页
			}
		 
		//为modelAndView封装一个pageBean
	public ModelAndView pageBean(ModelAndView m,int page) {
		
		
			
			int pages=(int)Math.ceil((double)lm.size()/2);//获取总页数
			m.addObject("pages",pages);//页面设置为1页5条数据，返回总页数

			
			//设置第一页的页面信息，返回第一页数据
			
			int lastpage=page-1;
			int nextpage=page+1;
			m.addObject("members", getMemberLimit(page,lm));
			
			//页面前后页的设置
			if(lastpage<1) {
				m.addObject("lastpage", "no page");
				
			}else {
				m.addObject("lastpage", lastpage);
			}
			if(nextpage>pages) {
				m.addObject("nextpage", "no page");
				
			}else {
				m.addObject("nextpage", nextpage);
			}
			return m;
		}
		//不通过sql分页，因为筛选后的分页很麻烦
		public List<Member> getMemberLimit(int page,List<Member> lm) {
			List<Member> members=new ArrayList<>();
			for(int i=2*(page-1);i<2*page;i++) {
				if(i<lm.size()) {
					members.add(lm.get(i));
				}
				
			}
		
			return members;
		}
	
}
