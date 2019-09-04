package com.KeepFitMS;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

@SpringBootApplication
public class KeepFitMsApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(KeepFitMsApplication.class);
		YamlPropertiesFactoryBean yamlProperties = new YamlPropertiesFactoryBean();
<<<<<<< HEAD
		ClassPathResource classPathResource = new ClassPathResource("applicationConfig/application.yml");
=======
		ClassPathResource classPathResource = new ClassPathResource("/applicationConfig/application.yml");
>>>>>>> 9c257fe7710ae5812c2f219fbdb00d1eac6ded78
		yamlProperties.setResources(classPathResource);
		springApplication.setDefaultProperties(yamlProperties.getObject());
		springApplication.run(args);
	}

}
