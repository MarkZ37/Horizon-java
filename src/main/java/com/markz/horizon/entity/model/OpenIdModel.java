package com.markz.horizon.entity.model;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
@ToString
public class OpenIdModel {
    @NotBlank(message = "openid为空")
    private String openid;
}
