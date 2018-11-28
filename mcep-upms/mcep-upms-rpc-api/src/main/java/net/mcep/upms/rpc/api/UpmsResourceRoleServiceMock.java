package net.mcep.upms.rpc.api;

import com.alibaba.fastjson.JSONArray;
import net.mcep.common.base.BaseServiceMock;
import net.mcep.upms.dao.mapper.UpmsResourceRoleMapper;
import net.mcep.upms.dao.model.UpmsResourceRole;
import net.mcep.upms.dao.model.UpmsResourceRoleExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* 降级实现UpmsResourceRoleService接口
* Created by shuzheng on 2018/1/28.
*/
public class UpmsResourceRoleServiceMock extends BaseServiceMock<UpmsResourceRoleMapper, UpmsResourceRole, UpmsResourceRoleExample> implements UpmsResourceRoleService {


    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsResourceRoleServiceMock.class);

    @Override
    public int resourceRole(JSONArray datas, int id) {
        LOGGER.info("UpmsResourceRoleServiceMock => resourceRole");
        return 0;
    }

    @Override
    public int resourceRole1(JSONArray datas, int id) {
        LOGGER.info("UpmsResourceRoleServiceMock => resourceRole1");
        return 0;
    }

}
