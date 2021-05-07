package com.markz.horizon.entity.model;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
@ToString
public class WebGetUserInfoModel {
    @NotBlank(message = "userName为空")
    private String userName;
}
