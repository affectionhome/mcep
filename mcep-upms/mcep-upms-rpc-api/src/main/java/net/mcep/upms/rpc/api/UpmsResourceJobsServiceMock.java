package net.mcep.upms.rpc.api;

import com.alibaba.fastjson.JSONArray;
import net.mcep.common.base.BaseServiceMock;
import net.mcep.upms.dao.mapper.UpmsResourceJobsMapper;
import net.mcep.upms.dao.model.UpmsResourceJobs;
import net.mcep.upms.dao.model.UpmsResourceJobsExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* 降级实现UpmsResourceJobsService接口
* Created by shuzheng on 2018/1/26.
*/
public class UpmsResourceJobsServiceMock extends BaseServiceMock<UpmsResourceJobsMapper, UpmsResourceJobs, UpmsResourceJobsExample> implements UpmsResourceJobsService {


    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsResourceJobsServiceMock.class);

    @Override
    public int resourceJobs(JSONArray datas, int id) {
        LOGGER.info("UpmsResourceJobsServiceMock => resourceJobs");
        return 0;
    }

    @Override
    public int resourceJobs1(JSONArray datas, int id) {
        LOGGER.info("UpmsResourceJobsServiceMock => resourceJobs1");
        return 0;
    }



}
