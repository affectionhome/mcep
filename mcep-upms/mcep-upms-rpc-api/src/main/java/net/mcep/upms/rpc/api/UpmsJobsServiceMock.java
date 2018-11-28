package net.mcep.upms.rpc.api;

import com.alibaba.fastjson.JSONArray;
import net.mcep.common.base.BaseServiceMock;
import net.mcep.upms.dao.mapper.UpmsJobsMapper;
import net.mcep.upms.dao.model.UpmsJobs;
import net.mcep.upms.dao.model.UpmsJobsExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* 降级实现UpmsJobsService接口
* Created by shuzheng on 2018/1/18.
*/
public class UpmsJobsServiceMock extends BaseServiceMock<UpmsJobsMapper, UpmsJobs, UpmsJobsExample> implements UpmsJobsService {


    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsJobsServiceMock.class);

    @Override
    public JSONArray getTreeByResourceId(Integer resourceId) {
        LOGGER.info("UpmsJobsServiceMock => getTreeByResourceId");
        return null;
    }


}
