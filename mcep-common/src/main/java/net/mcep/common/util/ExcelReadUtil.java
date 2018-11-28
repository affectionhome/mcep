package net.mcep.common.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import net.mcep.common.model.ExcelBean;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * 用户从Excel批量导入
 * 
 * 
 * 
 * @author PF
 */

public class ExcelReadUtil {

	private static final int version2003 = 2003;

	private static final int version2007 = 2007;

	private static int version = version2003;

	private static Workbook wb = null;

	private static Sheet sheet = null;

	private static Cell cell = null;

	private static Row row = null;

	public static List<ExcelBean> readExcel(InputStream stream, String suffix) throws Exception {
		if (suffix.endsWith(".xls")) {
			version = version2003;
		}else if (suffix.endsWith(".xlsx")){
			version = version2007;
		}else{
			throw new Exception("Excel文件类型只能为xls和xlsx");
		}

		List<ExcelBean> list = new ArrayList<ExcelBean>();
		//stream = new FileInputStream(excelFilePath);
		if (version == version2003) {
			wb = (Workbook) new HSSFWorkbook(stream);
		} else if (version == version2007) {
			//wb = (Workbook) new XSSFWorkbook(excelFilePath);
			wb = (Workbook) new XSSFWorkbook(stream);
		}

		sheet = wb.getSheetAt(0);

		// 行数(从0开始,相当于最后一行的索引),列数
		int count_row = sheet.getLastRowNum();//行数
		int count_cell = sheet.getRow(0).getPhysicalNumberOfCells();//列数
		String[][] rowCellArr = new String[count_row][count_cell];//二维数组(行和列)
		for (int i = 0; i < count_row; i++) {//遍历行
			ExcelBean bean = new ExcelBean();
			for (int j = 0; j < count_cell; j++) {//遍历列
				row = sheet.getRow(i + 1);
				cell = row.getCell(j);
				if(cell == null)continue;
				cell.setCellType(CellType.STRING);
				/*
				int type = cell.getCellType(); // 得到单元格数据类型
				String k = "";
				switch (type) { // 判断数据类型
				case Cell.CELL_TYPE_BLANK:
					k = "";
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					k = cell.getBooleanCellValue() + "";
					break;
				case Cell.CELL_TYPE_ERROR:
					k = cell.getErrorCellValue() + "";
					break;
				case Cell.CELL_TYPE_FORMULA:
					k = cell.getCellFormula();
					break;
				case Cell.CELL_TYPE_NUMERIC:
					if (DateUtil.isCellDateFormatted(cell)) {
						k = new DataFormatter().formatRawCellContents(
								cell.getNumericCellValue(), 0, "yyyy-mm-dd");// 格式化日期
					} else {
						k = cell.getNumericCellValue() + "";
					}
					break;
				case Cell.CELL_TYPE_STRING:
					k = cell.getStringCellValue();
					break;
				default:
					break;
				}*/
				
				try {
					Class<?> klass = bean.getClass();
					Method method = klass.getDeclaredMethod("setField" + (j+1), String.class);
					method.invoke(bean, cell.getStringCellValue());
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//rowCellArr[i][j] = k;
			}
			list.add(bean);
		}
		cell = null;
		row = null;
		sheet = null;
		wb = null;

		/*//遍历格式化后的行和列二维数组
		for (int k = 0; k < rowCellArr.length; k++) {
			String[] rowArr = rowCellArr[k];//行
			ExcelBean dto = new ExcelBean();
			//for (int s = 0; s < rowArr.length; s++) {//遍历行
				for(int i = 0; i < count_cell; i++) {
					try {
						Class<?> klass = dto.getClass();
						Method method = klass.getDeclaredMethod("setField" + (i+1), String.class);
						method.invoke(dto, rowArr[i]);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				list.add(dto);
				//break;
			//}
		}*/

		return list;
	}

	public static void main(String[] args) {
		ExcelReadUtil ui = new ExcelReadUtil();
		try {
			FileInputStream fis = new FileInputStream("F:/excel.xlsx");
			List<ExcelBean> list = ui.readExcel(fis, "excel.xlsx");
			ExcelBean bean = null;
			for (int i = 0; i < list.size(); i++) {
				bean = (ExcelBean) list.get(i);
	            System.out.println((i+1) + " " + bean.getField1() + "    " + bean.getField2() );
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
