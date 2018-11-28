package net.mcep.upms.rpc.api;

import com.alibaba.fastjson.JSONArray;
import net.mcep.common.base.BaseService;
import net.mcep.upms.dao.model.UpmsResourceJobs;
import net.mcep.upms.dao.model.UpmsResourceJobsExample;

/**
* UpmsResourceJobsService接口
* Created by shuzheng on 2018/1/26.
*/
public interface UpmsResourceJobsService extends BaseService<UpmsResourceJobs, UpmsResourceJobsExample> {



    /**
     * 资源岗位
     * @param datas 权限数据
     * @param id 资源id
     * @return
     */
    int resourceJobs(JSONArray datas, int id);


    /**
     * 岗位资源
     * @param datas 权限数据
     * @param id 岗位id
     * @return
     */
    int resourceJobs1(JSONArray datas, int id);



}