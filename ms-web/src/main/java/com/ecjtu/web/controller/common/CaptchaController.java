package com.ecjtu.web.controller.common;

import com.ecjtu.common.constant.CacheConstants;
import com.ecjtu.common.constant.Constants;
import com.ecjtu.common.utils.ApiResult;
import com.ecjtu.common.utils.RedisCache;
import com.google.code.kaptcha.Producer;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 验证码操作处理
 * @Author: ZengXueBin
 * @Date: 2023/4/3 18:06
 */
@RestController
@RequestMapping("/code")
public class CaptchaController {

    @Autowired
    private Producer captchaProducer;

    @Autowired
    private RedisCache redisCache;

    /**
     * 生成验证码
     * @return 验证码
     */
    @GetMapping("/captchaImage")
    public ApiResult getCode() {
        ApiResult apiResult = ApiResult.success();

        // 保存验证码信息
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + uuid;

        String capStr = null;
        String code = null;
        BufferedImage image = null;

        capStr = code = captchaProducer.createText();
        image = captchaProducer.createImage(capStr);

        redisCache.setCacheObject(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);

        FastByteArrayOutputStream outputStream = new FastByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", outputStream);
        } catch (IOException e) {
            return ApiResult.error("获取验证码失败");
        }
        apiResult.put("uuid", uuid);
        apiResult.put("img", Base64.encodeBase64String(outputStream.toByteArray()));
        return apiResult;
    }
}
