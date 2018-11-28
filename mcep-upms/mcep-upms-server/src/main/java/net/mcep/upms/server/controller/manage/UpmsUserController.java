package net.mcep.upms.server.controller.manage;

import com.alibaba.fastjson.JSONArray;
import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import net.mcep.common.base.BaseController;
import net.mcep.common.util.MD5Util;
import net.mcep.common.validator.LengthValidator;
import net.mcep.common.validator.NotNullValidator;
import net.mcep.upms.common.constant.UpmsResult;
import net.mcep.upms.common.constant.UpmsResultConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.mcep.upms.dao.model.*;
import net.mcep.upms.rpc.api.*;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 用户controller
 * Created by shuzheng on 2017/2/6.
 */
@Controller
@Api(value = "用户管理", description = "用户管理")
@RequestMapping("/manage/user")
public class UpmsUserController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsUserController.class);

    @Autowired
    private UpmsUserService upmsUserService;

    @Autowired
    private UpmsRoleService upmsRoleService;

    @Autowired
    private UpmsOrganizationService upmsOrganizationService;

    @Autowired
    private UpmsUserOrganizationService upmsUserOrganizationService;

    @Autowired
    private UpmsUserRoleService upmsUserRoleService;

    @Autowired
    private UpmsUserPermissionService upmsUserPermissionService;

    @Autowired
    private UpmsUserJobsService upmsUserJobsService;

    @Autowired
    private UpmsJobsService upmsJobsService;

    @Autowired
    private UpmsResourceUserService upmsResourceUserService;


    @ApiOperation(value = "用户首页")
    @RequiresPermissions("upms:user:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/user/index.jsp";
    }

    @ApiOperation(value = "用户组织")
    @RequiresPermissions("upms:user:organization")
    @RequestMapping(value = "/organization/{id}", method = RequestMethod.GET)
    public String organization(@PathVariable("id") int id, ModelMap modelMap) {
        // 所有组织
        List<UpmsOrganization> upmsOrganizations = upmsOrganizationService.selectByExample(new UpmsOrganizationExample());
        // 用户拥有组织
        UpmsUserOrganizationExample upmsUserOrganizationExample = new UpmsUserOrganizationExample();
        upmsUserOrganizationExample.createCriteria()
                .andUserIdEqualTo(id);
        List<UpmsUserOrganization> upmsUserOrganizations = upmsUserOrganizationService.selectByExample(upmsUserOrganizationExample);
        modelMap.put("upmsOrganizations", upmsOrganizations);
        modelMap.put("upmsUserOrganizations", upmsUserOrganizations);
        return "/manage/user/organization.jsp";
    }

    @ApiOperation(value = "用户组织")
    @RequiresPermissions("upms:user:organization")
    @RequestMapping(value = "/organization/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object organization(@PathVariable("id") int id, HttpServletRequest request) {
        String[] organizationIds = request.getParameterValues("organizationId");
        upmsUserOrganizationService.organization(organizationIds, id);
        return new UpmsResult(UpmsResultConstant.SUCCESS, "");
    }

    @ApiOperation(value = "用户角色")
    @RequiresPermissions("upms:user:role")
    @RequestMapping(value = "/role/{id}", method = RequestMethod.GET)
    public String role(@PathVariable("id") int id, ModelMap modelMap) {
        // 所有角色
        List<UpmsRole> upmsRoles = upmsRoleService.selectByExample(new UpmsRoleExample());
        // 用户拥有角色
        UpmsUserRoleExample upmsUserRoleExample = new UpmsUserRoleExample();
        upmsUserRoleExample.createCriteria()
                .andUserIdEqualTo(id);
        List<UpmsUserRole> upmsUserRoles = upmsUserRoleService.selectByExample(upmsUserRoleExample);
        modelMap.put("upmsRoles", upmsRoles);
        modelMap.put("upmsUserRoles", upmsUserRoles);
        return "/manage/user/role.jsp";
    }

    @ApiOperation(value = "用户角色")
    @RequiresPermissions("upms:user:role")
    @RequestMapping(value = "/role/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object role(@PathVariable("id") int id, HttpServletRequest request) {
        String[] roleIds = request.getParameterValues("roleId");
        upmsUserRoleService.role(roleIds, id);
        return new UpmsResult(UpmsResultConstant.SUCCESS, "");
    }
