package com.jachs.containertomcat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/***
 *集成三种常见模板引擎：
 *1： thymeleaf
 *2： freemarker
 *3：Jsp
 *如果想切换模板将pom里的依赖jar切换同步切换application.properties配置
 *
 *打WAR包时
 *1:pom文件中spring-boot-starter-web需排除tomcat依赖，同时添加spring-boot-starter-tomcat独立依赖
 *2:当前启动类需继承SpringBootServletInitializer，重写configure。
 * @author zhanchaohan
 *
 */
@SpringBootApplication
public class ContainerTomcatApplication extends SpringBootServletInitializer {

    public static void main ( String[] args ) {
        SpringApplication.run ( ContainerTomcatApplication.class, args );
    }

    @Override
    protected SpringApplicationBuilder configure ( SpringApplicationBuilder builder ) {
        return builder.sources ( ContainerTomcatApplication.class );
    }
}
