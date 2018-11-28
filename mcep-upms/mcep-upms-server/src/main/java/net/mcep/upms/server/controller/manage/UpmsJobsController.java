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
import net.mcep.upms.rpc.api.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * 角色controller
 * Created by shuzheng on 2017/2/6.
 */
@Controller
@Api(value = "岗位管理", description = "岗位管理")
@RequestMapping("/manage/jobs")
public class UpmsJobsController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsJobsController.class);

    @Autowired
    private UpmsJobsService upmsJobsService;

    @Autowired
    private UpmsJobsPermissionService upmsJobsPermissionService;

    @Autowired
    private UpmsOrganizationService upmsOrganizationService;

    @Autowired
    private UpmsJobsOrganizationService upmsJobsOrganizationService;

    @Autowired
    private UpmsResourceJobsService upmsResourceJobsService;



    @ApiOperation(value = "岗位首页")
    @RequiresPermissions("upms:jobs:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/jobs/index.jsp";
    }



    @ApiOperation(value = "资源岗位列表")
    @RequiresPermissions("upms:jobs:read")
    @RequestMapping(value = "/resource/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object role(@PathVariable("id") int id) {
        return upmsJobsService.getTreeByResourceId(id);
    }



    @ApiOperation(value = "岗位权限")
    @RequiresPermissions("upms:jobs:permission")
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.GET)
    public String permission(@PathVariable("id") int id, ModelMap modelMap) {
        UpmsJobs jobs = upmsJobsService.selectByPrimaryKey(id);
        modelMap.put("jobs", jobs);
        return "/manage/jobs/permission.jsp";
    }

    @ApiOperation(value = "岗位权限")
    @RequiresPermissions("upms:jobs:permission")
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object permission(@PathVariable("id") int id, HttpServletRequest request) {
        JSONArray datas = JSONArray.parseArray(request.getParameter("datas"));
        int result = upmsJobsPermissionService.jobsPermission(datas, id);                      //******
        return new UpmsResult(UpmsResultConstant.SUCCESS, result);
    }

    @ApiOperation(value = "岗位列表")
    @RequiresPermissions("upms:jobs:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            @RequestParam(required = false, defaultValue = "0", value = "organization") int organization,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        UpmsJobsExample upmsJobsExample = new UpmsJobsExample();
        UpmsJobsExample.Criteria criteria = upmsJobsExample.createCriteria();

        if (0 != organization) {
            criteria.andOrganizationIdEqualTo(organization);
        }

        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            upmsJobsExample.setOrderByClause(sort + " " + order);
        }
        if (StringUtils.isNotBlank(search)) {
            upmsJobsExample.or()
                    .andTitleLike("%" + search + "%");
        }
        List<UpmsJobs> rows = upmsJobsService.selectByExampleForOffsetPage(upmsJobsExample, offset, limit);
        long total = upmsJobsService.countByExample(upmsJobsExample);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }

    @ApiOperation(value = "新增岗位")
    @RequiresPermissions("upms:jobs:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "/manage/jobs/create.jsp";
    }

    @ApiOperation(value = "新增岗位")
    @RequiresPermissions("upms:jobs:create")
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(UpmsJobs upmsJobs) {
        ComplexResult result = FluentValidator.checkAll()
                .on(upmsJobs.getName(), new LengthValidator(1, 20, "名称"))
                .on(upmsJobs.getTitle(), new LengthValidator(1, 20, "标题"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        long time = System.currentTimeMillis();
        upmsJobs.setCtime(time);
        upmsJobs.setOrders(time);
        int count = upmsJobsService.insertSelective(upmsJobs);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "删除岗位")
    @RequiresPermissions("upms:jobs:delete")
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        int count = upmsJobsService.deleteByPrimaryKeys(ids);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "修改岗位")
    @RequiresPermissions("upms:jobs:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, ModelMap modelMap) {
        UpmsJobs jobs = upmsJobsService.selectByPrimaryKey(id);
        modelMap.put("jobs", jobs);
        return "/manage/jobs/update.jsp";
    }

    @ApiOperation(value = "修改岗位")
    @RequiresPermissions("upms:jobs:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable("id") int id, UpmsJobs upmsJobs) {
        ComplexResult result = FluentValidator.checkAll()
                .on(upmsJobs.getName(), new LengthValidator(1, 20, "名称"))
                .on(upmsJobs.getTitle(), new LengthValidator(1, 20, "标题"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        upmsJobs.setJobsId(id);
        int count = upmsJobsService.updateByPrimaryKeySelective(upmsJobs);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }



    @ApiOperation(value = "岗位组织")
    @RequiresPermissions("upms:jobs:organization")
    @RequestMapping(value = "/organization/{id}", method = RequestMethod.GET)
    public String organization(@PathVariable("id") int id, ModelMap modelMap) {
        // 所有组织
        List<UpmsOrganization> upmsOrganizations = upmsOrganizationService.selectByExample(new UpmsOrganizationExample());
        // 岗位拥有组织
        UpmsJobsOrganizationExample upmsJobsOrganizationExample = new UpmsJobsOrganizationExample();
        upmsJobsOrganizationExample.createCriteria()
                .andJobsIdEqualTo(id);
        List<UpmsJobsOrganization> upmsJobsOrganizations = upmsJobsOrganizationService.selectByExample(upmsJobsOrganizationExample);
        modelMap.put("upmsOrganizations", upmsOrganizations);
        modelMap.put("upmsJobsOrganizations", upmsJobsOrganizations);
        return "/manage/jobs/organization.jsp";
    }

    @ApiOperation(value = "岗位组织")
    @RequiresPermissions("upms:jobs:organization")
    @RequestMapping(value = "/organizations/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object organization(@PathVariable("id") int id, HttpServletRequest request) {
        String[] organizationIds = request.getParameterValues("organizationId");
        upmsJobsOrganizationService.organizations(organizationIds, id);
        return new UpmsResult(UpmsResultConstant.SUCCESS, "");
    }



    @ApiOperation(value = "岗位资源")
    @RequiresPermissions("upms:jobs:resource")
    @RequestMapping(value = "/resources/{id}", method = RequestMethod.GET)
    public String resource(@PathVariable("id") int id, ModelMap modelMap) {
        UpmsJobs jobs = upmsJobsService.selectByPrimaryKey(id);
        modelMap.put("jobs", jobs);
        return "/manage/jobs/resource.jsp";
    }

    @ApiOperation(value = "岗位资源")
    @RequiresPermissions("upms:jobs:resource")
    @RequestMapping(value = "/resources/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object resource(@PathVariable("id") int id, HttpServletRequest request) {
        JSONArray datas = JSONArray.parseArray(request.getParameter("datas"));
        int result = upmsResourceJobsService.resourceJobs1(datas, id);
        return new UpmsResult(UpmsResultConstant.SUCCESS, result);
    }



}
