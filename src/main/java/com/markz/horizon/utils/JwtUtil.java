package com.markz.horizon.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Jwt 生成token与验证token
 */
@Slf4j
public class JwtUtil {
    //    过期时间设置
    private static final long EXPTRE_TIME= 2 * 60 * 60 * 1000;
    // 濒死时间设置
    private  static final long NEARDEATH_TIME = 30 * 60 * 1000;
//    private static final long EXPTRE_TIME=10 * 1000;
    /**
     * token私钥
     */
    private static final String TOKEN_SECRET="8ae0d24822ef59d9e75745449b3501bc";
    /**
     * 生成签名
     */
    public static String Sign(String userName,String userID){
        try {
            //过期时间
            Date date=new Date(System.currentTimeMillis()+EXPTRE_TIME);
            //私钥加密算法
            Algorithm algorithm=Algorithm.HMAC256(TOKEN_SECRET);
            //设置头部信息
            Map<String,Object> header=new HashMap<>(2);
            header.put("typ","JWT");
            header.put("alg","HS256");
            //附带加密的信息
            return JWT.create()
                    .withHeader(header)
                    .withClaim("loginName",userName)
                    .withClaim("userID",userID)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * token解码,验证权限
     */
    public static boolean verify(String token){
        try{
            Algorithm algorithm=Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier=JWT.require(algorithm).build();
            DecodedJWT jwt=verifier.verify(token);
            return true;
        } catch (Exception e) {
//            e.printStackTrace();
            return false;
        }
    }

    /**
     * 验证token是否濒死
     * @param token
     * @return
     */
    public static boolean checkToken(String token) {

        DecodedJWT decodedJWT = JWT.decode(token);
        Map<String, Claim> tokenInfo = decodedJWT.getClaims();
        Claim exp = tokenInfo.get("exp");
        Date tokenDate = exp.asDate();
        long tokenTime = tokenDate.getTime();
        long sysTime = System.currentTimeMillis();
        long restTime = tokenTime - sysTime;
        if (0 < restTime && restTime < NEARDEATH_TIME) {
            //处于濒死
            return false;
        } else {
            //非濒死状态
            return true;
        }

    }

}
