package com.niqdev.app.dto;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegisterFeatureKeyRequest {
    private String kid;                // Feature Key ID（主鍵或隨機 UUID）
    private Set<String> modules;       // 啟用的模組清單
    private LocalDateTime expirationTime; // Feature Key 過期時間
}

