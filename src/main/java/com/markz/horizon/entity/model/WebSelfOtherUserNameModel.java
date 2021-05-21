package com.markz.horizon.entity.model;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
@ToString
public class WebSelfOtherUserNameModel {
    @NotBlank(message = "selfUsername为空")
    private String selfUserName;

    @NotBlank(message = "otherUsername为空")
    private String otherUserName;
}
