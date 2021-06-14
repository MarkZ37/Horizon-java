package com.markz.horizon.entity.model;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
@ToString
public class WebUserNameArticleIdModel {
    @NotBlank(message = "username为空")
    private String userName;

    @NotBlank(message = "article id为空")
    private Integer articleId;
}
