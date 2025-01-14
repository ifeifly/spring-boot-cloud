package cn.zhangxd.svca.controller;

import cn.zhangxd.svca.client.ServiceBClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.Tag;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Slf4j
@RefreshScope
@RestController
public class ServiceAController {

    @Value("${name:unknown}")
    private String name;

    @Autowired
    DiscoveryClient discoveryClient;
    @Resource
    private ServiceBClient serviceBClient;


    @GetMapping(value = "getProp")
    public String getProp() {
        Properties properties = System.getProperties();
        StringBuilder stringBuilder = new StringBuilder();
        Enumeration<?> enumeration = properties.propertyNames();
        while (enumeration.hasMoreElements()) {
            Object o = enumeration.nextElement();
            String line = "key:" + enumeration.nextElement() + " value:" + properties.get(o);
            line = line + "\n";
            System.out.println(line);
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }

    @GetMapping(value = "getEnv")
    public String getEnv() {
        Map<String, String> getenv = System.getenv();
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : getenv.keySet()) {
            String line = "key:" + s + " value:" + getenv.get(s);
            line = line + "\n";
            System.out.println(line);
            stringBuilder.append(line);
        }
        log.info("[getEnv] tid:{} segmentId:{} spanId:{}", TraceContext.traceId(), TraceContext.segmentId(), TraceContext.spanId());
        log.info("[getEnv] info:{}", stringBuilder.toString());
        return stringBuilder.toString();
    }


    @GetMapping(value = "getInstances")
    public String getInstances(String serviceName) {
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
        StringBuilder sb = new StringBuilder();
        for (ServiceInstance instance : instances) {
            sb.append("uri:" + instance.getUri() + " host:" + instance.getHost() + " port:" + instance.getPort());
        }
        log.info("[getInstances] info:{}", sb.toString());
        return "result,instances:" + instances.toString() + " list:" + sb.toString();
    }

    @Trace
    @GetMapping(value = "/")
    public String printServiceA() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            List<ServiceInstance> instances = discoveryClient.getInstances(service);
            System.out.println("------------" + service + "--------------");
            for (ServiceInstance instance : instances) {
                log.info("[printServiceA] serviceName:{} instances:{} port:{}", service, instance.getHost(), instance.getPort());
            }
        }
        log.info("[printServiceA] tid:{} segmentId:{} spanId:{}", TraceContext.traceId(), TraceContext.segmentId(), TraceContext.spanId());
        return "result:" + serviceBClient.printServiceB();
    }

    @Trace
    @Tag(key = "mytag1", value = "arg[0]")
    @Tag(key = "result", value = "returnedObj")
    @GetMapping(path = "/test")
    public String test(String param) {
        return "param" + param;
    }

    @GetMapping(path = "/current")
    public Principal getCurrentAccount(Principal principal) {
        return principal;
    }
}