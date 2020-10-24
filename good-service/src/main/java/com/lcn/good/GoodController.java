package com.lcn.good;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
@EnableDistributedTransaction // 表示txlcn的客户端
public class GoodController {

    public static void main(String[] args) {
        SpringApplication.run(GoodController.class,args);
    }
}
