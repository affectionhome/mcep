package net.mcep.upms.server.controller.manage;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import net.mcep.common.base.BaseController;
import net.mcep.common.validator.LengthValidator;
import net.mcep.upms.common.constant.UpmsResult;
import net.mcep.upms.common.constant.UpmsResultConstant;
import net.mcep.upms.dao.model.UpmsDataPermission;
import net.mcep.upms.dao.model.UpmsDataPermissionExample;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.mcep.upms.rpc.api.UpmsDataPermissionService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色controller
 * Created by shuzheng on 2017/2/6.
 */
@Controller
@Api(value = "数据权限管理", description = "数据权限管理")
@RequestMapping("/manage/dataPermission")
public class UpmsDataPermissionController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsDataPermissionController.class);

    @Autowired
    private UpmsDataPermissionService upmsDataPermissionService;


    @ApiOperation(value = "数据权限首页")
    @RequiresPermissions("upms:dataPermission:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/dataPermission/index.jsp";
    }


    @ApiOperation(value = "数据权限列表")
    @RequiresPermissions("upms:dataPermission:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        UpmsDataPermissionExample upmsDataPermissionExample = new UpmsDataPermissionExample();
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            upmsDataPermissionExample.setOrderByClause(sort + " " + order);
        }

        List<UpmsDataPermission> rows = upmsDataPermissionService.selectByExampleForOffsetPage(upmsDataPermissionExample, offset, limit);
        long total = upmsDataPermissionService.countByExample(upmsDataPermissionExample);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }

    @ApiOperation(value = "新增数据权限")
    @RequiresPermissions("upms:dataPermission:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "/manage/dataPermission/create.jsp";
    }

    @ApiOperation(value = "新增数据权限")
    @RequiresPermissions("upms:dataPermission:create")
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(UpmsDataPermission upmsDataPermission) {
        ComplexResult result = FluentValidator.checkAll()
                .on(upmsDataPermission.getAuthorizationType(), new LengthValidator(1, 20, "授权类型"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }

        int count = upmsDataPermissionService.insertSelective(upmsDataPermission);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "删除数据权限")
    @RequiresPermissions("upms:dataPermission:delete")
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        int count = upmsDataPermissionService.deleteByPrimaryKeys(ids);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "修改数据权限")
    @RequiresPermissions("upms:dataPermission:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, ModelMap modelMap) {
        UpmsDataPermission dataPermission = upmsDataPermissionService.selectByPrimaryKey(id);
        modelMap.put("dataPermission", dataPermission);
        return "/manage/dataPermission/update.jsp";
    }

    @ApiOperation(value = "修改数据权限")
    @RequiresPermissions("upms:dataPermission:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable("id") int id, UpmsDataPermission upmsDataPermission) {
        ComplexResult result = FluentValidator.checkAll()
                .on(upmsDataPermission.getAuthorizationType(), new LengthValidator(1, 20, "授权类型"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
     //   upmsDataPermission.setId(id);
        int count = upmsDataPermissionService.updateByPrimaryKeySelective(upmsDataPermission);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

}
