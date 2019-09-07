package com.KeepFitMS.service;

import java.util.List;

import com.KeepFitMS.entity.Wardrobe;
import com.KeepFitMS.entity.Wardroberecord;
/**
 * @衣柜service接口
 * @author 国国
 *
 */

public interface WardrobeService {
	/**
	 * @apiNote 查询所有衣柜
	 * @param 无
	 * @return 衣柜集合
	 */
	List<Wardrobe> selectAllW();
	
	/**
	 * @apiNote 查询所有衣柜出租记录
	 * @param 无
	 * @return 衣柜出租记录集合
	 */
	List<Wardroberecord> selectAllWR();
	
	/**
	 * @apiNote 租柜
	 * @param 会员卡号，会员姓名，衣柜名称，衣柜押金，租柜备注
	 * @return boolean
	 */
	boolean rentW(String cid,String mname,String wardrobe_name,String wr_deposit,String wr_desc);

	/**
	 * @apiNote 退柜
	 * @param 衣柜出租记录的id
	 * @return boolean
	 */
	boolean cancelW(String wr_id);
	
	/**
	 * @apiNote 设置衣柜故障状态与否
	 * @param 衣柜名称，衣柜状态
	 * @return boolean
	 */
	boolean setWStatus(String wardrobe_name,String wardrobe_status);

}
