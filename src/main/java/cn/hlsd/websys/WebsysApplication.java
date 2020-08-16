package cn.hlsd.websys;

import cn.hlsd.websys.config.security.JwtAuthenticationTokenFilter;
import cn.hlsd.websys.util.SpringContextUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("cn.hlsd.websys.mapper")
public class WebsysApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(WebsysApplication.class, args);
        SpringContextUtil.setApplicationContext(context);

    }

    @Bean
    public FilterRegistrationBean registration(JwtAuthenticationTokenFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setEnabled(false);
        return registration;
    }
}
