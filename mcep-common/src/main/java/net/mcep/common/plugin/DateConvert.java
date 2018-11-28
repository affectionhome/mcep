package net.mcep.common.plugin;

import net.mcep.common.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.convert.converter.Converter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 全局日期处理类
 * Convert<T,S>
 *         泛型T:代表客户端提交的参数 String
 *         泛型S:通过convert转换的类型

 */
public class DateConvert implements Converter<String, Date> {

    @Override
    public Date convert(String stringDate) {

        if(StringUtils.isBlank(stringDate)) return null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(stringDate);
        } catch (Exception e) {
            try {
                return simpleDateFormat.parse(stringDate);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

}