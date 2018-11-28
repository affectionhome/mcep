package net.mcep.ted.rpc.api;

import net.mcep.common.base.BaseService;
import net.mcep.ted.dao.model.TedDemo;
import net.mcep.ted.dao.model.TedDemoExample;

/**
* TedDemoService接口
* Created by shuzheng on 2018/9/17.
*/
public interface TedDemoService extends BaseService<TedDemo, TedDemoExample> {

    /** 简单的单表增改查接口已经在 BaseService 中处理*/

    /** 这里可以进行增加自己需要的接口，在Mock类中也需要生成实现，可以直接返回null等 */

}