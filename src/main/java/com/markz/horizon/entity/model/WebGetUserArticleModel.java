package com.markz.horizon.entity.model;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString
public class WebGetUserArticleModel {

    @NotNull(message = "userName为空")
    private String userName;
}
