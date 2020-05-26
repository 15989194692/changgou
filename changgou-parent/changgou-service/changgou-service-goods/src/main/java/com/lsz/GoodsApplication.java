package com.lsz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.lsz.dao")
public class GoodsApplication {

    @Value("${workerId}")
    private Long workerId;

    @Value("${datacenterId}")
    private Long datacenterId;

    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class, args);
    }

    @Bean
    public IdWorker getIdWorker() {
        return new IdWorker(workerId, datacenterId);
    }

}
