package com.KeepFitMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.KeepFitMS.entity.Wardrobe;
import com.KeepFitMS.service.WardrobeService;

@RequestMapping("/wardrobe")
@RestController
public class WardrobeController {
	
	@Autowired
	private WardrobeService wardrobeService;
	
	

	@RequestMapping("/getAllW.do")
	public  List<Wardrobe> getAllW() {
		return wardrobeService.selectAllW();
	}
	

}
