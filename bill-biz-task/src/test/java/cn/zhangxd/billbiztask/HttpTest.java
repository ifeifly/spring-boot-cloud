package cn.zhangxd.billbiztask;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import com.sun.media.jfxmedia.logging.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class HttpTest {
    public static void main(String[] args) throws Exception {
        LoggerContext logcontext = (LoggerContext) LoggerFactory.getILoggerFactory();
        logcontext.getLogger("org.apache").setLevel(Level.ERROR);
        logcontext.getLogger("cn.zhangxd").setLevel(Level.ERROR);
//        String url = "http://192.168.1.11:30860/hello/getInstances?serviceName=svca-service&param=";
        String url = "http://192.168.1.11:30880?id=";
        for (int i = 0; i < 1000; i++) {
            try {
                String body = HttpClientUtil.sendGet(url + i);
                System.out.println(new Date().toLocaleString()+"\t第[" + i + "]次请求,响应结果为:" + body);
                System.out.println();
                Thread.sleep(1_000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
