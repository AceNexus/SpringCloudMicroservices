package org.ecommerce.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer // EurekaServer 服務啟動類，接受其他服務註冊
public class EureKaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EureKaServiceApplication.class, args);
    }

}