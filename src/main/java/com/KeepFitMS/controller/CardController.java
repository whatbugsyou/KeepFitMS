package com.KeepFitMS.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.KeepFitMS.entity.Card;
import com.KeepFitMS.service.CardService;
@Controller
public class CardController {
	@Autowired
	private CardService cs;
	 @RequestMapping("deposit")
	 public ModelAndView deposit() {
		 return new ModelAndView("deposit.html");
	 }
	 //根据会员卡号查询会员卡
	 @ResponseBody
	 @RequestMapping("checkCard.do")
	 public Card checkCard(int cid) {
		 return cs.selectCardByCid(cid);
	 }
	 //根据卡ID充值
	 @RequestMapping("deposit.do")
	 public ModelAndView depositByCid(int cid,int  money) {
		 System.out.println(cid);
		 System.out.println(money);
			ModelAndView mv=new ModelAndView("deposit.html");
			if(cs.deposit(money, cid)==1) {
				mv.addObject("msg", "ok");
			}else {
				mv.addObject("msg", "no");
			}
			
			return mv;
	 }
	
	 
}
