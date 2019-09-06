package com.KeepFitMS.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.KeepFitMS.dao.GoodsDao;
import com.KeepFitMS.entity.Goods;
import com.KeepFitMS.entity.Pctype;
import com.KeepFitMS.entity.Ptype;
import com.KeepFitMS.service.GoodsService;
/**
 * 商品模块的业务层
 * @author suyin
 *
 */
@Service
public class GoodsServiceImpl implements GoodsService {
	
	@Autowired
	private GoodsDao goodsDao;
	


	//查询所有父类型
	@Override
	public List<Ptype> selectAllPtype() {
		return goodsDao.selectAllPtype();
	}

	//根据父类id查子类
	@Override
	public List<Pctype> selectPctypeByPtypeId(Integer ptype_id) {
		return goodsDao.selectPctypeByPtypeId(ptype_id);
	}
	
	
	//查询商品集合
	@Override
	public List<Goods> selectGoods(String name, Integer provid, Integer cityid, Integer curr, Integer limit) {
		return goodsDao.selectGoods(name,provid,cityid,curr,limit);
	}

	//查询总条数
	@Override
	public Integer selectAllCount(String name, Integer provid, Integer cityid) {
		return goodsDao.selectAllCount(name,provid,cityid);
	}

}
