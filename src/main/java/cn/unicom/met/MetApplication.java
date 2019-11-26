package cn.unicom.met;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("cn.unicom.met.mapper")
@SpringBootApplication
public class MetApplication {

    public static void main(String[] args) {
        SpringApplication.run(MetApplication.class, args);
    }

}
