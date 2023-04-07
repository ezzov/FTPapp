package com.example.ftpapp.service;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.util.Map;

/**
 * Service that works with {@link FTPClient}
 */
public interface FtpService {

    /**
     * Method that gets all photos with given prefix in given packages
     * @return {@link Map} of {@link FTPFile} and String
     */
    Map<FTPFile, String> getPhotosWithPrefix();
}
