package com.ecjtu.web.service;

import com.ecjtu.common.constant.IpConstants;
import com.ecjtu.common.exception.CustomException;
import com.ecjtu.common.utils.AddressUtil;
import com.ecjtu.common.utils.RedisCache;
import com.ecjtu.common.utils.RedisKeyUtil;
import com.ecjtu.domain.entity.SysUser;
import com.ecjtu.domain.model.LoginUser;
import com.ecjtu.service.SysUserService;
import com.ecjtu.web.security.context.AuthenticationContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description: 登陆校验
 * @Author: ZengXueBin
 * @Date: 2023/3/31 20:19
 */
@Component
public class SysLoginService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private SysUserService sysUserService;

    /**
     * 登陆验证
     * @param username 用户名
     * @param password 密码
     * @param code 验证码
     * @param uuid 标识
     * @return token
     */
    public String login(String username, String password, String code, String uuid) {
        // 验证码校验
        validateCaptcha(code, uuid);
        // 用户认证
        Authentication authentication = null;

        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            AuthenticationContextHolder.setContext(authenticationToken);

            // 该方法调用userDetailServiceImpl.loadUserByUsername
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException e) {
            throw new CustomException("用户名或密码错误");
        } finally {
            AuthenticationContextHolder.clearContext();
        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        recordLoginInfo(loginUser.getUserId());
        return tokenService.createToken(loginUser);
    }

    /**
     * 校验验证码
     * @param code 验证码
     * @param uuid 唯一标识
     */
    public void validateCaptcha(String code, String uuid) {
        String verifyKey = RedisKeyUtil.getCaptchaKey(uuid);
        String captcha = redisCache.getCacheObject(verifyKey);
        if (!code.equalsIgnoreCase(captcha)) {
            throw new CustomException("验证码错误");
        }
    }

    public void recordLoginInfo(Long userId) {
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userId);
        sysUser.setLoginIp(AddressUtil.getIpInfo(IpConstants.IP));
        sysUser.setLoginLocation(AddressUtil.getIpInfo(IpConstants.ADDR));
        sysUser.setOnline("0");
        sysUser.setLoginTime(new Date());
        sysUserService.updateById(sysUser);
    }
}
