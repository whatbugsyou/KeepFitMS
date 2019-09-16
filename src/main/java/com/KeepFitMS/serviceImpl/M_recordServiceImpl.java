package com.KeepFitMS.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KeepFitMS.dao.m_recordDao;
import com.KeepFitMS.entity.M_record;
import com.KeepFitMS.service.M_recordService;
@Service
public class M_recordServiceImpl implements M_recordService {
	@Autowired
	private m_recordDao mrd;
	@Override
	public List<M_record> getAllM_records() {
		// TODO Auto-generated method stub
		return mrd.getAllM_records();
	}

	@Override
	public List<M_record> getM_recordsByTimeLimit(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return mrd.getM_recordsByTimeLimit(pageNo, pageSize);
	}

	@Override
	public int addRecord(M_record mr) {
		// TODO Auto-generated method stub
		return mrd.addRecord(mr);
	}

	@Override
	public int deleteRecordByMrid(int mrid) {
		// TODO Auto-generated method stub
		return mrd.deleteRecordByMrid(mrid);
	}

	@Override
	public List<M_record> shaixuanM_records(M_record mr) {
		// TODO Auto-generated method stub
		return mrd.shaixuanM_records(mr);
	}

}
