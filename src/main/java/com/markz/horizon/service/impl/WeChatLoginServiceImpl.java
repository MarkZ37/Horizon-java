package com.markz.horizon.service.impl;

import com.markz.horizon.entity.Userinfo;
import com.markz.horizon.entity.base.BaseResponse;
import com.markz.horizon.entity.model.WeChatLoginModel;
import com.markz.horizon.mapper.UserinfoMapper;
import com.markz.horizon.service.WeChatLoginService;
import com.markz.horizon.utils.AesCbcUtil;
import okhttp3.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class WeChatLoginServiceImpl implements WeChatLoginService {

    private final static String LOGINOK = "LoginOK";
    private final static String LOGINFAILED = "Loginfailed";
    private final static Integer LOGINOKSTATUS = 0;
    private final static Integer LOGINFAILEDSTATUS = 1;

    @Autowired
    UserinfoMapper userinfoMapper;
    /**
     * 用户登录，获取用户信息
     * @param weChatLoginModel
     * @return
     */
    @Override
    public @NotNull BaseResponse loginService(@NotNull WeChatLoginModel weChatLoginModel) {
        //接收的数据参数
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";
        BaseResponse baseResponse = new BaseResponse();
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .addHeader("content-type", "application/json")
                .url(String.format(url,"wx70aa677b63bc9086","02e8df450582d3ee6ffcf4fcc6ee7624",weChatLoginModel.getCode()))
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            JSONObject jsonObject = new JSONObject(response.body().string());
            //获取到openid和session_key
            System.out.println(jsonObject);
            //AES解密用户数据
            try {
                String result = AesCbcUtil.decrypt(weChatLoginModel.getEncryptedData(),jsonObject.getString("session_key"),weChatLoginModel.getIv(),"utf-8");
                if (result != null && result.length() > 0) {
                    JSONObject userInfoJSON = new JSONObject(result);
                    System.out.println(userInfoJSON);
                    if (userinfoMapper.selectByPrimaryKey(userInfoJSON.getString("openId")) == null){
                        System.out.println("enter insert");
                        Userinfo userinfo = new Userinfo();
                        userinfo.setOpenid(userInfoJSON.getString("openId"));
                        userinfo.setNickname(userInfoJSON.getString("nickName"));
                        userinfo.setAvatarurl(userInfoJSON.getString("avatarUrl"));
                        userinfo.setCountry(userInfoJSON.getString("country"));
                        userinfo.setProvince(userInfoJSON.getString("province"));
                        userinfo.setCity(userInfoJSON.getString("city"));
                        userinfo.setGender(userInfoJSON.getInt("gender"));
                        userinfoMapper.insert(userinfo);
                    }
                    Map<String,Object> userInfoMap = new HashMap<String, Object>();
                    userInfoMap.put("nickName", userInfoJSON.get("nickName"));
                    userInfoMap.put("gender", userInfoJSON.get("gender"));
                    userInfoMap.put("city", userInfoJSON.get("city"));
                    userInfoMap.put("province", userInfoJSON.get("province"));
                    userInfoMap.put("country", userInfoJSON.get("country"));
                    userInfoMap.put("avatarUrl", userInfoJSON.get("avatarUrl"));
                    userInfoMap.put("openId", userInfoJSON.get("openId"));
                    baseResponse.setData(userInfoMap);

                } else {
                    baseResponse.setData("用户信息解析失败");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            baseResponse.setMessage(LOGINOK);
            baseResponse.setStatus(LOGINOKSTATUS);

        } catch (IOException e){
            e.printStackTrace();
            baseResponse.setMessage(LOGINFAILED);
            baseResponse.setStatus(LOGINFAILEDSTATUS);
        }
        return baseResponse;
    }
}
