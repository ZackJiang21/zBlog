package com.zack.zblog.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * Created by ZackJiang on 2018/5/13.
 */
public class TokenUtil {

    private static final Logger logger = LoggerFactory.getLogger(TokenUtil.class);

    private static String key;

    static {
        KeyGenerator keyGenerator;
        try {
            keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            SecretKey secretKey = keyGenerator.generateKey();
            key = new String(secretKey.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            logger.error("init key error");
        }
    }
    /**
     * 创建JWT
     */
    public static String createJwt() throws IllegalArgumentException, UnsupportedEncodingException {
        Algorithm al = Algorithm.HMAC256(key);
        String token = JWT.create()
                .withIssuer("zBlog")
                .withSubject("Administrator")
                .withClaim("zblog", "Administrator")
                .withExpiresAt(new Date(System.currentTimeMillis() + 360000))
                .sign(al);

        return token;

    }

    /**
     * 验证jwt
     */
    public static boolean verifyJwt(String token) {
        try {
            if (token == null) {
                return false;
            }
            Algorithm algorithm = Algorithm.HMAC256(key);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception e) {
            logger.error("auth fail.");
            return false;
        }
    }

}
