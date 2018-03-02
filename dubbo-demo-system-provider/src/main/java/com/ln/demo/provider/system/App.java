package com.ln.demo.provider.system;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Dubbo服务启动类
 *
 */
public class App {
    
    private final static Logger logger = LoggerFactory.getLogger(App.class);
    
    public static void main(String[] args) {
        logger.info("Start system dubbo service provider...");
        
        String confHome = System.getProperty("conf.home");
        if(StringUtils.isBlank(confHome)) {
            logger.error("请在启动命令中设置配置文件路径，设置方法：-Dconf.home=xxx");
            return;
        }
        
        if(confHome.endsWith("/") || confHome.endsWith("\\")) {
            confHome = confHome.substring(0, confHome.length() - 1);
        }
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("file:" + confHome + "/spring/applicationContext.xml");
        context.start();
        logger.info("System dubbo service provider started.");

        try {
            System.in.read(); // 按任意键退出
            context.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
    
}
