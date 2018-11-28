package net.mcep.upms.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.mcep.common.base.BaseController;
import net.mcep.common.util.RedisUtil;
import net.mcep.upms.dao.mapper.UpmsUserMapper;
import net.mcep.upms.dao.model.UpmsUser;
import net.mcep.upms.dao.model.UpmsUserExample;
import net.mcep.upms.rpc.api.UpmsRedisService;
import net.mcep.upms.rpc.api.UpmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 方法名称 : net.mcep.upms.server.controller.RedisManageController
 * 作   者 : Administrator
 * 创建时间 : 2018/8/17 10:11
 * 方法描述 : redis缓存管理
 * <p>
 * 修改作者 :
 * 修改时间 :
 * 修改描述 :
 */
@Controller
@RequestMapping("/manage/redis")
@Api(value = "redis缓存管理", description = "redis缓存管理")
public class RedisManageController extends BaseController {

    @Autowired
    UpmsRedisService upmsRedisService;

    @ApiOperation(value = "用户信息")
    @RequestMapping(value = "/init/user", method = RequestMethod.GET)
    @ResponseBody
    public String initUser(){
        upmsRedisService.initUserToRedis();
        return "OK";
    }

}
