package com.markz.horizon.entity.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:返回的json
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {
    private Integer status;
    private String Message;
    private String refreshToken;
    private Object data;

}
