package net.mcep.upms.rpc.service.impl;

import net.mcep.common.annotation.BaseService;
import net.mcep.common.base.BaseServiceImpl;
import net.mcep.common.db.DynamicDataSource;
import net.mcep.upms.dao.mapper.UpmsCodeClassMapper;
import net.mcep.upms.dao.mapper.UpmsCodeMapper;
import net.mcep.upms.dao.model.UpmsCode;
import net.mcep.upms.dao.model.UpmsCodeClass;
import net.mcep.upms.dao.model.UpmsCodeClassExample;
import net.mcep.upms.rpc.api.UpmsCodeClassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UpmsCodeClassService实现
* Created by shuzheng on 2018/1/26.
*/
@Service
@Transactional
@BaseService
public class UpmsCodeClassServiceImpl extends BaseServiceImpl<UpmsCodeClassMapper, UpmsCodeClass, UpmsCodeClassExample> implements UpmsCodeClassService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsCodeClassServiceImpl.class);

    @Autowired
    UpmsCodeClassMapper upmsCodeClassMapper;

    @Autowired
    UpmsCodeMapper upmsCodeMapper;

    public int createReturnKey(UpmsCodeClass upmsCodeClass) {
        try {


            //公共编码
            for(UpmsCode upmsCode:upmsCodeClass.getUpmsCodeList()){


                upmsCode.setCatNo(upmsCodeClass.getCatNo());

                upmsCodeMapper.insertSelective(upmsCode);


            }

            return upmsCodeClass.getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DynamicDataSource.clearDataSource();
        return 0;
    }


    public int updateReturnKey(UpmsCodeClass upmsCodeClass) {
        try {


            //公共编码
            for(UpmsCode upmsCode:upmsCodeClass.getUpmsCodeList()){

               if(upmsCode.getOld() == "0"){

                   upmsCode.setCatNo(upmsCodeClass.getCatNo());

                   upmsCodeMapper.insertSelective(upmsCode);
               }



            }

            return upmsCodeClass.getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DynamicDataSource.clearDataSource();
        return 0;
    }


}