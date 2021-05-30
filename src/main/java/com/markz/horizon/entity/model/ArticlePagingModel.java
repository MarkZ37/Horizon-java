package com.markz.horizon.entity.model;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ToString
public class ArticlePagingModel {
    @NotBlank(message = "openid为空")
    private String userName;

    @NotNull(message = "article为空")
    private Integer pageNum;

}
