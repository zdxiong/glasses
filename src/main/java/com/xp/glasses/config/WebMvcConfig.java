package com.xp.glasses.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Mrxiong
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {


    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        return  new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("index");
//                registry.addViewController("/shop/myShop").setViewName("shop");
//                registry.addViewController("order/myOrder").setViewName("order");
//                registry.addViewController("user/myConsumer").setViewName("user");
//                registry.addViewController("/banner/weChatBanner").setViewName("banner");
            }
        };
    }
}
