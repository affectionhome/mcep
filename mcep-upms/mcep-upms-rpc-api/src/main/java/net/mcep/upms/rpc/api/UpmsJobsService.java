package net.mcep.upms.rpc.api;

import com.alibaba.fastjson.JSONArray;
import net.mcep.common.base.BaseService;
import net.mcep.upms.dao.model.UpmsJobs;
import net.mcep.upms.dao.model.UpmsJobsExample;

/**
* UpmsJobsService接口
* Created by shuzheng on 2018/1/18.
*/
public interface UpmsJobsService extends BaseService<UpmsJobs, UpmsJobsExample> {

    JSONArray getTreeByResourceId(Integer resourceId);

}