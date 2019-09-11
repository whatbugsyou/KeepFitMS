package com.KeepFitMS.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration
public class ResourceConfigAdapter extends WebMvcConfigurerAdapter {
	 @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//映射图片保存地址
	       registry.addResourceHandler("image/**").addResourceLocations("file:D:/image/");

	 }
}
