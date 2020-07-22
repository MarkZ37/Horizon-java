package com.markz.horizon.entity.VO;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@ToString
public class LoginParams {
    @NotBlank(message = "用户名不能为空")
    @Size(max = 100,message = "账号超出长度{max}")
    private String account;

    @NotBlank(message = "密码不能为空")
    @Size(max = 100,message = "密码超出长度{max}")
    private String password;
}
