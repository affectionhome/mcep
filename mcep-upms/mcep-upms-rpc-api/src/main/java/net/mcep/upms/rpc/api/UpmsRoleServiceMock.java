package net.mcep.upms.rpc.api;

import com.alibaba.fastjson.JSONArray;
import net.mcep.common.base.BaseServiceMock;
import net.mcep.upms.dao.mapper.UpmsRoleMapper;
import net.mcep.upms.dao.model.UpmsRole;
import net.mcep.upms.dao.model.UpmsRoleExample;
import net.mcep.upms.dao.model.UpmsUserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
* 降级实现UpmsRoleService接口
* Created by shuzheng on 2017/3/20.
*/
public class UpmsRoleServiceMock extends BaseServiceMock<UpmsRoleMapper, UpmsRole, UpmsRoleExample> implements UpmsRoleService {


    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsRoleServiceMock.class);

    @Override
    public JSONArray getTreeByResourceId(Integer resourceId) {
        LOGGER.info("UpmsRoleServiceMock => getTreeByResourceId");
        return null;
    }

    @Override
    public int updateAndCommit(int id, List<UpmsUserRole> userRoles) {
        return 0;
    }

}
