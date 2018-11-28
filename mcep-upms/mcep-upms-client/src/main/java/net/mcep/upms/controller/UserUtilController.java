package net.mcep.upms.controller;

import io.swagger.annotations.ApiOperation;
import net.mcep.common.base.BaseController;
import net.mcep.upms.dao.model.UpmsUser;
import net.mcep.upms.dao.model.UpmsUserExample;
import net.mcep.upms.rpc.api.UpmsUserService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 方法名称 : net.mcep.upms.controller.UserUtilController
 * 作   者 : admin
 * 创建时间 : 2018/3/14 19:45
 * 方法描述 : 用户
 * <p>
 * 修改作者 :
 * 修改时间 :
 * 修改描述 :
 */

@Controller
@RequestMapping("/common/user")
public class UserUtilController extends BaseController {

    @Autowired
    private UpmsUserService upmsUserService;


    @ApiOperation(value = "用户列表")
    @RequestMapping(value = "/select", method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        UpmsUserExample upmsUserExample = new UpmsUserExample();
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            upmsUserExample.setOrderByClause(sort + " " + order);
        }
        if (StringUtils.isNotBlank(search)) {
            upmsUserExample.or()
                    .andRealnameLike("%" + search + "%");
            upmsUserExample.or()
                    .andUsernameLike("%" + search + "%");
        }
        List<UpmsUser> rows = upmsUserService.selectByExampleForOffsetPage(upmsUserExample, offset, limit);
        long total = upmsUserService.countByExample(upmsUserExample);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }




}
