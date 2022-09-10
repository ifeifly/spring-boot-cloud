package cn.zhangxd.userbiztask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;

@EnableTask
@SpringBootApplication
public class UserBizTaskApplication {


    public static void main(String[] args) {
        SpringApplication.run(UserBizTaskApplication.class, args);
    }


}
