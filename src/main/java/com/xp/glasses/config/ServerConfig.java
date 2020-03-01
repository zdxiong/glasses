package com.xp.glasses.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Mrxiong
 */
@Component
public class ServerConfig implements ApplicationListener<WebServerInitializedEvent>{


    private Logger logger = LoggerFactory.getLogger(getClass());


    @Value("${file-path}")
    public String filePath;

    @Value("${server.port}")
    public int serverPort;

    @Value("${domain.name}")
    public String domainName;


    private String getHttpsPrefix() {
        return "https://" + domainName + ":" + serverPort;
    }


    public String imageRealUlr(String imageName){

        if (StringUtils.isEmpty(imageName)){
            return null;
        }
        return getHttpsPrefix()+"/"+imageName;
    }

    public boolean createDirs() {
        File file = new File(filePath);
        if (!file.exists()) {
            if (file.mkdirs()) {
                file.setWritable(true);
                file.setReadable(true);
                return true;
            }
            return false;
        }
        return true;
    }

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        serverPort = event.getWebServer().getPort();
        boolean dirs = createDirs();
        logger.info("图片文件目录创建是否成功：{}", dirs);
    }
}
