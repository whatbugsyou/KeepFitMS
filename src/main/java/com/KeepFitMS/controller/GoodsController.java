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
	
	//修改状态
	@RequestMapping("/changeStatus.do")
	@ResponseBody
	public void changestatus(Integer id,Boolean status) {
		System.out.println(id+":状态："+status);
		 goodsService.updateGoodsStatus(id,status);
	}
	
	//修改商品信息
	@RequestMapping("/changeGoods.do")
	@ResponseBody
	public boolean changeGoods(Integer goods_id,String goods_name,String goods_img,Integer goods_num,Integer goods_price,String goods_desc,Integer ptype_id,Integer pctype_id,Boolean goods_status) {		
		Goods goods = new Goods();
		Ptype ptype = new Ptype();
		ptype.setPtype_id(ptype_id);
		Pctype pctype = new Pctype();
		pctype.setPctype_id(pctype_id);
		goods.setGoods_id(goods_id);
		goods.setGoods_name(goods_name);
		goods.setGoods_img(goods_img);
		goods.setGoods_num(goods_num);
		goods.setGoods_price(goods_price);
		goods.setGoods_desc(goods_desc);
		goods.setGoods_status(goods_status);
		goods.setPtype(ptype);
		goods.setPctype(pctype);
		return goodsService.updateGoods(goods);
	}
	
	//修改商品信息
		@RequestMapping("/addGoods.do")
		@ResponseBody
		public boolean addGoods(Integer goods_id,String goods_name,String goods_img,Integer goods_num,Integer goods_price,String goods_desc,Integer ptype_id,Integer pctype_id,Boolean goods_status) {		
			Goods goods = new Goods();
			Ptype ptype = new Ptype();
			ptype.setPtype_id(ptype_id);
			Pctype pctype = new Pctype();
			pctype.setPctype_id(pctype_id);
			goods.setGoods_id(goods_id);
			goods.setGoods_name(goods_name);
			goods.setGoods_img(goods_img);
			goods.setGoods_num(goods_num);
			goods.setGoods_price(goods_price);
			goods.setGoods_desc(goods_desc);
			goods.setGoods_status(goods_status);
			goods.setPtype(ptype);
			goods.setPctype(pctype);
			return goodsService.insertGoods(goods);
		}

}
