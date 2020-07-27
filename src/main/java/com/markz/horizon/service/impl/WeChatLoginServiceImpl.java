package com.markz.horizon.service.impl;

import com.markz.horizon.entity.base.BaseResponse;
import com.markz.horizon.entity.model.WeChatLoginModel;
import com.markz.horizon.service.WeChatLoginService;
import okhttp3.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.io.IOException;

@Service
@Transactional
public class WeChatLoginServiceImpl implements WeChatLoginService {

    @Override
    public @NotNull BaseResponse loginService(@NotNull WeChatLoginModel weChatLoginModel) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";
        BaseResponse baseResponse = new BaseResponse();
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .addHeader("content-type", "application/json")
                .url(String.format(url,"wx70aa677b63bc9086","02e8df450582d3ee6ffcf4fcc6ee7624",weChatLoginModel.getCode()))
                .build();
//        try {
//            Response execute = okHttpClient.newCall(request).execute();
//            if (execute.isSuccessful()) {
//                //获取openid等信息
//                System.out.println("execute:"+execute);
//            }
//        } catch (IOException e){
//            e.printStackTrace();
//        }
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
//                Log.d(TAG, "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.body().string());
            }
        });
        return baseResponse;
    }
}
