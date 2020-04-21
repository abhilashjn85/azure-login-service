package com.practice;

import com.practice.filter.AdalFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
@ComponentScan (basePackages = "com.practice.*")
public class Application {

	private @Autowired AutowireCapableBeanFactory beanFactory;
	private static final Logger logger = Logger.getLogger(Application.class.getName());

	@Value("${authenticated.path}")
	private String authenticatedpaths;

	public static void main(String[] args) throws Throwable {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public FilterRegistrationBean adalFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		AdalFilter adalFilter = new AdalFilter();
		beanFactory.autowireBean(adalFilter);
		registration.setFilter(adalFilter);
		for (String path : authenticatedpaths.split(",")) {
			registration.addUrlPatterns(path);
			logger.log(Level.INFO, "adding path: {0} to path", path);
		}
		registration.setOrder(1);
		return registration;
	}
}
