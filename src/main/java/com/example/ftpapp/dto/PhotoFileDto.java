package com.example.ftpapp.dto;

import com.example.ftpapp.controller.FtpController;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * Data transfer object that is used in response of method {@link FtpController#getPhotos()}
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PhotoFileDto {
    private String absolutePath;
    private LocalDateTime created;
    private Long size;
}
