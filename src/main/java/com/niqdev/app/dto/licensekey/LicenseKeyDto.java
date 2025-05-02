package com.niqdev.app.dto.licensekey;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 回傳 License Key 詳細資訊
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LicenseKeyDto {
    private String licenseKey;         
    private String licenseName;
    private LocalDateTime expirationTime;
    private Integer maxDevices;
    private Boolean active;            // 是否啟用中
}
