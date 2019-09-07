package com.KeepFitMS.service;

import java.util.List;

import com.KeepFitMS.entity.Wardrobe;
import com.KeepFitMS.entity.Wardroberecord;


public interface WardrobeService {
	
	List<Wardrobe> selectAllW();
	
	List<Wardroberecord> selectAllWR();
	
	boolean rentW(String cid,String mname,String wardrobe_name,String wr_deposit,String wr_desc);

	boolean cancelW(String wr_id);
	
	boolean setWStatus(String wardrobe_name,String wardrobe_status);

}
