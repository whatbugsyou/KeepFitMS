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
	
	//查询所有商品
	@Override
	public List<Goods> selectAllGoods() {
		return goodsDao.selectAllGoods();
	}

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
	
	//根据关键字传值
	@Override
	public List<Goods> selectGoodsByKeys(String name, Integer provid, Integer cityid) {
		//先判断name是否为空
		if(name==""||name==null) {
			//如果商品名称是空
			if(provid==0) {
				//再判断父类型名是不是0，查询全部商品信息集合
				return goodsDao.selectAllGoods();
			}else{
				if(cityid==0) {
					//如果子类型也是0，name得到商品名称为空，子类型为0所以按照父类型id查询商品信息
					return goodsDao.selectAllGoodsByPtypeId(provid);
				}else{
					//如果子类不是0,得到子类型id和父类型id,则按照父类型和子类型查询
					return goodsDao.selectAllGoodsByPtypeIdAndPctype(provid,cityid);
				}	
			}
		}else {
			//如果商品名称不是空，默认按照商品名称查询
			return goodsDao.selectAllGoodsByName(name);
		}
		
	}

}
