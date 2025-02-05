package cn.zhangxd.svcb.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RefreshScope
@RestController
public class ServiceBController {

    @Autowired
    DiscoveryClient discoveryClient;

    @Value("${msg:unknown}")
    private String msg;

    @GetMapping(value = "/")
    public String printServiceB() {
        StringBuilder sb = new StringBuilder();
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            List<ServiceInstance> instances = discoveryClient.getInstances(service);
            for (ServiceInstance serviceInstance : instances) {
                sb.append(serviceInstance.getServiceId() + " (" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + ")" + "===>[][i am v5][i am v5][i am v5][]Say " + msg);
            }
        }
        log.info("[printServiceB] tid:{} segmentId:{} spanId:{}", TraceContext.traceId(), TraceContext.segmentId(), TraceContext.spanId());
        return "i am svcb-service" + sb.toString();
    }
}