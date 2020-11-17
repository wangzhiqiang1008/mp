package com.example.mp.config;


import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@MapperScan("com.example.mp.dao")
public class MpConfig {

    @Bean
    public OptimisticLockerInterceptor optimisticLockerInnerInterceptor(){
        return new OptimisticLockerInterceptor();
    }

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

    @Bean
    public ISqlInjector sqlInjector(){
        return  new LogicSqlInjector();
    }

    //开启SQL性能分析插件
    @Bean
    @Profile({"dev","test"})    //设置dev和test环境开启，生产不开启
    public PerformanceInterceptor performanceInterceptor(){
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        performanceInterceptor.setMaxTime(500); //设置sql执行的最大时间，单位为毫秒。超过最大时间则为慢sql
        performanceInterceptor.setFormat(true); //是否格式化代码
        return performanceInterceptor;
    }
}
