package com.ecjtu.web.service;

import com.ecjtu.common.constant.Constants;
import com.ecjtu.common.utils.RedisCache;
import com.ecjtu.common.utils.RedisKeyUtil;
import com.ecjtu.domain.model.LoginUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Description: jwt token配置
 * @Author: ZengXueBin
 * @Date: 2023/3/30 17:00
 */
@Component
@Data
@ConfigurationProperties(prefix = "config.jwt")
public class TokenService {

    /**
     * 令牌密钥
     */
    private String secret;
    /**
     * 令牌有效期
     */
    private Long expire;
    /**
     * 令牌标识
     */
    private String header;
    private static final long MILLIS_MIN = 24 * 60 * 60 * 1000L;

    @Autowired
    private RedisCache redisCache;

    /**
     * 生成uuid
     * @return 不带-
     */
    private String getUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 创建令牌
     * @param loginUser 用户信息
     * @return 令牌
     */
    public String createToken(LoginUser loginUser) {
        String token = getUuid();
        loginUser.setToken(token);
        refreshToken(loginUser);

        HashMap<String, Object> claims = new HashMap<>();
        claims.put(Constants.LOGIN_USER_KEY, token);
        return createToken(claims);
    }

    /**
     * 从数据中生成令牌
     * @param claims 数据声明
     * @return 令牌
     */
    private String createToken(Map<String, Object> claims) {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        Key key = Keys.hmacShaKeyFor(keyBytes);
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(getUuid())
                .setClaims(claims)
                .signWith(key, SignatureAlgorithm.HS256);
        return jwtBuilder.compact();
    }

    /**
     * 刷新令牌有效期
     * @param loginUser 登陆用户
     */
    public void refreshToken(LoginUser loginUser) {
        // 设置当前登陆时间
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + expire);
        String userKey = RedisKeyUtil.getTokenKey(loginUser.getToken());
        redisCache.setCacheObject(userKey, loginUser, expire, TimeUnit.MILLISECONDS);
    }

    /**
     * 验证令牌有效期 相差不足20分钟 自动刷新缓存
     * @param loginUser 用户对象
     */
    public void verifyToken(LoginUser loginUser) {
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MILLIS_MIN) {
            refreshToken(loginUser);
        }
    }

    /**
     * 获取请求token
     * @param request 请求
     * @return token
     */
    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(header);
        if (StringUtils.isNotBlank(token) && token.startsWith(Constants.TOKEN_PREFIX)) {
            token = token.replace(Constants.TOKEN_PREFIX, "");
        }
        return token;
    }

    /**
     * 从令牌中获取数据声明
     * @param token 令牌
     * @return 数据声明
     */
    private Claims parseToken(String token) {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        Key key = Keys.hmacShaKeyFor(keyBytes);
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 获取用户身份信息
     * @param request 请求
     * @return 用户信息
     */
    public LoginUser getLoginUser(HttpServletRequest request) {
        // 获取请求携带的令牌
        String token = getToken(request);
        if (StringUtils.isNotBlank(token)) {
            Claims claims = parseToken(token);
            String uuid = (String) claims.get(Constants.LOGIN_USER_KEY);
            String userKey = RedisKeyUtil.getTokenKey(uuid);
            return redisCache.getCacheObject(userKey);
        }
        return null;
    }

    /**
     * 通过token获取用户名
     * @param token 令牌
     * @return 用户名
     */
    public String getUsernameByToken(String token) {
        Claims claims = parseToken(token);
        return claims.getSubject();
    }

    /**
     * 设置用户身份
     * @param loginUser 用户信息
     */
    public void setLoginUser(LoginUser loginUser) {
        if (loginUser != null && StringUtils.isNotBlank(loginUser.getToken())) {
            refreshToken(loginUser);
        }
    }

    /**
     * 删除用户身份信息
     * @param token 令牌
     */
    public void delLoginUser(String token) {
        if (StringUtils.isNotBlank(token)) {
            String userKey = RedisKeyUtil.getTokenKey(token);
            redisCache.deleteObject(userKey);
        }
    }
}
