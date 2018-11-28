package net.mcep.upms.controller;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.mcep.common.validator.NotNullValidator;
import net.mcep.upms.common.constant.UpmsResult;
import net.mcep.upms.common.constant.UpmsResultConstant;
import net.mcep.upms.utils.FastDFSClient;
import net.mcep.upms.dao.model.UpmsFileInfo;
import net.mcep.upms.dao.model.UpmsFileInfoExample;
import net.mcep.upms.rpc.api.UpmsFileInfoService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * 方法名称 : net.mcep.upms.file.UpmsFIleController
 * 作   者 : admin
 * 创建时间 : 2018/1/12 16:46
 * 方法描述 :
 * <p>
 * 修改作者 :
 * 修改时间 :
 * 修改描述 :
 */
@Controller
@Api(value = "文件管理", description = "文件管理")
@RequestMapping("/manage/file")
public class UpmsFileController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsFileController.class);

    @Autowired
    private UpmsFileInfoService upmsFileInfoService;

    @ApiOperation(value = "文件列表")
    //@RequiresPermissions("upms:user:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = true, value = "file_token") String file_token) {
        UpmsFileInfoExample upmsFileInfoExample = new UpmsFileInfoExample();

        if (StringUtils.isNotBlank(file_token)) {
            upmsFileInfoExample.or()
                    .andFileTokenEqualTo(file_token);
        }
        List<UpmsFileInfo> rows = upmsFileInfoService.selectByExample(upmsFileInfoExample);
        //Map<String, Object> result = new HashMap<>();
        //result.put("rows", rows);
        //result.put("total", total);
        return rows;
    }



    @ApiOperation(value = "上传文件")
    //@RequiresPermissions("upms:user:create")
    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Object upload(@RequestParam("file")MultipartFile[] files,HttpServletRequest request) {
        Map<String,Object> map = new HashMap<>();
        String fileToken = request.getParameter("fileToken");
        if(files.length>0 && (fileToken == null||fileToken.equals(""))){
            fileToken = UUID.randomUUID().toString().replaceAll("-", "");
        }
        InputStream inputStream = null;
        for(MultipartFile file : files){

            long size = file.getSize();
            String fileRealName = file.getOriginalFilename();
            System.out.println("文件名："+fileRealName);

            try {
                //获取文件输入流
                inputStream = file.getInputStream();
                //创建文件信息对象
                UpmsFileInfo upmsFileInfo = new UpmsFileInfo();
                Date time = new Date();
                //upmsFileInfo.setGuid(UUID.randomUUID().toString().replaceAll("-", ""));
                upmsFileInfo.setFileToken(fileToken);
                upmsFileInfo.setFileName(fileRealName);
                upmsFileInfo.setUploadTime(time);
                upmsFileInfo.setFileSize(size);
                //上传文件
                String recoredId = upmsFileInfoService.upload(upmsFileInfo,inputStream);
                map.put("recoredId",recoredId);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        try {
            if(inputStream != null){
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        map.put("fileToken",fileToken);
        return map;

    }

    @ApiOperation(value = "下载文件")
    //@RequiresPermissions("upms:user:create")
    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
    public void download(@PathVariable("id") Integer id, HttpServletResponse response) {

        ComplexResult result = FluentValidator.checkAll()
                .on(id, new NotNullValidator<Integer>("id"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        UpmsResult message = null;
        if (!result.isSuccess()) {
            message = new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }

        //从数据库读取文件的相关信息

        UpmsFileInfo file = upmsFileInfoService.selectByPrimaryKey(id);
        String fileId = file.getFileId();
        String fileName = file.getFileName();
        Long fileSize = file.getFileSize();

        //下载文件
        InputStream inputStream = upmsFileInfoService.downloadByFileId(fileId);

        response.setContentType("application/octet-stream");
        try {
            response.setHeader("Content-disposition", "attachment; filename="
                    + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //设置输出长度
        response.setHeader("Content-Length", String.valueOf(fileSize));


        //用于记录以完成的下载的数据量，单位是byte
        //long downloadedLength = 0l;
        try {
            //激活下载操作
            OutputStream os = response.getOutputStream();

            //循环写入输出流
            byte[] b = new byte[2048];
            int length = 0;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
                //downloadedLength += b.length;
            }

            // 这里主要关闭。
            os.flush();
            os.close();
            inputStream.close();
        } catch (Exception e){

        }

    }

    @ApiOperation(value = "删除文件")
    //@RequiresPermissions("upms:file:delete")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("id") String id) {
        boolean state = upmsFileInfoService.deleteRecoredAndFile(id);
        return new UpmsResult(UpmsResultConstant.SUCCESS, state);
    }
}
