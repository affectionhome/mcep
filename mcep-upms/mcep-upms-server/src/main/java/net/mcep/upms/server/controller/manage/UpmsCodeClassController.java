package net.mcep.upms.server.controller.manage;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.mcep.common.base.BaseController;
import net.mcep.common.validator.LengthValidator;
import net.mcep.common.validator.NotNullValidator;
import net.mcep.upms.common.constant.UpmsResult;
import net.mcep.upms.common.constant.UpmsResultConstant;
import net.mcep.upms.dao.model.UpmsCode;
import net.mcep.upms.dao.model.UpmsCodeClass;
import net.mcep.upms.dao.model.UpmsCodeClassExample;
import net.mcep.upms.common.constant.UpmsResult;
import net.mcep.upms.common.constant.UpmsResultConstant;
import net.mcep.upms.dao.model.UpmsCodeExample;
import net.mcep.upms.rpc.api.UpmsCodeClassService;
import net.mcep.upms.rpc.api.UpmsCodeService;
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
 * 方法名称 : net.mcep.upms.server.controller.manage.UpmsCodeClassController
 * 作   者 : Administrator
 * 创建时间 : 2018/3/8 9:43
 * 方法描述 :
 * <p>
 * 修改作者 :
 * 修改时间 :
 * 修改描述 :
 */
@Controller
@Api(value = "公共编码分类管理", description = "公共编码分类管理")
@RequestMapping("/manage/codeClass")
public class UpmsCodeClassController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsCodeClassController.class);

    @Autowired
    private UpmsCodeClassService upmsCodeClassService;

    @Autowired
    private UpmsCodeService upmsCodeService;


    @ApiOperation(value = "公共编码分类首页")
    @RequiresPermissions("upms:codeclass:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/codeClass/index.jsp";
    }


    @ApiOperation(value = "公共编码分类列表")
    @RequiresPermissions("upms:codeclass:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        UpmsCodeClassExample upmsCodeClassExample = new UpmsCodeClassExample();
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            upmsCodeClassExample.setOrderByClause(sort + " " + order);
        }

        List<UpmsCodeClass> rows = upmsCodeClassService.selectByExampleForOffsetPage(upmsCodeClassExample, offset, limit);
        long total = upmsCodeClassService.countByExample(upmsCodeClassExample);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }


    @ApiOperation(value = "新增公共编码分类")
    @RequiresPermissions("upms:codeClass:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "/manage/codeClass/create.jsp";
    }

    @ApiOperation(value = "新增公共编码分类")
    @RequiresPermissions("upms:codeClass:create")
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(@RequestBody UpmsCodeClass upmsCodeClass) {
        ComplexResult result = FluentValidator.checkAll()
                .on(upmsCodeClass.getCatNo(), new LengthValidator(1, 50, "分类编码"))
                .on(upmsCodeClass.getCatName(), new LengthValidator(1, 50, "分类名称"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }

        int counts = upmsCodeClassService.createReturnKey(upmsCodeClass);

        int count = upmsCodeClassService.insertSelective(upmsCodeClass);
        return new UpmsResult(UpmsResultConstant.SUCCESS, 1);
    }


    @ApiOperation(value = "修改公共编码分类")
    @RequiresPermissions("upms:codeClass:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, HttpServletRequest request , ModelMap modelMap) {
        UpmsCodeClass upmsCodeClass = upmsCodeClassService.selectByPrimaryKey(id);
        modelMap.put("upmsCodeClass", upmsCodeClass);
        return "/manage/codeClass/update.jsp";
    }

    @ApiOperation(value = "查询公共编码")
    @RequiresPermissions("upms:codeClass:update")
    @RequestMapping(value = "/code", method = RequestMethod.GET)
    @ResponseBody
    public Object defect(@RequestParam(required = false, value = "catNo")  String catNo){

        ComplexResult result = FluentValidator.checkAll()
                .on(catNo, new NotNullValidator("分类编号"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        UpmsCodeExample upmsCodeExample = new UpmsCodeExample();
        upmsCodeExample.createCriteria().andCatNoEqualTo(catNo);

        List<UpmsCode> rows = upmsCodeService.selectByExample(upmsCodeExample);
        return rows;
    }

    @ApiOperation(value = "修改公共编码分类")
    @RequiresPermissions("upms:codeClass:update")
    @RequestMapping(value = "/update/{catNo}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable("catNo") String catNo, @RequestBody UpmsCodeClass upmsCodeClass) {
        UpmsCodeExample upmsCodeExample = new UpmsCodeExample();
        upmsCodeExample.createCriteria().andCatNoEqualTo(upmsCodeClass.getCatNo());

        int countd = upmsCodeService.deleteByExample(upmsCodeExample);

//        int counts = upmsCodeClassService.updateReturnKey(upmsCodeClass);
//
//        int count = upmsCodeClassService.updateByPrimaryKeySelective(upmsCodeClass);

        int counts = upmsCodeClassService.createReturnKey(upmsCodeClass);

//        int count = upmsCodeClassService.insertSelective(upmsCodeClass);

        return new UpmsResult(UpmsResultConstant.SUCCESS, 1);
    }

    @ApiOperation(value = "删除公共编码分类")
    @RequiresPermissions("upms:codeClass:delete")
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        int count = upmsCodeClassService.deleteByPrimaryKeys(ids);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }


}
