package com.markz.horizon.entity.model;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ToString
public class WeChatLoginModel {

    @NotBlank(message = "encryptedData为空")
    private String encryptedData;

    @NotNull(message = "iv为空")
    private String iv;

    @NotBlank(message = "code为空")
    private String code;
}
