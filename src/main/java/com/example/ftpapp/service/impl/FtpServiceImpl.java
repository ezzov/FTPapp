package com.example.ftpapp.service.impl;

import com.example.ftpapp.service.FtpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of {@link FtpService}
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FtpServiceImpl implements FtpService {

    private final FTPClient client;

    private String prefix = "GRP327_";
    private String directory = "фотографии";

    /**
     * {@inheritDoc}
     * Uses method {@link FtpServiceImpl#findFiles}
     * and closes FTP connection.
     * @return {@link Map} of {@link FTPFile} and String
     */
    @Override
    public Map<FTPFile, String> getPhotosWithPrefix() {
            try {
                log.info("Return map of FTPFile");
                return findFiles(client);
            } catch (IOException e) {
                throw new RuntimeException("Connection problem");
            } finally {
                try {
                    client.logout();
                    client.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }

    /**
     * Recursive method that finds files with given prefix in given directory
     * @param ftpClient {@link FTPClient}
     * @return {@link Map} of {@link FTPFile} and String
     * @throws IOException
     */
    public Map<FTPFile, String> findFiles(FTPClient ftpClient) throws IOException {
        Map<FTPFile, String> result = new HashMap<>();
        FTPFile[] ftpFiles = ftpClient.listFiles();
        for (FTPFile ftpFile : ftpFiles) {
            if (ftpFile.getName().startsWith(".")) {
                continue;
            }
            if (ftpFile.isDirectory()) {
                ftpClient.changeWorkingDirectory(ftpFile.getName());
                result.putAll(findFiles(ftpClient));
                ftpClient.changeToParentDirectory();
            } else if (ftpFile.isFile() && checkPath(ftpClient) && ftpFile.getName().startsWith(prefix)) {
                log.info("Add file to map: {}", ftpFile.getName());
                result.put(ftpFile, ftpClient.printWorkingDirectory());
            }
        }
        return result;
    }

    /**
     * Method that returns true if there is given name of package in absolute path
     * @param ftpClient {@link FTPClient}
     * @return boolean
     * @throws IOException
     */
    public boolean checkPath(FTPClient ftpClient) throws IOException {
        String path = ftpClient.printWorkingDirectory();
        return Arrays.stream(path.split("/")).anyMatch(s -> s.equalsIgnoreCase(directory));
    }

}
