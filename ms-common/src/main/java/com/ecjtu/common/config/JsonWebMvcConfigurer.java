package com.ecjtu.common.config;

import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.support.config.FastJsonConfig;
import com.alibaba.fastjson2.support.spring6.http.converter.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

/**
 * @Description: fastjson 配置类
 * @Author: ZengXueBin
 * @Date: 2023/4/2 11:20
 */
@Configuration
public class JsonWebMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 转换器
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        converter.setDefaultCharset(StandardCharsets.UTF_8);
        converter.setFastJsonConfig(fastJsonConfig());
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        converters.add(0, converter);
    }

    @Bean
    public FastJsonConfig fastJsonConfig() {
        // 自定义配置
        FastJsonConfig config = new FastJsonConfig();
        config.setDateFormat("yyyy-MM-dd HH:mm:ss");

        // 配置序列化的行为
        //JSONWriter.Feature.PrettyFormat:格式化输出
        config.setWriterFeatures(JSONWriter.Feature.PrettyFormat);

        // 配置反序列化的行为
        config.setReaderFeatures(JSONReader.Feature.FieldBased, JSONReader.Feature.SupportArrayToBean);
        return config;
    }
}
