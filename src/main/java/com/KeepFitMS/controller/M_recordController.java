package com.KeepFitMS.controller;
/*
 * 会员记录控制层
 * */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//会员来访记录控制层
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

import com.KeepFitMS.entity.M_record;
import com.KeepFitMS.entity.Member;
import com.KeepFitMS.service.M_recordService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Controller
public class M_recordController {
	@Autowired
	private M_recordService mrs;
	//会员记录集合
	private List<M_record> records;
	
	
	//添加记录
	@RequestMapping("addM_record.do")
	public ModelAndView addM_record(M_record mr) {
		ModelAndView mv=new ModelAndView("m_record.html");
		
		if(mrs.addRecord(mr)==1) {
			mv.addObject("msg", "ok");
		}else {
			mv.addObject("msg", "no");
		}
		return mv;
	}

	//获取总页面数，第一页记录

			@RequestMapping("getAllRecords.do")
			public ModelAndView test() {
				ModelAndView mv=new ModelAndView("m_records.html");
				records=mrs.getAllM_records();//获取所有记录
				
				return pageBean(mv, 1);
				
			
			}
			//分页展示记录
			@RequestMapping("getRecordsByPage.do")
			
			public ModelAndView getMemberByPage(int page){
				ModelAndView mv=new ModelAndView("m_records.html");	
				System.out.println();
				return pageBean(mv, page);
				
			}
		
			//根据条件筛选
			@RequestMapping("shaixuanRecords.do")
			public ModelAndView shaixuanMember(String mid,String mname,String edate,String sdate) {
				ModelAndView mv=new ModelAndView("m_records.html");
				M_record mr=new M_record();
				
				//封装筛选的bean对象
				if(mid=="") {
					mr.setMid(-1);
				}else {
					mr.setMid(Integer.parseInt(mid));
				}
				mr.setEdate(edate);
				mr.setSdate(sdate);
				Member m= new Member();
				m.setMname(mname);
				mr.setMember(m);
				if(mrs.shaixuanM_records(mr).size()>0) {
					records=mrs.shaixuanM_records(mr);//筛选后更新页面数据
					return pageBean(mv, 1);//返回第一页的数据
				}else {
					mv.addObject("msg", "未查询到数据!");//未查询到数据，不更新数据返回提示信息
					return pageBean(mv, 1);//返回第一页的数据
				}
				
				
				
				
			}
			//删除一条记录
		
			
		@RequestMapping("deleteM_recordBymr_id.do")
		public ModelAndView deleteRecord(int mrid) {
			ModelAndView m=new ModelAndView("m_records.html");
			if(mrs.deleteRecordByMrid(mrid)==1) {
				m.addObject("msg", "删除成功!");
			}else {
				m.addObject("msg","删除失败!");
				
			}
			records=mrs.getAllM_records();//重新更新一次数据
			return pageBean(m, 1);
		}
			//为modelAndView封装一个pageBean
		public ModelAndView pageBean(ModelAndView m,int page) {
			
			
				
				int pages=(int)Math.ceil((double)records.size()/2);//获取当前records总页数
				m.addObject("pages",pages);//页面设置为1页5条数据，返回总页数

				
				//设置第一页的页面信息，返回第一页数据
				
				int lastpage=page-1;
				int nextpage=page+1;
				m.addObject("records", getRecordsLimit(page,records));//获取当前页面的记录集合
				
				//页面前后页的设置
				if(lastpage<1) {
					m.addObject("lastpage", "no page");
					
				}else {
					m.addObject("lastpage", lastpage);
				}
				if(nextpage>pages) {
					m.addObject("nextpage", "no page");
					
				}else {
					m.addObject("nextpage", nextpage);
				}
				return m;
			}
		
			//不通过sql分页，因为筛选后的分页很麻烦
			public List<M_record> getRecordsLimit(int page,List<M_record> records) {
				List<M_record> beans=new ArrayList<>();
				for(int i=2*(page-1);i<2*page;i++) {
					if(i<records.size()) {
						beans.add(records.get(i));
					}
					
				}
			
				return beans;
			}
	
}
