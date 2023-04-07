package com.example.ftpapp.controller;

import com.example.ftpapp.dto.PhotoFileDto;
import com.example.ftpapp.mapper.FtpMapper;
import com.example.ftpapp.service.FtpService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller that handles requests to server by FTP
 */
@Slf4j
@RestController
@AllArgsConstructor
public class FtpController {

    private FtpService ftpService;
    private FtpMapper ftpMapper;

    /**
     * End-point that gets all photos with given prefix in given packages
     * @return {@link List} of {@link PhotoFileDto}
     */
    @GetMapping("/photos")
    public List<PhotoFileDto> getPhotos(){
        log.info("Response with list of photo file data");
        return ftpMapper.toPhotoFileDtoList(ftpService.getPhotosWithPrefix());
    }
}
