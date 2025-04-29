package com.niqdev.app.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 接收註冊 License Key 的請求
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FindLicenseKeyRequest {
	private String licenseKey;
    private String licenseName;        // License 的名字，例如公司名
    private LocalDateTime expirationTime; // License 有效期限
    private Integer maxDevices;        // 最多允許幾台裝置使用
}
