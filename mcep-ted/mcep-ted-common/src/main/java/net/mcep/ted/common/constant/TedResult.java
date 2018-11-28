package net.mcep.ted.common.constant;

import net.mcep.common.base.BaseResult;

/**
 * 系统常量枚举类
 */
public class TedResult extends BaseResult {

    public TedResult(TedResultConstant cmsResultConstant, Object data) {
        super(cmsResultConstant.getCode(), cmsResultConstant.getMessage(), data);
    }

}
