package com.ecjtu.web.controller.alert;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecjtu.common.utils.ApiResult;
import com.ecjtu.domain.PageInfo;
import com.ecjtu.domain.entity.AlertRule;
import com.ecjtu.domain.model.AlertRuleQuery;
import com.ecjtu.service.AlertRuleService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 预警规则
 * @Author: ZengXueBin
 * @Date: 2023/5/17 00:58
 */
@RestController
@RequestMapping("/alert/rule")
public class AlertRuleController {

    @Autowired
    private AlertRuleService alertRuleService;

    /**
     * 查询预警规则分页信息
     * @param query 查询条件
     * @return 分页预警规则
     */
    @PostMapping("/page")
    public ApiResult queryPageList(@RequestBody PageInfo<AlertRuleQuery> query) {
        LambdaQueryWrapper<AlertRule> queryWrapper = new LambdaQueryWrapper<>();
        AlertRuleQuery entity = query.getEntity();
        if (ObjectUtils.isNotEmpty(entity)) {
            if (StringUtils.isNotBlank(entity.getRuleName())) {
                queryWrapper.like(AlertRule::getRuleName, entity.getRuleName());
            }
            if (StringUtils.isNotBlank(entity.getAlertLevel())) {
                queryWrapper.eq(AlertRule::getAlertLevel, entity.getAlertLevel());
            }
            if (StringUtils.isNotBlank(entity.getMetric())) {
                queryWrapper.like(AlertRule::getMetric, entity.getMetric());
            }
        }

        Page<AlertRule> page = new Page<>(query.getPageNum(), query.getPageSize());
        return  ApiResult.success(alertRuleService.page(page, queryWrapper));
    }
}
