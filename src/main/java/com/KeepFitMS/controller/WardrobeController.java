package com.KeepFitMS.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.KeepFitMS.entity.Member;
import com.KeepFitMS.entity.Wardrobe;
import com.KeepFitMS.entity.Wardroberecord;
import com.KeepFitMS.service.MemberService;
import com.KeepFitMS.service.WardrobeService;

@RequestMapping("/wardrobe")
@RestController
public class WardrobeController {
	
	@Resource
	private WardrobeService wardrobeService;
	
	@Resource
	private MemberService memberService;
	
	@RequestMapping("/getAllW.do")
	public  List<Wardrobe> getAllW() {
		return wardrobeService.selectAllW();
	}
	
	@RequestMapping("/getAllM.do")
	public  List<Member> getAllM() {
		return memberService.getAllMember();
	}
	
	@RequestMapping("/getAllWR.do")
	public  List<Wardroberecord> getAllWR() {
		return wardrobeService.selectAllWR();
	}
	
	
	@RequestMapping("/rentW.do")
	public  String rentW(String cid,String mname,String wardrobe_name,String wr_deposit,String wr_desc) {
		boolean flag = wardrobeService.rentW(cid,mname,wardrobe_name,wr_deposit,wr_desc);
		if(flag) {
			return "success";
		} else {
			return "error";
		}
	}
	
	@RequestMapping("/cancelW.do")
	public  String cancelW(String wr_id) {
		boolean flag = wardrobeService.cancelW(wr_id);
		if(flag) {
			return "success";
		} else {
			return "error";
		}
	}
	
	
	@RequestMapping("/setWStatus.do")
	public  String setWStatus(String wardrobe_name,String wardrobe_status) {
		boolean flag = wardrobeService.setWStatus(wardrobe_name,wardrobe_status);
		if(flag) {
			return "success";
		} else {
			return "error";
		}
	}

}
