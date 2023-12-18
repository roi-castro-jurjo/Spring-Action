package gal.usc.grei.cn.precios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringActionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringActionApplication.class, args);
    }

}
