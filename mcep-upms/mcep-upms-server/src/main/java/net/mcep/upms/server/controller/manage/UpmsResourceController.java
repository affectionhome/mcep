package net.mcep.upms.server.controller.manage;

import com.alibaba.fastjson.JSONArray;
import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import net.mcep.common.base.BaseController;
import net.mcep.common.validator.LengthValidator;
import net.mcep.upms.common.constant.UpmsResult;
import net.mcep.upms.common.constant.UpmsResultConstant;
import net.mcep.upms.dao.model.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.mcep.upms.rpc.api.*;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 组织controller
 * Created by shuzheng on 2017/2/6.
 */
@Controller
@Api(value = "资源定义管理", description = "资源定义管理")
@RequestMapping("/manage/resource")
public class UpmsResourceController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsResourceController.class);

    @Autowired
    private UpmsResourceService upmsResourceService;

    @Autowired
    private UpmsResourceUserService upmsResourceUserService;

    @Autowired
    private UpmsResourceJobsService upmsResourceJobsService;

    @Autowired
    private UpmsResourceRoleService upmsResourceRoleService;

    @Autowired
    private UpmsCodeService upmsCodeService;

    @ApiOperation(value = "资源定义首页")
    @RequiresPermissions("upms:resource:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/resource/index.jsp";
    }

    @ApiOperation(value = "资源定义列表")
    @RequiresPermissions("upms:resource:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        UpmsResourceExample upmsResourceExample = new UpmsResourceExample();
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            upmsResourceExample.setOrderByClause(sort + " " + order);
        }
//        if (StringUtils.isNotBlank(search)) {
//            upmsResourceExample.or()
//                    .andNameLike("%" + search + "%");
//        }
        List<UpmsResource> rows = upmsResourceService.selectByExampleForOffsetPage(upmsResourceExample, offset, limit);
        long total = upmsResourceService.countByExample(upmsResourceExample);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }

    @ApiOperation(value = "新增资源定义")
    @RequiresPermissions("upms:resource:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "/manage/resource/create.jsp";
    }

    @ApiOperation(value = "新增资源定义")
    @RequiresPermissions("upms:resource:create")
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(UpmsResource upmsResource) {
        ComplexResult result = FluentValidator.checkAll()
                .on(upmsResource.getResourceName(), new LengthValidator(1, 20, "名称"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        long time = System.currentTimeMillis();
        upmsResource.setCtime(time);
        upmsResource.setOrders(time);
        int count = upmsResourceService.insertSelective(upmsResource);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "删除资源定义")
    @RequiresPermissions("upms:resource:delete")
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        int count = upmsResourceService.deleteByPrimaryKeys(ids);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "修改资源定义")
    @RequiresPermissions("upms:resource:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, ModelMap modelMap) {
        UpmsResource resource = upmsResourceService.selectByPrimaryKey(id);
        modelMap.put("resource", resource);
        return "/manage/resource/update.jsp";
    }

    @ApiOperation(value = "修改资源定义")
    @RequiresPermissions("upms:resource:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable("id") int id, UpmsResource upmsResource) {
        ComplexResult result = FluentValidator.checkAll()
                .on(upmsResource.getResourceName(), new LengthValidator(1, 20, "名称"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        upmsResource.setResourceId(id);
        int count = upmsResourceService.updateByPrimaryKeySelective(upmsResource);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }


    @ApiOperation(value = "资源用户")
    @RequiresPermissions("upms:resource:user")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String user(@PathVariable("id") int id, ModelMap modelMap) {
        UpmsResource resource = upmsResourceService.selectByPrimaryKey(id);
        modelMap.put("resource", resource);
        return "/manage/resource/user.jsp";
    }

    @ApiOperation(value = "资源用户")
    @RequiresPermissions("upms:resource:user")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object user(@PathVariable("id") int id, HttpServletRequest request) {
        JSONArray datas = JSONArray.parseArray(request.getParameter("datas"));
        int result = upmsResourceUserService.resourceUser(datas, id);
        return new UpmsResult(UpmsResultConstant.SUCCESS, result);
    }



    @ApiOperation(value = "资源岗位")
    @RequiresPermissions("upms:resource:jobs")
    @RequestMapping(value = "/jobs/{id}", method = RequestMethod.GET)
    public String jobs(@PathVariable("id") int id, ModelMap modelMap) {
        UpmsResource resource = upmsResourceService.selectByPrimaryKey(id);
        modelMap.put("resource", resource);
        return "/manage/resource/jobs.jsp";
    }

    @ApiOperation(value = "资源岗位")
    @RequiresPermissions("upms:resource:jobs")
    @RequestMapping(value = "/jobs/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object jobs(@PathVariable("id") int id, HttpServletRequest request) {
        JSONArray datas = JSONArray.parseArray(request.getParameter("datas"));
        int result = upmsResourceJobsService.resourceJobs(datas, id);
        return new UpmsResult(UpmsResultConstant.SUCCESS, result);
    }



    @ApiOperation(value = "资源角色")
    @RequiresPermissions("upms:resource:role")
    @RequestMapping(value = "/role/{id}", method = RequestMethod.GET)
    public String role(@PathVariable("id") int id, ModelMap modelMap) {
        UpmsResource resource = upmsResourceService.selectByPrimaryKey(id);
        modelMap.put("resource", resource);
        return "/manage/resource/role.jsp";
    }

    @ApiOperation(value = "资源角色")
    @RequiresPermissions("upms:resource:role")
    @RequestMapping(value = "/role/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object role(@PathVariable("id") int id, HttpServletRequest request) {
        JSONArray datas = JSONArray.parseArray(request.getParameter("datas"));
        int result = upmsResourceRoleService.resourceRole(datas, id);
        return new UpmsResult(UpmsResultConstant.SUCCESS, result);
    }


    @ApiOperation(value = "角色资源列表")
    @RequiresPermissions("upms:resource:read")
    @RequestMapping(value = "/roles/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object role(@PathVariable("id") int id) {
        return upmsResourceService.getTreeByRoleId(id);
    }

    @ApiOperation(value = "岗位资源列表")
    @RequiresPermissions("upms:resource:read")
    @RequestMapping(value = "/jobss/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object jobs(@PathVariable("id") int id) {
        return upmsResourceService.getTreeByJobsId(id);
    }

    @ApiOperation(value = "用户资源列表")
    @RequiresPermissions("upms:resource:read")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object user(@PathVariable("id") int id) {
        return upmsResourceService.getTreeByUserId(id);
    }



    @ApiOperation(value = "资源字典列表")
    @RequiresPermissions("upms:resource:read")
    @RequestMapping(value = "/code", method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "1000", value = "limit") int limit,
                          String businessObject
    ) {
        UpmsCodeExample upmsCodeExample = new UpmsCodeExample();
        upmsCodeExample.createCriteria().andCatNoEqualTo(businessObject);
        List<UpmsCode> rows = upmsCodeService.selectByExampleForOffsetPage(upmsCodeExample, offset, limit);
        long total = upmsCodeService.countByExample(upmsCodeExample);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }



}
