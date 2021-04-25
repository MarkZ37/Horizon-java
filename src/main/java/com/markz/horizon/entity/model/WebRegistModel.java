package com.markz.horizon.entity.model;


import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 网页端注册
 */
@Data
@ToString
public class WebRegistModel {
    @NotBlank(message = "account为空")
    private String account;

    @NotNull(message = "password为空")
    private String password;

    @NotBlank(message = "nickname为空")
    private String nickname;


}
