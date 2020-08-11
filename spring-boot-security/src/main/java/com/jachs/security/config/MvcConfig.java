package com.jachs.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/****
 * 
 * @author zhanchaohan
 *
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/").setViewName("/log.html");
//		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
//	}
}