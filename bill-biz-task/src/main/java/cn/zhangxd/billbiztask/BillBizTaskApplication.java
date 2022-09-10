package cn.zhangxd.billbiztask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;

/**
 * @author puhaiyang
 * @date 2021/3/13 10:11
 * BillBizTaskApplication
 */
@EnableTask
@SpringBootApplication
public class BillBizTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillBizTaskApplication.class, args);
    }

}
