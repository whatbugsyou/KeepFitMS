package com.KeepFitMS.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KeepFitMS.dao.CardDao;
import com.KeepFitMS.dao.GoodsDao;
import com.KeepFitMS.entity.Card;
import com.KeepFitMS.entity.Goods;
import com.KeepFitMS.entity.Pctype;
import com.KeepFitMS.entity.Ptype;
import com.KeepFitMS.entity.Records;
import com.KeepFitMS.exception.PersonnelServiceException;
import com.KeepFitMS.service.GoodsService;
import com.KeepFitMS.util.getUuidUtil;
/**
 * 商品模块的业务层
 * @author suyin
 *
 */
@Service
public class GoodsServiceImpl implements GoodsService {
	
	@Autowired
	private GoodsDao goodsDao;
	
	@Autowired
	private CardDao cd;

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

	//查询商品总条数
	@Override
	public Integer selectAllCount(String name, Integer provid, Integer cityid) {
		return goodsDao.selectAllCount(name,provid,cityid);
	}

	//修改状态
	@Override
	public int updateGoodsStatus(Integer id, Boolean status) {
		if(status==true){
			status=false;
			return	goodsDao.updateGoodsStatus(id,status);
		}else {
			status=true;
			return	goodsDao.updateGoodsStatus(id,status);
		}
		 
	}

	//修改商品信息
	@Override
	public int updateGoods(Goods goods) {
		return goodsDao.updateGoods(goods);
	}
	//添加商品
	@Override
	public int insertGoods(Goods goods) {
		//首先判断商品是否存在，存在则增加加数量
		List<Goods> list = goodsDao.selecGoodsByName(goods.getGoods_name());
		if(list==null) {
			return 0;
		}else{
			goodsDao.insertGoods(goods);
		    return 1;
		}	
	}

	//查询出售记录
	@Override
	public List<Records> selectRecords(String start_time, String end_time, Integer curr, Integer limit) {
		
		return goodsDao.selectRecords( start_time,  end_time,  curr,  limit);
	}
	
	//查询出售记录条数
	@Override
	public Integer selectRecordsCount(String start_time, String end_time) {
		return goodsDao.selectRecordsCount( start_time,  end_time);
	}

	//根据类型id=1查询出售记录条数
	@Override
	public Integer selectRcordsCountByptypeid1() {
		//根据父类型id=1查出商品id集合
		List<Integer> goods_id= goodsDao.selectGoodsByPtypeid(1);
		List<Integer>  ptypeCount1;
		int pcount1 = 0;
		for (int id : goods_id) {
			if(id!=0) {
			 ptypeCount1 = goodsDao.selectRecordsCountBygoodsid(id);	
				 for (int  count1: ptypeCount1) {
					 pcount1+=count1;
				}
			}
			
		}
		return  pcount1;
	}
	//根据类型id=2查询出售记录条数
	@Override
	public Integer selectRcordsCountByptypeid2() {
		List<Integer> goods_id= goodsDao.selectGoodsByPtypeid(2);
		List<Integer> ptypeCount2;
		int pcount2 = 0;
		for (int id : goods_id) {
			
			 if(id!=0) {
				 ptypeCount2 = goodsDao.selectRecordsCountBygoodsid(id);
				 for (int  count2: ptypeCount2) {
					 pcount2+=count2;
				}
				
				}
			 
		}
		return pcount2;
	}
	//根据类型id=1查询金额数
	@Override
	public Integer selectRcordsMoneyByptypeid1() {
		List<Integer> goods_id= goodsDao.selectGoodsByPtypeid(1);
		List<Integer> ptypeMoney1;
		int pmoney1=0;
		for (int id : goods_id) {
			if(id!=0) {
				ptypeMoney1 = goodsDao.selectRecordsMoneyBygoodsid(id);				 
				 for (int  money1: ptypeMoney1) {
					 pmoney1+=money1;
				}			
			}			 
		}
		return pmoney1;
	}
	
	//根据类型id=2查询金额数
	@Override
	public Integer selectRcordsMoneyByptypeid2() {
		List<Integer> goods_id= goodsDao.selectGoodsByPtypeid(2);
		List<Integer> ptypeMoney2;
		int pmoney2=0;
		for (int id : goods_id) {
			if(id!=0) {
				ptypeMoney2 = goodsDao.selectRecordsMoneyBygoodsid(id);
				 for (int  money2: ptypeMoney2) {
					 pmoney2+=money2;
				}				
			}			
		}
		return pmoney2;
	}

	@Override
	public List<Integer> selectCountByTime(String records_time) {		
		return goodsDao.selectCountByTime(records_time);
	}

	@Override
	public List<Integer> selectMoneyByTime(String records_time) {		
		return goodsDao.selectMoneyByTime(records_time);
	}

	//使用：根据cid查出余额，对比余额扣费，同时添加一条记录
	@Override
	public int use(String start_time, String end_time, Integer records_money, int goods_id,int cid,int goods_num) {	
		//首先判断卡号是否存在
		Card card = cd.selectCardByCid(cid);
		if(card!=null) {
			//根据cid查余额
			int cardMoney = cd.getCardMoney(cid);
			//如果卡内余额小于金额，返回余额不足
			if(cardMoney<records_money) {
				return 1;
			}else {
			//如果金额足够，扣费，同时添加一条记录
				cd.deposit(-records_money,cid);
			//添加记录
				//随机生成一个uuid
				String uuid_8 = getUuidUtil.getUUID_8();		
				goodsDao.insertRecords(uuid_8,start_time,end_time,records_money,goods_id,cid);
				//数量减少
				Goods goods = new Goods();
				goods.setGoods_num(-goods_num);
				goods.setGoods_id(goods_id);
				goodsDao.updateGodosNum(goods);
				return 0;
			}
		}else {
			return 2;
		}

	}

}