////////////////////////////////////////////////////////////////////////////////////
    @ApiOperation(value = "用户岗位")
    @RequiresPermissions("upms:user:jobs")
    @RequestMapping(value = "/jobs/{id}", method = RequestMethod.GET)
    public String jobs(@PathVariable("id") int id, ModelMap modelMap) {
        // 所有岗位
        List<UpmsJobs> upmsJobss = upmsJobsService.selectByExample(new UpmsJobsExample());
        // 用户拥有岗位
        UpmsUserJobsExample upmsUserJobsExample = new UpmsUserJobsExample();
        upmsUserJobsExample.createCriteria()
                .andUserIdEqualTo(id);
        List<UpmsUserJobs> upmsUserJobss = upmsUserJobsService.selectByExample(upmsUserJobsExample);
        modelMap.put("upmsJobss", upmsJobss);
        modelMap.put("upmsUserJobss", upmsUserJobss);
        return "/manage/user/jobs.jsp";
    }

    @ApiOperation(value = "用户岗位")
    @RequiresPermissions("upms:user:jobs")
    @RequestMapping(value = "/jobs/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object jobs(@PathVariable("id") int id, HttpServletRequest request) {
        String[] jobsIds = request.getParameterValues("jobsId");
        upmsUserJobsService.jobs(jobsIds, id);
        return new UpmsResult(UpmsResultConstant.SUCCESS, "");
    }
