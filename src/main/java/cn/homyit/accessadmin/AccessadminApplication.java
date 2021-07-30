package cn.homyit.accessadmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.homyit.accessadmin.mapper")
public class AccessadminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccessadminApplication.class, args);
    }

}
