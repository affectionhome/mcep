package net.mcep.upms.rpc.api;

import com.alibaba.fastjson.JSONArray;
import net.mcep.common.base.BaseServiceMock;
import net.mcep.upms.dao.mapper.UpmsResourceUserMapper;
import net.mcep.upms.dao.model.UpmsResourceUser;
import net.mcep.upms.dao.model.UpmsResourceUserExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* 降级实现UpmsResourceUserService接口
* Created by shuzheng on 2018/1/26.
*/
public class UpmsResourceUserServiceMock extends BaseServiceMock<UpmsResourceUserMapper, UpmsResourceUser, UpmsResourceUserExample> implements UpmsResourceUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsResourceUserServiceMock.class);

    @Override
    public int resourceUser(JSONArray datas, int id) {
        LOGGER.info("UpmsResourceUserServiceMock => resourceUser");
        return 0;
    }

    @Override
    public int resourceUser1(JSONArray datas, int id) {
        LOGGER.info("UpmsResourceUserServiceMock => resourceUser1");
        return 0;
    }



}
