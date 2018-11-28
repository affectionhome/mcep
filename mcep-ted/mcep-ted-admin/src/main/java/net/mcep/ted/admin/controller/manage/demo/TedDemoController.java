package net.mcep.ted.admin.controller.manage.demo;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.mcep.common.base.BaseController;
import net.mcep.common.util.StringUtil;
import net.mcep.common.validator.LengthValidator;
import net.mcep.common.validator.NotNullValidator;
import net.mcep.ted.common.constant.TedResult;
import net.mcep.ted.common.constant.TedResultConstant;
import net.mcep.ted.dao.model.TedDemo;
import net.mcep.ted.dao.model.TedDemoExample;
import net.mcep.ted.rpc.api.TedDemoService;
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
 * 方法名称 : net.mcep.mms.admin.controller.manage.demo.TedDemoController
 * 作   者 : Administrator
 * 创建时间 : 2018/9/17 16:14
 * 方法描述 : 测试样例
 * <p>
 * 修改作者 :
 * 修改时间 :
 * 修改描述 :
 */
@Controller
@Api(value = "测试样例", description = "测试样例")
@RequestMapping("/manage/demo")
public class TedDemoController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TedDemoController.class);

    @Autowired
    private TedDemoService tedDemoService;


    @ApiOperation(value = "测试信息首页")
    @RequiresPermissions("ted:demo:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/demo/index.html";
    }


    @ApiOperation(value = "业主信息列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(  @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
                         @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
                         @RequestParam(required = false, defaultValue = "", value = "search") String search,
                         @RequestParam(required = false, value = "sort") String sort,
                         @RequestParam(required = false, value = "order") String order,
                         String proprietorName  ) {

        TedDemoExample example = new TedDemoExample();
        if(!StringUtils.isBlank(proprietorName)){
            example.or().andProprietorNameEqualTo(proprietorName);
        }

        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            example.setOrderByClause(StringUtil.humpToLine(sort) + " " + order);
        }
        List<TedDemo> rows = tedDemoService.selectByExampleForOffsetPage(example, offset, limit);
        long total = tedDemoService.countByExample(example);
        Map<String, Object> result = new HashMap<>(2);
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }


    @ApiOperation(value = "新增测试信息")
    @RequiresPermissions("ted:demo:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(ModelMap modelMap) {

        modelMap.put("maxNo","10101");//TODO 先写死的
        return "/manage/demo/create.html";
    }

    @ApiOperation(value = "新增测试信息")
    @RequiresPermissions("ted:demo:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Object create(TedDemo tedDemo) {
        ComplexResult result = FluentValidator.checkAll()
                .on(tedDemo.getProprietorName(), new LengthValidator(1, 200, "****名称"))
                .on(tedDemo.getContactId(), new NotNullValidator<Integer>( "联络人"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new TedResult(TedResultConstant.INVALID_LENGTH, result.getErrors());
        }
        int count = tedDemoService.insertSelective(tedDemo);
        return new TedResult(TedResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "修改")
    @RequiresPermissions("ted:demo:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, ModelMap modelMap) {
        TedDemo tedDemo = tedDemoService.selectByPrimaryKey(id);

        modelMap.put("tedDemo", tedDemo);
        return "/manage/demo/update.html";
    }

    @ApiOperation(value = "修改")
    @RequiresPermissions("ted:demo:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable("id") int id, TedDemo tedDemo) {
        ComplexResult result = FluentValidator.checkAll()
                .on(tedDemo.getProprietorName(), new NotNullValidator<String>("业主名称"))
                .doValidate()
                .result(ResultCollectors.toComplex());
//        if (!result.isSuccess()) {
//            return new TedResult(TedResultConstant.INVALID_LENGTH, result.getErrors());
//        }
        int count = tedDemoService.updateByPrimaryKeySelective(tedDemo);
        return new TedResult(TedResultConstant.SUCCESS, count);
    }


}
