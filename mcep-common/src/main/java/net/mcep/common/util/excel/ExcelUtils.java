package net.mcep.common.util.excel;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by xuc on 2018/7/6.
 * Excel工具
 */
public class ExcelUtils{

    static class PObject {
        private String v1;
        private String v2;

        public PObject(String v1, String v2) {
            this.v1 = v1;
            this.v2 = v2;
        }

        public String getV1() {
            return v1;
        }

        public void setV1(String v1) {
            this.v1 = v1;
        }

        public String getV2() {
            return v2;
        }

        public void setV2(String v2) {
            this.v2 = v2;
        }
    }

    public static void main(String[] args) {
        List<PObject> list = new ArrayList<>();
        PObject pObject1 = new PObject("val11","val12");
        PObject pObject2 = new PObject("val21","val22");
        list.add(pObject1);
        list.add(pObject2);
        ExcelUtils excelUtils = new ExcelUtils();
        HSSFWorkbook workbook = excelUtils.exportExcelBySheet(new HSSFWorkbook(), "title11", new String[]{"1","2"}, new String[]{"v1","v2"}, list,"yyyy-MM-DD HH:mm:ss", new HashMap<String, String>());


    }
    /**
     *
     * 功能描述: 将放置在集合中的数据以EXCEL的形式输出到指定IO设备上.
     *
     * @param title 表格标题名
     * @param headers 表格属性列名数组
     * @param fields 表格列属性对应的数据对象属性数组
     * @param datalist 需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象
     * @param params 查询条件
     */
    public HSSFWorkbook exportExcelBySheet(HSSFWorkbook workbook, String title, String[] headers, String[] fields, Collection datalist, String pattern, Map<String, String> params) {
        // 声明一个工作薄
        /* HSSFWorkbook workbook = new HSSFWorkbook();*/

        // 获得表头单元格样式
        HSSFCellStyle headerStyle = this.setColumnTopStyle(workbook);
        // 获得表体单元格样式
        HSSFCellStyle bodyStyle = this.setTableStyle(workbook, 0);
        HSSFCellStyle bodyStyle2 = this.setTableStyle(workbook, 1);

        HSSFSheet sheet = createSheet(title, workbook);
        setSheet(headers, params, sheet, headerStyle);

        HSSFRow row;
        // 遍历集合数据，产生数据行
        Iterator it = datalist.iterator();
        int index = 1;
        while (it.hasNext()) {
            index++;
            if (index > 63000) {
                sheet = null;
                HSSFSheet sheet1 = workbook.createSheet();
                sheet = sheet1;
                setSheet(headers, params, sheet, headerStyle);
                index = 1;
            }
            row = sheet.createRow(index);
            Object t = it.next();
            // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
            Field[] fields2 = t.getClass().getDeclaredFields();// 获得Class对象所表示的类或接口所声明的所有字段
            if (isMatch(fields, fields2)) {
                for (int i = 0; i < fields.length; i++) {
                    HSSFCell cell = row.createCell(i);
                    if (index % 2 == 0) {
                        cell.setCellStyle(bodyStyle);
                    } else {
                        cell.setCellStyle(bodyStyle2);
                    }
                    String fieldName = fields[i];
                    String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    Class<? extends Object> tClass = t.getClass();
                    try {
                        Method getMethod = tClass.getMethod(getMethodName);
                        Object value = getMethod.invoke(t);
                        String textValue = dataTypeCast(pattern, value);

                    } catch (SecurityException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return workbook;
    }

    private void setSheet(String[] headers, Map<String, String> params, HSSFSheet sheet, HSSFCellStyle headerStyle) {
        // 产生表格标题行
        HSSFRow row = sheet.createRow(0); // 在索引0的位置创建行(最顶端的行)
        int cellParams = 0;
        Iterator<Map.Entry<String, String>> itParams = params.entrySet().iterator();
        while(itParams.hasNext()){
            Map.Entry<String, String> entry = itParams.next();
            HSSFCell cell = row.createCell(cellParams);
            // 设置表头单元格的数据类型
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            // 设置表头单元格样式
            cell.setCellStyle(headerStyle);
            HSSFRichTextString text = new HSSFRichTextString(entry.getKey() + entry.getValue());
            // 设置表头单元格的值
            cell.setCellValue(text);
            cellParams++;
        }
        row = sheet.createRow(1);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            // 设置表头单元格的数据类型
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            // 设置表头单元格样式
            cell.setCellStyle(headerStyle);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            // 设置表头单元格的值
            cell.setCellValue(text);
        }
    }

    private HSSFSheet createSheet(String title, HSSFWorkbook workbook) {
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        // 设置工作表列宽
        sheet.setDefaultColumnWidth(40);
        // 设置工作表行高
        // sheet.setDefaultRowHeight((short) 10);
        return sheet;
    }

    /**
     * 功能描述: 判断值的类型后进行强制类型转换.
     *
     * @param pattern 日期数据的格式
     * @param value 要转换的数据
     * @return 转换后的数据
     */
    private String dataTypeCast(String pattern, Object value) {
        String textValue = null;
        if (value == null) {
            return "";
        }
        if (value instanceof Date) {
            Date date = (Date) value;
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            textValue = sdf.format(date);
        } else {
            // 其它数据类型都当作字符串简单处理
            textValue = value.toString();
        }

        return textValue;
    }

    /**
     * 功能描述: 判断所给属性组与所给数据集合中对象的属性是否匹配.
     *
     * @param fields 所给属性数组
     * @param fields2 所给数据集合中对象的属性组
     * @return boolean
     */
    private boolean isMatch(String[] fields, Field[] fields2) {
        boolean flag = true;
        int n;
        for (int i = 0; i < fields.length; i++) {
            n = 0;
            for (int j = 0; j < fields2.length; j++) {
                if (fields[i].equals(fields2[j].getName())) {
                    n++;
                }
            }
            if (n == 0) {
                flag = false;
            }
        }
        return flag;
    }

    /**
     * 功能描述: 设置表体单元格样式.
     *
     * @param workbook 工作薄对象
     * @param i 隔行颜色不同
     * @return 单元格样式
     */
    private HSSFCellStyle setTableStyle(HSSFWorkbook workbook, int i) {
        // 设置字体
        HSSFFont font = workbook.createFont();
        // 设置字体大小
        // font.setFontHeightInPoints((short)10);
        // 字体加粗
        // font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 设置字体名字
        font.setFontName("Courier New");
        // 设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        // 设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        // 设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        // 设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        // 设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        // 设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        // 设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        // 设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        if (i == 0) {
            // 设置背景色
            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        }
        // 在样式用应用设置的字体;
        style.setFont(font);
        // 设置自动换行;
        style.setWrapText(false);
        // 设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        return style;
    }

    /**
     * 功能描述: 设置表头单元格样式.
     *
     * @param workbook 工作薄对象
     * @return 单元格样式
     */
    private HSSFCellStyle setColumnTopStyle(HSSFWorkbook workbook) {
        // 设置字体
        HSSFFont font = workbook.createFont();
        // 设置字体大小
        font.setFontHeightInPoints((short) 11);
        // 字体加粗
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 设置字体名字
        font.setFontName("Courier New");
        // 设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        // 设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        // 设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        // 设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        // 设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        // 设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        // 设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        // 设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        // 设置背景色
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);

        // 在样式用应用设置的字体;
        style.setFont(font);
        // 设置自动换行;
        style.setWrapText(false);
        // 设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        return style;
    }
}
