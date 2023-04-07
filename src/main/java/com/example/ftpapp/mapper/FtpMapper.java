package com.example.ftpapp.mapper;

import com.example.ftpapp.dto.PhotoFileDto;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Mapper that converts to data transfer object
 */
@Component
public class FtpMapper {

    /**
     * Method that converts FTP file and its directory to photo file dto
     * @param ftpFile {@link FTPFile}
     * @param directory FTPFile directory
     * @return {@link PhotoFileDto}
     */
    PhotoFileDto toPhotoFileDto(FTPFile ftpFile, String directory) {
        if (ftpFile == null) {
            return null;
        }
        return PhotoFileDto.builder()
                .absolutePath(directory + "/" + ftpFile.getName())
                .created(LocalDateTime.ofInstant(ftpFile.getTimestamp().toInstant(), ftpFile.getTimestamp().getTimeZone().toZoneId()))
                .size(ftpFile.getSize())
                .build();
    }

    /**
     * Method that converts map of FTP file and its directory to ist of photo file dto
     * @param ftpFiles {@link Map} of {@link FTPFile} and String
     * @return {@link List} of {@link PhotoFileDto}
     */
    public List<PhotoFileDto> toPhotoFileDtoList(Map<FTPFile, String> ftpFiles) {
        List<PhotoFileDto> dtoList = new ArrayList<>();
        for (Map.Entry<FTPFile, String> entry: ftpFiles.entrySet()) {
            dtoList.add(toPhotoFileDto(entry.getKey(), entry.getValue()));
        }
        return dtoList;
    }
}
