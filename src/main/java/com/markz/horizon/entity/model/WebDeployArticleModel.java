package com.markz.horizon.entity.model;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ToString
public class WebDeployArticleModel {


    @NotNull(message = "article为空")
    private String article;

    @NotNull(message = "userName为空")
    private String userName;

    @NotNull(message = "title为空")
    private String title;
}
