package net.mcep.upms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.mcep.common.model.ExcelBean;
import net.mcep.common.util.ExcelReadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


/**
 * Excel操作action
 * 
 */
@Controller
@RequestMapping("/excel")
public class ExcelController {

	/**
	 * 导入excel
	 * @param files
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/import", method = RequestMethod.POST)
	public Object fileUpload(@RequestParam("file")MultipartFile[] files, HttpServletRequest request){
	    
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<ExcelBean> returnList = new ArrayList<ExcelBean>();
		String statusCode = "-1";//状态码
		/**判断文件是否为空,空直接返回上传错误**/
		if(files.length>0){
			
			//获得文件后缀名
			String suffix = files[0].getOriginalFilename().substring(files[0].getOriginalFilename().lastIndexOf("."));
			System.out.println("文件=" + files[0].getOriginalFilename());
			
			if(suffix.equals(".xls") || suffix.equals(".xlsx")) {//检测文件类型
				try {
					returnList = ExcelReadUtil.readExcel(files[0].getInputStream(), files[0].getOriginalFilename());
					statusCode = "1";
				}catch (Exception e) {
					statusCode = "0";
					e.printStackTrace();
				}
			}
		}
		return returnList;
		
		/*resMap.put("statusCode", statusCode);//状态码
		resMap.put("list", returnList);//结果集
		response.setContentType("text/html;charset=UTF-8");//指定响应内容类型，避免<pre...
		
		try {
			PrintWriter out = response.getWriter();
			Gson gson = new GsonBuilder().create();
			String gsonStr = gson.toJson(resMap);
			out.write(gsonStr);
			out.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}*/
    }
	
}
