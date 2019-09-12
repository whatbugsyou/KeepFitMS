package com.KeepFitMS.controller;


import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KeepFitMS.entity.Goods;
import com.KeepFitMS.entity.Pctype;
import com.KeepFitMS.entity.Ptype;
import com.KeepFitMS.entity.Records;
import com.KeepFitMS.exception.PersonnelServiceException;
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
		List<Goods> list;
		Integer count;
		try {
			list = goodsService.selectGoods(name,provid,cityid,(curr-1)*limit,limit);
			count = goodsService.selectAllCount(name,provid,cityid);
			map.put("goods", list);
			map.put("count", count);
			map.put("code", "ok");
		} catch (PersonnelServiceException e) {
			map.put("code", e.getErrorCode());
			map.put("msg", e.getMessage());
		}		
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
	public HashMap<String, Object> changestatus(Integer id,Boolean status) {
		HashMap<String, Object> map = new HashMap<>();	
		 try {
			goodsService.updateGoodsStatus(id,status);
			 map.put("code", "ok");
		} catch (PersonnelServiceException e) {
			map.put("code", e.getErrorCode());
			map.put("msg", e.getMessage());
		}
		return map;
	}
	
	//修改商品信息
	@RequestMapping("/changeGoods.do")
	@ResponseBody
	public HashMap<String, Object> changeGoods(Integer goods_id,String goods_name,String goods_img,Integer goods_num,Integer goods_price,String goods_desc,Integer ptype_id,Integer pctype_id) {		
		HashMap<String, Object> map = new HashMap<>();	
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
		goods.setPtype(ptype);
		goods.setPctype(pctype);
		try {
			 goodsService.updateGoods(goods);
			 map.put("code", "ok");
		} catch (PersonnelServiceException e) {
			map.put("code", e.getErrorCode());
			map.put("msg", e.getMessage());
		}
		return map;
	}
	
	//修改商品信息
		@RequestMapping("/addGoods.do")
		@ResponseBody
		public HashMap<String, Object> addGoods(Integer goods_id,String goods_name,String goods_img,Integer goods_num,Integer goods_price,String goods_desc,Integer ptype_id,Integer pctype_id) {		
			HashMap<String, Object> map = new HashMap<>();	
			Goods goods = new Goods();
			Ptype ptype = new Ptype();
			ptype.setPtype_id(ptype_id);
			Pctype pctype = new Pctype();
			pctype.setPctype_id(pctype_id);
			pctype.setPctype_id(pctype_id);
			goods.setGoods_id(goods_id);
			goods.setGoods_name(goods_name);
			goods.setGoods_img(goods_img);
			goods.setGoods_num(goods_num);
			goods.setGoods_price(goods_price);
			goods.setGoods_desc(goods_desc);
			goods.setGoods_status(true);
			goods.setPtype(ptype);
			goods.setPctype(pctype);
			int status = 0 ;
			String msg="";
			try {
				status = goodsService.insertGoods(goods);
				if(status==1) {
					msg="添加成功";
				}else if(status==0){
					msg="商品已存在";
				}else{
					msg="出现了一个预料之外的异常";
				}
				 map.put("code", "ok");
				 map.put("msg", msg);
			} catch (PersonnelServiceException e) {
				map.put("code", e.getErrorCode());
				map.put("msg", e.getMessage());
				System.out.println("出问题了，问题是："+ e.getErrorCode()+":"+e.getMessage());
			}
			
			 return map;
		}
	
	//查询出售记录
	@RequestMapping("/findRecords.do")
	@ResponseBody
	public HashMap<String, Object> findRecords(String startRecords_time,String endRecords_time,Integer curr,Integer limit){
		HashMap<String, Object> map = new HashMap<>();	
		List<Records> list = goodsService.selectRecords(startRecords_time,endRecords_time,(curr-1)*limit,limit);		
		Integer count = goodsService.selectRecordsCount(startRecords_time,endRecords_time);
		map.put("records", list);
		map.put("count", count);
		map.put("code", "ok");
		return map;	
	}
	
	//查询出售记录数量金额统计
	@RequestMapping("/findAllRecords.do")
	@ResponseBody
	public HashMap<String, Object> findAllRecords() {
		HashMap<String, Object> map = new HashMap<>();
		Integer listCount = goodsService.selectRecordsCount(null, null);
		Integer ptypeCount1 =  goodsService.selectRcordsCountByptypeid1();
		Integer ptypeCount2 =  goodsService.selectRcordsCountByptypeid2();
		Integer ptypeMoney1 = goodsService.selectRcordsMoneyByptypeid1();
		Integer ptypeMoney2=  goodsService.selectRcordsMoneyByptypeid2();
		map.put("allCount", listCount);
		map.put("count1", ptypeCount1);
		map.put("count2", ptypeCount2);
		map.put("money1", ptypeMoney1);
		map.put("money2", ptypeMoney2);
		map.put("code", "ok");
		return map;
	}

	//根据时间查总条数和总金额
	@RequestMapping("/selectCountByTime.do")
	@ResponseBody
	public HashMap<String, Object> findCountMoney(String records_time){
		HashMap<String, Object> map = new HashMap<>();
		List<Integer> count = goodsService.selectCountByTime(records_time);
		List<Integer> money = goodsService.selectMoneyByTime(records_time);
		map.put("count", count);
		map.put("money", money);
		map.put("code", "ok");
		return map;
	}

	@RequestMapping("/use.do")
	@ResponseBody
	public HashMap<String,Object> use(String start_time,String end_time,Integer records_money, int goods_id,int cid,int goods_num){
		HashMap<String, Object> map = new HashMap<>();
		try {
			int status= goodsService.use(start_time,end_time,records_money,goods_id,cid,goods_num);
			if(status==1) {
				map.put("code", "ok");
				map.put("msg", "该卡余额不足");
			}else if(status==2){
				map.put("code", "ok");
				map.put("msg","该账号不存在");
			}else {
				map.put("code", "ok");
				map.put("msg","添加了一条出售记录");
			}
		} catch (PersonnelServiceException e) {
			map.put("code", e.getErrorCode());
			map.put("msg", e.getMessage());
		}
		return map;
		
	}
}
