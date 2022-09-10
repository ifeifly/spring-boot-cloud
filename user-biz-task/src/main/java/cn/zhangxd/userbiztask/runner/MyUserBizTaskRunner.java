package cn.zhangxd.userbiztask.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author puhaiyang
 * @date 2021/3/11 21:50
 * MyUserBizTaskRunner
 */
@Component
public class MyUserBizTaskRunner implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${biz.process.seconds:10}")
    private Integer processTimeSeconds;

    @Override
    public void run(String... args) throws Exception {
        logger.error("MyUserBizTaskRunner start! thread:{}", Thread.currentThread().toString());
        for (int i = 1; i <= processTimeSeconds; i++) {
            System.out.println("biz run." + i);
            Thread.sleep(1_000);
        }
        logger.error("MyUserBizTaskRunner end! thread:{}", Thread.currentThread().toString());
    }
}