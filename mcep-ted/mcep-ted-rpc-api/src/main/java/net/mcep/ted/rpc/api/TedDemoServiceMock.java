package net.mcep.ted.rpc.api;

import net.mcep.common.base.BaseServiceMock;
import net.mcep.ted.dao.mapper.TedDemoMapper;
import net.mcep.ted.dao.model.TedDemo;
import net.mcep.ted.dao.model.TedDemoExample;

/**
* 降级实现TedDemoService接口
* Created by shuzheng on 2018/9/17.
*/
public class TedDemoServiceMock extends BaseServiceMock<TedDemoMapper, TedDemo, TedDemoExample> implements TedDemoService {

}
