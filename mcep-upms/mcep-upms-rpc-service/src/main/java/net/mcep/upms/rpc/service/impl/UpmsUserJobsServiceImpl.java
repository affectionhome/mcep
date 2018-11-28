package net.mcep.upms.rpc.service.impl;

import net.mcep.common.annotation.BaseService;
import net.mcep.common.base.BaseServiceImpl;
import net.mcep.upms.dao.mapper.UpmsUserJobsMapper;
import net.mcep.upms.dao.model.UpmsUserJobs;
import net.mcep.upms.dao.model.UpmsUserJobsExample;
import net.mcep.upms.dao.model.UpmsUserRole;
import net.mcep.upms.dao.model.UpmsUserRoleExample;
import net.mcep.upms.rpc.api.UpmsUserJobsService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * UpmsUserJobsService实现
 * Created by shuzheng on 2018/1/26.
 */
@Service
@Transactional
@BaseService
public class UpmsUserJobsServiceImpl extends BaseServiceImpl<UpmsUserJobsMapper, UpmsUserJobs, UpmsUserJobsExample> implements UpmsUserJobsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsUserJobsServiceImpl.class);

    @Autowired
    UpmsUserJobsMapper upmsUserJobsMapper;

    @Override
    public int jobs(String[] jobsIds, int id) {
        int result = 0;

        // 删除旧记录
        UpmsUserJobsExample upmsUserJobsExample = new UpmsUserJobsExample();
        upmsUserJobsExample.createCriteria()
                .andUserIdEqualTo(id);
        List<UpmsUserJobs> upmsUserJobs1 = upmsUserJobsMapper.selectByExample(upmsUserJobsExample);
        upmsUserJobsMapper.deleteByExample(upmsUserJobsExample);
        // 增加新记录
        if (null != jobsIds) {
            for (String jobsId : jobsIds) {
                if (StringUtils.isBlank(jobsId)) {
                    continue;
                }
                UpmsUserJobs upmsUserJobs = new UpmsUserJobs();
                upmsUserJobs.setUserId(id);
                upmsUserJobs.setJobsId(NumberUtils.toInt(jobsId));
                result = upmsUserJobsMapper.insertSelective(upmsUserJobs);
            }
        }
        return result;
    }


    @Override
    public UpmsUserJobs createUserJobs(UpmsUserJobs upmsUserJobs) {
        UpmsUserJobsExample upmsUserJobsExample = new UpmsUserJobsExample();

        upmsUserJobsMapper.insert(upmsUserJobs);
        return upmsUserJobs;
    }

}