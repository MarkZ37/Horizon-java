package com.markz.horizon.entity.model;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 网页端登陆
 */
@Data
@ToString
public class WebLoginModel {
    @NotBlank(message = "account为空")
    private String account;

    @NotNull(message = "password为空")
    private String password;
}
