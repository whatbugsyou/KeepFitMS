package com.KeepFitMS;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement //开启事务管理
public class KeepFitMsApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(KeepFitMsApplication.class);
		YamlPropertiesFactoryBean yamlProperties = new YamlPropertiesFactoryBean();
		ClassPathResource classPathResource = new ClassPathResource("/applicationConfig/application.yml");
		yamlProperties.setResources(classPathResource);
		springApplication.setDefaultProperties(yamlProperties.getObject());
		springApplication.run(args);
	}

}
