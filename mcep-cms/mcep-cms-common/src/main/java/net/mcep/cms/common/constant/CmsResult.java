package net.mcep.cms.common.constant;

import net.mcep.common.base.BaseResult;

/**
 * cms系统常量枚举类
 * Created by shuzheng on 2017/2/19.
 */
public class CmsResult extends BaseResult {

    public CmsResult(CmsResultConstant cmsResultConstant, Object data) {
        super(cmsResultConstant.getCode(), cmsResultConstant.getMessage(), data);
    }

}
