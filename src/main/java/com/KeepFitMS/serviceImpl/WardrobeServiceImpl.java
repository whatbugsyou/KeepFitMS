package com.KeepFitMS.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.KeepFitMS.dao.WardrobeDao;
import com.KeepFitMS.entity.Wardrobe;
import com.KeepFitMS.service.WardrobeService;
<<<<<<< HEAD
=======

>>>>>>> 9c257fe7710ae5812c2f219fbdb00d1eac6ded78
@Service
public class WardrobeServiceImpl implements WardrobeService{

	@Resource
	private WardrobeDao wardrobeDao;
	
	@Override
	public List<Wardrobe> selectAllW() {
		return wardrobeDao.selectAllW();
	}

}
