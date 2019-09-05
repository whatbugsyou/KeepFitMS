package com.KeepFitMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KeepFitMS.entity.Goods;
import com.KeepFitMS.entity.Pctype;
import com.KeepFitMS.entity.Ptype;
import com.KeepFitMS.service.GoodsService;

/**
 * 商品模块的控制层
 * @author suyin
 *
 */
@Controller
public class GoodsController {
	@Autowired
	private GoodsService goodsService;
	
	//查询所有商品以及它的父类型子类型属性
	@RequestMapping("/selectAllGoods.do")
	 @ResponseBody 
	public List<Goods> getAllGoods(){	  
		 return goodsService.selectAllGoods();
	}
	
	//查询父类型
	@RequestMapping("/selectAllType.do")
	@ResponseBody
	public List<Ptype> getPtype(){
		 return goodsService.selectAllPtype();
	}
	
	//根据父类型id查子类型
	@RequestMapping("/selectPctypeByPtypeId.do")
	@ResponseBody
	public List<Pctype> getPctypeByPtypeId(Integer  ptype_id){
		return goodsService.selectPctypeByPtypeId(ptype_id);
	}
	
	//根据key值查子类
	@RequestMapping("/selectGoodsByKeys.do")
	@ResponseBody
	public List<Goods> getGoodsByKeys(String name,Integer provid,Integer cityid){
		return goodsService.selectGoodsByKeys(name,provid,cityid);	
	}
}
