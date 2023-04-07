package com.example.ftpapp.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * FTP configuration to connect server
 */
@Configuration
public class FtpConfig {
    @Value("${ftp.host}")
    private String HOST;
    @Value("${ftp.port}")
    private Integer PORT;
    @Value("${ftp.username}")
    private String USERNAME;
    @Value("${ftp.password}")
    private String PASSWORD;

    /**
     * Method that connects to server
     * @return {@link FTPClient}
     * @throws IOException
     */
    @Bean
    public FTPClient ftpConnection() throws IOException {
        FTPClient ftpClient = new FTPClient();
        ftpClient.setControlEncoding("utf-8");
        ftpClient.connect(HOST, PORT);
        ftpClient.enterLocalPassiveMode();
        ftpClient.login(USERNAME, PASSWORD);
        return ftpClient;
    }
}
