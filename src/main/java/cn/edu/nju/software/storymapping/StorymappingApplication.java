package cn.edu.nju.software.storymapping;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"cn.edu.nju.software.storymapping.system.dao", "cn.edu.nju.software.storymapping.map.dao"})
public class StorymappingApplication {

    public static void main(String[] args) {
        SpringApplication.run(StorymappingApplication.class, args);
    }
}