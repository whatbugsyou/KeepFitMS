package com.KeepFitMS.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.KeepFitMS.dao.WardrobeDao;
import com.KeepFitMS.entity.Wardrobe;
import com.KeepFitMS.entity.Wardroberecord;
import com.KeepFitMS.exception.WardrobeServiceException;
import com.KeepFitMS.service.WardrobeService;

@Service
public class WardrobeServiceImpl implements WardrobeService{

	@Resource
	private WardrobeDao wardrobeDao;
	
	@Resource
	private RedisTemplate redisTemplate;
	
	@Override
	public List<Wardrobe> selectAllW() {
		//从redis中获取数据
		List<Wardrobe> wList = (List<Wardrobe>) redisTemplate.boundValueOps("wList").get();
		//判断redis中是否存在数据
		if(wList == null) {//redis中不存在，从数据库中获取
			wList = wardrobeDao.selectAllW();
			//将结果存储到redis中
			redisTemplate.boundValueOps("wList").set(wList);
			//System.out.println("数据库获得的");
		}//else则是从redis获得的
		return wList;
	}

	@Override
	public List<Wardroberecord> selectAllWR() {
		//从redis中获取数据
		List<Wardroberecord> wRList = (List<Wardroberecord>) redisTemplate.boundValueOps("wRList").get();
		//判断redis中是否存在数据
		if(wRList == null) {//redis中不存在，从数据库中获取
			wRList = wardrobeDao.selectAllWR();
			//将结果存储到redis中
			redisTemplate.boundValueOps("wRList").set(wRList);
		}//else则是从redis获得的
		return wRList;
	}

	@Override
	@Transactional
	public synchronized boolean rentW(String cid, String mname, String wardrobe_name, String wr_deposit, String wr_desc) throws WardrobeServiceException {
		//先判断是否已租,抛出异常
		if(wardrobeDao.selectWStatusByName(wardrobe_name) == 1) {
			throw new WardrobeServiceException("这个衣柜已经被人抢先租走了！");
		}
		//修改衣柜状态
		Integer wardrobe_status = 1;
		wardrobeDao.updateWByWname(wardrobe_status, wardrobe_name);
		Wardroberecord wardroberecord = new Wardroberecord();
		Date currentDate = new Date();
		SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String wr_regtime = timeFormat.format(currentDate);
		String wr_starttime = timeFormat.format(currentDate);
		wardroberecord.setCid(Integer.parseInt(cid));
		wardroberecord.setMname(mname);
		wardroberecord.setWardrobe_name(wardrobe_name);
		wardroberecord.setWr_status("租用中");
		wardroberecord.setWr_regtime(wr_regtime);
		wardroberecord.setWr_starttime(wr_starttime);
		wardroberecord.setWr_deposit(Integer.parseInt(wr_deposit));
		wardroberecord.setWr_desc(wr_desc);
		//添加衣柜出租记录
		wardrobeDao.insertWR(wardroberecord);
		//写入之后清空redis的缓存，达到数据同步
		redisTemplate.delete("wList");
		redisTemplate.delete("wRList");
		return true;
	}

	@Override
	@Transactional
	public boolean cancelW(String wr_id) {
		Wardroberecord wardroberecord = wardrobeDao.selectWRById(Integer.parseInt(wr_id));
		String wardrobe_name = wardroberecord.getWardrobe_name();
		Integer wardrobe_status = 0;
		//修改衣柜状态
		wardrobeDao.updateWByWname(wardrobe_status, wardrobe_name);
		Date currentDate = new Date();
		SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String wr_endtime = timeFormat.format(currentDate);
		wardroberecord.setWr_status("已完成");
		wardroberecord.setWr_endtime(wr_endtime);
		//更新衣柜出租记录
		wardrobeDao.updateWR(wardroberecord);
		//写入之后清空redis的缓存，达到数据同步
		redisTemplate.delete("wList");
		redisTemplate.delete("wRList");
		return true;
	}

	@Override
	public boolean setWStatus(String wardrobe_name, String wardrobe_status) {
		Integer w_status = Integer.parseInt(wardrobe_status);
		wardrobeDao.updateWByWname(w_status, wardrobe_name);
		//写入之后清空redis的缓存，达到数据同步
		redisTemplate.delete("wList");
		return true;
	}

}
