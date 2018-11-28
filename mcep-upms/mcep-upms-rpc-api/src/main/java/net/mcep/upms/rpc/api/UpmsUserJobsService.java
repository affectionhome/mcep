package net.mcep.upms.rpc.api;

import net.mcep.common.base.BaseService;
import net.mcep.upms.dao.model.UpmsUserJobs;
import net.mcep.upms.dao.model.UpmsUserJobsExample;

/**
* UpmsUserJobsService接口
* Created by shuzheng on 2018/1/26.
*/
public interface UpmsUserJobsService extends BaseService<UpmsUserJobs, UpmsUserJobsExample> {

    /**
     * 用户岗位
     * @param jobsIds 岗位ids
     * @param id 用户id
     * @return
     */
    int jobs(String[] jobsIds, int id);

    UpmsUserJobs createUserJobs(UpmsUserJobs upmsUserJobs); //新增主岗位

}