////////////////////////////////////////////////////////////////////////////////////////
    @ApiOperation(value = "用户权限")
    @RequiresPermissions("upms:user:permission")
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.GET)
    public String permission(@PathVariable("id") int id, ModelMap modelMap) {
        UpmsUser user = upmsUserService.selectByPrimaryKey(id);
        modelMap.put("user", user);
        return "/manage/user/permission.jsp";
    }

    @ApiOperation(value = "用户权限")
    @RequiresPermissions("upms:user:permission")
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object permission(@PathVariable("id") int id, HttpServletRequest request) {
        JSONArray datas = JSONArray.parseArray(request.getParameter("datas"));
        upmsUserPermissionService.permission(datas, id);
        return new UpmsResult(UpmsResultConstant.SUCCESS, datas.size());
    }

    @ApiOperation(value = "用户列表")
    @RequiresPermissions("upms:user:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
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

    @ApiOperation(value = "新增用户")
    @RequiresPermissions("upms:user:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(ModelMap modelMap) {
        UpmsOrganizationExample upmsOrganizationExample = new UpmsOrganizationExample();
        List<UpmsOrganization> upmsOrganizations = upmsOrganizationService.selectByExample(upmsOrganizationExample);
        modelMap.put("upmsOrganizations",upmsOrganizations);
        return "/manage/user/create.jsp";
    }

    @ApiOperation(value = "新增用户")
    @RequiresPermissions("upms:user:create")
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(UpmsUser upmsUser) {
        ComplexResult result = FluentValidator.checkAll()
                .on(upmsUser.getUsername(), new LengthValidator(1, 20, "帐号"))
                .on(upmsUser.getPassword(), new LengthValidator(5, 32, "密码"))
                .on(upmsUser.getRealname(), new NotNullValidator("姓名"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        long time = System.currentTimeMillis();
        String salt = UUID.randomUUID().toString().replaceAll("-", "");
        upmsUser.setSalt(salt);
        upmsUser.setPassword(MD5Util.md5(upmsUser.getPassword() + upmsUser.getSalt()));
        upmsUser.setCtime(time);
        upmsUser = upmsUserService.createUser(upmsUser);
        if (null == upmsUser) {
            return new UpmsResult(UpmsResultConstant.FAILED, "帐号名已存在！");
        }
        LOGGER.info("新增用户，主键：userId={}", upmsUser.getUserId());


        UpmsUserJobs upmsUserJobs = new UpmsUserJobs(); //增加主岗位记录
        upmsUserJobs.setUserId(upmsUser.getUserId());
        upmsUserJobs.setJobsId(upmsUser.getJobs());
        upmsUserJobs = upmsUserJobsService.createUserJobs(upmsUserJobs);

        UpmsUserOrganization upmsUserOrganization = new UpmsUserOrganization();  //增加主部门记录
        upmsUserOrganization.setUserId(upmsUser.getUserId());
        upmsUserOrganization.setOrganizationId(upmsUser.getOrganization());
        upmsUserOrganization = upmsUserOrganizationService.createUserOrganization(upmsUserOrganization);

//        UpmsUser upmsJOUser = new UpmsUser();
//        upmsJOUser.setUserId(upmsUser.getUserId());
//        upmsJOUser.setUserJobsId(upmsUserJobs.getUserJobsId());
//        upmsJOUser.setUserOrganizationId(upmsUserOrganization.getUserOrganizationId());
//        int countJO = upmsUserService.updateByPrimaryKeySelective(upmsJOUser);

        return new UpmsResult(UpmsResultConstant.SUCCESS, 1);
    }

    @ApiOperation(value = "删除用户")
    @RequiresPermissions("upms:user:delete")
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        int count = upmsUserService.deleteByPrimaryKeys(ids);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "修改用户")
    @RequiresPermissions("upms:user:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, ModelMap modelMap) {
        UpmsOrganizationExample upmsOrganizationExample = new UpmsOrganizationExample();
        List<UpmsOrganization> upmsOrganizations = upmsOrganizationService.selectByExample(upmsOrganizationExample);
        modelMap.put("upmsOrganizations",upmsOrganizations);
        UpmsUser user = upmsUserService.selectByPrimaryKey(id);
        modelMap.put("user", user);
        modelMap.put("upmsOrganizations",upmsOrganizations);
        return "/manage/user/update.jsp";
    }

    @ApiOperation(value = "修改用户")
    @RequiresPermissions("upms:user:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable("id") int id, UpmsUser upmsUser) {
        ComplexResult result = FluentValidator.checkAll()
                .on(upmsUser.getUsername(), new LengthValidator(1, 20, "帐号"))
                .on(upmsUser.getRealname(), new NotNullValidator("姓名"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        // 不允许直接改密码
        upmsUser.setPassword(null);
        upmsUser.setUserId(id);
        int count = upmsUserService.updateByPrimaryKeySelective(upmsUser);


        UpmsUserJobs upmsUserJobs = new UpmsUserJobs();
//        upmsUserJobs.setUserJobsId(upmsUser.getUserJobsId());
        upmsUserJobs.setUserId(upmsUser.getUserId());
        upmsUserJobs.setJobsId(upmsUser.getJobs());
//        int countj = upmsUserJobsService.updateByPrimaryKeySelective(upmsUserJobs);
        int countj = upmsUserJobsService.insertSelective(upmsUserJobs);

        UpmsUserOrganization upmsUserOrganization = new UpmsUserOrganization();
//        upmsUserOrganization.setUserOrganizationId(upmsUser.getUserOrganizationId());
        upmsUserOrganization.setUserId(upmsUser.getUserId());
        upmsUserOrganization.setOrganizationId(upmsUser.getOrganization());
//        int counto = upmsUserOrganizationService.updateByPrimaryKeySelective(upmsUserOrganization);
        int counto = upmsUserOrganizationService.insertSelective(upmsUserOrganization);

        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "修改密码")
    //@RequiresPermissions("upms:user:password")
    @RequestMapping(value = "/password", method = RequestMethod.GET)
    public String password(HttpServletRequest request,ModelMap modelMap) {
        UpmsUser upmsUser = (UpmsUser)request.getAttribute("upmsUser");
        int userid = upmsUser.getUserId();
        modelMap.put("userid",userid);
        return "/manage/user/password.jsp";
    }

    @ApiOperation(value = "修改密码")
    //@RequiresPermissions("upms:user:password")
    @RequestMapping(value = "/password", method = RequestMethod.POST)
    @ResponseBody
    public Object password(int userId, String oldPassword,String newPassword) {
        ComplexResult result = FluentValidator.checkAll()
                .on(userId, new NotNullValidator("用户ID"))
                .on(oldPassword, new NotNullValidator("旧密码"))
                .on(newPassword, new NotNullValidator("新密码"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        UpmsUser user = upmsUserService.selectByPrimaryKey(userId);
        String password = user.getPassword();
        String salt = user.getSalt();
        if(MD5Util.md5(oldPassword + salt).equals(password)){//输入的旧密码是正确的
            //String salt = UUID.randomUUID().toString().replaceAll("-", "");//这里可以重新生成一个新的盐值
            //user.setSalt(salt);
            user.setPassword(MD5Util.md5(newPassword + salt));
        }else{
            return new UpmsResult(UpmsResultConstant.INVALID_PASSWORD, "旧密码错误");
        }
        int count = upmsUserService.updateByPrimaryKeySelective(user);
        return new UpmsResult(UpmsResultConstant.SUCCESS, 1);
    }

    @ApiOperation(value = "初始化密码")
    //@RequiresPermissions("upms:user:password")
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(HttpServletRequest request,ModelMap modelMap) {
        UpmsUser upmsUser = (UpmsUser)request.getAttribute("upmsUser");
        int userid = upmsUser.getUserId();
        modelMap.put("userid",userid);
        return "/manage/user/initPassword.jsp";
    }

    @ApiOperation(value = "初始化密码")
    //@RequiresPermissions("upms:user:password")
    @RequestMapping(value = "/init", method = RequestMethod.POST)
    @ResponseBody
    public Object init(int userId, String password) {
        ComplexResult result = FluentValidator.checkAll()
                .on(userId, new NotNullValidator("用户ID"))
                .on(password, new NotNullValidator("新密码"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        UpmsUser user = upmsUserService.selectByPrimaryKey(userId);
        String salt = user.getSalt();
        //String salt = UUID.randomUUID().toString().replaceAll("-", "");//这里可以重新生成一个新的盐值
        //user.setSalt(salt);
        user.setPassword(MD5Util.md5(password + salt));
        int count = upmsUserService.updateByPrimaryKeySelective(user);
        return new UpmsResult(UpmsResultConstant.SUCCESS, "/manage/index");
    }


    @ApiOperation(value = "上传")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Object upload(HttpServletRequest request,HttpServletResponse response) throws IOException {

        String affixCode="";
        List<MultipartFile> files = new ArrayList<MultipartFile>();
        // 创建一个通用的多部分解析器
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        MultipartHttpServletRequest multipartRequest = commonsMultipartResolver.resolveMultipart((HttpServletRequest) request);

        Iterator<String> itr = multipartRequest.getFileNames();
        MultipartFile multipartFile = null;

        while (itr.hasNext()) {

            multipartFile = multipartRequest.getFile(itr.next());
            String fileRealName = multipartFile.getOriginalFilename();
            System.out.println("文件名："+fileRealName);

            InputStream inputStream = multipartFile.getInputStream();
            File file = new File("I:/rongjiawen.jpg");
            FileOutputStream fos = new FileOutputStream(file);
            byte b[] = new byte[10000000];
            int len = inputStream.read(b);


            fos.write(b,0,len);
            fos.flush();
            fos.close();
            inputStream.close();

        }

        return null;
    }


    @ApiOperation(value = "资源用户列表")
    @RequiresPermissions("upms:user:read")
    @RequestMapping(value = "/resource/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object role(@PathVariable("id") int id) {
        return upmsUserService.getTreeByResourceId(id);
    }



    @ApiOperation(value = "用户资源权限")
    @RequiresPermissions("upms:user:resource")
    @RequestMapping(value = "/resources/{id}", method = RequestMethod.GET)
    public String resource(@PathVariable("id") int id, ModelMap modelMap) {
        UpmsUser user = upmsUserService.selectByPrimaryKey(id);
        modelMap.put("user", user);
        return "/manage/user/resource.jsp";
    }

    @ApiOperation(value = "用户资源权限")
    @RequiresPermissions("upms:user:resource")
    @RequestMapping(value = "/resources/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object resource(@PathVariable("id") int id, HttpServletRequest request) {
        JSONArray datas = JSONArray.parseArray(request.getParameter("datas"));
        int result = upmsResourceUserService.resourceUser1(datas, id);
        return new UpmsResult(UpmsResultConstant.SUCCESS, result);
    }




}
