package cn.zhangxd.svca.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
//import org.springframework.integration.leader.event.OnGrantedEvent;
//import org.springframework.integration.leader.event.OnRevokedEvent;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class LeaderElectionEventListener implements ApplicationListener {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        String localIp = getLocalIp();
//        if (applicationEvent instanceof OnGrantedEvent) {
//            OnGrantedEvent onGrantedEvent = (OnGrantedEvent) applicationEvent;
//            logger.error("[onApplicationEvent] onGrantedEvent:{} source:{} role:{} ip:{}", onGrantedEvent.toString(), onGrantedEvent.getSource(), onGrantedEvent.getRole(), localIp);
//        } else if (applicationEvent instanceof OnRevokedEvent) {
//            OnRevokedEvent onRevokedEvent = (OnRevokedEvent) applicationEvent;
//            logger.error("[onApplicationEvent] onRevokedEvent:{} source:{} role:{} ip:{}", onRevokedEvent.toString(), onRevokedEvent.getSource(), onRevokedEvent.getRole(), localIp);
//        } else {
//            logger.error("[onApplicationEvent] otherEvent:{} source:{} timestamp:{} ip:{}", applicationEvent.toString(), applicationEvent.getSource(), applicationEvent.getTimestamp(), localIp);
//        }
    }

    private String getLocalIp() {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            return addr.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "unkown ip";
        }
    }

}
