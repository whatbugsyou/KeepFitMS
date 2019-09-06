package com.KeepFitMS.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	
	@RequestMapping("/selectGoods.do")
	@ResponseBody
	//查询商品集合
	public HashMap<String,Object> getGoodsListPage(String name,Integer provid,Integer cityid,Integer curr,Integer limit){
		HashMap<String, Object> map = new HashMap<>();	
		List<Goods> list = goodsService.selectGoods(name,provid,cityid,(curr-1)*limit,limit);		
		Integer count = goodsService.selectAllCount(name,provid,cityid);
		map.put("goods", list);
		map.put("count", count);
		return map;
		
	}
}
