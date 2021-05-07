package com.markz.horizon.entity.model;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
@ToString
public class UploadAvatarUrlModel {
    @NotBlank(message = "avatarUrl为空")
    private String avatarUrl;

    @NotBlank(message = "userName为空")
    private String userName;
}
