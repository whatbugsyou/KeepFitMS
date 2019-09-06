package com.KeepFitMS.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.KeepFitMS.entity.Goods;
import com.KeepFitMS.entity.Pctype;
import com.KeepFitMS.entity.Ptype;

public interface GoodsService {


	List<Ptype> selectAllPtype();

	List<Pctype> selectPctypeByPtypeId(Integer ptype_id);

	List<Goods> selectGoods(String name, Integer provid, Integer cityid, Integer curr, Integer limit);

	Integer selectAllCount(String name, Integer provid, Integer cityid);

}
